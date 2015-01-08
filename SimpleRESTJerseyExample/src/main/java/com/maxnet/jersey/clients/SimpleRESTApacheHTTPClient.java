package com.maxnet.jersey.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class SimpleRESTApacheHTTPClient {
	
	public static void runClientCode(String requestURL) {
		
		try {
			 
			// create default HTTP Client  - this is changed from original example
			
			HttpClientBuilder builder = HttpClientBuilder.create();
			CloseableHttpClient httpClient = builder.build();

			// Create new getRequest with below mentioned URL
			HttpGet getRequest = new HttpGet(requestURL);

			// Add additional header to getRequest which accepts application/xml data
			getRequest.addHeader("accept", "application/xml");

			// Execute your request and catch response
			HttpResponse response = httpClient.execute(getRequest);

			// Check for HTTP response code: 200 = success
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			// Get-Capture Complete application/xml body response
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			System.out.println("============Output:============");

			// Simply iterate through XML response and show on console.
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.close();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void runAllTests() {
		// the client is coded to request application/xml
		runClientCode(SimpleRESTClientUtils.getCToFServiceURL());
		runClientCode(SimpleRESTClientUtils.getCToFServiceURL(new String [] {"100"}));
	}
	
	public static void main(String[] args) {
		runAllTests();
	}	
}
