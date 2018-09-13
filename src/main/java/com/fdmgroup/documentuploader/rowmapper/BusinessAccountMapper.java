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
import com.fdmgroup.documentuploader.pojo.ServiceLevel;
import com.fdmgroup.documentuploader.pojo.UserAccount;

public class BusinessAccountMapper implements RowMapper<BusinessAccount> {

	@Override
	public BusinessAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BusinessAccount business = new BusinessAccount();
		ApplicationContext context = DispatchController.getContext();

		UserAccountDao dao = (UserAccountDao)context.getBean("UserAccountDao");
				
		UserAccount owner = dao.read(rs.getInt("useraccountownerid"));
		business.setOwner(owner);
		
		business.setBusinessAccountId(rs.getInt("businessaccountid"));
		
		business.setAccountName(rs.getString("accountname"));
		

		business.setServicelevel(new ServiceLevel(rs.getInt("servicelevel")));
		
		return business;
	}

}
