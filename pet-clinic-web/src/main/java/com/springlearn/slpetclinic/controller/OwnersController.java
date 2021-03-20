package com.springlearn.slpetclinic.controller;

import com.springlearn.slpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnersController {

    private final OwnerService ownerService;

    public OwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"/owners", "/owners/index", "/owners/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }
}
