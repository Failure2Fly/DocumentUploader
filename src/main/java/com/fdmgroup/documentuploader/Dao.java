package com.fdmgroup.documentuploader;

import javax.sql.DataSource;

public interface Dao<T, U> {

void setDataSource(DataSource ds);
	
void create(T item);

void delete(T item);
                                                                                                                   
void update(T item);
                                                                                                                   
T read(U item);
                                                                                                                   
}