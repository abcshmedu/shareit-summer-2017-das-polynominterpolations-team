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
public class TestData {

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

}
