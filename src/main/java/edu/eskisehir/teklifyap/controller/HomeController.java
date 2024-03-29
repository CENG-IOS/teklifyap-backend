package edu.eskisehir.teklifyap.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin
@AllArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }
}
