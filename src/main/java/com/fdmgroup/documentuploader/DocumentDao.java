package com.fdmgroup.documentuploader;

import java.io.IOException;
import java.nio.file.Files;

public class DocumentDao implements DAO<Document, Integer> {

	
	@Override
	public void create(Document document) {
		
		try {			
			byte[] data = Files.readAllBytes(document.getSourcePath());
			Files.write(document.getRepositoryPath(), data);
			    
		} catch (IOException x) {
			System.err.println("Problem creating file - check document paths");
		    System.err.println(x);
		}
		
		
	}

	@Override
	public void delete(Document document) {
		try {
			Files.delete(document.getRepositoryPath());
		} catch (IOException e) {
			System.err.println("File to be deleted does not exist or document does not have a repository path, probably");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Document document) {
		try {
			Files.delete(document.getRepositoryPath());
			byte[] data = Files.readAllBytes(document.getSourcePath());
			Files.write(document.getRepositoryPath(), data);
		} catch (IOException e) {
			System.err.println("Error with updating a file- check document paths");
			e.printStackTrace();
		}
		
	}

	@Override
	public Document read(Integer id) {
		return null;
	}
	
	

}
