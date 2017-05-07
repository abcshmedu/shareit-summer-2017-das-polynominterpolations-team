package edu.hm.tests;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import edu.hm.api.MediaResource;
import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;
import edu.hm.logic.MediaService;
import edu.hm.logic.MediaServiceImpl;
import edu.hm.logic.MediaServiceResult;

/** Unsere Test-Klasse für die API und Logic.
 * 
 * @author Sebastian Becker, Peter Straßer */
public class TestLogicAndAPI {
    private static final int OK = 200;
    private static final int FAIL = 300;

    private MediaService ms;
    private MediaResource mr;

    /** Diese Methode erzeugt immer ein neues MediaServiceImpl- und
     * MediaResource-Objekt. */
    @Before
    public void setup() {
        ms = new MediaServiceImpl();
        mr = new MediaResource();
    }

    // =============================================BOOK_AND_DISC_TESTS===============================================
    /** Diese Methode testet die hashCode- und equals-Methode der
     * Book-Klasse. */
    @Test
    public void testBooks() {
        Book book1 = new Book("A", "A", "978-1-11111-111-1");
        Book book2 = new Book("A", "A", "978-1-11111-111-1");
        assertEquals(book1.hashCode(), book2.hashCode());
        assertTrue(book1.equals(book2));
    }

    /** Diese Methode testet die hashCode- und equals-Methode der
     * Disc-Klasse. */
    @Test
    public void testDiscs() {
        Disc disc1 = new Disc("A", "A", "123456789012", 0);
        Disc disc2 = new Disc("A", "A", "123456789012", 0);
        assertEquals(disc1.hashCode(), disc2.hashCode());
        assertTrue(disc1.equals(disc2));
    }

    // =============================================BOOK_TESTS_API===============================================

    /** Diese Methode testet die API-Methode "submitNewBook". */
    @Test
    public void testBookPost() {
        Book book = new Book("A", "A", "978-1-11111-111-1");
        Response response = mr.submitNewBook(book);
        assertEquals(response.getStatus(), OK);
        book = new Book(null, "A", "978-1-11111-111-1");
        response = mr.submitNewBook(book);
        assertEquals(response.getStatus(), FAIL);
    }

    /** Diese Methode testet die API-Methode "modifyBook". */
    @Test
    public void testBookPut() {
        String isbn = "978-1-11111-111-1";
        Book book = new Book("A", "1", isbn);
        mr.submitNewBook(book);

        Book testBook = new Book("B", "2", "978-1-111-111-1"); // No disc with
                                                               // this barcode
                                                               // exists
        Response result = mr.modifyBook("978-1-111-111-1", testBook);
        assertEquals(result.getStatus(), FAIL);

        testBook = new Book(null, "2", isbn); // no title
        result = mr.modifyBook(isbn, testBook);
        assertEquals(result.getStatus(), FAIL);

        testBook = new Book("B", "2", isbn); // valid disc no error
        mr.modifyBook(isbn, testBook);
        book = mr.getBook(isbn);
        assertEquals(book, testBook);
    }

    // =============================================DISC_TESTS_API===============================================

    /** Diese Methode testet die API-Methode "submitNewDisc". */
    @Test
    public void testDiscPost() {
        Disc disc = new Disc("A", "1", "123456789012", 0);
        Response response = mr.submitNewDisc(disc);
        assertEquals(response.getStatus(), OK);
        disc = new Disc(null, "2", "123456789012", 0);
        response = mr.submitNewDisc(disc);
        assertEquals(response.getStatus(), FAIL);
    }

    /** Diese Methode testet die API-Methode "modifyDisc". */
    @Test
    public void testDiscPut() {
        String barcode = "123456789012";
        Disc disc = new Disc("A", "1", barcode, 0);
        mr.submitNewDisc(disc);

        Disc testDisc = new Disc("B", "2", "123456789000", 0); // No disc with
                                                               // this barcode
                                                               // exists
        Response result = mr.modifyDisc("123456789000", testDisc);
        assertEquals(result.getStatus(), FAIL);

        testDisc = new Disc(null, "2", barcode, 0); // no title
        result = mr.modifyDisc(barcode, testDisc);
        assertEquals(result.getStatus(), FAIL);

        testDisc = new Disc("B", "2", barcode, 0); // valid disc no error
        mr.modifyDisc(barcode, testDisc);
        disc = mr.getDisc(barcode);
        assertEquals(disc, testDisc);
    }

    // =============================================BOOK_TESTS_LOGIC===============================================

    /** Diese Methode testet die Logic-Methode "addBook". */
    @Test
    public void testAddBook() {
        Book[] books = new Book[5];
        books[0] = new Book("A", "A", "978-1-11111-111-1"); // korrektes Buch
                                                            // hinzugef�gt
        books[1] = new Book("A", "A", "978-1-11111-111-1"); // ISBN schon
                                                            // vorhanden
        books[2] = new Book("A", "A", "98-1-11111-111-1"); // Falsche ISBN
        books[3] = new Book("A", null, "978-1-111111-111-1"); // Author fehlt
        MediaServiceResult result = ms.addBook(books[0]);
        assertEquals("OK", result.getDetail());
        result = ms.addBook(books[1]);
        assertEquals("A book with the given ISBN is already present in the database.", result.getDetail());
        result = ms.addBook(books[2]);
        assertEquals("The ISBN is not valid.", result.getDetail());
        result = ms.addBook(books[3]);
        assertEquals("The author is not valid.", result.getDetail());
    }

    /** Diese Methode testet die Logic-Methode "getBooks". */
    @Test
    public void testGetBooks() {
        Medium[] test = new Medium[6];

        Medium tmp = new Book("A", "1", "978-3-123-192-9");
        test[0] = tmp;
        ms.addBook((Book) tmp);
        tmp = new Book("B", "2", "978-3-1234-192-9");
        test[1] = tmp;
        ms.addBook((Book) tmp);
        tmp = new Book("C", "3", "978-3-124-192-9");
        test[2] = tmp;
        ms.addBook((Book) tmp);
        tmp = new Book("D", "4", "978-3-134-192-9");
        test[3] = tmp;
        ms.addBook((Book) tmp);
        tmp = new Book("E", "5", "978-3-135-192-9");
        test[4] = tmp;
        ms.addBook((Book) tmp);
        tmp = new Book("F", "6", "978-3-145-192-9");
        test[5] = tmp;
        ms.addBook((Book) tmp);
        Medium[] test2 = ms.getBooks();
        assertArrayEquals(test2, test);
    }

    /** Diese Methode testet die Logic-Methode "getBook(String isbn)". */
    @Test
    public void testGetBook() {
        String author = "A";
        String title = "B";
        String isbn = "978-3-863210-192-9";
        Book book = new Book(author, title, isbn);
        ms.addBook(book);
        Book book2 = ms.getBook(isbn);
        assertEquals(book, book2);
    }

    /** Diese Methode testet die Logic-Methode "updateBook". */
    @Test
    public void testUpdateBook() {
        String isbn = "978-3-123-192-9";
        Book disc = new Book("A", "1", isbn);
        ms.addBook(disc);

        Book testBook = new Book("B", "12", "978-3-13-1292-9"); // No disc with
                                                                // this barcode
                                                                // exists
        MediaServiceResult result = ms.updateBook(testBook);
        assertEquals("No Book exists with the given ISBN. Modification of the Book aborted.", result.getDetail());

        testBook = new Book(null, "2", isbn); // no title
        result = ms.updateBook(testBook);
        assertEquals("The title is not valid.", result.getDetail());

        testBook = new Book("B", "2", isbn); // valid disc no error
        ms.updateBook(testBook);
        disc = ms.getBook(isbn);
        assertEquals(disc, testBook);
    }

    // =============================================DISC_TESTS_LOGIC===============================================

    /** Diese Methode testet die Logic-Methode "addDisc". */
    @Test
    public void testAddDisc() {
        String barcode = "123456789012";
        Disc disc = new Disc("A", "2", barcode, 0);
        ms.addDisc(disc);
        Disc disc2 = ms.getDisc(barcode);
        assertEquals(disc, disc2);
    }

    /** Diese Methode testet die Logic-Methode "getDiscs". */
    @Test
    public void testGetDiscs() {
        Medium[] test = new Medium[6];

        Medium tmp = new Disc("A", "1", "123456789012", 0);
        test[0] = tmp;
        ms.addDisc((Disc) tmp);
        tmp = new Disc("B", "2", "123456789019", 0);
        test[1] = tmp;
        ms.addDisc((Disc) tmp);
        tmp = new Disc("C", "3", "123456789011", 0);
        test[2] = tmp;
        ms.addDisc((Disc) tmp);
        tmp = new Disc("D", "4", "123456789018", 0);
        test[3] = tmp;
        ms.addDisc((Disc) tmp);
        tmp = new Disc("E", "5", "123456789013", 0);
        test[4] = tmp;
        ms.addDisc((Disc) tmp);
        tmp = new Disc("F", "6", "123456789014", 0);
        test[5] = tmp;
        ms.addDisc((Disc) tmp);
        Medium[] test2 = ms.getDiscs();
        assertArrayEquals(test2, test);
    }

    /** Diese Methode testet die Logic-Methode "getDisc". */
    @Test
    public void testGetDisc() {
        Disc disc = new Disc("F", "6", "123456789014", 0);
        ms.addDisc(disc);
        Disc disc2 = ms.getDisc("123456789014");
        assertEquals(disc, disc2);
    }

    /** Diese Methode testet die Logic-Methode "updateDisc". */
    @Test
    public void testUpdateDisc() {
        String barcode = "123456789012";
        Disc disc = new Disc("A", "1", barcode, 0);
        ms.addDisc(disc);

        Disc testDisc = new Disc("B", "2", "123456789000", 0); // No disc with
                                                               // this barcode
                                                               // exists
        MediaServiceResult result = ms.updateDisc(testDisc);
        assertEquals("No Disc exists with the given Barcode. Modification of the Disc aborted.", result.getDetail());

        testDisc = new Disc(null, "2", barcode, 0); // no title
        result = ms.updateDisc(testDisc);
        assertEquals("The title is not valid.", result.getDetail());

        testDisc = new Disc("B", "2", barcode, 0); // valid disc no error
        ms.updateDisc(testDisc);
        disc = ms.getDisc(barcode);
        assertEquals(disc, testDisc);
    }
}
