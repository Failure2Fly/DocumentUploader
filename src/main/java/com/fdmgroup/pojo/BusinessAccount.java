package com.fdmgroup.pojo;

import java.util.ArrayList;
import java.util.List;

public class BusinessAccount {

	private UserAccount owner;
	private ServiceLevel servicelevel;
	private List<UserAccount> userAccounts = new ArrayList<>();
	private List<String> fileList = new ArrayList<>();
	private String accountName;
	private int businessAccountId;
	
	public UserAccount getOwner() {
		return owner;
	}
	public void setOwner(UserAccount owner) {
		this.owner = owner;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	public BusinessAccount(UserAccount owner,  ServiceLevel servicelevel,
			List<UserAccount> userAccounts, List<String> fileList,String accountName, int businessAccountId) {
		super();
		this.owner = owner;
		
		this.servicelevel = servicelevel;
		this.userAccounts = userAccounts;
		this.fileList = fileList;
		this.accountName = accountName;
		this.businessAccountId = businessAccountId;
	}
	public BusinessAccount() {
		super();
	}
	public BusinessAccount(UserAccount owner, ServiceLevel servicelevel,
			List<UserAccount> userAccounts, List<String> fileList, String accountName) {
		super();
		this.owner = owner;
		
		this.servicelevel = servicelevel;
		this.userAccounts = userAccounts;
		this.fileList = fileList;
		this.accountName = accountName;
	}
	@Override
	public String toString() {
		return "BusinessAccount [owner=" + owner + ", accountName=" + accountName + ", servicelevel=" + servicelevel
				+ ", userAccounts=" + userAccounts + ", fileList=" + fileList + ", businessAccountId="
				+ businessAccountId + "]";
	}
	
}
