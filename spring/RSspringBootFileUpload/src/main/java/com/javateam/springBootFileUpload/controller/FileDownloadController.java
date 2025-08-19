package com.javateam.springBootFileUpload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileDownloadController {

	@Value("${fileUpload.path}")
	// application.properties 파일의 fileUpload.path=E:/students/lsh/upload/
	private String fileUploadPath;

	private String getBrowser(HttpServletRequest request) {

		String header = request.getHeader("User-Agent");

		if (header.contains("MSIE")) {
			return "MSIE";
		} else if (header.contains("Trident")) {
			return "MSIE11";
		} else if (header.contains("Chrome")) {
			return "Chrome";
		} else if (header.contains("Opera")) {
			return "Opera";
		}

		return "Firefox";
	}

	// 주의사항) {fileName:.+} 에서 ".+" 를 포함하지 않으면 파일의 확장자가 인자로 넘어오지 않음.
	@GetMapping("/download/{originalFilename:.+}/{filename:.+}")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("originalFilename") String originalFilename, @PathVariable("filename") String filename)
					throws IOException 
	{
		log.info("####### originalFilename : " + originalFilename);
		log.info("####### filename : " + filename);

		String filePath = fileUploadPath + "/" + filename;

		log.info("########### file Path : " + filePath);

		File file = null;
		file = new File(filePath);

		if (!file.exists()) {

			String msg = "파일이 존재하지 않습니다.";
			log.error(msg);
			return;
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());

		if (mimeType == null) {

			log.error("mimetype 인지 불가. 기본 Mime으로 설정 !");
			mimeType = "application/octet-stream";
		}

		log.error("mimetype : " + mimeType);

		// 다운로드시 한글 파일 깨짐 현상 방지
		String header = getBrowser(request);

		if (header.contains("MSIE")) {
			
			String docName = URLEncoder.encode(originalFilename, "UTF-8").replaceAll("\\+", "%20");
			response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
			
		} else if (header.contains("Firefox")) {
			
			String docName = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
			
		} else if (header.contains("Opera")) {
			
			String docName = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
			
		} else if (header.contains("Chrome")) {
			
			String docName = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
		}

		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		response.setContentType(mimeType);
		response.setContentLength((int) file.length());

		// 원래 파일명으로 저장(다운로드)
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	} //

}
