package com.fdmgroup.documentuploader.pojo;

import java.nio.file.Path;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Document {

	private String name;
	private Path sourcePath;
	private Path repositoryPath;
	private Date date;
	private int accountId;
	
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
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
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
