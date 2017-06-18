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

		Survey survey = new Survey();
		survey.setName("hans");
		System.out.println(survey.getId() + "0");

		session.save(survey);
		session.flush();

		System.out.println(survey.getId() + "1");
		Survey surveyInSession = (Survey) session.get(Survey.class, survey.getId());
		System.out.println(surveyInSession.getName());

		session.close();
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
		hibernateUtil.executeSQLCommand("UPDATE Book SET author = '" + a.getAuthor() + "' isbn = '" + a.getIsbn()
				+ "' title = '" + a.getTitle() + "' WHERE id = " + a.getId() + "; ");
		
		return false;
	}

	@Override
	public boolean update(Disc a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book get(String isbn) {
		Query query =session.createQuery("FROM Book b where b.isbn='" + isbn + "'");
		return (Book) query.uniqueResult();
	}

	@Override
	public Disc get(Disc a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book[] getAllBooks() {
		Query query = null;
		try {
			query=session.createQuery("select * from " + Book.class.getSimpleName());
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
		// TODO Auto-generated method stub
		return null;
	}

}
