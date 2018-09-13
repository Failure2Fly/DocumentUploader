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

		UserAccount owner = dao.read(rs.getInt("useraccountownerid"));
		
		business.setOwner(owner);
		business.setBusinessAccountId(rs.getInt("businessaccountid"));
		business.setAccountName(rs.getString("accountname"));
		business.setServiceLevel(new ServiceLevel(rs.getInt("servicelevel")));
		business.setUserLimit(rs.getInt("userlimit"));

		return business;
	}

}
