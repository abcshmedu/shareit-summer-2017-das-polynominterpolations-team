package edu.hm.storageoperations;

import java.util.HashMap;
import java.util.Map;

import edu.hm.data.Book;
import edu.hm.data.Disc;

public class MediaNonPersistanceImpl implements MediaPersistance{
    private Map<String, Book> bookStorage;
    
    private Map<String, Disc> discStorage;
    
    public MediaNonPersistanceImpl(){
	bookStorage = new HashMap<>();
	discStorage = new HashMap<>();
    }
    
    @Override
    public boolean save(Book a) {
	boolean returnValue = true;
	
	
	
	return false;
    }

    @Override
    public boolean save(Disc a) {
	return false;
    }

    @Override
    public boolean update(Book a) {
	return false;
    }

    @Override
    public boolean update(Disc a) {
	return false;
    }

    @Override
    public Book getBook(String isbn) {
	return null;
    }

    @Override
    public Disc getDisc(String barcode) {
	return null;
    }

    @Override
    public Book[] getAllBooks() {
	return null;
    }

    @Override
    public Disc[] getAllDiscs() {
	return null;
    }

}
