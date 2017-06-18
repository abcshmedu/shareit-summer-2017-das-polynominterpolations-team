package edu.hm.logic;

import java.util.Arrays;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;
import edu.hm.storageoperations.MediaPersistance;
import edu.hm.storageoperations.MediaPersistanceImpl;

/** Dies ist unsere Implementierung der Geschäftslogik.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class MediaServiceImpl implements MediaService {
    /** Eine Referenz auf die genutzte Datenbank. */
    private MediaPersistance database;

    private static final int ISBN_PARTS = 5;
    private static final int BARCODE_LENGTH = 12;

    /** Default Ctor-für die MediaServiceImpl-Klasse. */
    public MediaServiceImpl() {
	database = new MediaPersistanceImpl();
    }

    @Override
    public MediaServiceResult addBook(final Book newBook, final String token) {
	MediaServiceResult result = null;

	if (hasAccessRights(token)) {
	    result = addBook(newBook);
	}
	else {
	    result = MediaServiceResult.TOKEN_INVALID;
	    result.setDetail("The Auth-Token is invalid.");
	}

	return result;
    }

    /** Diese Methode fügt ein neues Buch zur Datenbank hinzu.
     * 
     * @param book
     *            Das Buch, welches hinzugefügt werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    public MediaServiceResult addBook(final Book newBook) {
	MediaServiceResult result = null;

	//Testen ob das Buch okay ist
	if (result == null) {
	    result = testBookAttributes(newBook);
	}
	
	if(result == null){
	    result = testBookInStorage(newBook);
	}

	if (result == null) {
	    database.save(newBook);
	    result = MediaServiceResult.OK;
	    result.setDetail("OK");
	}
	return result;
    }

    @Override
    public MediaServiceResult updateBook(final Book book, final String token) {
	MediaServiceResult result = null;

	if (hasAccessRights(token)) {
	    result = updateBook(book);
	}
	else {
	    result = MediaServiceResult.TOKEN_INVALID;
	    result.setDetail("The Auth-Token is invalid.");
	}

	return result;
    }

    /** Diese Methode updatet ein Buch, welches bereits in der Datenbank
     * vorhanden ist.
     * 
     * @param book
     *            Das Buch welches geupdatet werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    public MediaServiceResult updateBook(final Book book) {
	MediaServiceResult result = null;

	if (result == null)
	    result = testBookAttributes(book);

	// Falls kein Test fehlgeschlagen hat das Buch speichern
	if (result == null) {
	    database.update(book);
	    result = MediaServiceResult.OK;
	    result.setDetail("OK");
	}
	return result;
    }

    @Override
    public Medium[] getBooks() {
	return database.getAllBooks();
    }

    @Override
    public Book getBook(String isbn) {
	Book book;

	if (!testISBN(isbn)) {
	    return null;
	}

	book = database.getBook(isbn);
	System.out.println("MediaServiceImpl.getBook: book = " + book);

	return book;
    }
    
    private MediaServiceResult testBookInStorage(final Book book){
	MediaServiceResult result = null;
	
	if(database.getBook(book.getIsbn()) != null){
	    result = MediaServiceResult.CONFLICT;
	    result.setDetail("Book with this ISBN already exists.");
	}
	
	return result;
    }

    /** Diese Methode testet, ob die Attribute eines Buches korrekt sind.
     * 
     * @param book
     *            Das zu testende Buch
     * @return MediaServiceResult mit Details des Tests */
    private MediaServiceResult testBookAttributes(final Book book) {
	MediaServiceResult result = null;

	if (book.getIsbn() == null) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The Book does not have an ISBN.");
	}
	else if (!testISBN(book.getIsbn())) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The ISBN is not valid.");
	}
	else if (book.getAuthor() == null || book.getAuthor().equals("")) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The author is not valid.");
	}
	else if (book.getTitle() == null || book.getTitle().equals("")) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The title is not valid.");
	}

	return result;
    }

    /** Diese Methode testet eine übergebene ISBN auf ihre Richtigkeit.
     * 
     * @param isbn
     *            Die ISBN, die getestet werden soll
     * @return true falls die ISBN richtig ist */
    private boolean testISBN(final String isbn) {
	boolean isbnIsCorrect = true;
	if (isbn.contains("-")) {
	    String[] isbnParts = isbn.split("-");

	    if (isbnParts.length != ISBN_PARTS) {
		isbnIsCorrect = false;
	    }
	    else if (!(isbnParts[0].equals("978") || isbnParts[0].equals("979"))) {
		isbnIsCorrect = false;
	    }
	    else if (isbnParts[isbnParts.length - 1].length() != 1) {
		isbnIsCorrect = false;
	    }

	    for (String part : isbnParts) {
		try {
		    Integer.parseInt(part);
		}
		catch (NumberFormatException e) {
		    isbnIsCorrect = false;
		    break;
		}
		catch (NullPointerException e) {
		    System.out.println("MediaServiceImpl.testISBN: NullPointerException");
		    isbnIsCorrect = false;
		    break;
		}
	    }
	    System.out.println("MediaServiceImpl.testISBN: isbnParts = " + Arrays.toString(isbnParts));
	}
	else if (!(isbn.matches("[0-9]+") && isbn.length() > 3))
	    isbnIsCorrect = false;

	return isbnIsCorrect;
    }

    /** Diese Methode testet ob ein Barcode korrekt ist.
     * 
     * @param barcode
     *            Der zu testende Barcode
     * @return true falls der Barcode korrekt ist, false falls nicht */
    private boolean testBarcode(final String barcode) {
	boolean isValid = true;
	if (barcode.length() != BARCODE_LENGTH) {
	    isValid = false;
	}
	return isValid;
    }

    // ====================================================ALL DISC
    // STUFF========================================================================================================

    @Override
    public MediaServiceResult addDisc(final Disc newDisc, final String token) {
	MediaServiceResult result = null;

	if (hasAccessRights(token)) {
	    result = addDisc(newDisc);
	}
	else {
	    result = MediaServiceResult.TOKEN_INVALID;
	    result.setDetail("The Auth-Token is invalid.");
	}

	return result;
    }

    /** Diese Methode fügt eine neue Disc zur Datenbank hinzu.
     * 
     * @param disc
     *            Die Disc, die hinzugefügt werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    public MediaServiceResult addDisc(final Disc newDisc) {
	MediaServiceResult result = null;

	if (result == null) {
	    result = testDisc(newDisc);
	}

	if (result == null) {
	    database.save(newDisc);
	    result = MediaServiceResult.OK;
	    result.setDetail("OK");
	}
	return result;
    }

    @Override
    public MediaServiceResult updateDisc(final Disc disc, final String token) {
	MediaServiceResult result = null;

	if (hasAccessRights(token)) {
	    result = updateDisc(disc);
	}
	else {
	    result = MediaServiceResult.TOKEN_INVALID;
	    result.setDetail("The Auth-Token is invalid.");
	}

	return result;
    }

    /** Diese Methode updatet eine Disc, welche bereits in der Datenbank
     * vorhanden ist.
     * 
     * @param disc
     *            Die Disc welche geupdatet werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    public MediaServiceResult updateDisc(final Disc disc) {
	MediaServiceResult result = null;

	if (result == null) {
	    result = testDisc(disc);
	}

	if (result == null) {
	    database.update(disc);
	    result = MediaServiceResult.OK;
	    result.setDetail("OK");
	}

	return result;
    }

    @Override
    public Medium[] getDiscs() {
	return database.getAllDiscs();
    }

    @Override
    public Disc getDisc(String barcode) {
	Disc disc;

	if (!testBarcode(barcode)) {
	    return null;
	}

	disc = database.getDisc(barcode);

	System.out.println("MediaServiceImpl.getDisc: disc = " + disc);
	return disc;
    }

    /** Diese Methode testet die Attribute einer Disc auf deren Richtigkeit.
     * 
     * @param disc
     *            Die zu testende Disc
     * @return MediaServiceResult mit den Details des Tests */
    private MediaServiceResult testDisc(final Disc disc) {
	MediaServiceResult result = null;

	if (disc.getBarcode() == null) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The Disc does not have Barcode.");
	}
	else if (!testBarcode(disc.getBarcode())) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The Barcode is not valid.");
	}
	else if (disc.getDirector() == null || disc.getDirector().equals("")) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The director is not valid.");
	}
	else if (disc.getTitle() == null || disc.getTitle().equals("")) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The title is not valid.");
	}
	else if (disc.getFsk() < 0) {
	    result = MediaServiceResult.FAIL;
	    result.setDetail("The FSK is smaller than zero.");
	}

	return result;
    }

    // ==============================================================================================================
    /** Diese Methode testet, ob ein Token gültig ist.
     * 
     * @param token
     *            Das zu testende Token
     * @return True, falls das Token gültig ist; False, falls nicht */
    private boolean hasAccessRights(final String token) {
	TokenTester tokenTester = new TokenTester();

	return tokenTester.testToken(token) == MediaServiceResult.TOKEN_VALID;
    }

}
