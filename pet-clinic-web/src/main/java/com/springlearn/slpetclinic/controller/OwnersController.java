package com.springlearn.slpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnersController {

    @GetMapping({"/owners", "/owners/index", "/owners/index.html"})
    public String listOwners(){
        return "owners/index";
    }
}
