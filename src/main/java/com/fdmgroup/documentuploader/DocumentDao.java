package com.fdmgroup.documentuploader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocumentDao implements DAO<Document, Integer> {

	
	@Override
	public void create(Document document) {
		
		try {			
			byte[] data = Files.readAllBytes(document.getSourcePath());
			Files.write(document.getRepositoryPath(), data);
			    
		} catch (IOException x) {
			System.err.println("Problem creating file");
		    System.err.println(x);
		}
		
		
	}

	@Override
	public void delete(Document document) {
		try {
			Files.delete(document.getRepositoryPath());
		} catch (IOException e) {
			System.err.println("File to be deleted does not exist, probably");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Document read(Integer id) {
		return null;
	}
	
	

}
