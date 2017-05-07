package edu.hm.logic;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;

/** Dies ist unser Interface für die Geschäftslogik.
 * 
 * @author Sebastian Becker
 * @author Peter Straßer */
public interface MediaService {
    /** Diese Methode fügt ein neues Buch zur Datenbank hinzu.
     * 
     * @param book
     *        Das Buch, welches hinzugefügt werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    MediaServiceResult addBook(Book book);

    /** Diese Methode fügt eine neue Disc zur Datenbank hinzu.
     * 
     * @param disc
     *        Die Disc, die hinzugefügt werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    MediaServiceResult addDisc(Disc disc);

    /** Diese Methode liefert alle aktuell gespeicherten Bücher zurück.
     * 
     * @return Book[] mit allen Büchern der Datenbank */
    Medium[] getBooks();

    /** Diese Methode liefert alle aktuell gespeicherten Discs zurück.
     * 
     * @return Disc[] mit allen Discs der Datenbank */
    Medium[] getDiscs();

    /** Diese Methode liefert das Buch mit der vorgegebenen ISBN zurück (sollte
     * es gespeichert sein).
     * 
     * @param isbn
     *        Die ISBN des zu suchenden Buches
     * @return Liefert das Buch mit der vorgegebenen ISBN zurück oder null,
     *         falls ein Buch mit dieser ISBN nicht vorhanden ist */
    Book getBook(String isbn);

    /** Diese Methode liefert die Disc mit dem vorgegebenen Barcode zurück
     * (sollte sie gespeichert sein).
     * 
     * @param barcode
     *        Der Barcode der zu suchenden Disc
     * @return Liefert die Disc mit dem vorgegebenen Barcode zurück oder null,
     *         falls eine Disc mit diesem Barcode nicht vorhanden ist */
    Disc getDisc(String barcode);

    /** Diese Methode updatet ein Buch, welches bereits in der Datenbank
     * vorhanden ist.
     * 
     * @param book
     *        Das Buch welches geupdatet werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    MediaServiceResult updateBook(Book book);

    /** Diese Methode updatet eine Disc, welche bereits in der Datenbank
     * vorhanden ist.
     * 
     * @param disc
     *        Die Disc welche geupdatet werden soll
     * @return Liefert ein MediaServiceResult-Objekt zurück, welches zusätzliche
     *         Information enthält */
    MediaServiceResult updateDisc(Disc disc);
}
