package com.xdc.rest.security;

import javax.servlet.http.HttpServletResponse;

public class RestCorsHttpServletResponse {
	public static HttpServletResponse setResponse(HttpServletResponse response){
		//response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "authorization, x-requested-with, content-type");
        //response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "1800");//30 min
		//response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        //response.setHeader("Access-Control-Allow-Headers", "authorization, x-requested-with");
        //response.setHeader("Access-Control-Max-Age", "1800");//30 min
		//response.setHeader("Access-Control-Allow-Origin", "http://localhost http://abs.xdcorp.com http://50.116.10.223/");
		
        //System.out.println(".. cors:");
        //System.out.println(response.getHeader("Access-Control-Allow-Origin"));
        //System.out.println(response.getHeader("Access-Control-Allow-Methods"));
        //System.out.println(response.getHeader("Access-Control-Allow-Headers"));
        
        return response;
	}
}
