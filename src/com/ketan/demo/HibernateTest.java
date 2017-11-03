package com.ketan.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.ordering.antlr.Factory;

import com.ketan.model.Item;
import com.ketan.model.Student;

public class HibernateTest {

	public static void main(String[] args) {
		
		//create the session fatory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();	

		//create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			//creating students
			Student student1 = new Student("nikhil", "junjal", "nj@neptune.com");
			Student student2 = new Student("ketan", "patil", "kp@neptune.com");
			Student student3 = new Student("sharif", "malik", "sm@neptune.com");
//			Item item = new Item(1);
			
			//start transaction
			session.beginTransaction();
			
			//save the object
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			//commmit the transaction
			session.getTransaction().commit();
			
			//Reading form the DB
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			List<Student> retrievedStudents = session.createQuery("from Student").getResultList();
			
			Student tempStudent = session.get(Student.class, 3);
			
			System.out.println("Temp Student : " + tempStudent);
			
			tempStudent.setEmail("changedEmail@gmail.com");
			
			session.getTransaction().commit();
		}
		finally{
			sessionFactory.close();
		}
		
	}

}
