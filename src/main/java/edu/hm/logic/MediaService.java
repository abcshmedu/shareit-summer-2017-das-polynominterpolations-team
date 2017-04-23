package edu.hm.logic;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;

public interface MediaService {
	public MediaServiceResult addBook(Book b);
	public MediaServiceResult addDisc(Disc d);
	public Medium[] getBooks();
	public Medium[] getDiscs();
	public MediaServiceResult updateBook(Book b);
	public MediaServiceResult updateDisc(Disc d);
}
