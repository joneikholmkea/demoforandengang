package com.example.demoforandengang.controller;

import com.example.demoforandengang.model.DBManager;
import com.example.demoforandengang.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<Person> persons = new ArrayList<>();
    private final String personsStr = "persons";  // vi har fundet på dette navn selv!
    private DBManager dbManager = new DBManager();
    @RequestMapping("/")
    public String getIndex(Model model){
        // problem: persons arrayet er tomt !!
        //persons = dbManager.readAllPersons();
        model.addAttribute("persons", persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping(value = "/", params = "login")
    public String login(Model model, Person person){
        if(dbManager.login(person)) {
            persons = dbManager.readAllPersons();
        }else {
            persons = new ArrayList<>();
        }
        model.addAttribute(personsStr, persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping(value = "/", params = "addperson")
    public String addPerson(Model model, Person person){
        dbManager.insertPerson(person);
        System.out.println("modtaget Person " + person.getUname());
        persons = dbManager.readAllPersons();
        model.addAttribute(personsStr, persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping(value = "/", params = "deleteperson")
    public String deletePerson(Model model, Person person){
        //1. kald på DBManager om at slette denne person
        dbManager.deletePerson(person);
        // 2. kald på DBManager om at returnere alle rækker, efter at have slettet
        persons = dbManager.readAllPersons();
        model.addAttribute(personsStr, persons);
        return "index";
    }

    @RequestMapping("/updatePerson")
    public String updatePerson(Model model, Person person){
        replacePerson(person);
        model.addAttribute(personsStr, persons);
        return "index";
    }

    private void replacePerson(Person person){
        for (Person p : persons) {
            if(p.getId() == person.getId()){
                p.setUname(person.getUname());
            }
        }
    }

}
