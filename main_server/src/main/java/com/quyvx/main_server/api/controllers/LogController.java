package com.quyvx.main_server.api.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("log")
@Slf4j
public class LogController {

    @RequestMapping("request")
    public void requestLog() {

    }
}
