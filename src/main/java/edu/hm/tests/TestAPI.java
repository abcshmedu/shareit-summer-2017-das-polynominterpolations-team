//package edu.hm.tests;
//
//import static org.junit.Assert.assertEquals;
//
//import javax.ws.rs.core.Response;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import edu.hm.api.MediaResource;
//import edu.hm.data.Book;
//import edu.hm.data.Disc;
//
//public class TestAPI {
//    private static final int OK = 200;
//    private static final int FAIL = 300;
//    
//    private MediaResource mr;
//
//    /** Diese Methode erzeugt immer ein neues MediaServiceImpl- und
//     * MediaResource-Objekt. */
//    @Before
//    public void setup() {
//	mr = new MediaResource();
//	
//    }
//    // =============================================BOOK_TESTS_API===============================================
//
//    /** Diese Methode testet die API-Methode "submitNewBook". */
//    @Test
//    public void testBookPost() {
//	Book book = new Book("A", "A", "978-1-11111-111-1");
//	Response response = mr.submitNewBook(book);
//	assertEquals(response.getStatus(), OK);
//	book = new Book(null, "A", "978-1-11111-111-1");
//	response = mr.submitNewBook(book);
//	assertEquals(response.getStatus(), FAIL);
//    }
//
//    /** Diese Methode testet die API-Methode "modifyBook". */
//    @Test
//    public void testBookPut() {
//	String isbn = "978-1-11111-111-1";
//	Book book = new Book("A", "1", isbn);
//	mr.submitNewBook(book);
//
//	Book testBook = new Book("B", "2", "978-1-111-111-1"); // No disc with
//							       // this barcode
//							       // exists
//	Response result = mr.modifyBook("978-1-111-111-1", testBook);
//	assertEquals(result.getStatus(), FAIL);
//
//	testBook = new Book(null, "2", isbn); // no title
//	result = mr.modifyBook(isbn, testBook);
//	assertEquals(result.getStatus(), FAIL);
//
//	testBook = new Book("B", "2", isbn); // valid disc no error
//	mr.modifyBook(isbn, testBook);
//	book = mr.getBook(isbn);
//	assertEquals(book, testBook);
//    }
//
//    // =============================================DISC_TESTS_API===============================================
//
//    /** Diese Methode testet die API-Methode "submitNewDisc". */
//    @Test
//    public void testDiscPost() {
//	Disc disc = new Disc("A", "1", "123456789012", 0);
//	Response response = mr.submitNewDisc(disc);
//	assertEquals(response.getStatus(), OK);
//	disc = new Disc(null, "2", "123456789012", 0);
//	response = mr.submitNewDisc(disc);
//	assertEquals(response.getStatus(), FAIL);
//    }
//
//    /** Diese Methode testet die API-Methode "modifyDisc". */
//    @Test
//    public void testDiscPut() {
//	String barcode = "123456789012";
//	Disc disc = new Disc("A", "1", barcode, 0);
//	mr.submitNewDisc(disc);
//
//	Disc testDisc = new Disc("B", "2", "123456789000", 0); // No disc with
//							       // this barcode
//							       // exists
//	Response result = mr.modifyDisc("123456789000", testDisc);
//	assertEquals(result.getStatus(), FAIL);
//
//	testDisc = new Disc(null, "2", barcode, 0); // no title
//	result = mr.modifyDisc(barcode, testDisc);
//	assertEquals(result.getStatus(), FAIL);
//
//	testDisc = new Disc("B", "2", barcode, 0); // valid disc no error
//	mr.modifyDisc(barcode, testDisc);
//	disc = mr.getDisc(barcode);
//	assertEquals(disc, testDisc);
//    }
//
//}
