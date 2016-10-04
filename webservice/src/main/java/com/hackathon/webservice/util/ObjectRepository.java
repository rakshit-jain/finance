package com.hackathon.webservice.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="objectRepo")
@Scope("singleton")
public class ObjectRepository implements ApplicationContextAware{

	private static ApplicationContext appContext;
	
	private static ObjectRepository repo;
	
	public static ObjectRepository getRepo(){
		return repo;
	}
	public static ApplicationContext getContext(){
		return appContext;
	}
	
	public static <T> T getBean(Class<T> requiredType) throws BeansException{
		return appContext.getBean(requiredType);
	}
	
	public static <T> T getBean(String beanId, Class<T> requiredType) throws BeansException{
		return appContext.getBean(beanId,requiredType);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appContext = applicationContext;
	}
}

