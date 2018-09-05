package com.fdmgroup.documentuploader;

import javax.sql.DataSource;

import org.aspectj.apache.bcel.util.Repository;

public interface DAOExample<T, U> {

void setDataSource(DataSource ds);
	
void create(T item);

void delete(T item);
                                                                                                                   
void update(T item);
                                                                                                                   
T read(U item);
                                                                                                                   
}