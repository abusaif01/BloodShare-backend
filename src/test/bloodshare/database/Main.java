package test.bloodshare.database;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bloodshare.dao.DatabaseHelper;
import com.bloodshare.entity.Donor;

public class Main {

	public static void main(String[] args) {
		
		Session session = DatabaseHelper.getSession();
		Transaction transaction = session.beginTransaction();
//		User user=new User();
//		
//		user.setName("test");
//		user.setBirthDate(new Date());
//		user.setId("xtz");
//		user.setMobile("2136554");
//		user.setBloodGroup("0+");
//		session.save(user);
		Donor user=(Donor) session.get(Donor.class , "abc");
		System.out.println(user);
		transaction.commit();
		DatabaseHelper.closeSession(session);
	}
	
}
