package com.encrypt.encrypt.controller;

import com.encrypt.encrypt.config.DBConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MainController {

    final DBConfiguration dbConfiguration;

    public MainController(DBConfiguration dbConfiguration) {
        this.dbConfiguration = dbConfiguration;
    }

    @GetMapping("")
    @ResponseBody
    public String test(){
        return dbConfiguration.toString();
    }
}
