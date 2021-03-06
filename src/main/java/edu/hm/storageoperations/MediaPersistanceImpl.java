package edu.hm.storageoperations;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.hm.data.Book;
import edu.hm.data.Disc;
import edu.hm.data.Survey;

public class MediaPersistanceImpl implements MediaPersistance {
	Session session;
	HibernateUtil hibernateUtil;

	public MediaPersistanceImpl() {
		hibernateUtil = new HibernateUtil();

		session = hibernateUtil.getSession();
	}

	@Override
	public boolean save(Book a) {
		if(a.getIsbn().contains("-")){
			a = new Book(a.getTitle(), a.getAuthor(), a.getIsbn().replaceAll("-", ""));
		}
		session.save(a);
		session.flush();
		return true;
	}

	@Override
	public boolean save(Disc a) {
		session.save(a);
		session.flush();
		return true;
	}

	@Override
	public boolean update(Book a) {
		String bookIsbn = normalizeIsbn(a.getIsbn());
		if(this.getBook(bookIsbn) != null){
			Query query = session.createQuery("delete from Book where isbn = '" + bookIsbn +"' ");
			query.executeUpdate();
			save(a);
		}
		return true;
	}

	@Override
	public boolean update(Disc a) {
		if(this.getDisc(a.getBarcode()) != null){
			Query query = session.createQuery("delete from Disc where barcode = '" + a.getBarcode()+"' ");
			query.executeUpdate();
			save(a);
		}
		return true;
	}

	@Override
	public Book getBook(String isbn) {
		Query query = session.createQuery("FROM Book b where b.isbn='" + normalizeIsbn(isbn) + "'");
		return (Book) query.uniqueResult();
	}

	@Override
	public Disc getDisc(String barcode) {
		Query query = session.createQuery("FROM Disc b where b.barcode='" + barcode + "'");
		return (Disc) query.uniqueResult();
	}

	@Override
	public Book[] getAllBooks() {
		Query query = null;
		try {
			query = session.createQuery("from Book");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Book> list = (List<Book>) query.list();
		Book[] books = list.toArray(new Book[list.size()]);
		return books;
	}

	@Override
	public Disc[] getAllDiscs() {
		Query query = null;
		try {
			query = session.createQuery("from Disc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Disc> list = (List<Disc>) query.list();
		Disc[] discs = list.toArray(new Disc[list.size()]);
		return discs;
	}

	private String normalizeIsbn(String isbn){
		if(isbn.contains("-")){
			isbn = isbn.replaceAll("-", "");
		}
		return isbn;
	}
	
}
