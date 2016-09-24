package com.bloodshare.dao;

public interface GenericDAO<T> {

	public T read(String id);
	public T update(T t);
	public boolean save(T t);
	public boolean delete(T t);
}
