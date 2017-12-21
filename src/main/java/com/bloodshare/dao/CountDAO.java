package com.bloodshare.dao;


public interface CountDAO {
	public int getCount(Class className);
	public int getCount(Class className,String whereCondition);
}
