package br.edu.iff.ccc.bsi.petshopvirtual.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewMainController {
    @GetMapping("/home")
    public String home() {
        return "home"; 
    }
}
