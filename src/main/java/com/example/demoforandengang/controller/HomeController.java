package com.example.demoforandengang.controller;

import com.example.demoforandengang.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<Person> persons = new ArrayList<>();
    private static final String personsStr = "persons";
    @RequestMapping("/")
    public String getIndex(){
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping("/addPerson")
    public String addPerson(Model model, Person person){
        person.setId(persons.size() + 1);
        persons.add(person);
        System.out.println("modtaget Person " + person.getUname());
        model.addAttribute(personsStr, persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
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
