package com.fdmgroup.documentuploader;

import java.util.List;
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
    jdbcTemplateObject.update( SQL,item.getUsername(),item.getLastName(),item.getPassword(),item.getEmailAddress(),);
    
    //TODO more questions/answers
   }
   @Override
   public void delete(UserAccount item) {
   	//TODO sql query creation 
   	
   }
   @Override
   public void update(UserAccount item) {
   	//TODO sql query creation 
   	String SQL = "";
   }
   @Override
   public UserAccount read(String username) {
   	//TODO sql query creation 
	  String SQL = "SELECT use.username,use.userpassword,use.useremail,use.firstname,use.lastname,sec.question,uats.questionanswer FROM USERACCOUNT USE JOIN USERACCOUNTTOSECURITYQUESTION UATS ON UATS.USERACCOUNTSECURITYJOINID = USE.USERID JOIN SECURITYQUESTION SEC ON UATS.SECURITYQUESTIONJOINID = SEC.QUESTIONID WHERE USERNAME = ?";
	  UserAccount user = jdbcTemplateObject.queryForObject(SQL,new Object[]{username},new UserAccountMapper());
   	return user;
   }
  /* public Student getStudent(Integer id) {
      String SQL = "select * from Student where id = ?";
      Student student = jdbcTemplateObject.queryForObject(SQL, 
         new Object[]{id}, new StudentMapper());
      
      return student;
   }
   public List<Student> listStudents() {
      String SQL = "select * from Student";
      List <Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
      return students;
   }
   public void delete(Integer id) {
      String SQL = "delete from Student where id = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
      return;
   }
   public void update(Integer id, Integer age){
      String SQL = "update Student set age = ? where id = ?";
      jdbcTemplateObject.update(SQL, age, id);
      System.out.println("Updated Record with ID = " + id );
      return;
   }*/

}
