package com.maxnet.jersey.clients;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
 
public class SimpleRESTSendJSONClient {

	private static final String sampleJSON = ""
	+"  {'menu': {"
	+"	  'id': 'file',"
	+"	  'value': 'File',"
	+"	  'popup': {"
	+"	    'menuitem': ["
	+"	      {'value': 'New', 'onclick': 'CreateNewDoc()'},"
	+"	      {'value': 'Open', 'onclick': 'OpenDoc()'},"
	+"	      {'value': 'Close', 'onclick': 'CloseDoc()'}"
	+"	    ]"
	+"	  }"
	+"	}}"
	+ "";
	
	public static void runClientCode(String requestURL) {
	
	        String string = "";
	        try {
	 
	        	ByteArrayInputStream bais = new ByteArrayInputStream(sampleJSON.getBytes());
	        	
	            InputStreamReader crunchifyReader = new InputStreamReader(bais);
	            
	            BufferedReader br = new BufferedReader(crunchifyReader);
	            String line;
	            while ((line = br.readLine()) != null) {
	                string += line + "\n";
	            }
	 
	            JSONObject jsonObject = new JSONObject(string);
	            
	            System.out.println(jsonObject);
	 
	            // Step2: Now pass JSON File Data to REST Service
	            try {
	                
	            	URL url = new URL(requestURL);
	                
	                URLConnection connection = url.openConnection();
	                
	                connection.setDoOutput(true);
	                
	                connection.setRequestProperty("Content-Type", "application/json");  // !!!
	                
	                connection.setConnectTimeout(5000);
	                
	                connection.setReadTimeout(5000);
	                
	                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
	                
	                out.write(jsonObject.toString());
	                
	                out.close();
	 
	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	 
	                // read and ignore reply !!!
	                String responseLine = null;
	                System.out.println("\nPRINTING RESPONSE:\n");
	                System.out.println("----------------\n");
	                while ((responseLine = in.readLine()) != null) {  
	                	System.out.println(responseLine);
	                }
	                System.out.println("\n----------------\n");
	                
	                System.out.println("\nREST Service Invoked Successfully..");
	                in.close();
	                
	            } catch (Exception e) {
	                System.out.println("\nError while calling REST Service");
	                System.out.println(e);
	            }
	 
	            br.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
		public static void runAllTests() {
			runClientCode(SimpleRESTClientUtils.getSendJSONToClientURL());
		}
		
		public static void main(String[] args) {
			runAllTests();
		}
}
