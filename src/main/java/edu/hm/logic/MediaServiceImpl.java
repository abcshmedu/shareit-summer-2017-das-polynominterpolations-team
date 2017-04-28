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
	List<Medium> bookStorage;
	
	/** Diese Liste enthält alle aktuell gespeicherten Discs. */
	List<Medium> discStorage;
	
	/** Diese Liste enthält die ISBNs aller aktuell gespeicherten Bücher. */
	List<String> allISBNs;
	
	/** Diese Liste enthält alle Barcodes aller aktuell gespeicherten Disc. */
	List<String> allBarcodes;

	public MediaServiceImpl(){
		bookStorage = new ArrayList<>();
		discStorage = new ArrayList<>();
		allISBNs = new ArrayList<>();
		allBarcodes = new ArrayList<>();
	}
	
	@Override
	public MediaServiceResult addBook(Book newBook) {
		String newBookISBN = newBook.getIsbn();
		MediaServiceResult result = MediaServiceResult.FAIL;
		
		if(newBookISBN == null){
			result.setDetail("The Book does not have an ISBN.");
			return result;}
		else if(!testIsbnAndBarcode(newBookISBN)){
			result.setDetail("The ISBN is not valid.");
			return result;}
		else if(allISBNs.contains(newBookISBN)){
			result.setDetail("A book with the given ISBN is already present in the database.");
			return result;}
		else if(newBook.getAuthor() == null || newBook.getAuthor().equals("")){
			result.setDetail("The author is not valid.");
			return result;}
		else if(newBook.getTitle() == null || newBook.getTitle().equals("")){
			result.setDetail("The title is not valid.");
			return result;}
		
		bookStorage.add(newBook);
		allISBNs.add(newBookISBN);
		
		System.out.println("MediaServiceImpl.addBook: bookStorage.size() = " + bookStorage.size());
		
		result = MediaServiceResult.OK;
		result.setDetail("OK");
		return result;
	}

	@Override
	public Medium[] getBooks() {
		return  bookStorage.toArray(new Medium[0]);
	}

	@Override
	public MediaServiceResult updateBook(Book book) {
		if(!bookStorage.contains(book))
			return MediaServiceResult.FAIL;
			
		bookStorage.remove(bookStorage.indexOf(book));
		bookStorage.add(book);
		
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
		System.out.println("MediaServiceImpl.getBook: book = " + book);
		
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
