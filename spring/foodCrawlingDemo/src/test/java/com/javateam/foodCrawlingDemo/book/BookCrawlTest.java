package com.javateam.foodCrawlingDemo.book;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BookCrawlTest {
	
	/**
	 * yes24 서점 크롤링 예시
	 * 
	 * @throws IOException
	 */
	@Test
	public void Test() throws IOException {
		
		String bookSite = "http://www.yes24.com/24/Category/Display/001001019001";
		Document doc = Jsoup.connect(bookSite).get();
		
		log.info("타이틀 : " + doc.select("title").text());
		
		// 개별 도서상품 패널 : <div class="itemUnit"> .... </div>
		log.info("카테고리 도서 수 : " + doc.select("div.itemUnit").size());
		
		// 첫째 도서 고유번호 : <a href="/product/goods/147570099" ...
		log.info("카테고리 첫째 도서 고유번호 : " + doc.select("div.itemUnit span.img_grp a[href]").attr("href").split("/")[3]);
		
		// 첫째 도서 제목 : <img class="lazy" ...... alt="편안함의 습격">
		log.info("카테고리 첫째 도서 제목 : " + doc.select("div.itemUnit span.img_grp a[href] img").attr("alt"));
		
		log.info("-------------------------------");
		
		int len = doc.select("div.itemUnit").size();
		
		for (int i=0; i<len; i++) {
			
			log.info("카테고리 {} 도서 번호 : {}", i+1, 
					doc.select("div.itemUnit span.img_grp a[href]").get(i).attr("href").split("/")[3]);
		} //
		
	} //

}