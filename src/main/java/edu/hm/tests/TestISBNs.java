package edu.hm.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.hm.data.Book;
import edu.hm.logic.MediaServiceImpl;

public class TestISBNs {
    MediaServiceImpl ms;
    
    @Before
    public void setup(){
	ms = new MediaServiceImpl();
    }
    
    
    @Test
    public void testCorrectISBNwithDash() {
	String correctISBN = "978-1-12345-123-1";
	assertEquals(true, ms.testISBN(correctISBN));
    }
    
    @Test
    public void testCorrectISBNwithoutDash() {
	String correctISBN = "9781123451231";
	assertEquals(true, ms.testISBN(correctISBN));
    }
    
    @Test
    public void testCorrectISBNwithAndwithoutDash() {
	String correctISBN = "978-1-12345-123-1";
	String correctISBN2 = "9781123451231";
	
	Book book = new Book("Titel", "Author", correctISBN);
	Book book2 = new Book("Titel", "Author", correctISBN2);
	Book testBook;
	
	ms.addBook(book);
	testBook = ms.getBook(correctISBN2);
	
	assertEquals(book2, testBook);
    }

}
