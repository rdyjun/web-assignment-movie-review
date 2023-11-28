package com.dongyang.moviewreviewweb.moviereviewer.jspmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageMapping {
    @GetMapping("/")
    public String index () {
        return "index";
    }
    @GetMapping("/{path}")
    public ModelAndView getPage (@PathVariable String path) {
        ModelAndView modelAndView = new ModelAndView(path);
        return modelAndView;
    }

}
