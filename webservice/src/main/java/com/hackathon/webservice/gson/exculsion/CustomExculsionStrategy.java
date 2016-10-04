package com.hackathon.webservice.gson.exculsion;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class CustomExculsionStrategy implements ExclusionStrategy{

	public String regexPattern;
	
	public String getRegexPattern() {
		return regexPattern;
	}

	public void setRegexPattern(String regexPattern) {
		this.regexPattern = regexPattern;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		String currentKey=field.getName();
		if (currentKey.matches(regexPattern)){
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}