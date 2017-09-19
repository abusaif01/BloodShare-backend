package com.adrjun.authentication;

public class CookiesIdGenerator {

	private static class CookiesIdGeneratorHolder{
		public static final CookiesIdGenerator INSTANCE=new CookiesIdGenerator();
	}
	
	private CookiesIdGenerator()
	{
		
	}
	
	public static CookiesIdGenerator getInstance()
	{
		return CookiesIdGeneratorHolder.INSTANCE;
	}
	
	public synchronized String generateCookiesId(String userId)
	{
		return userId;
	}
}
