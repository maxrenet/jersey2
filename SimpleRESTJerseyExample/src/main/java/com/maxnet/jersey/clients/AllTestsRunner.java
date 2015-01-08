package com.maxnet.jersey.clients;

public class AllTestsRunner {

	public static void main(String[] args) {
		
		SimpleRESTNetURLClient.runAllTests();
		
		SimpleRESTApacheHTTPClient.runAllTests();
		
		SimpleRESTJerseyClient.runAllTests();
		
		SimpleRESTSendJSONClient.runAllTests();
		
	}

}
