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
    @RequestMapping("/")
    public String getIndex(){
        return "index"; // henviser til index.html som vi skal lave...
    }

    @RequestMapping("/addPerson")
    public String addPerson(Model model, Person person){
        persons.add(person);
        System.out.println("modtaget Person " + person.getUname());
        model.addAttribute("persons", persons); // tag persons med over til html siden
        return "index"; // henviser til index.html som vi skal lave...
    }

}
