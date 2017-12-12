package com.bloodshare.dao;

public interface GenericDAO<T,I> {

	public T read(I id);
	public T update(T t);
	public T save(T t);
	public boolean delete(T t);
}
