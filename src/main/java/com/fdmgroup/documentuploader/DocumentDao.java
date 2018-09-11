package com.fdmgroup.documentuploader;

import java.io.IOException;
import java.nio.file.Files;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDao implements DAO<Document, String> {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Document document) {

		try {
			byte[] data = Files.readAllBytes(document.getSourcePath());
			Files.write(document.getRepositoryPath(), data);

			String SQL1 = "INSERT INTO DOCUMENTS (fileid,filename,storedfilepath,storedate) VALUES(?,?,?,SYSDATE)";
			jdbcTemplateObject.update(SQL1,getId(), document.getName(), document.getRepositoryPath().toString());

		} catch (IOException x) {
			System.err.println("Problem creating file - check document paths");
			System.err.println(x);
		}

	}

	@Override
	public void delete(Document document) {
		try {
			Files.delete(document.getRepositoryPath());
			String SQL = "DELETE FROM DOCUMENTS WHERE storedfilepath = ?";
			jdbcTemplateObject.update(SQL, document.getRepositoryPath().toString());

		} catch (IOException e) {
			System.err
					.println("File to be deleted does not exist or document does not have a repository path, probably");
			e.printStackTrace();
		}

	}

	@Override
	public void update(Document document) {
		try {
			Files.delete(document.getRepositoryPath());
			String SQL = "DELETE FROM DOCUMENTS WHERE storedfilepath = ?";
			jdbcTemplateObject.update(SQL, document.getRepositoryPath().toString());

			byte[] data = Files.readAllBytes(document.getSourcePath());
			Files.write(document.getRepositoryPath(), data);

			String SQL1 = "INSERT INTO DOCUMENTS (fileid,filename,storedfilepath,storedate) VALUES(file_seq.nextval,?,?,SYSDATE)";
			jdbcTemplateObject.update(SQL1, document.getName(), document.getRepositoryPath().toString());

		} catch (IOException e) {
			System.err.println("Error with updating a file- check document paths");
			e.printStackTrace();
		}

	}

	@Override
	public Document read(String path) {
		String SQL = "SELECT filename, storedate FROM DOCUMENTS WHERE storedfilepath = ?";
		Document document = (Document) jdbcTemplateObject.queryForObject(SQL, new Object[] { path },
				new DocumentMapper());

		return document;
	}
	
	public int getId() {
		String SQL = "SELECT MAX(fileid) FROM documents";
		try {
			
			
			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class))+1;

		} catch (NullPointerException e) {
			return 1;
		}

	}

}
