package com.fdmgroup.documentuploader.dao;

import java.io.File;
import java.io.FileWriter;
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
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.documentuploader.pojo.Document;
import com.fdmgroup.documentuploader.rowmapper.DocumentMapper;

@Repository
public class DocumentDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	
	public void create(Document document, MultipartFile file) {

		try {
			File destination = document.getRepositoryPath().toFile(); 
			
			String repositoryPath = document.getRepositoryPath().toString();
			File debugFile = new File("H:\\DebugCreate.txt");
			try {
				FileWriter writer = new FileWriter(debugFile);
				writer.write("Path: "+repositoryPath);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			repositoryPath=repositoryPath.replaceAll("\\\\", "/");
			file.transferTo(destination);
			String SQL1 = "INSERT INTO DOCUMENTS (file_ id, file_name, stored_file_path, store_date, associated_account_id) VALUES(?, ?, ?, SYSDATE, ?)";
			jdbcTemplateObject.update(SQL1, getId(), document.getName(), repositoryPath,
					document.getAccountId());

		} catch (IOException x) {
			
			System.err.println("Problem creating file - check document paths");
			System.err.println(x);
		}
	}

	public void delete(Document document) {
		try {
			Files.delete(document.getRepositoryPath());
			String repositoryPath = document.getRepositoryPath().toString();
			repositoryPath=repositoryPath.replaceAll("\\\\", "/");
			File debugFile = new File("H:\\DebugDeleteDao.txt");
			try {
				FileWriter writer = new FileWriter(debugFile);
				writer.write("Path: "+repositoryPath);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String SQL = "DELETE FROM DOCUMENTS WHERE stored_file_path = ?";
			jdbcTemplateObject.update(SQL, repositoryPath);
		} catch (IOException e) {
			System.err
					.println("File to be deleted does not exist or document does not have a repository path, probably");
			e.printStackTrace();
		}
	}


	public void update(Document document, MultipartFile file) {
			delete(document);
			create(document, file);

	}


	public Document read(String path) {
		String SQL = "SELECT file_name, stored_file_path, store_date, associated_account_id FROM DOCUMENTS WHERE stored_file_path = ?";
		Document document = jdbcTemplateObject.queryForObject(SQL, new Object[] { path },
				new DocumentMapper());

		return document;
	}

	public List<Document> read(int businessAccountId) {
		String SQL = "SELECT file_name, stored_file-path, store_date, associated_account_id FROM DOCUMENTS WHERE associated_account_id = ?";
		List<Document> documents = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, businessAccountId);
		for (Map<String, Object> map : rows) {
			Document document = new Document();
			document.setAccountId(businessAccountId);
			Date date = ((Date) map.get("store_date"));
			document.setDate(date);
			document.setName((String) map.get("file_name"));
			document.setRepositoryPath(Paths.get((String) map.get("stored-file_path")));
			documents.add(document);
		}

		return documents;
	}

	
	public int getId() {
		String SQL = "SELECT MAX(file_id) FROM documents";
		try {
			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class)) + 1;

		} catch (NullPointerException e) {
			return 1;
		}

	}
	
}
