package com.fdmgroup.documentuploader;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountJdbcTemplate implements DAO<UserAccount,String> {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   
   @Override
   public void create(UserAccount item) {
   	String SQL1="INSERT INTO USERACCOUNT (userid,username,lastname,firstname,userpassword,useremail) VALUES(?,?,?,?,?,?)";
   	String SQL2="INSERT INTO USERACCOUNTTOSECURITYQUESTION VALUES(?,?,?)";
   	Entry<SecurityQuestion, String> entry = item.getMapQA().entrySet().iterator().next();
   	String key = entry.getKey().name().toLowerCase().replace("_", " ")+"?";
   	key = key.substring(0, 1).toUpperCase() + key.substring(1);
   	File file = new File("H:\\DebugInCreate.txt");
	try {
		FileWriter writer= new FileWriter(file);
		writer.write(item.toString()); 
	    writer.flush();
	    writer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    jdbcTemplateObject.update(SQL1,lastID()+1,item.getUsername(),item.getLastName(),item.getFirstName(),item.getPassword(),item.getUserEmail());
    jdbcTemplateObject.update(SQL2,lastID(),entry.getKey().ordinal()+1,entry.getValue());
    //TODO more questions/answers
   }
   @Override
   public void delete(UserAccount item) {
   	//TODO sql query deletion all useraccount's stuff
	   String SQL1 = "DELETE FROM USERACCOUNTTOSECURITYQUESTION WHERE USERACCOUNTSECURITYJOINID = ?";
	   String SQL2 = "DELETE FROM USERACCOUNT WHERE username = ?";
	   //String SQL3 = "DELETE FROM BUSINESSACCOUNT WHERE USERACCOUNTOWNERID = ?";
	   jdbcTemplateObject.update(SQL1,getID(item.getUsername()));
	   jdbcTemplateObject.update(SQL2,item.getUsername());
   }
   @Override
   public void update(UserAccount item) {
   	//TODO sql query update username
   	String SQL = "UPDATE useraccount SET firstname=?,lastname=?,userpassword=?,useremail=? WHERE username=?";
   	jdbcTemplateObject.update(SQL,item.getFirstName(),item.getLastName(),item.getPassword(),item.getUserEmail(),item.getUsername());
   }
   @Override
   public UserAccount read(String username) {
	  String SQL = "SELECT username, userpassword, useremail, firstname, lastname FROM USERACCOUNT WHERE username = ?";
	  UserAccount user = jdbcTemplateObject.queryForObject(SQL,new Object[]{username},new UserAccountMapper());
   	return user;
   }
   public int lastID(){
	   String SQL = "SELECT MAX(USERID) FROM USERACCOUNT";
	   return (int) jdbcTemplateObject.queryForObject(SQL,Integer.class);
   }
   public int getID(String username){
	   String SQL = "SELECT USERID FROM USERACCOUNT where username = ?";
	   return (int) jdbcTemplateObject.queryForObject(SQL,Integer.class,username);
   }
}
