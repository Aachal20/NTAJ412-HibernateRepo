package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class LoadObjectTestingUsingGetMethod {

	public static void main(String[] args) {
		//configuration object
		Configuration cfg= new Configuration();
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");

		//Build SessionFactory Object
		SessionFactory factory= cfg.buildSessionFactory();

		//build session
		Session ses= factory.openSession();

		try(factory;ses){      //try-With-resource(java9)
			//Load object using get method
			Product prod=ses.get(Product.class,1001);
			if(prod==null)
				System.out.println("Product Not Found");
			else
				System.out.println(prod);
				
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class
