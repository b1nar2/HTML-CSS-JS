package com.javateam.SpringJstlELDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javateam.SpringJstlELDemo.domain.Employees;
import com.javateam.SpringJstlELDemo.service.EmployeesService;


@Controller
public class EmployeesController {

	 @Autowired
	 private EmployeesService employeesService;
	 
	 @GetMapping("/")
	 public String home() {
		
		return "redirect:/employeesList";
	 }

	 @GetMapping("/employeesList")
	 public ModelAndView getEmployeesList(){

		 ModelAndView result = new ModelAndView();
		 List<Employees> employeesList = employeesService.getEmployeesList();

		 result.addObject("employeesList", employeesList);
		 result.setViewName("/employeesList");

		 return result; 
	 }

	 /*
	 
	 @RequestMapping("/employeesList") 
	 public String getEmployeesList(HttpServletRequest request) {

		 List<Employees> employeesList = employeesService.getEmployeesList();
		 request.setAttribute("employeesList",  employeesList);
		 
		 return "/employeesList";
	 }
	 */
	 
	 @GetMapping("/member")
	 public String getMember(@RequestParam("id") int id,
			 				Model model) {
		 
		 model.addAttribute("member", 
				 			employeesService.getMember(id));
		 
		 return "/member";
	 }
}