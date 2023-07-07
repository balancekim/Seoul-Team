package com.green.nowon.service.proc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.green.nowon.openapi.first.InfoHappycard;
import com.green.nowon.openapi.first.Item;
import com.green.nowon.openapi.first.ResponseData;
import com.green.nowon.service.ApiService;

import lombok.extern.slf4j.Slf4j;


@Service
public class ApiserviceProcess implements ApiService {

	@Value("${api.key}")
    private String apiKey;
	
	private String readBody(InputStream inputStream) throws IOException {
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader lineReader = new BufferedReader(isr);

		StringBuilder responseBody = new StringBuilder();

		String data;

		while ((data = lineReader.readLine()) != null) {
			responseBody.append(data);
		}

		lineReader.close();
		isr.close();
		return responseBody.toString();
	}

	@Override
	public List<Item> LocalCate(String keyWord) throws Exception {
		List<Item> allList = new ArrayList<>();
		for (int i = 1; i < 4000; i += 1000) {
			URL url = new URL("http://openapi.seoul.go.kr:8088/"+apiKey+"/json/InfoHappycard/" + i
					+ "/" + (i + 999));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Content-type", "application/json");
			int responseCode = conn.getResponseCode(); /* �곌껐 ��泥댁�� ���� ���몄�� ������誘�濡� 異�媛��⑸����. */
			String responseJSONData = null;

			// apiUrl二쇱���� �������쇰� ����������
			if (responseCode == HttpURLConnection.HTTP_OK) {
				responseJSONData = readBody(conn.getInputStream());

			} else {
				responseJSONData = readBody(conn.getErrorStream());

			}
			conn.disconnect();
			// System.out.println(responseJSONData);u
			// JSON_STRING->java Obj濡� 留ㅽ��
			// ObjectMapper mapper =
			// JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
			// true).build();
			ObjectMapper mapper = new ObjectMapper();
			ResponseData result = mapper.readValue(responseJSONData, ResponseData.class);
			// System.out.println(result.getInfoHappycard().getListTotalCount());
			allList.addAll(result.getInfoHappycard().getRow());
		}
		List<Item> filteredItems = new ArrayList<>();
		
		if (keyWord.equals("서울시 전체")) {
			filteredItems = allList;
			
		} else {
			for (Item item : allList) {
				if (item.getAddr().contains(keyWord)) {
					filteredItems.add(item);
				}
			}
		}
		
		

		return filteredItems;
	}

	@Override
	public List<Item> LocalCateSearch(String keyWord, String searchValue) throws Exception {
		List<Item> allList = new ArrayList<>();
		for (int i = 1; i < 4000; i += 1000) {
			URL url = new URL("http://openapi.seoul.go.kr:8088/"+apiKey+"/json/InfoHappycard/" + i
					+ "/" + (i + 999));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Content-type", "application/json");
			int responseCode = conn.getResponseCode(); /* �곌껐 ��泥댁�� ���� ���몄�� ������誘�濡� 異�媛��⑸����. */
			String responseJSONData = null;

			// apiUrl二쇱���� �������쇰� ����������
			if (responseCode == HttpURLConnection.HTTP_OK) {
				responseJSONData = readBody(conn.getInputStream());

			} else {
				responseJSONData = readBody(conn.getErrorStream());

			}
			conn.disconnect();
			// System.out.println(responseJSONData);u
			// JSON_STRING->java Obj濡� 留ㅽ��
			// ObjectMapper mapper =
			// JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
			// true).build();
			ObjectMapper mapper = new ObjectMapper();
			ResponseData result = mapper.readValue(responseJSONData, ResponseData.class);
			// System.out.println(result.getInfoHappycard().getListTotalCount());
			allList.addAll(result.getInfoHappycard().getRow());
		}
		List<Item> filteredItems = new ArrayList<>();
		List<Item> filteredAndSearchedItems = new ArrayList<>();
		
		if (keyWord.equals("서울시 전체")) {
			filteredItems = allList;
			
			
		} else {
			for (Item item : allList) {
				if (item.getAddr().contains(keyWord)) {
					filteredItems.add(item);
					
				}
			}
		}
		for(Item item : filteredItems) {
			if(item.getCompanyName().contains(searchValue))
			filteredAndSearchedItems.add(item);
		}
		//System.out.println(keyWord + "의 협력업체의 수는 총" + filteredItems.size() + "개 임둥");
		//System.out.println(keyWord+"의 "+searchValue + "이(가) 포함된 업체의 수는 총" + filteredAndSearchedItems.size() + "개 임둥");
		

		
		return filteredAndSearchedItems;
	}

}
