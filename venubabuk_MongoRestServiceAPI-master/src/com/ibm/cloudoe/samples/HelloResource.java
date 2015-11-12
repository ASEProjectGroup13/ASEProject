package com.ibm.cloudoe.samples;

import javax.ws.rs.Path;

@Path("hello")
public class HelloResource {

	public String returnString(){
		return "hello rest of world";
	}
}
