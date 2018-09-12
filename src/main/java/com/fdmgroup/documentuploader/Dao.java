package com.fdmgroup.documentuploader;

import javax.sql.DataSource;

public interface Dao<T, U> {

public void setDataSource(DataSource ds);
	
public void create(T item);

public void delete(T item);
                                                                                                                   
public void update(T item);
                                                                                                                   
public T read(U item);
                                                                                                                   
}