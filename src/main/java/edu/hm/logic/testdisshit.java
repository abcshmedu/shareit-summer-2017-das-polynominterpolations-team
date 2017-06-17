package edu.hm.logic;

import java.util.List;


import org.hibernate.*;

import edu.hm.data.Survey;
import edu.hm.storageoperations.HibernateUtil;

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
//	    hibernateUtil.executeSQLCommand("create table survey (id int,name varchar(5))");

	    Session session = hibernateUtil.getSession();

	    Survey survey = new Survey();
	    survey.setName("hans");
	    System.out.println(survey.getId()+ "0");
	    
	    session.save(survey);
	    session.flush();
	    
	    System.out.println(survey.getId() + "1");
	    Survey surveyInSession = (Survey) session.get(Survey.class, survey.getId());
	    System.out.println(surveyInSession.getName());

	    session.close();
	    hibernateUtil.checkData("select * from survey");
	}

}
