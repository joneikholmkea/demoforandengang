package com.example.demoforandengang.controller;

import com.example.demoforandengang.model.DBManager;
import com.example.demoforandengang.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<Person> persons = new ArrayList<>();
    private final String personsStr = "persons";  // vi har fundet på dette navn selv!
    private DBManager dbManager = new DBManager();
    @RequestMapping("/")
    public String getIndex(Model model, HttpSession session){
        if(session.getAttribute("isLoggedIn") == "yes") {
            persons = dbManager.readAllPersons();
        }else {
            persons = new ArrayList<>();
        }
            model.addAttribute("persons", persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping(value = "/", params = "login")
    public String login(Model model, Person person, HttpSession session){
        if(dbManager.login(person)) {
            persons = dbManager.readAllPersons();
            session.setAttribute("isLoggedIn", "yes");
        }else {
            session.setAttribute("isLoggedIn", "no");
            persons = new ArrayList<>();
        }
        model.addAttribute(personsStr, persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping(value = "/", params = "addperson") // mangler login check
    public String addPerson(Model model, Person person){
        dbManager.insertPerson(person);
        System.out.println("modtaget Person " + person.getUname());
        persons = dbManager.readAllPersons();
        model.addAttribute(personsStr, persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping(value = "/", params = "deleteperson") // mangler login check
    public String deletePerson(Model model, Person person){
        //1. kald på DBManager om at slette denne person
        dbManager.deletePerson(person);
        // 2. kald på DBManager om at returnere alle rækker, efter at have slettet
        persons = dbManager.readAllPersons();
        model.addAttribute(personsStr, persons);
        return "index";
    }

    @RequestMapping(value = "/", params = "updateperson")  // mangler login check
    public String updatePerson(Model model, Person person){
        dbManager.updatePerson(person);
        System.out.println("updateperson er blevet kaldt med " + person);
        persons = dbManager.readAllPersons();
        model.addAttribute(personsStr, persons);
        return "index";
    }



}
