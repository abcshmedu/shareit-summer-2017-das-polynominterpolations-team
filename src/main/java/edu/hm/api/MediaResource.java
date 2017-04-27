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

import org.eclipse.jetty.server.Response;
import org.mockito.Mockito;

import edu.hm.data.Book;
import edu.hm.data.Medium;
import edu.hm.logic.MediaService;
import edu.hm.logic.MediaServiceImpl;
import edu.hm.logic.MediaServiceResult;

/**
 * Dies ist unsere Implementierung der REST-API.
 * @author Sebastian Becker
 * @author Peter Straßer
 *
 */
@Path("media")
public class MediaResource {
	MediaService service = new MediaServiceImpl();
	
	/**
	 * Diese Methode wird aufgerufen wenn ein neues Buch erstellt werden soll.
	 * @param title	Der Titel des Buches
	 * @param author Der Author des Buches
	 * @param isbn Die ISBN des Buches
	 * @return 
	 */
	@POST
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public MediaServiceResult createNewBook(@QueryParam("title") String title, @QueryParam("author") String author, @QueryParam("isbn") String isbn) {
		System.out.println(title + " " + author + " " + isbn);
		Book newBook = new Book(title, author, isbn);
		MediaServiceResult msr = service.addBook(newBook);
		//System.out.println("createNewBook: after service.addBook(newBook)");
		
		return msr;
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald ein Buch mit einer bestimmten ISBN angefordert wird.
	 * @param isbn Die ISBN des gesuchten Buches
	 * @return
	 */
	@GET
	@Path("books/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public MediaServiceResult getBook(@PathParam("isbn") String isbn) {
		System.out.println("getBook");
		MediaServiceResult msr = null;
		Book book = service.getBook(isbn);
		if(book == null){
			msr = MediaServiceResult.FAIL;
			msr.setDetail("Book was not found.");
		}
		else {
			msr = MediaServiceResult.OK;
			msr.setDetail("Book was found");
		}
		
		return msr;
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald alle aktuellen Bücher gelistet werden sollen.
	 * @return
	 */
	@GET
	@Path("books")
	public Medium[] listBooks(){
		System.out.println("listBooks");
		Medium[] books = service.getBooks();
		return books;
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald ein schon vorhandenes Buch modifiziert werden soll.
	 * @return
	 */
	@PUT
	@Path("books/{isbn}")
	public String modifyBook(){
		System.out.println("modifyBook");
		return "lol";
	}
	
//	
//	@POST
//	@Path("discs")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public MediaServiceResult createNewDisc(@QueryParam("title") String title, @QueryParam("author") String author, @QueryParam("isbn") String isbn) {
//		//System.out.println(title + " " + author + " " + isbn);
//		Book newBook = new Book(title, author, isbn);
//		MediaServiceResult msr = service.addBook(newBook);
//		//System.out.println("createNewBook: after service.addBook(newBook)");
//		
//		return msr;
//	}
//	
//	@GET
//	@Path("discs/{barcode}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Book getDisc(@PathParam("barcode") String barcode) {
//		Book[] storedBooks = (Book[]) service.getBooks();
//		Book book = service.getBook(barcode);
//		
//		return book;
//	}
//	
//	@GET
//	@Path("discs")
//	public Disc[] listDisc(){
//		Disc[] discs = (Disc[]) service.getDiscs();
//		return discs;
//	}
//	
//	@PUT
//	@Path("discs/{isbn}")
//	public String modifyDisc(){return "lol";
//	}
}