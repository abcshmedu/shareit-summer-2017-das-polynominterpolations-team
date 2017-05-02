package edu.hm.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hm.data.Book;
import edu.hm.data.Disc;
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
	/** Diese Variable enthält die Referenz auf die Geschäftslogik. */
	private static MediaService service = new MediaServiceImpl();
	
	/**
	 * Diese Methode wird aufgerufen wenn ein neues Buch erstellt werden soll.
	 * @param Book book das neue Buch
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@POST
	@Path("books")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response submitNewBook(Book book){
		System.out.println("MediaResource.submitNewBook: book = " + book);
		MediaServiceResult msr = service.addBook(book);

		return Response.status(msr.getStatus()).entity(msr.getDetail()).build();
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald ein Buch mit einer bestimmten ISBN angefordert wird.
	 * @param isbn Die ISBN des gesuchten Buches
	 * @return das angefragte Buch
	 */
	@GET
	@Path("books/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("isbn") String isbn) {
		System.out.println("MediaResource.getBook");
		
		Book book = service.getBook(isbn);

		return book;
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald alle aktuellen Bücher gelistet werden sollen.
	 * @return alle angelegten B�cher
	 */
	@GET
	@Path("books")
	@Produces(MediaType.APPLICATION_JSON)
	public Book[] listBooks(){
		System.out.println("MediaResource.listBooks");
		Book[] books = (Book[]) service.getBooks();

		return books;
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald ein schon vorhandenes Buch modifiziert werden soll.
	 * @param isbn Die ISBN zu �ndernden Buches
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@PUT
	@Path("books/{isbn}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyBook(@PathParam("isbn") String isbn, Book book){
		MediaServiceResult result = MediaServiceResult.FAIL;
		
		if(!isbn.equals(book.getIsbn())){
			result.setDetail("ISBN of the query is not the same as the ISBN in the JSON-Object.");
			return Response.status(result.getStatus()).entity(result.getDetail()).build();
		}
		
		result = service.updateBook(book);
		
		return Response.status(result.getStatus()).entity(result.getDetail()).build();
	}

	//=====================================================0
	
	
	
	
	/**
	 * Diese Methode wird aufgerufen wenn eine neues Disc erstellt werden soll.
	 * @param Disc Die neue Disc
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@POST
	@Path("discs")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response submitNewDisc(Disc disc){
		System.out.println("MediaResource.submitNewDisc: disc = " + disc);
		MediaServiceResult msr = service.addDisc(disc);

		return Response.status(msr.getStatus()).entity(msr.getDetail()).build();
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald eine Disc mit einem bestimmten Barcode angefordert wird.
	 * @param barcode Der Barcode des gesuchten Disc
	 * @return Die angefragte Disc
	 */
	@GET
	@Path("discs/{barcode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getDisc(@PathParam("barcode") String barcode) {
		System.out.println("MediaResource.getDisc");
		
		Book disc = service.getBook(barcode);

		return disc;
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald alle aktuellen Discs gelistet werden sollen.
	 * @return alle angelegten Discs
	 */
	@GET
	@Path("discs")
	@Produces(MediaType.APPLICATION_JSON)
	public Disc[] listDiscs(){
		System.out.println("MediaResource.listDiscs");
		Disc[] disc = (Disc[]) service.getDiscs();

		return disc;
	}
	
	/**
	 * Diese Methode wird aufgerufen sobald ein schon vorhandene Disc modifiziert werden soll.
	 * @param barcode Der Barcode des gesuchten Disc
	 * @return Liefert ein Response-Objekt zurück, welches den response-code und -message enthält
	 */
	@PUT
	@Path("discs/{barcode}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyDisc(@PathParam("barcode") String barcode, Disc disc){
		MediaServiceResult result = MediaServiceResult.FAIL;
		
		if(!barcode.equals(disc.getBarcode())){
			result.setDetail("Barcode of the query is not the same as the Barcode in the JSON-Object.");
			return Response.status(result.getStatus()).entity(result.getDetail()).build();
		}
		
		result = service.updateDisc(disc);
		
		return Response.status(result.getStatus()).entity(result.getDetail()).build();
	}
}