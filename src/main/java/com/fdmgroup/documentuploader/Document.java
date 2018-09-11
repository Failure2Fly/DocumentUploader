package com.fdmgroup.documentuploader;

import java.nio.file.Path;
import java.sql.Date;

public class Document {
	String name;
	Path sourcePath;
	Path repositoryPath;
	Date date;
	int accountId;
	
	
	
	@Override
	public String toString() {
		return "Document [name=" + name + ", sourcePath=" + sourcePath + ", repositoryPath=" + repositoryPath
				+ ", date=" + date + "]";
	}
	public Document(String name, Path sourcePath, Path repositoryPath, Date date) {
		super();
		this.name = name;
		this.sourcePath = sourcePath;
		this.repositoryPath = repositoryPath;
		this.date = date;
	}
	public Document() {
		super();
	}
	
	protected int getAccountId() {
		return accountId;
	}
	protected void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Path getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(Path sourcePath) {
		this.sourcePath = sourcePath;
	}
	public Path getRepositoryPath() {
		return repositoryPath;
	}
	public void setRepositoryPath(Path repositoryPath) {
		this.repositoryPath = repositoryPath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
