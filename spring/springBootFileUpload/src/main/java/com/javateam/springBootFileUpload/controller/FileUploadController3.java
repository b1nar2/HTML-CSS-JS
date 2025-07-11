package com.javateam.springBootFileUpload.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.springBootFileUpload.domain.FileUploadForm;
import com.javateam.springBootFileUpload.service.FileNamingEnDecodingUtil;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Slf4j
public class FileUploadController3 {

	@Value("${imageUpload.path}") 
	// application.properties 파일의 imageUpload.path=E:/KSZ/upload/image/
	private String imageUploadPath; 
	
	@Autowired
	FileNamingEnDecodingUtil fileNamingEnDecodingUtil;

	@PostMapping("/save3")
	public String save3(@ModelAttribute("uploadForm") FileUploadForm uploadForm, 
						Model model, BindingResult result) {
		
		log.info("file save3");

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
			if (imgFileKinds.contains(tmpFileExt.trim().toUpperCase())) {

				log.info("그림 파일 확장자 OK !");

				for (MultipartFile multipartFile : files) {

					String fileName = multipartFile.getOriginalFilename();
					
					fileNames.add(fileName);
					
					// 암호화된 파일명 적용 
					String encryptedFilename = fileNamingEnDecodingUtil.encodeFilename(fileName);
					File outFileName = new File(imageUploadPath + encryptedFilename);					

					// 파일 저장소(E:/KSZ/upload/image/)에 저장
					// try (FileOutputStream fos = new FileOutputStream(outFileName)) {
					try {
						
						// byte[] bytes = multipartFile.getBytes();
						
						// 썸네일 파일명 생성
						// 썸네일(thumbnail) path : webp/gif를 제외한 파일들은 PNG 형식으로 저장
						// ex) E:/KSZ/upload/image/thumbnail

						String thumbPath = imageUploadPath + "thumbnail/";						

						// 파일명 암호화 적용
						String thumbPathFileName = "thumb_" 
												 // + fileNamingEnDecodingUtil.encodeFilename(fileName).split("\\.")[0]
												 + encryptedFilename.split("\\.")[0]
												 + ".png";

						// fos.write(bytes);
						
						// nio 패키지 저장방식
						log.info("outFileName.getPath() : {}", outFileName.getPath());						
						log.info("multipartFile.getInputStream() 공백 여부 : {}", multipartFile.isEmpty());
						
						/* ---------------------------------------------------------------------- */
						
						try (InputStream inputStream = multipartFile.getInputStream()) {
						
//							Files.copy(inputStream, 
//									   Paths.get(outFileName.getAbsolutePath()), 
//									   StandardCopyOption.REPLACE_EXISTING);
							
							// Spring FileUtils 저장 방식
							FileUtils.copyToFile(inputStream, outFileName);
							
						} catch (Exception e) {
							log.error("업로드 파일 저장 실패 : " + e);
							
							model.addAttribute("error_msg", "업로드 파일 저장에 실패하였습니다.");
							return "/error/upload_error"; // 에러 처리 페이지로 이동
						}
						
						/* ---------------------------------------------------------------------- */

						// 썸네일 파일 저장 시작
						// https://github.com/coobird/thumbnailator
						// 200*100 크기의 썸네일 작성

						File thumbnail = new File(thumbPathFileName);
						
						Thumbnails.of(outFileName)
								  .size(200, 100)
								  .outputFormat("png")
								  .toFile(thumbPath + thumbnail);
						// 썸네일 파일 저장 끝
						
						// 파일명 복호화 적용
						encodedThumbFileNames.add(fileNamingEnDecodingUtil.decodeFilename(thumbPathFileName, "png"));

						// 썸네일 파일 인자 저장
						thumbFileNames.add(thumbPathFileName);

					} catch (Exception e) {
						
						log.info("FileUploadController3 : 파일 저장 오류 !");
						e.printStackTrace();
						
						// 추가
						model.addAttribute("error_msg", "업로드 파일이 저장되지 않았습니다.");
						return "/error/upload_error"; // 에러 처리 페이지로 이동
						
					} 

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