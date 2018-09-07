package com.fdmgroup.documentuploader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DocumentDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_writesAndSendsToTestFileToCheck() {
		File file = new File("H:\\TestText.txt");
		try {
			FileWriter writer= new FileWriter(file);
			writer.write("This is some test text! I am written in the test for docDao"); 
		      writer.flush();
		      writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		DocumentDao dao = (DocumentDao)context.getBean("DocumentDao");
	
		Path sourcePath = Paths.get(file.toString());
		Path repositoryPath = Paths.get("H:\\RepositoryText.txt");
	
		Document document = new Document("TestText", sourcePath,repositoryPath, null);
		dao.create(document);
		Document documentRead = dao.read(repositoryPath.toString());
		System.out.println(documentRead);
		dao.delete(document);
		
	}
	
	@Test
	public void test_deleteMethodDeletesAFileThatHasBeenCreated() {
		File file = new File("H:\\AnotherTest.txt");
		try {
			FileWriter writer= new FileWriter(file);
			writer.write("This is some test text! I am written in the test for docDao"); 
		      writer.flush();
		      writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		DocumentDao dao = (DocumentDao)context.getBean("DocumentDao");
	
		Path sourcePath = Paths.get(file.toString());
		Path repositoryPath = Paths.get("H:\\RepositoryText.txt");
	
		Document document = new Document("TestText", sourcePath,repositoryPath, null);
		dao.create(document);
		Document documentRead = dao.read(repositoryPath.toString());
		System.out.println("Before delete: " + document);
		dao.delete(document);
		System.out.println("After delete: " + document);
	}
	
	@Test
	public void test_updateMethodWorks_ChangesInfoOnTheDatabase() {
		File file = new File("H:\\MoreTestText.txt");
		try {
			FileWriter writer= new FileWriter(file);
			writer.write("This is some test text! I am written in the test for docDao"); 
		      writer.flush();
		      writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		DocumentDao dao = (DocumentDao)context.getBean("DocumentDao");

		Path sourcePath = Paths.get(file.toString());
		Path repositoryPath = Paths.get("H:\\RepositoryText.txt");
	
		Document document = new Document("MoreTestText", sourcePath,repositoryPath, null);
		dao.create(document);
		System.out.println("Name: " + document.getName() + " Source Path: " + document.getSourcePath());
		
		System.out.println("Before Update: " + document.getName());
		document.setName("UpdatedFileName");
		dao.update(document);
		System.out.println("After update: " + document.getName());
		dao.delete(document);
	}
	


}
