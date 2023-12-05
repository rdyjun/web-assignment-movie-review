package com.dongyang.moviewreviewweb.moviereviewer.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class adminController {
    @RequestMapping("/admin")
    public String admin (Model model) {
        List<MemberDTO> memberDto =
        model.addAttribute("member", "aaa");
        return "/admin";
    }
}
