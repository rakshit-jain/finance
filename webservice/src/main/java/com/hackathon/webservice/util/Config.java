package com.hackathon.webservice.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.xml.resolver.apps.resolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * This is the Config file for loading the configuraiton at runtime.
 * 
 * In order to change the configuration reading in future, this will be the
 * single file which will need to be changed.
 * 
 * Currently we are using Apache Common configuration and FileChange Reload
 * mechanism to change the file.
 * 
 * @author Puneet Behl
 */
@Component
public class Config {

	private String configFile;

	private static Configuration config;

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	@PostConstruct
	public void init() throws ConfigurationException, IOException {
		printSytemProperties();
		String home = System.getProperty("catalina.home");
		//Resource resource = new ClassPathResource("application.properties");
		File file = new File(home + "/conf/application.properties");
		//File file = resource.getFile();
		LoggerUtil.debug("This file should exist here. and it should include the desired property file"
				+ file.getAbsolutePath());
		LoggerUtil.debug("Parnet config file exists:" + file.exists());
		PropertiesConfiguration configuration = new PropertiesConfiguration(file);
		// Adding a reloading strategy, This will reload the file, as there will
		// be any change in file.
		configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
		config = configuration;
		LoggerUtil.debug("Configuraiton loaded successfully");
	}

	private void printSytemProperties() {
		Properties p = System.getProperties();
		Enumeration keys = p.keys();
		while (keys.hasMoreElements()) {
		  String key = (String)keys.nextElement();
		  String value = (String)p.get(key);
		  LoggerUtil.debug(key + ": " + value);
		}
	}

	public static String getString(String key, String defaultValue) {
		String value =  config.getString(key);
		if (value==null){
			return defaultValue;
		}
		return value;
	}
	
	public static String getString(String key) {
		return getString(key,null);
	}

	public static int getInt(String key) {
		int result = 0;
		try {
			result = config.getInt(key);
		} catch (ConversionException e) {
			LoggerUtil.error("Not able to translate the config, returning zero");
		}catch (NoSuchElementException ne){
			LoggerUtil.error("The integer value of Key doesnot exist in configuration. Kindly check: "+key);
		}
		return result;
	}

	public static Boolean getBoolean(String key) {
		return config.getBoolean(key);
	}

	public static List<Object> getList(String key) {
		List<Object> list = config.getList(key);
		return list;
	}

	public static String[] getStringArray(String key) {
		String[] stringArray = config.getStringArray(key);
		return stringArray;
	}
	
}
