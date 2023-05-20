package com.wf.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@PreAuthorize("hasAuthority('admin')")
public class SysUserController {

    @GetMapping("/hello")
    public String sayHello() {
        return "user";
    }
}
