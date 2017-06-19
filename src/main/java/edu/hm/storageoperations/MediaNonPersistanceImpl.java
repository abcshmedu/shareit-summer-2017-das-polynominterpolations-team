package edu.hm.storageoperations;

import java.util.HashMap;
import java.util.Map;

import edu.hm.data.Book;
import edu.hm.data.Disc;

public class MediaNonPersistanceImpl implements MediaPersistance {
    private Map<String, Book> bookStorage;

    private Map<String, Disc> discStorage;

    public MediaNonPersistanceImpl() {
	bookStorage = new HashMap<>();
	discStorage = new HashMap<>();
    }

    @Override
    public boolean save(Book book) {
	boolean returnValue = false;

	// Falls das Buch noch nicht vorhanden ist hinzufügen
	if (!bookStorage.containsKey(book.getIsbn())) {
	    bookStorage.put(book.getIsbn(), book);
	    returnValue = true;
	}

	return returnValue;
    }

    @Override
    public boolean update(Book book) {
	boolean returnValue = false;

	// Falls das Buch schon vorhanden ist ändern
	if (bookStorage.containsKey(book.getIsbn())) {
	    bookStorage.put(book.getIsbn(), book);
	    returnValue = true;
	}

	return returnValue;
    }

    @Override
    public Book getBook(String isbn) {
	return bookStorage.get(isbn);
    }

    @Override
    public Book[] getAllBooks() {
	return bookStorage.entrySet().toArray(new Book[0]);
    }

    @Override
    public boolean save(Disc disc) {
	boolean returnValue = false;

	// Falls die Disc noch nicht vorhanden ist hinzufügen
	if (!discStorage.containsKey(disc.getBarcode())) {
	    discStorage.put(disc.getBarcode(), disc);
	    returnValue = true;
	}

	return returnValue;
    }

    @Override
    public boolean update(Disc disc) {
	boolean returnValue = false;

	// Falls die Disc schon vorhanden ist ändern
	if (discStorage.containsKey(disc.getBarcode())) {
	    discStorage.put(disc.getBarcode(), disc);
	    returnValue = true;
	}

	return returnValue;
    }

    @Override
    public Disc getDisc(String barcode) {
	return discStorage.get(barcode);
    }

    @Override
    public Disc[] getAllDiscs() {
	return discStorage.entrySet().toArray(new Disc[0]);
    }

}
