package com.fdmgroup.documentuploader.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fdmgroup.documentuploader.pojo.Document;
import com.fdmgroup.documentuploader.rowmapper.DocumentMapper;

@Repository
public class DocumentDao implements Dao<Document, String> {

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

			String SQL1 = "INSERT INTO DOCUMENTS (fileid,filename,storedfilepath,storedate,associatedaccountid) VALUES(?,?,?,SYSDATE,?)";
			jdbcTemplateObject.update(SQL1, getId(), document.getName(), document.getRepositoryPath().toString(),
					document.getAccountId());

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

			String SQL1 = "INSERT INTO DOCUMENTS (fileid,filename,storedfilepath,storedate,associatedaccountid) VALUES(?,?,?,SYSDATE,?)";
			jdbcTemplateObject.update(SQL1, getId(), document.getName(), document.getRepositoryPath().toString(),
					document.getAccountId());

		} catch (IOException e) {
			System.err.println("Error with updating a file- check document paths");
			e.printStackTrace();
		}

	}

	@Override
	public Document read(String path) {
		String SQL = "SELECT filename, storedfilepath, storedate, associatedaccountid FROM DOCUMENTS WHERE storedfilepath = ?";
		Document document = (Document) jdbcTemplateObject.queryForObject(SQL, new Object[] { path },
				new DocumentMapper());

		return document;
	}

	public List<Document> read(int businessAccountId) {
		String SQL = "SELECT filename, storedfilepath, storedate, ASSOCIATEDACCOUNTID FROM DOCUMENTS WHERE ASSOCIATEDACCOUNTID = ?";
		List<Document> documents = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, businessAccountId);
		for (Map<String, Object> map : rows) {
			Document document = new Document();
			document.setAccountId(businessAccountId);
			Date date = ((Date) map.get("storedate"));
			document.setDate(date);
			document.setName((String) map.get("filename"));
			document.setRepositoryPath(Paths.get((String) map.get("storedfilepath")));
			documents.add(document);
		}

		return documents;
	}

	public int getId() {
		String SQL = "SELECT MAX(fileid) FROM documents";
		try {

			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class)) + 1;

		} catch (NullPointerException e) {
			return 1;
		}

	}

}
