package com.encrypt.encrypt.controller;

import com.encrypt.encrypt.config.DBConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MainController {

    final DBConfig dbConfig;

    public MainController(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @GetMapping("")
    @ResponseBody
    public String test(){
        return dbConfig.toString();
    }
}
