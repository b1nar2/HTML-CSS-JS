package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.net.*;
import java.io.*;
import java.util.*;

@Service
public class WeatherService {

    private final String SERVICE_KEY = "api 원본키값";

    public Map<String, Object> getUltraSrtNcst(String date, String time, int nx, int ny) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
        urlBuilder.append("?serviceKey=").append(SERVICE_KEY);
        urlBuilder.append("&numOfRows=10&pageNo=1&dataType=XML");
        urlBuilder.append("&base_date=").append(date);
        urlBuilder.append("&base_time=").append(time);
        urlBuilder.append("&nx=").append(nx).append("&ny=").append(ny);

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());

        NodeList itemList = doc.getElementsByTagName("item");
        List<Map<String, String>> items = new ArrayList<>();

        for (int i = 0; i < itemList.getLength(); i++) {
            Element item = (Element) itemList.item(i);
            Map<String, String> data = new HashMap<>();
            data.put("category", item.getElementsByTagName("category").item(0).getTextContent());
            data.put("obsrValue", item.getElementsByTagName("obsrValue").item(0).getTextContent());
            items.add(data);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("items", items);
        return result;
    }
}
