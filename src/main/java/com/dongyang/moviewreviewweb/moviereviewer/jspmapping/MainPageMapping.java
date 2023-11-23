package com.dongyang.moviewreviewweb.moviereviewer.jspmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageMapping {
    @GetMapping("/")
    public String index () {
        return "index";
    }
}
