package test.bloodshare.database;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.dao.DonorDAOImpl;

public class Main {

	public static void main(String[] args) {
		DonorDAO donorDAO=new DonorDAOImpl();
		System.out.println("\n\n\n");
		System.out.println(donorDAO.read("abc"));
		System.out.println("\n\n\n");
	}
	
}
