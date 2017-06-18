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
	hibernateUtil.executeSQLCommand("UPDATE Book SET author = '" + a.getAuthor() + "' title = '" + a.getTitle() + "' WHERE isbn = " + a.getIsbn() + "; ");
	return true;
    }

    @Override
    public boolean update(Disc a) {
	hibernateUtil.executeSQLCommand("UPDATE Book SET director = '" + a.getDirector() + "' title = '" + a.getTitle() + "' WHERE barcode = " + a.getBarcode() + "; ");
	return false;
    }

    @Override
    public Book getBook(String isbn) {
	Query query = session.createQuery("FROM Book b where b.isbn='" + isbn + "'");
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
	    query = session.createQuery("select * from " + Book.class.getSimpleName());
	}
	catch (Exception e) {
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
	    query = session.createQuery("select * from " + Disc.class.getSimpleName());
	}
	catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	List<Disc> list = (List<Disc>) query.list();
	Disc[] discs = list.toArray(new Disc[list.size()]);
	return discs;
    }

}
