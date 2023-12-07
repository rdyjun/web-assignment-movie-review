package com.dongyang.moviewreviewweb.moviereviewer.jspmapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class PageMappingController {
    @GetMapping("/login")
    public String login (HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String previousPageUrl = request.getHeader("Referer");
        URI uri = new URI(previousPageUrl);
        String path = uri.getPath();
        session.setAttribute("prevURL", path);
        return "login";
    }
    @GetMapping("/register")
    public String register (HttpSession session, HttpServletRequest request) throws URISyntaxException {
        String previousPageUrl = request.getHeader("Referer");
        URI uri = new URI(previousPageUrl);
        String path = uri.getPath();
        session.setAttribute("prevURL", path);
        return "register";
    }
}
