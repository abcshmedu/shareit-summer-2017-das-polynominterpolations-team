package edu.hm.logic;

import java.util.ArrayList;
import java.util.List;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;

/**
 * Dies ist unsere Implementierung der Geschäftslogik.
 * @author Sebastian Becker
 * @author Peter Straßer
 *
 */
public class MediaServiceImpl implements MediaService{
	/** Diese Liste enthält alle aktuell gespeicherten Bücher. */
	List<Medium> bookStorage = new ArrayList<>();
	
	/** Diese Liste enthält alle aktuell gespeicherten Discs. */
	List<Medium> discStorage = new ArrayList<>();
	
	/** Diese Liste enthält die ISBNs aller aktuell gespeicherten Bücher. */
	List<String> allISBNs = new ArrayList<String>();
	
	/** Diese Liste enthält alle Barcodes aller aktuell gespeicherten Disc. */
	List<String> allBarcodes = new ArrayList<String>();

	@Override
	public MediaServiceResult addBook(Book newBook) {
		String newBookISBN = newBook.getIsbn();
		
		if(newBookISBN == null)
			return MediaServiceResult.FAIL;
		else if(!testIsbnAndBarcode(newBookISBN))
			return MediaServiceResult.FAIL;
		else if(allISBNs.contains(newBookISBN))
			return MediaServiceResult.FAIL;
		else if(newBook.getAuthor() == null || newBook.getAuthor().equals(""))
			return MediaServiceResult.FAIL;
		else if(newBook.getTitle() == null || newBook.getTitle().equals(""))
			return MediaServiceResult.FAIL;
		
		bookStorage.add(newBook);
		allISBNs.add(newBookISBN);
		
		System.out.println(bookStorage.size());
		
		return MediaServiceResult.OK;
	}

	@Override
	public Medium[] getBooks() {
		return  bookStorage.toArray(new Medium[1]);
	}

	@Override
	public MediaServiceResult updateBook(Book book) {
		if(!bookStorage.contains(book))
			return MediaServiceResult.FAIL;
			
		bookStorage.get(bookStorage.indexOf(book));
		bookStorage.remove(bookStorage.indexOf(book));
		
		return MediaServiceResult.OK;
	}

	@Override
	public Book getBook(String isbn){
		Medium[] storedBooks;
		Book book;
		
		if(!testIsbnAndBarcode(isbn))
			return null;
		
		storedBooks =  getBooks();
		book = null;
		//System.out.println("MediaServiceImpl.getBook: vor loop");
		for(Medium mBook : storedBooks){
			Book currentBook = (Book) mBook;
			if(currentBook != null && currentBook.getIsbn().equals(isbn)){
				book = currentBook;
			}
		}
		System.out.println(book);
		
		return book;
	}
	
	/**
	 * Diese Methode testet, ob eine ISBN oder ein Barcode gültig ist.
	 * @param code Die zu testende ISBN oder der zu testende Barcode
	 * @return Liefert true zurück, falls die/der getestete ISBN/Barcode gültig ist. Fals andernfalls.
	 */
	private boolean testIsbnAndBarcode(String code){
		//System.out.println("MediaServiceImpl.testIsbnAndBarcode: code: " + code);
		boolean isCorrect = true;
		for(char car : code.toCharArray()){
			isCorrect &= Character.isDigit(car);
		}
		return isCorrect;
	}


	@Override
	public MediaServiceResult addDisc(Disc newDisc) {
		String newDiscBarcode = newDisc.getBarcode();
		
		if(newDiscBarcode == null)
			return MediaServiceResult.FAIL;
		else if(!testIsbnAndBarcode(newDiscBarcode))
			return MediaServiceResult.FAIL;
		else if(allBarcodes.contains(newDiscBarcode))
			return MediaServiceResult.FAIL;
		else if(newDisc.getDirector() == null || newDisc.getDirector().equals(""))
			return MediaServiceResult.FAIL;
		else if(newDisc.getTitle() == null || newDisc.getTitle().equals(""))
			return MediaServiceResult.FAIL;
		else if(newDisc.getFsk() < 0)
			return MediaServiceResult.FAIL;
		
		discStorage.add(newDisc);
		allBarcodes.add(newDisc.getBarcode());
		
		return MediaServiceResult.OK;
	}
	
	@Override
	public Medium[] getDiscs() {
		return discStorage.toArray(new Medium[1]);
	}

	
	@Override
	public MediaServiceResult updateDisc(Disc disc) {
		if(!discStorage.contains(disc))
			return MediaServiceResult.FAIL;
			
		discStorage.get(discStorage.indexOf(disc));
		discStorage.remove(discStorage.indexOf(disc));
		
		return MediaServiceResult.OK;
	}
	
	@Override
	public Disc getDisc(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}
}
