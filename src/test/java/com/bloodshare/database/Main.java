package com.bloodshare.database;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.adrjun.authentication.OtpGenerator;


public class Main {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
//		DonorDAO donorDAO=new DonorDAOImpl();
//		System.out.println("\n\n\n");
//		System.out.println(donorDAO.read("abc"));
//		System.out.println("\n\n\n");
		System.out.println(OtpGenerator.getInstance().generateOTP("019148200101") );
		System.out.println(OtpGenerator.getInstance().generateOTP("019148200101") );
		
	}
	
}
