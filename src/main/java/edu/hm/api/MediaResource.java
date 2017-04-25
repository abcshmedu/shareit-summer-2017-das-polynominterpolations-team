package edu.hm.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.hm.data.Book;
import edu.hm.logic.MediaService;
import edu.hm.logic.MediaServiceImpl;
import edu.hm.logic.MediaServiceResult;

@Path("media")
public class MediaResource {
	MediaService service = new MediaServiceImpl();
	
	@POST
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MediaServiceResult createNewBook(@QueryParam("title") String title, @QueryParam("author") String author, @QueryParam("isbn") String isbn) {
		//System.out.println(title + " " + author + " " + isbn);
		Book newBook = new Book(title, author, isbn);
		MediaServiceResult msr = service.addBook(newBook);
		//System.out.println("createNewBook: after service.addBook(newBook)");
		
		return msr;
	}
	
	@GET
	@Path("books/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("isbn") String isbn) {
		Book[] storedBooks = (Book[]) service.getBooks();
		Book book = null;
		for(Book currentBook : storedBooks){
			if(currentBook != null && currentBook.getIsbn().equals(isbn)){
				book = currentBook;
			}
		}
		
		return book;
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