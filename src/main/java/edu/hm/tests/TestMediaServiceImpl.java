package edu.hm.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hm.data.Book;
import edu.hm.logic.MediaService;
import edu.hm.logic.MediaServiceImpl;

public class TestMediaServiceImpl {
	MediaService ms;
	
	@Before
	public void setup(){
		ms = new MediaServiceImpl();
		ms.addBook(new Book("author", "title", "1337"));
	}
	
	@Test
	public void testAddBook(){
		String author = "A";
		String title = "B";
		String isbn = "1";
		Book book = new Book(author, title, isbn);
		ms.addBook(book);
		Book book2 = ms.getBook(isbn);
		assertEquals(book, book2);
	}

	@Test
	public void testGetBooks(){
		
	}
	
	@Test
	public void testGetBook(){
		
	}
	
	
	@Test
	public void testAddDisc(){
		
	}
	
	@Test
	public void testGetDiscs(){
		
	}
	
	@Test
	public void testGetDisc(){
		
	}
	
	@Test
	public void testUpdateBook(){
		
	}
	
	@Test
	public void testUpdateDisc(){

	}
}
