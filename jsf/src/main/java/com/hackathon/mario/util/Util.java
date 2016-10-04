package com.hackathon.mario.util;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Util {

	private static RestTemplate restTemplate = new RestTemplate();

	public static JsonObject httpGetRequest(String url, Map<String,String> header) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		if (header!=null){
			for (String key: header.keySet()){
				headers.set(key, header.get(key));
			}
		}
		
		HttpEntity entity = new HttpEntity(headers);

		HttpEntity<JsonObject> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonObject.class);
		JsonObject jsonResponse = response.getBody();
		JsonObject object = jsonResponse.getAsJsonObject("body");
		return object;
	}
	
	public static JsonArray httpGetRequestArray(String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);

		HttpEntity<JsonObject> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonObject.class);
		JsonObject jsonResponse = response.getBody();
		JsonArray object = jsonResponse.getAsJsonArray("body");
		return object;
	}
	
	
	public static JsonArray httpPostRequestArray(String url, Object postData, Map<String,String> header) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		if (header!=null){
			for (String key: header.keySet()){
				headers.set(key, header.get(key));
			}
		}
	
		HttpEntity entity;
		
		if(postData!=null){
			entity=new HttpEntity(postData, headers);
		}else{
			entity=new HttpEntity(headers);
		}
		JsonArray object=null;
		try{
			HttpEntity<JsonObject> response = restTemplate.exchange(url, HttpMethod.POST, entity, JsonObject.class);
			JsonObject jsonResponse = response.getBody();
			object = jsonResponse.getAsJsonArray("body");
		}catch(Exception ex){
			LoggerUtil.error("Exception caused while trying to connect to user",ex);
		}
		return object;
	}
	
	public static JsonObject httpPost(String url, Object postData, Map<String,String> header) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		if (header!=null){
			for (String key: header.keySet()){
				headers.set(key, header.get(key));
			}
		}
	
		HttpEntity entity;
		
		if(postData!=null){
			entity=new HttpEntity(postData, headers);
		}else{
			entity=new HttpEntity(headers);
		}
		JsonObject object=null;
		try{
			HttpEntity<JsonObject> response = restTemplate.exchange(url, HttpMethod.POST, entity, JsonObject.class);
			JsonObject jsonResponse = response.getBody();
			object = jsonResponse.getAsJsonObject("body");
		}catch(Exception ex){
			LoggerUtil.error("Exception caused while trying to connect to user",ex);
		}
		return object;
	}
}
