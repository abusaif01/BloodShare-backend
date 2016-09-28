package com.bloodshare.module.otp;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class OtpGenerator {
	private static final int OTP_COUNTER_MAX=2147483647;
	private static final int OTP_COUNTER_MIN=1;
	private static int OTP_COUNTER=randInt(OTP_COUNTER_MIN, OTP_COUNTER_MAX);
	
	private static class OtpGeneratorHolder
	{
		public static final OtpGenerator INSTANCE = new OtpGenerator();
	}
	
	private OtpGenerator()
	{
		
	}
	
	public static OtpGenerator getInstance(){
		return OtpGeneratorHolder.INSTANCE;
	}
	
	
	public synchronized String  generateOTP(String seed) throws InvalidKeyException, NoSuchAlgorithmException
	{
	    byte[] code = seed.getBytes();
	    String otp=HOTPAlgorithm.generateOTP(code, OTP_COUNTER, 6, false, OTP_COUNTER);
	    changeOTPCounter();
	    return otp;
	}
	

	private static int randInt(int min, int max) {
	    Random rand=new Random(System.currentTimeMillis());
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	private void changeOTPCounter()
	{
		if(OTP_COUNTER==OTP_COUNTER_MAX)
		{
			OTP_COUNTER=randInt(OTP_COUNTER_MIN, OTP_COUNTER_MAX);
		}
		else OTP_COUNTER++;
	}
}
