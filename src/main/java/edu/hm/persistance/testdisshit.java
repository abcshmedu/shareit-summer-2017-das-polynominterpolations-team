package edu.hm.persistance;

import java.util.List;


import org.hibernate.*;

public class testdisshit {

	public static void main(String[] args) throws Exception {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		  
//        session.beginTransaction();
// 
//        TBook testBook = new TBook("A", "A", "978-1-11111-111-1");
//        session.save(testBook);
//        testBook = new TBook("look at my pussy", "dei muadda", "978-1-11111-111-2");
//        session.save(testBook);
//        session.getTransaction().commit();
// 
//        List<TBook> resultList = session.createCriteria(TBook.class).list();
//        
//        System.out.println("num of books:" + resultList.size());
//        for( TBook book : resultList) {
//            System.out.println(" Title: " + book.getTitle() + "Author: " + book.getAuthor() + "ISBN like any1 cares: " + book.getIsbn());
//        }
		
	    HibernateUtil hibernateUtil = new HibernateUtil();
	    hibernateUtil.executeSQLCommand("create table survey (id int,name varchar);");

	    Session session = hibernateUtil.getSession();

	    TBook testBook = new TBook("A", "A", "978-1-11111-111-1");

	    System.out.println(testBook.getId());
	    
	    session.save(testBook);
	    session.flush();
	    
	    System.out.println(testBook.getId());
	    TBook surveyInSession = (TBook) session.get(TBook.class, testBook.getId());
	    System.out.println(surveyInSession.getTitle());

	    session.close();
	}

}
