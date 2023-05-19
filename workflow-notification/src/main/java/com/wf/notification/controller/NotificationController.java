package com.wf.notification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
public class NotificationController {


    @GetMapping("/hello")
    public String sayHello() {

        return "notification";
    }

}
