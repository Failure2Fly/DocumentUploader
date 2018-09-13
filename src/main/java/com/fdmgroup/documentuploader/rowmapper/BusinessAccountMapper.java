package com.fdmgroup.documentuploader.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
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

		UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");

		UserAccount owner = dao.read(rs.getInt("user_account_owner_id"));
		
		business.setOwner(owner);
		business.setBusinessAccountId(rs.getInt("business_account_id"));
		business.setAccountName(rs.getString("account_name"));
		business.setServiceLevel(new ServiceLevel(rs.getInt("service_level")));
		business.setUserLimit(rs.getInt("user_limit"));

		return business;
	}

}
