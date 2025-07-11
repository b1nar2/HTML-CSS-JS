package com.javateam.springBootFileUpload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.springBootFileUpload.domain.FileUploadForm;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Slf4j
public class FileUploadController {

	@Value("${imageUpload.path}") 
	// application.properties 파일의 imageUpload.path=E:/KSZ/upload/image/
	private String imageUploadPath; 
	
//	@GetMapping(value = {"/", "/show"})
//	public String form() {
//
//		log.info("form");
//		return "file_upload_form3";
//	}

	@PostMapping("/save")
	public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm, 
						Model model, BindingResult result) {
		
		log.info("file save");

		List<MultipartFile> files = uploadForm.getFiles();
		List<String> fileNames = new ArrayList<String>(); // 파일(들)명(인자 전송용)

		// 썸네일(thumbnail picture) 파일(인자 전송용)
		List<String> thumbFileNames = new ArrayList<String>();
		
		// 썸네일(thumbnail picture) 파일(인자 전송용) : 암호화 적용
		List<String> encodedThumbFileNames = new ArrayList<>();

		// 오류(에러) 처리
		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				log.error("파일 업로드 Error : " + error.getCode() + " - " + error.getDefaultMessage());
			}
			
			return "redirect:/show"; // 교정
		}

		log.info("파일 갯수 : " + files.size());

		if (!uploadForm.getFiles().isEmpty() && files != null && files.size() > 0) { // 파일(들) 유효성 점검

			String tmpFileName = "";
			String tmpFileExt = "";

			// 파일 정보
			for (int i = 0; i < files.size(); i++) {

				// 파일명
				tmpFileName = uploadForm.getFiles().get(i).getOriginalFilename();
				// 확장자
				tmpFileExt = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1, tmpFileName.length());

				log.info("파일명 - 확장자 :  " + tmpFileName + "-" + tmpFileExt);

			} // for
			
			// 업로드 가능 이미지 파일들 
			List<String> imgFileKinds 
				= Arrays.asList("JPG", "JPEG", "JFIF", "PNG");
		
			// 업로드 파일 확장자 제한 : 그림 파일 업로드 게시판용
			//if (tmpFileExt.equalsIgnoreCase("JPG") || tmpFileExt.equalsIgnoreCase("JPEG")
			//		|| tmpFileExt.equalsIgnoreCase("JFIF") || tmpFileExt.equalsIgnoreCase("PNG")) {
			
			if (imgFileKinds.contains(tmpFileExt.trim().toUpperCase())) {

				log.info("그림 파일 확장자 OK !");

				// if (files != null && files.size() > 0) {

				// FileOutputStream fos = null;

				for (MultipartFile multipartFile : files) {

					// String fileName = multipartFile.getOriginalFilename();
					String fileName = multipartFile.getOriginalFilename();
					
					fileNames.add(fileName);
					
					File outFileName = new File(imageUploadPath + fileName);

					// 파일 저장소(E:/KSZ/upload/image/)에 저장
					try (FileOutputStream fos = new FileOutputStream(outFileName)) {
						
						// byte[] bytes = uploadForm.getFiles().get(i).getBytes();
						byte[] bytes = multipartFile.getBytes();
						
						// File outFileName = new File(imageUploadPath + fileName);						

						// 썸네일 파일명 생성
						// 썸네일(thumbnail) path : webp/gif를 제외한 파일들은 PNG 형식으로 저장
						// ex) E:/KSZ/upload/image/thumbnail

						String thumbPath = imageUploadPath + "thumbnail/";						
						String thumbPathFileName = "thumb_" + fileName.split("\\.")[0] + ".png";

						// fos = new FileOutputStream(outFileName);

						fos.write(bytes);

						// 썸네일 파일 저장 시작
						// https://github.com/coobird/thumbnailator
						// 200*100 크기의 썸네일 작성

						File thumbnail = new File(thumbPathFileName);
						
						// if (outFileName.exists()) {
						// thumbnail.getParentFile().mkdirs();
						
						Thumbnails.of(outFileName).size(200, 100).outputFormat("png").toFile(thumbPath + thumbnail);
						// }
						// 썸네일 파일 저장 끝
						
						// 썸네일 파일 인자 저장
						thumbFileNames.add(thumbPathFileName);

					} catch (IOException e) {
						
						log.info("FileUploadController : 파일 저장 오류 !");
						e.printStackTrace();
						
					} finally {
//						try {
//							if (fos != null) {
//								fos.close();
//							}	
//						} catch (IOException e) {
//							log.info("FileUploadController save IOE : ");
//							e.printStackTrace();
//						}
//						log.info("파일 업로드 성공 !");
					} // try

				} // for

				// if : 그림 파일 점검
			} else {

				log.info("변환이 지원되지 않는 파일이거나 올바른 그림 파일 형식이 아닙니다.");
				model.addAttribute("error_msg", "변환이 지원되지 않는 파일이거나 올바른 그림 파일 형식이 아닙니다.");
				return "/error/upload_error"; // 에러 처리 페이지로 이동
			} //

		} else {
			log.error("파일 타입 에러 ! ");
			// 추가
			model.addAttribute("error_msg", "변환이 지원되지 않는 파일이거나 올바른 그림 파일 형식이 아닙니다.");
			return "/error/upload_error"; // 에러 처리 페이지로 이동
		}

		// 전송 인자(원(original) 이미지, 썸네일 이미지)
		model.addAttribute("files", fileNames);
		model.addAttribute("thumbFiles", thumbFileNames);
		
		// 암호화된 파일명
		model.addAttribute("encodedthumbFiles", encodedThumbFileNames);

		return "file_upload_success";
	} //

}