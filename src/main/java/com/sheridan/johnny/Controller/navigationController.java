package com.sheridan.johnny.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class navigationController {

    @RequestMapping("/")
    public String home() {
    return "index";
    }

    @RequestMapping("/secure")
    public String secureIndex() {
    return "/secure/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/permission-denied")
    public String permissionDenied() {
    return "/error/permission-denied";
    }
}
