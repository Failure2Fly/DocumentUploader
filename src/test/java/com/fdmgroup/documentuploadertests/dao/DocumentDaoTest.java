package com.fdmgroup.documentuploadertests.dao;
//
//package com.fdmgroup.dao;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.fdmgroup.controller.DispatchController;
//import com.fdmgroup.pojo.BusinessAccount;
//import com.fdmgroup.pojo.Document;
//import com.fdmgroup.pojo.UserAccount;
//
//public class DocumentDaoTest {
//	private ApplicationContext context;
//	private UserAccountDao userDao;
//	private BusinessAccountDao accountDao;
//	private DocumentDao documentDao;
//	private UserAccount testUser;
//	private BusinessAccount testAccount;
//	
//	@Before
//	public void setUp() throws Exception {
//		context = DispatchController.getContext();
//		userDao = (UserAccountDao) context.getBean("UserAccountDao");
//		accountDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
//		documentDao = (DocumentDao) context.getBean("DocumentDao");
//		testUser=new UserAccount("TestUser","Test","User","password","email");
//		userDao.create(testUser);
//		List<UserAccount> userList = new ArrayList<>();
//		userList.add(testUser);
//		List<String> fileList = new ArrayList<>();
//		testAccount = new BusinessAccount(testUser,null,userList,fileList,"Test account",accountDao.getId());
//		accountDao.create(testAccount);
//	}
//	@After
//	public void tearDown() throws Exception {
//		accountDao.delete(testAccount);
//		userDao.delete(testUser);
//		
//	}
//
//	@Test
//	public void test_writesAndSendsToTestFileToCheck() {
//		
//		
//		
//		
//		
//		
//		File file = new File("H:\\TestText.txt");
//		try {
//			FileWriter writer= new FileWriter(file);
//			writer.write("This is some test text! I am written in the test for docDao"); 
//		      writer.flush();
//		      writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		DocumentDao dao = (DocumentDao)context.getBean("DocumentDao");
//	
//		Path sourcePath = Paths.get(file.toString());
//		Path repositoryPath = Paths.get("H:\\RepositoryText.txt");
//	
//		Document document = new Document("TestText", sourcePath,repositoryPath, null);
//		document.setAccountId(testAccount.getBusinessAccountId());
//		dao.create(document);
//		Document documentRead = dao.read(repositoryPath.toString());
//		dao.delete(documentRead);
//		
//	}
//	
//	@Test
//	public void test_deleteMethodDeletesAFileThatHasBeenCreated() {
//		File file = new File("H:\\AnotherTest.txt");
//		try {
//			FileWriter writer= new FileWriter(file);
//			writer.write("This is some test text! I am written in the test for docDao"); 
//		      writer.flush();
//		      writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	
//		Path sourcePath = Paths.get(file.toString());
//		Path repositoryPath = Paths.get("H:\\RepositoryText.txt");
//	
//		Document document = new Document("TestText", sourcePath,repositoryPath, null);
//		document.setAccountId(testAccount.getBusinessAccountId());
//		documentDao.create(document);
//		Document documentRead = documentDao.read(repositoryPath.toString());
//		documentDao.delete(documentRead);
//	}
//	
//	@Test
//	public void test_updateMethodWorks_ChangesInfoOnTheDatabase() {
//		File file = new File("H:\\MoreTestText.txt");
//		try {
//			FileWriter writer= new FileWriter(file);
//			writer.write("This is some test text! I am written in the test for docDao"); 
//		      writer.flush();
//		      writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		Path sourcePath = Paths.get(file.toString());
//		Path repositoryPath = Paths.get("H:\\RepositoryText.txt");
//	
//		Document document = new Document("MoreTestText", sourcePath,repositoryPath, null);
//		document.setAccountId(testAccount.getBusinessAccountId());
//		documentDao.create(document);
//		
//		document.setName("UpdatedFileName");
//		
//		documentDao.update(document);
//		document=documentDao.read(repositoryPath.toString());
//		documentDao.delete(document);
//	}
//	
//	@Test
//	public void test_readAllDocumentsWithGivenAssociatedAccountId() {
//		File file = new File("H:\\YetMoreTestText.txt");
//		try {
//			FileWriter writer= new FileWriter(file);
//			writer.write("This is some test text! I am written in the test for docDao"); 
//		      writer.flush();
//		      writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		File file2 = new File("H:\\YetMoreTestTextFromTheSameAccount.txt");
//		try {
//			FileWriter writer= new FileWriter(file2);
//			writer.write("This is some test text! I am written in the test for docDao"); 
//		      writer.flush();
//		      writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//
//		Path sourcePath = Paths.get(file.toString());
//		Path repositoryPath = Paths.get("H:\\RepositoryText.txt");
//		Path sourcePath2 = Paths.get(file2.toString());
//		Path repositoryPath2 = Paths.get("H:\\RepositoryText2.txt");
//	
//		Document document = new Document("YetMoreTestText", sourcePath,repositoryPath, null);
//		Document document2 = new Document("YetMoreTestTextFromTheSameAccount", sourcePath2,repositoryPath2, null);
//		
//		document.setAccountId(testAccount.getBusinessAccountId());
//		document2.setAccountId(testAccount.getBusinessAccountId());
//		documentDao.create(document);
//		documentDao.create(document2);
//		System.out.println("List of documents:"+documentDao.read(testAccount.getBusinessAccountId()));
//		documentDao.delete(document);
//		documentDao.delete(document2);
//	}
//	
//	
//	
//
//	
//
//
//}
