package com.bloodshare.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class  HibernateUtil {

	private static SessionFactory sessionFactory=null;

	public static synchronized SessionFactory  getSessionFactory()
	{
		if(sessionFactory==null)
			initSession();
		return sessionFactory;
	}
	
	private static void  initSession()
	{
		
		try{
			
			ServiceRegistry serviceRegistry;
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    	
	    	}
	    	catch (Throwable ex) { 
	            System.err.println("Failsed to create sessionFactory object." + ex);
	            throw new ExceptionInInitializerError(ex); 
	         }
	}
	
//	public static synchronized void closeSession(Session session)
//	{
//		try {
//			if(session!=null)
//			{
//				session.close();
//			}
//			
//			session=null;
//		} catch (Exception e) {
//			System.err.println("Error in closing Sessio ");
//			e.printStackTrace();
//		}
//		
//			
//	}
//	
////	public static synchronized void closeSession()
////	{
////		try {
////			if(!transaction.wasCommitted())
////			transaction.rollback();
////		} catch (Exception e) {
////			closeSession();
////			e.printStackTrace();
////		}
////
////	}
//	
//	
////	public synchronized Transaction getTransaction()
////	{
//////		beginTransaction();
////		return DatabaseHelper.transaction;
////	}
////	public static synchronized void beginTransaction()
////	{
////		if(transaction==null)
////		{
////			transaction=session.beginTransaction();
////		}
////
////	}
//
////	public static synchronized void commitTransaction()
////	{
////		try {
////			System.err.println("DatabaseHelper.commitTransaction()");
////			System.err.println("transaction initially "+transaction);
////			System.err.println("transaction wascom "+transaction.wasCommitted());
//////			if(!transaction.wasCommitted())
//////			{
////				System.err.println("COmmiting ............");
////				System.err.println("COmmiting ............");
////				System.err.println("COmmiting ............");
////				System.err.println("COmmiting ............");
////				transaction.commit();
////				transaction=null;
////				System.out.println("transaction now "+transaction);
//////			}
////			
////			
////			
////		} catch (Exception e) {
////			if(transaction!=null)
////				transaction.rollback();
////				
////			closeSession();
////			e.printStackTrace();
////		}		
////		
////	}
//	public static synchronized void flushAndClear(Session session) {
//		if(session!=null)
//		{
//			session.flush();
//			session.clear();
//		}
//		
//	}
//	
//	
}