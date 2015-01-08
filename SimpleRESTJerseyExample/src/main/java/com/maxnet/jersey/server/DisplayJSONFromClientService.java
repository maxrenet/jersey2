package com.maxnet.jersey.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/")
public class DisplayJSONFromClientService {

	    @POST
	    
	    @Path("/displayJSONFromClientService")
	    
	    @Consumes(MediaType.APPLICATION_JSON)  //   !!! MARKS THE CONSUMER OF JSON METHOD
	    
	    public Response displayJSON(InputStream incomingData) {
	    	
	        StringBuilder sb = new StringBuilder();
	        
	        try {
	            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
	            String line = null;
	            while ((line = in.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (Exception e) {
	            System.out.println("Error Parsing: - ");
	        }

	        JSONObject jo = new JSONObject(sb.toString());
	        
	        String prettyJSON = jo.toString(4);
	        
	        System.out.println("DisplayJSONFromClientService Data Received: \n" + prettyJSON);
	 
	        // return HTTP response 200 in case of success
	        return Response.status(200).entity(sb.toString()).build();  // !!!
	    
	    }
	    
}
