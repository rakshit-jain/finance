package com.hackathon.webservice.notify;

import java.util.Map;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.hackathon.webservice.util.Config;
import com.hackathon.webservice.util.LoggerUtil;


public class CustomMailSender extends JavaMailSenderImpl {

	private boolean isInitalized;
	private String prefix = "hackathon.email.";

	@Autowired
	private TemplateEngine templateEngine;

	public void init() {
		if (isInitalized() == false) {
			setHost(Config.getString(prefix + "host"));
			setPort(Config.getInt(prefix + "port"));
			setUsername(Config.getString(prefix + "username"));
			setPassword(Config.getString(prefix + "password"));
			setJavaMailProperties(getEmailProperties());
			setInitalized(true);
		}
	}

	private Properties getEmailProperties() {
		String[] propertiesConfig = Config.getStringArray(prefix + "properties");
		Properties properties = new Properties();
		for (String tempProperty : propertiesConfig) {
			String key = Config.getString(prefix + "properties." + tempProperty + ".key");
			String value = Config.getString(prefix + "properties." + tempProperty + ".value");
			properties.setProperty(key, value);
		}
		return properties;
	}

	private Session getPPSession() {
		Session session = Session.getInstance(getJavaMailProperties(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getUsername(), getPassword());
			}
		});
		return session;
	}

	public boolean isInitalized() {
		return isInitalized;
	}

	public void setInitalized(boolean isInitalized) {
		this.isInitalized = isInitalized;
	}

	public void sendEmail(String template, String to, String from, String subject, Map objectMap) {
		String[] tos = { to };
		sendEmail(template, tos, subject, objectMap);
	}

	public void sendEmail(String template, String[] to, String subject, Map objectMap) {
		sendEmail(template, to, Config.getString(prefix + "default.from"), subject, objectMap);
	}
	
	public void sendEmail(String template, String[] to, String from, String subject, Map objectMap) {
		try {
			MimeMessage message = createMimeMessage();
			final MimeMessageHelper messages =new MimeMessageHelper(message, true, "UTF-8"); 
			messages.setTo(to);
			messages.setSubject(subject);
			messages.setFrom(from);
			String body=getTemplateHTML(template, objectMap);
			messages.setText(body,true);
			LoggerUtil.debug(" Sending email to:" + to[0] + " with email template as:" + template+" Subject as :"+subject+" and body"+body);
			if (Config.getString(prefix+"send").equals("prod"))
				send(message);
		} catch (Exception e) {
			LoggerUtil.error("Exception is caused while sending email", e);
		}
	}

	public void sendEmail(String template, String to, String subject, Map objectMap) {
		sendEmail(template, to, Config.getString(prefix + "default.from"), subject, objectMap);
	}

	public void sendEmail(String template, String to, String subject) {
		sendEmail(template, to, subject, null);
	}

	private String getTemplateHTML(String templateName, Map<String, Object> objectMap) {
		if (templateName == null) {
			return "This is a test email";
		}
		Context context = new Context();
		if (objectMap != null)
			for (String key : objectMap.keySet()) {
				context.setVariable(key, objectMap.get(key));
			}
		String templatePath   = Config.getString(prefix+"template.location") + Config.getString(prefix+"template."+templateName);
		String result = templateEngine.process(templatePath, context);
		return result;
	}

}
