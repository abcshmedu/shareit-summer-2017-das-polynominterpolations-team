package edu.hm.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;

/** Dies ist unsere Implementierung der Geschäftslogik.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public class MediaServiceImpl implements MediaService {
    /** Diese Liste enthält alle aktuell gespeicherten Bücher. */
    private List<Book> bookStorage;

    /** Diese Liste enthält alle aktuell gespeicherten Discs. */
    private List<Disc> discStorage;

    /** Diese Liste enthält die ISBNs aller aktuell gespeicherten Bücher. */
    private List<String> allISBNs;

    /** Diese Liste enthält alle Barcodes aller aktuell gespeicherten Disc. */
    private List<String> allBarcodes;

    private static final int ISBN_PARTS = 5;
    private static final int BARCODE_LENGTH = 12;

    /** Default Ctor-für die MediaServiceImpl-Klasse. */
    public MediaServiceImpl() {
        bookStorage = new ArrayList<>();
        discStorage = new ArrayList<>();
        allISBNs = new ArrayList<>();
        allBarcodes = new ArrayList<>();
    }

    @Override
    public MediaServiceResult addBook(Book newBook) {
        String newBookISBN = newBook.getIsbn();
        MediaServiceResult result = MediaServiceResult.FAIL;

        if (newBookISBN == null) {
            result.setDetail("The Book does not have an ISBN.");
            return result;
        } else if (!testISBN(newBookISBN)) {
            result.setDetail("The ISBN is not valid.");
            return result;
        } else if (allISBNs.contains(newBookISBN)) {
            result.setDetail("A book with the given ISBN is already present in the database.");
            return result;
        } else if (newBook.getAuthor() == null || newBook.getAuthor().equals("")) {
            result.setDetail("The author is not valid.");
            return result;
        } else if (newBook.getTitle() == null || newBook.getTitle().equals("")) {
            result.setDetail("The title is not valid.");
            return result;
        }

        bookStorage.add(newBook);
        allISBNs.add(newBookISBN);

        System.out.println("MediaServiceImpl.addBook: bookStorage.size() = " + bookStorage.size());

        result = MediaServiceResult.OK;
        result.setDetail("OK");
        return result;
    }

    @Override
    public Medium[] getBooks() {
        return bookStorage.toArray(new Book[0]);
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        MediaServiceResult result = MediaServiceResult.FAIL;
        String isbn = book.getIsbn();
        Book bookToReplace = getBookByISBN(isbn);

        if (bookToReplace == null) {
            result.setDetail("No Book exists with the given ISBN. Modification of the Book aborted.");
            return result;
        } else if (book.getAuthor() == null || book.getAuthor().equals("")) {
            result.setDetail("The author is not valid.");
            return result;
        } else if (book.getTitle() == null || book.getTitle().equals("")) {
            result.setDetail("The title is not valid.");
            return result;
        }

        bookStorage.remove(bookToReplace);
        bookStorage.add(book);
        result = MediaServiceResult.OK;
        result.setDetail("OK");

        return result;
    }

    @Override
    public Book getBook(String isbn) {
        Medium[] storedBooks;
        Book book;

        if (!testISBN(isbn)) {
            return null;
        }

        storedBooks = getBooks();
        book = null;
        // System.out.println("MediaServiceImpl.getBook: vor loop");
        for (Medium mBook : storedBooks) {
            Book currentBook = (Book) mBook;
            if (currentBook != null && currentBook.getIsbn().equals(isbn)) {
                book = currentBook;
            }
        }
        System.out.println("MediaServiceImpl.getBook: book = " + book);

        return book;
    }

    // ====================================================HELPER
    // METHODS========================================================================================================
    /** Diese Methode sucht ein Book mit der gegeben ISBN aus dem Speicher.
     * 
     * @param isbn
     *        Die ISBN des gesuchten Book
     * @return Liefert das Buch mit der gesuchten ISBN zurück, oder null */
    private Book getBookByISBN(String isbn) {
        Book book = null;

        for (Book currentBook : bookStorage) {
            if (currentBook.getIsbn().equals(isbn)) {
                book = currentBook;
            }
        }

        return book;
    }

    /** Diese Methode testet eine übergebene ISBN auf ihre Richtigkeit.
     * 
     * @param isbn
     *        Die ISBN, die getestet werden soll
     * @return true falls die ISBN richtig ist */
    private boolean testISBN(final String isbn) {
        boolean isbnIsCorrect = true;
        String[] isbnParts = isbn.split("-");

        if (isbnParts.length != ISBN_PARTS) {
            isbnIsCorrect = false;
        } else if (!(isbnParts[0].equals("978") || isbnParts[0].equals("979"))) {
            isbnIsCorrect = false;
        } else if (isbnParts[isbnParts.length - 1].length() != 1) {
            isbnIsCorrect = false;
        }

        for (String part : isbnParts) {
            try {
                Integer.parseInt(part);
            } catch (NumberFormatException e) {
                isbnIsCorrect = false;
                break;
            } catch (NullPointerException e) {
                System.out.println("MediaServiceImpl.testISBN: NullPointerException");
                isbnIsCorrect = false;
                break;
            }
        }
        System.out.println("MediaServiceImpl.testISBN: isbnParts = " + Arrays.toString(isbnParts));

        return isbnIsCorrect;
    }

    /** Diese Methode testet, ob eine ISBN oder ein Barcode gültig ist.
     * 
     * @param barcode
     *        Die zu testende ISBN oder der zu testende Barcode
     * @return Liefert true zurück, falls die/der getestete ISBN/Barcode gültig
     *         ist. Fals andernfalls. */

    private Disc getDiscByBarcode(String barcode) {
        Disc disc = null;

        for (Disc currentDisc : discStorage) {
            if (currentDisc.getBarcode().equals(barcode)) {
                disc = currentDisc;
            }
        }

        return disc;
    }

    /** Diese Methode testet ob ein Barcode korrekt ist.
     * 
     * @param barcode
     *        Der zu testende Barcode
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
    public MediaServiceResult addDisc(Disc newDisc) {
        String newDiscBarcode = newDisc.getBarcode();
        MediaServiceResult result = MediaServiceResult.FAIL;

        if (newDiscBarcode == null) {
            result.setDetail("The Disc does not have Barcode.");
            return result;
        } else if (!testBarcode(newDiscBarcode)) {
            result.setDetail("The Barcode is not valid.");
            return result;
        } else if (allBarcodes.contains(newDiscBarcode)) {
            result.setDetail("A Disc with the given Barcode is already present in the database.");
            return result;
        } else if (newDisc.getDirector() == null || newDisc.getDirector().equals("")) {
            result.setDetail("The director is not valid.");
            return result;
        } else if (newDisc.getTitle() == null || newDisc.getTitle().equals("")) {
            result.setDetail("The title is not valid.");
            return result;
        }

        discStorage.add(newDisc);
        allBarcodes.add(newDiscBarcode);

        System.out.println("MediaServiceImpl.addDisc: discStorage.size() = " + discStorage.size());

        result = MediaServiceResult.OK;
        result.setDetail("OK");
        return result;
    }

    @Override
    public Medium[] getDiscs() {
        return discStorage.toArray(new Disc[0]);
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        MediaServiceResult result = MediaServiceResult.FAIL;
        String barcode = disc.getBarcode();
        Disc discToReplace = getDiscByBarcode(barcode);

        if (discToReplace == null) {
            result.setDetail("No Disc exists with the given Barcode. Modification of the Disc aborted.");
            return result;
        } else if (disc.getDirector() == null || disc.getDirector().equals("")) {
            result.setDetail("The director is not valid.");
            return result;
        } else if (disc.getTitle() == null || disc.getTitle().equals("")) {
            result.setDetail("The title is not valid.");
            return result;
        }

        discStorage.remove(discToReplace);
        discStorage.add(disc);
        result = MediaServiceResult.OK;
        result.setDetail("OK");

        return result;
    }

    @Override
    public Disc getDisc(String barcode) {
        Disc disc;

        if (!testBarcode(barcode)) {
            return null;
        }

        disc = getDiscByBarcode(barcode);

        System.out.println("MediaServiceImpl.getDisc: disc = " + disc);
        return disc;
    }
}
