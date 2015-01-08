package com.maxnet.jersey.clients;

public class SimpleRESTClientUtils {

	private static final String WEB_SERVER_HOST = "localhost";
	
	private static final int WEB_SERVER_PORT = 8080;
	
	// Becomes document base base when deployed to web server
	// For example: <Context docBase="SimpleRESTJerseyExample" path="/SimpleRESTJerseyExample" />
	private static final String documentBase = "/SimpleRESTJerseyExample";
	
	/*
	   Sertvlet URL pattern matches one in web.xml
	   
	  <servlet-mapping>
	    <servlet-name>Jersey Web Application</servlet-name>
	    <url-pattern>/simple/*</url-pattern>
	  </servlet-mapping>
	  
	*/  
	private static final String SERVLET_URL_PATTERN = "/simple";
	
	
	private static String getServletURL() {
		return "http://" + WEB_SERVER_HOST + ":" + WEB_SERVER_PORT + documentBase + SERVLET_URL_PATTERN;
	}
	
	
	private static final String [][] serviceMethodPath = {
			{"/ctofservice", "/"},
			{"/ftocservice", "/"},
			{"/displayJSONFromClientService","/"}
	};
	
	public static String getCToFServiceURL(String... parms) {
		return getJetseyServiceURL(serviceMethodPath[0][0], serviceMethodPath[0][1], parms);
	}

	public static String getFToCServiceURL(String... parms) {
		return getJetseyServiceURL(serviceMethodPath[1][0], serviceMethodPath[1][1], parms);
	}

	public static String getSendJSONToClientURL(String... parms) {
		return getJetseyServiceURL(serviceMethodPath[2][0], serviceMethodPath[2][1], parms);
	}

	/**
	 * Generate client servlet/class/method URL
	 * @param classPath
	 * @param methodPath
	 * @param parms
	 * @return
	 * TODO: Figure out how to handle multiple parameters
	 */
	private static String getJetseyServiceURL(String servicePath, String methodPath, String ... parms) {
		String servPath = servicePath.length() == 1 ? "" : servicePath;
		String methPath = methodPath.length() == 1 ? "" : methodPath;
		String parameters = parms == null || parms.length == 0 ? "" : "/" + parms[0];
		return getServletURL() + servPath + methPath + parameters;
	}
	
}
