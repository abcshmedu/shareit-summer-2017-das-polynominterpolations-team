package edu.hm.logic;

import java.util.ArrayList;
import java.util.List;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Medium;

public class MediaServiceImpl implements MediaService{
	List<Medium> bookStorage = new ArrayList<>();
	
	List<Medium> discStorage = new ArrayList<>();
	
	List<String> allISBNs = new ArrayList<String>();
	
	List<String> allBarcodes = new ArrayList<String>();

	@Override
	public MediaServiceResult addBook(Book newBook) {
		String newBookISBN = newBook.getIsbn();
		boolean isbnIsCorrect = true;
		
		if(newBookISBN == null)
			return MediaServiceResult.FAIL;
		
		for(char car : newBookISBN.toCharArray())
			isbnIsCorrect &= Character.isDigit(car);
		
		if(isbnIsCorrect == false)
			return MediaServiceResult.FAIL;
		else if(allISBNs.contains(newBookISBN))
			return MediaServiceResult.FAIL;
		else if(newBook.getAuthor() == null || newBook.getAuthor().equals(""))
			return MediaServiceResult.FAIL;
		else if(newBook.getTitle() == null || newBook.getTitle().equals(""))
			return MediaServiceResult.FAIL;
		
		bookStorage.add(newBook);
		allISBNs.add(newBookISBN);
		
		return MediaServiceResult.OK;
	}

	@Override
	public MediaServiceResult addDisc(Disc newDisc) {
		String newDiscBarcode = newDisc.getBarcode();
		boolean barcodeIsCorrect = true;
		
		if(newDiscBarcode == null)
			return MediaServiceResult.FAIL;
			
		for(char car : newDiscBarcode.toCharArray())
			barcodeIsCorrect &= Character.isDigit(car);
		
		if(barcodeIsCorrect == false)
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
	public Medium[] getBooks() {
		return (Medium[]) bookStorage.toArray();
	}

	@Override
	public Medium[] getDiscs() {
		return (Medium[]) discStorage.toArray();
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
	public MediaServiceResult updateDisc(Disc disc) {
		if(!discStorage.contains(disc))
			return MediaServiceResult.FAIL;
			
		discStorage.get(discStorage.indexOf(disc));
		discStorage.remove(discStorage.indexOf(disc));
		
		return MediaServiceResult.OK;
	}

}
