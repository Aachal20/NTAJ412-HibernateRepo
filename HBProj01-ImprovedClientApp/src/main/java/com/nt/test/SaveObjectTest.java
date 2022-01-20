package com.nt.test;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest{

	public static void main(String[] args) {
		//Bootstrap/Activate the hibernate
		Configuration cfg= new Configuration();
		//specify the hibernate cfg filename and location
		cfg.configure("com/nt/cfg/hibernate.cfg.xml");
		//build SessionFactory having all services specified in cfg file and mapping file
		SessionFactory factory=cfg.buildSessionFactory();
		//create session object
		Session ses = factory.openSession();
		org.hibernate.Transaction tx=null;
		//Transaction tx=null;
		try {
			//begin Transaction
			tx= ses.beginTransaction();   //internally calls con.setAutoCommit(false) to disable autoCommit mode on DB s/w
			//prepare entity object
			Product prod = new Product();
			prod.setPid(1008);
			prod.setPname("Luxory Car");
			prod.setPrice(25000.35f);
			prod.setQty(2.01f);
			//save object 
			
			Integer idValue=(Integer)ses.save(prod);             //Gives persistence instructions to hibernate to save object(insert object data as the record)
			tx.commit();                             //internally calls con.commit() to make insertion execution result permemet
			System.out.println("The generatred ID value::" +idValue);
			System.out.println("Object is saved(record is inserted)");
		}
		catch(HibernateException he){
			he.printStackTrace();
			tx.rollback();                                     //internally calls con.rollaback() to rollback the result of query execution
			System.out.println("Object is not  saved(Record is not inserted)");
		}
		//close session obj
		ses.close();
		//close sessionFactory object
		factory.close();
	}//main
}//class
