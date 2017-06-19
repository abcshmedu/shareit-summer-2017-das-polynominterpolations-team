package edu.hm.storageoperations;

import edu.hm.data.Book;
import edu.hm.data.Disc;

public interface MediaPersistance {
    boolean save(Book book);
    boolean update(Book book);
    Book getBook(String isbn);
    Book[] getAllBooks();
    
    boolean save(Disc disc);
    boolean update(Disc disc);
    Disc getDisc(String barcode);
    Disc[] getAllDiscs();
}
