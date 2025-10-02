package com.example.exspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home () {
        return "index";
    }

    @GetMapping("/profile")
    public String profile (Model model) {
        model.addAttribute("msg", "프로필 페이지 이동 (로그인 성공)");
        return "profile";
    }
}
