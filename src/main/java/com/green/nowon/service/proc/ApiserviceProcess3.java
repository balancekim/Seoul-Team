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
import com.green.nowon.openapi.thr.Item;
import com.green.nowon.openapi.thr.ResponseData3;
import com.green.nowon.service.ApiService3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiserviceProcess3 implements ApiService3 {

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
	public List<Item> LocalCate3(String keyWord) throws Exception {
		List<Item> allList = new ArrayList<>();
		
			URL url = new URL("http://openapi.seoul.go.kr:8088/"+apiKey+"/json/LOCALDATA_030708/1/1000");
					
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
			ResponseData3 result = mapper.readValue(responseJSONData, ResponseData3.class);
			// System.out.println(result.getInfoHappycard().getListTotalCount());
			allList.addAll(result.getLOCALDATA_030708().getRow());
		
		List<Item> filteredItems = new ArrayList<>();

		if (keyWord.equals("서울시 전체")) {
			filteredItems = allList;

		} else {
			for (Item item : allList) {
				if (item.getRdnWhlAddr().contains(keyWord)) {
					filteredItems.add(item);
				}
			}
		}
		//System.out.println(keyWord + "의 협력업체의 수는 총" + filteredItems.size() + "개 임둥");

		return filteredItems;
	}

	
	  @Override public List<Item> LocalCateSearch3(String keyWord, String searchValue) throws Exception {
		  List<Item> allList = new ArrayList<>();
	  URL url = new URL("http://openapi.seoul.go.kr:8088/"+apiKey+"/json/LOCALDATA_030708/1/1000");
	   HttpURLConnection conn = (HttpURLConnection)
	  url.openConnection(); conn.setRequestMethod("GET"); //
	  conn.setRequestProperty("Content-type", "application/json"); int responseCode
	  = conn.getResponseCode(); 
	  String responseJSONData = null;
	  
	  // apiUrl二쇱���� �������쇰� ���������� if (responseCode ==
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
		ResponseData3 result = mapper.readValue(responseJSONData, ResponseData3.class);
		// System.out.println(result.getInfoHappycard().getListTotalCount());
		allList.addAll(result.getLOCALDATA_030708().getRow());
	
		List<Item> filteredItems = new ArrayList<>(); 
		List<Item> filteredAndSearchedItems = new ArrayList<>();
	  
	  if (keyWord.equals("서울시 전체")) { 
		  filteredItems = allList;
	  } else {
		  for (Item item : allList) {
			  if (item.getRdnWhlAddr().contains(keyWord)) {
				  	filteredItems.add(item);
	  
			  }
		} 
	  } 
	  for(Item item : filteredItems) {
	  if(item.getBplcNm().contains(searchValue))
	  filteredAndSearchedItems.add(item); } 
	  //System.out.println(keyWord +  "의 협력업체의 수는 총" + filteredItems.size() );
	  //System.out.println(keyWord+"의 "+searchValue + "이(가) 포함된 업체의 수는 총" + filteredAndSearchedItems.size() );
	  
	  
	  
	  return filteredAndSearchedItems; }
	 

}
