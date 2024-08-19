package br.edu.iff.ccc.bsi.webdev.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerMain {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
