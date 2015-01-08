package com.maxnet.jersey.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
// @Path specifies the relative path for a resource class or method.
@Path("/ctofservice")

public class CtoFService {
	
	// @GET indicates that the following method will answer to a HTTP GET request.
	
	@GET
	
	// @Produces specifies the response MIME media types.
	
	@Produces("application/xml")
	
	public String convertCtoF() {
 
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") "
				+ "Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		
		return "<ctofservice>" 
					+ "<celsius>" + celsius + "</celsius>" 
					+ "<ctofoutput>" + result + "</ctofoutput>" 
				+ "</ctofservice>";
	}
 
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") "
				+ "Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		
		return "<ctofservice>" 
		          + "<celsius>" + celsius + "</celsius>" 
			      + "<ctofoutput>" + result + "</ctofoutput>" 
		     + "</ctofservice>";
	}
}