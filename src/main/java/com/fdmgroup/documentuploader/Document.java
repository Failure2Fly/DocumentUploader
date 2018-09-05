package com.fdmgroup.documentuploader;

import java.nio.file.Path;

public class Document {
	String name;
	Path sourcePath;
	Path repositoryPath;
	public Document(String name, Path sourcePath, Path repositoryPath) {
		super();
		this.name = name;
		this.sourcePath = sourcePath;
		this.repositoryPath = repositoryPath;
	}
	public Document() {
		super();
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
	

}
