package com.fdmgroup.documentuploader.rowmapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import com.fdmgroup.documentuploader.controller.DispatchController;
import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.pojo.BusinessAccount;
import com.fdmgroup.documentuploader.pojo.UserAccount;

public class BusinessAccountMapper implements RowMapper<BusinessAccount> {

	@Override
	public BusinessAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		File file = new File("H:\\DebugBusinessMapper.txt");
		try {
			FileWriter writer = new FileWriter(file);

			writer.write("Got in the mapper");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BusinessAccount business = new BusinessAccount();
		ApplicationContext context = DispatchController.getContext();

		UserAccountDao dao = (UserAccountDao)context.getBean("UserAccountDao");
				
		UserAccount owner = dao.read(rs.getInt("useraccountownerid"));
		business.setOwner(owner);
		
		business.setBusinessAccountId(rs.getInt("businessaccountid"));
		
		business.setAccountName(rs.getString("accountname"));
		
		//TODO: ServiceLevel DAO
		business.setServicelevel(null);
		
		try {
			FileWriter writer = new FileWriter(file);

			writer.append("Ended mapper; business account = "+business);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return business;
	}

}
