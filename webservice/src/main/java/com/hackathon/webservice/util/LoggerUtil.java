package com.hackathon.webservice.util;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * This is a helper class which is used for logging into the application Only
 * this class should be used for loggin in the application. This will maitain a
 * cache of all the logging in the application.
 * 
 * @author puneetbehl
 */
public class LoggerUtil extends SecurityManager {

	private static Map<String, Logger> loggerMap = new ConcurrentHashMap<String, Logger>();

	public static Logger getLog() {
		String className = new LoggerUtil().getClassName();
		if (!loggerMap.containsKey(className)) {
			loggerMap.put(className, Logger.getLogger(className));
		}
		return loggerMap.get(className);
	}

	public String getClassName() {
		return getClassContext()[3].getName();
	}

	public static void debug(Object message) {
		getLog().debug(message);
	}

	public static void debug(Object message, Throwable t) {
		getLog().debug(message, t);
	}

	public static void error(Object message) {
		getLog().error(message);
	}

	public static void error(Object message, Throwable t) {
		getLog().error(message, t);
	}

	public static void fatal(Object message) {
		getLog().fatal(message);
	}

	public static void fatal(Object message, Throwable t) {
		getLog().fatal(message, t);
	}

	public static void info(Object message) {
		getLog().info(message);
	}

	public static void info(Object message, Throwable t) {
		getLog().info(message, t);
	}

	public static boolean isDebugEnabled() {
		return getLog().isDebugEnabled();
	}

	public static boolean isEnabledFor(Priority level) {
		return getLog().isEnabledFor(level);
	}

	public static boolean isInfoEnabled() {
		return getLog().isInfoEnabled();
	}

	public static void setLevel(Level level) {
		getLog().setLevel(level);
	}

	public static void warn(Object message) {
		getLog().warn(message);
	}

	public static void warn(Object message, Throwable t) {
		getLog().warn(message, t);
	}
}
