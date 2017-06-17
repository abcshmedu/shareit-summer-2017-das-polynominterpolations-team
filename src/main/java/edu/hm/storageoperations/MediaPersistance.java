package edu.hm.storageoperations;

import edu.hm.data.Book;
import edu.hm.data.Disc;

public interface MediaPersistance {
	boolean save(Book a);
	boolean save(Disc a);
	
	boolean update(Book a);
	boolean update(Disc a);
	
	Book get(Book a);
	Disc get(Disc a);
	
	Book[] getAllBooks();
	Disc[] getAllDiscs();
}
