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
import javax.ws.rs.core.Response;

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
	static MediaService service = new MediaServiceImpl();
	
	/**
	 * Diese Methode wird aufgerufen wenn ein neues Buch erstellt werden soll.
	 * @param title	Der Titel des Buches
	 * @param author Der Author des Buches
	 * @param isbn Die ISBN des Buches
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@POST
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitNewBook(@QueryParam("title") String title, @QueryParam("author") String author, @QueryParam("isbn") String isbn) {
		System.out.println("MediaResource.sbumitNewBook: title = " + title + " | author = " + author + " | isbn = " + isbn);
		Book newBook = new Book(title, author, isbn);
		MediaServiceResult msr = service.addBook(newBook);
		
		return Response.status(msr.getCode()).entity(msr.getDetail()).build();
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald ein Buch mit einer bestimmten ISBN angefordert wird.
	 * @param isbn Die ISBN des gesuchten Buches
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@GET
	@Path("books/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("isbn") String isbn) {
		System.out.println("getBook");
		MediaServiceResult result = null;
		Book book = service.getBook(isbn);
		if(book == null){
			result = MediaServiceResult.FAIL;
			result.setDetail("Book was not found.");
		}
		else {
			result = MediaServiceResult.OK;
			result.setDetail("Book was found");
		}
		
		return Response.status(result.getCode()).entity(result.getDetail()).build();
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald alle aktuellen Bücher gelistet werden sollen.
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@GET
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listBooks(){
		System.out.println("MediaResource.listBooks");
		Medium[] books = service.getBooks();
		MediaServiceResult result = MediaServiceResult.FAIL;
		
		if(books == null){
			result.setDetail("A database-error occured.");
			return Response.status(result.getCode()).entity(result.getDetail()).build();
			}
		else if(books.length == 0){
			result.setDetail("The database currently does not contain books.");
			return Response.status(result.getCode()).entity(result.getDetail()).build();
		}
		
		result = MediaServiceResult.OK;
		result.setDetail(books.length + " book(s) are currently stored inside the database.");
		return Response.status(result.getCode()).entity(result.getDetail()).build();
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald ein schon vorhandenes Buch modifiziert werden soll.
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@PUT
	@Path("books/{isbn}")
	public Response modifyBook(){
		System.out.println("MediaResource.modifyBook");
		MediaServiceResult result = MediaServiceResult.FAIL;
		
		return Response.status(result.getCode()).entity(result.getDetail()).build();
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