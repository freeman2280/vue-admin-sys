package com.cr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t")
    @PreAuthorize("hasAuthority('sys:test:add')")
    String t() {
        return "ok";
    }
    @GetMapping("/o")
    @PreAuthorize("hasAuthority('sys:role:list')")
    String o() {
        return "ok";
    }
}
