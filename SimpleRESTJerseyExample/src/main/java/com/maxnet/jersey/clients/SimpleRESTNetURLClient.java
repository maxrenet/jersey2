package com.maxnet.jersey.clients;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class SimpleRESTNetURLClient {

	public static void runClientCode(String requestURL) {
		
		System.out.println("Requested URL: " + requestURL);
		
		StringBuilder sb = new StringBuilder();
		
		URLConnection urlConn = null;
		
		InputStreamReader in = null;
		
		try {
			
			URL url = new URL(requestURL);
			
			urlConn = url.openConnection();
			
			if (urlConn != null) {
				urlConn.setReadTimeout(60 * 1000);
			}
			
			if (urlConn != null && urlConn.getInputStream() != null) {
				
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				
				BufferedReader bufferedReader = new BufferedReader(in);
				
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			
			in.close();
			
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + requestURL, e);
		}

		System.out.println("\n============Output:============ \n"  
				+ sb.toString());
	}

	public static void runAllTests() {
		// THIS TEST IS AGNOSTIC TO WHAT IS RETURNED - XML or JSON
		runClientCode(SimpleRESTClientUtils.getCToFServiceURL());
		runClientCode(SimpleRESTClientUtils.getCToFServiceURL(new String [] {"100"}));
		
		runClientCode(SimpleRESTClientUtils.getFToCServiceURL());
		runClientCode(SimpleRESTClientUtils.getFToCServiceURL(new String [] {"100"}));
	}
	
	public static void main(String[] args) {
		runAllTests();
	}
	
}