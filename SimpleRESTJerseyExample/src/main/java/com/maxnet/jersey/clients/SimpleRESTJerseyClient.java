package com.maxnet.jersey.clients;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SimpleRESTJerseyClient {

	private static void getCtoFResponse(String requestedURL) {
		try {
 
			Client client = Client.create();
			WebResource webResource = client.resource(requestedURL);
			ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
 
			String output = response.getEntity(String.class);
			System.out.println("============getCtoFResponse============");
			System.out.println(output);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getFtoCResponse(String requestedURL) {
		try {
 
			Client client = Client.create();
			WebResource webResource2 = client.resource(requestedURL);
			ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
			if (response2.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
			}
 
			String output2 = response2.getEntity(String.class);
			System.out.println("\n============getFtoCResponse============");
			System.out.println(output2);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runAllTests() {
		// THIS ONE MAKES REQUEST FOR XML
		getCtoFResponse(SimpleRESTClientUtils.getCToFServiceURL());
		getCtoFResponse(SimpleRESTClientUtils.getCToFServiceURL(new String [] {"100"}));
	
		// THIS ONE MAKES REQUEST FOR JSON
		getFtoCResponse(SimpleRESTClientUtils.getFToCServiceURL());
		getFtoCResponse(SimpleRESTClientUtils.getFToCServiceURL(new String [] {"100"}));

	}
	public static void main(String [] args) {
		runAllTests();
	}
}
