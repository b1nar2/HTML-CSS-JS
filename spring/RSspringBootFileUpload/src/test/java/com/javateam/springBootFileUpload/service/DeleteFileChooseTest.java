package com.javateam.springBootFileUpload.service;

import java.util.Arrays;
import java.util.List;

public class DeleteFileChooseTest {
	
	public static void main(String[] args) {
		
		List<String> defaultList = Arrays.asList(new String[]{"A.jpg", "B.jpg", "C.jpg"});
		List<String> updateList = Arrays.asList(new String[]{"A.jpg", "D.jpg", "C.jpg", "E.jpg"});
		
		defaultList.stream().filter(x -> !updateList.contains(x)).forEach(x -> System.out.println(x));
	}

}
