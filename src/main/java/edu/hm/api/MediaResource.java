package edu.hm.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.hm.logic.MediaServiceResult;

public class MediaResource{
	@POST
	@Path("/media/books")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createNewBook(){
		System.out.println("test");
		String returnString = "";
		return returnString;
	}
	
	
	@GET
	@Path("/media/books/{isbn}")
	public MediaServiceResult getbooks(){
		return MediaServiceResult.OK;
	}
	

	
}
