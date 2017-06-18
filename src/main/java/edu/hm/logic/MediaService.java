package edu.hm.logic;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;

/** Dies ist unser Interface für die Geschäftslogik.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public interface MediaService {
    MediaServiceResult addBook(Book book, String token);
    MediaServiceResult updateBook(Book book, String token);

    /** Diese Methode liefert das Buch mit der vorgegebenen ISBN zurück (sollte
     * es gespeichert sein).
     * 
     * @param isbn
     *            Die ISBN des zu suchenden Buches
     * @return Liefert das Buch mit der vorgegebenen ISBN zurück oder null,
     *         falls ein Buch mit dieser ISBN nicht vorhanden ist */
    Book getBook(String isbn);

    /** Diese Methode liefert alle aktuell gespeicherten Bücher zurück.
     * 
     * @return Book[] mit allen Büchern der Datenbank */
    Medium[] getBooks();

    MediaServiceResult addDisc(Disc disc, String token);
    MediaServiceResult updateDisc(Disc disc, String token);

    /** Diese Methode liefert die Disc mit dem vorgegebenen Barcode zurück
     * (sollte sie gespeichert sein).
     * 
     * @param barcode
     *            Der Barcode der zu suchenden Disc
     * @return Liefert die Disc mit dem vorgegebenen Barcode zurück oder null,
     *         falls eine Disc mit diesem Barcode nicht vorhanden ist */
    Disc getDisc(String barcode);

    /** Diese Methode liefert alle aktuell gespeicherten Discs zurück.
     * 
     * @return Disc[] mit allen Discs der Datenbank */
    Medium[] getDiscs();
}
