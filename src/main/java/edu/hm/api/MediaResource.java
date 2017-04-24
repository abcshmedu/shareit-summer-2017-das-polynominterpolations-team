package edu.hm.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("media")
public class MediaResource {
	@POST
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createNewBook() {
		System.out.println("test");
		String returnString = "";
		return null;
	}
	
	@GET
	@Path("books/{isbn}")
	public String getBooks(@PathParam("isbn") String name) {
		// return MediaServiceResult.OK;
		return "hello world";
	}
	
	@GET
	@Path("books")
	public String listBooks(){
		return "books";
	}
	
	@PUT
	@Path("books/{isbn}")
	public String modifyBook(){return "lol";}
	
	
	
	

}
