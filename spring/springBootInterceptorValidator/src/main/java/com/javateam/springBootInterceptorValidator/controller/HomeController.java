package com.javateam.springBootInterceptorValidator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.javateam.springBootInterceptorValidator.domain.Book;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

//	@ModelAttribute
//	public void addAttributes(Model model) {
//		// model.addAttribute("addTitle", "신규 도서 등록");
//	}
//
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category",
//				"unitsInStock", "totalPages", "releaseDate", "condition");
//	}

	@GetMapping("/")
	public String home(@ModelAttribute("newBook") Book book) {
	// public String home(Model model) {
		
		log.info("home");
		
		// model.addAttribute("newBook", book);
		
		return "addBook";
	}
	
	// 폼 유효성 점검(form data validation)
	@PostMapping("/add")
	public String submitAddNewBook(@Valid @ModelAttribute("newBook") Book book, BindingResult result) {
				
		log.info("newBook :" + book); // 로그 추가
		
		// 폼 점검 에러가 있다면 ..... 
		if (result.hasErrors() == true) {
			
			log.error("폼 점검 에러가 존재합니다.");
			
			return "addBook";
		}		
		
		return "redirect:/";
	}
	
}