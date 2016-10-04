package com.hackathon.webservice.notify;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.hackathon.webservice.util.Config;
import com.hackathon.webservice.util.LoggerUtil;

public class Text2LocalImpl implements SMSGateway {

	private final String SERVER_URL = "http://api.textlocal.in/send/?";
	private final String CONFIG_PREFIX="hackathon.sms.";
	
	@Autowired
	RestTemplate template;
	
	@Override
	public void send(String message, String to)  {

		// Construct data
		String user = "username=" + Config.getString(CONFIG_PREFIX+"email");
		String hash = "&hash=" + Config.getString(CONFIG_PREFIX+"hashkey");
		String messagefinal = "&message=" + message;
		String test = "&test=" + Config.getString(CONFIG_PREFIX+"test","true");
		String sender = "&sender=" + "TXTLCL";
		String numbers = "&numbers=" + to;

		String data = user + hash + numbers + test + messagefinal + sender;
		String finalURL = SERVER_URL+data;

		HttpHeaders headers = new HttpHeaders();
		

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Content-Length", Integer.toString(data.length()));
		ResponseEntity<String> result = null;
		try {
			HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);
			new RestTemplate();
			if (! Config.getBoolean(CONFIG_PREFIX+"test")){
				LoggerUtil.debug("Text2Local::: Sending SMS to:"+to+" with Message as:"+message);
				result = template.exchange(finalURL, HttpMethod.GET, entity, String.class);
			}
		} catch (HttpServerErrorException e) {
			LoggerUtil.error(e.getMessage());
			LoggerUtil.error(e);
		}
		LoggerUtil.debug(result);
	}

}
