package edu.hm.logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import edu.hm.data.Book;

public class MediaResource extends AbstractHandler{
	@POST
	@Path("/media/books")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public MediaServiceResult createBook(Book book){
		
		return MediaServiceResult.OK;
	}
	
	@GET
	@Path("/media/books/{isbn}")
	public MediaServiceResult getbooks(){
		return MediaServiceResult.OK;
	}

	@Override
	public void handle(String arg0, Request arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
}
