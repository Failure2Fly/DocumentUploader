//package com.fdmgroup.documentuploader;
//
//import static org.junit.Assert.*;
//
//import static java.nio.file.StandardOpenOption.*;
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class DocumentDaoTest {
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@Test
//	public void test() {
//		Path sourcePath = Paths.get("C:\\Users\\daniel.klingensmith\\javawindow.PNG");
//		Path repositoryPath = Paths.get("C:\\Users\\daniel.klingensmith\\read.PNG");
//		DocumentDao dao = new DocumentDao();
//		Document document = new Document("TestImage", sourcePath,repositoryPath, null);
//		dao.create(document);
//		dao.delete(document);
//	}
//
//}
