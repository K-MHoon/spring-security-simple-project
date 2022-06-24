package com.example.springsecuritysimpleproject.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @GetMapping("/messages")
    public String messages() {
        return "user/messages";
    }

    @GetMapping("/api/messages")
    public @ResponseBody String apiMessage() {
        return "messages ok";
    }
}
