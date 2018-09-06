package com.fdmgroup.documentuploader;

import java.util.ArrayList;
import java.util.List;

public class BusinessAccount {

	private UserAccount admin;
	private ServiceLevel servicelevel;
	private List<UserAccount> userAccounts = new ArrayList<>();
	private List<String> fileList = new ArrayList<>();
	private int businessAccountId;
	public UserAccount getAdmin() {
		return admin;
	}
	public void setAdmin(UserAccount admin) {
		this.admin = admin;
	}
	public ServiceLevel getServicelevel() {
		return servicelevel;
	}
	public void setServicelevel(ServiceLevel servicelevel) {
		this.servicelevel = servicelevel;
	}
	public List<UserAccount> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(List<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}
	public List<String> getFileList() {
		return fileList;
	}
	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
	public int getBusinessAccountId() {
		return businessAccountId;
	}
	public void setBusinessAccountId(int businessAccountId) {
		this.businessAccountId = businessAccountId;
	}
	public BusinessAccount(UserAccount admin, ServiceLevel servicelevel, List<UserAccount> userAccounts,
			List<String> fileList, int businessAccountId) {
		super();
		this.admin = admin;
		this.servicelevel = servicelevel;
		this.userAccounts = userAccounts;
		this.fileList = fileList;
		this.businessAccountId = businessAccountId;
	}
	public BusinessAccount() {
		super();
	}
	@Override
	public String toString() {
		return "BusinessAccount [admin=" + admin + ", servicelevel=" + servicelevel + ", userAccounts=" + userAccounts
				+ ", fileList=" + fileList + ", businessAccountId=" + businessAccountId + "]";
	}
	
	
	
	

}
