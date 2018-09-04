package com.fdmgroup.documentuploader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class UserAccountDao implements DAO<UserAccount, String> {

	@Override
	public void create(UserAccount user) {
		try (Connection connection = createManager();) {
		//Insert query after database is created
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(UserAccount user) {
		try (Connection connection = createManager();) {
		//Insert query after database is created
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(UserAccount user) {
		try (Connection connection = createManager();) {
		//Insert query after database is created
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public UserAccount read(String username) {
		try (Connection connection = createManager();) {
		//Insert query after database is created
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	public static Connection createManager() throws SQLException {
		if (!DriverManager.getDrivers().hasMoreElements()) {
			DriverManager.registerDriver(new OracleDriver());
		}
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "trainee1", "!QAZSE4");
	}







}
