package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    /** トップページを表示 */
    @GetMapping
    public String getHomePage(Model model) {
        // トップページに画面遷移
        return "home";
    }
}
