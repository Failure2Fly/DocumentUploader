package com.fdmgroup.documentuploader;

public interface DAO<T, U> {

	public void create(T item);

	public void delete(T item);

	public void update(T item);

	public T read(U item);

}
