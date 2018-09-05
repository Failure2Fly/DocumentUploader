package com.fdmgroup.documentuploader;


import java.util.Map.Entry;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserAccountJdbcTemplate implements DAO<UserAccount,String> {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   
   @Override
   public void create(UserAccount item) {
   	String SQL = "exec register_account(?,?,?,?,?,?,?)";
   	Entry<SecurityQuestion, String> entry = item.getMapQA().entrySet().iterator().next();
    jdbcTemplateObject.update(SQL,item.getUsername(),item.getLastName(),item.getFirstName(),item.getPassword(),item.getEmailAddress(),entry.getKey().name(),entry.getValue());
    
    //TODO more questions/answers
   }
   
   @Override
   public void delete(UserAccount item) {
   	//TODO sql query deletion all useraccount's stuff
	   String SQL1 = "DELETE FROM USERACCOUNTTOSECURITYQUESTION WHERE USERACCOUNTSECURITYJOINID = ?";
	   String SQL2 = "DELETE FROM USERACCOUNT WHERE username = ?";
	   String SQL3 = "DELETE FROM BUSINESSACCOUNT WHERE USERACCOUNTOWNERID = ?";  
   }
   
   @Override
   public void update(UserAccount item) {
   	//TODO sql query update username
   	String SQL = "UPDATE useraccount SET firstname=?,lastname=?,userpassword=?,useremail=? WHERE username=?";
   	jdbcTemplateObject.update(SQL,item.getFirstName(),item.getLastName(),item.getPassword(),item.getEmailAddress(),item.getUsername());
   }
   
   @Override
   public UserAccount read(String username) {
	  String SQL = "SELECT use.username,use.userpassword,use.useremail,use.firstname,use.lastname,sec.question,uats.questionanswer FROM USERACCOUNT USE JOIN USERACCOUNTTOSECURITYQUESTION UATS ON UATS.USERACCOUNTSECURITYJOINID = USE.USERID JOIN SECURITYQUESTION SEC ON UATS.SECURITYQUESTIONJOINID = SEC.QUESTIONID WHERE USERNAME = ?";
	  UserAccount user = jdbcTemplateObject.queryForObject(SQL,new Object[]{username},new UserAccountMapper());
   	return user;
   }
}
