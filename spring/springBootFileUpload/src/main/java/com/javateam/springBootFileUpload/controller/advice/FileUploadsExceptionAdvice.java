package com.javateam.springBootFileUpload.controller.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

// @RestControllerAdvice
@ControllerAdvice
// @ControllerAdvice(basePackages = {"com.javateam.springBootFileUpload.controller"})
@Slf4j
public class FileUploadsExceptionAdvice {
	
	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxSize;	

//	REST 방식(@RestControllerAdvice) 예외처리
//	
//	@ExceptionHandler(MaxUploadSizeExceededException.class)
//	public ResponseEntity<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
//	
//	    log.error("파일 업로드 전역 예외처리 : " + exception.getMessage());
//		
//		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
//				             .body("업로드 파일 크기가 " + maxSize + "를 초과하였습니다.");
//		
//	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	private String handleException(MaxUploadSizeExceededException exception,
						RedirectAttributes redirectAttributes, Model model) {
		
		log.error("업로드 파일 크기가 " + maxSize + "를 초과하였습니다.");
		log.error("파일 업로드 전역 예외처리 : " + exception.getMessage());
		
		model.addAttribute("error_msg", "업로드 파일 크기가 " + maxSize + "를 초과하였습니다.");
		
		log.info("에러 처리 페이지로 이동");
		
		return "/error/upload_error";
	}

}