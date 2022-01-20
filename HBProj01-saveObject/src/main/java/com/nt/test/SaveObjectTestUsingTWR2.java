package com.nt.test;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTestUsingTWR2{

	public static void main(String[] args) {
		//Bootstrap/Activate the hibernate
		Configuration cfg= new Configuration();
		//specify the hibernate cfg filename and location
		cfg.configure("com/nt/cfg/hibernate.cfg.xml");
		
		//build SessionFactory having all services specified in cfg file and mapping file
		SessionFactory factory=cfg.buildSessionFactory();
		//create session object
		Session ses = factory.openSession();
		org.hibernate.Transaction tx1=null;
		// Using TWR 
		try(ses;factory){
			//begin Transaction
			tx1= ses.beginTransaction();   //internally calls con.setAutoCommit(false) to disable autoCommit mode on DB s/w
			//prepare entity object
			Product prod = new Product();
			prod.setPid(1011);
			prod.setPname("Partener Car");
			prod.setPrice(255000.35f);
			prod.setQty(2.51f);
			//save object 

			Integer idValue=(Integer)ses.save(prod);             //Gives persistence instructions to hibernate to save object(insert object data as the record)
			tx1.commit();                             //internally calls con.commit() to make insertion execution result permemet
			//System.out.println("The generatred ID value::" +idValue);
			System.out.println("Object is saved");

		}
		catch(HibernateException he){
			he.printStackTrace();
			if(tx1!=null && tx1.getRollbackOnly() ||tx1.getStatus()!=null) {
				tx1.rollback();
				System.out.println("Object is saved");
			}
		}
	}//main
}//class
