package edu.hm.storageoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    Session session;
    Statement st;

    public HibernateUtil() {
	SessionFactory sessionFactory = new Configuration().configure("/resource/hibernate.cfg.xml").buildSessionFactory();
	session = sessionFactory.openSession();
	try {
	    // Load the JDBC driver.
	    Class.forName("org.hsqldb.jdbcDriver");
	    System.out.println("Driver Loaded.");
	}
	catch (ClassNotFoundException e) {
	    System.out.println("class not found: " + e.getMessage());
	    return;
	}

	// Establish the connection to the database.
	String url = "jdbc:hsqldb:mem:.";

	Connection conn;
	try {
	    conn = DriverManager.getConnection(url, "sa", "");
	    System.out.println("Got Connection.");
	    st = conn.createStatement();
	}
	catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public Session getSession() {
	return session;
    }

    public void executeSQLCommand(String sql) {
	try {
	    st.executeUpdate(sql);
	}
	catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void checkData(String sql) throws Exception {
	ResultSet rs = st.executeQuery(sql);
	ResultSetMetaData metadata = rs.getMetaData();

	for (int i = 0; i < metadata.getColumnCount(); i++) {
	    System.out.print("\t" + metadata.getColumnLabel(i + 1));
	}
	System.out.println("\n----------------------------------");

	while (rs.next()) {
	    for (int i = 0; i < metadata.getColumnCount(); i++) {
		Object value = rs.getObject(i + 1);
		if (value == null) {
		    System.out.print("\t       ");
		}
		else {
		    System.out.print("\t" + value.toString().trim());
		}
	    }
	    System.out.println("");
	}
    }
}