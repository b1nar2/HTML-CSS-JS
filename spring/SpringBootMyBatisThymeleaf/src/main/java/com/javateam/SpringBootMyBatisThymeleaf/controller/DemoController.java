package com.javateam.SpringBootMyBatisThymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javateam.SpringBootMyBatisThymeleaf.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController {
	
	@Autowired
    private EmployeeService service;
	
	@GetMapping("/")
    public String home() {
        return "redirect:/getAll";
    }
   
    @GetMapping("/getAll")
    public String getAll(Model model) {
       
        log.info("getAll");
        model.addAttribute("list", service.getAll());
        return "getAll";
    }
   
    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable("id") int id, Model model) {
       
        log.info("getOne");
        model.addAttribute("employees", service.getOne(id));
        return "getOne";
    }

}