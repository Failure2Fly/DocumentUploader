package com.fdmgroup.documentuploader.pojo;

import java.util.ArrayList;
import java.util.List;

public class BusinessAccount {

	private UserAccount owner;
	private ServiceLevel servicelevel;
	private int userLimit;
	private List<UserAccount> userAccounts = new ArrayList<>();
	private List<String> fileList = new ArrayList<>();
	private String accountName;
	private int businessAccountId;
	
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
	
	public BusinessAccount(UserAccount owner, ServiceLevel servicelevel,
			List<UserAccount> userAccounts, List<String> fileList, String accountName) {
		super();
		this.owner = owner;
		this.servicelevel = servicelevel;
		this.userAccounts = userAccounts;
		this.fileList = fileList;
		this.accountName = accountName;
	}
		
	public BusinessAccount() {
		super();
	}
	
	public void increaseUserLimit(int num){
		userLimit += num;
	}
	
	public void reduceUserLimit(int num) {
		userLimit -= num;
	}
	
	@Override
	public String toString() {
		return "BusinessAccount [owner=" + owner + ", accountName=" + accountName + ", servicelevel=" + servicelevel
				+ ", userAccounts=" + userAccounts + ", fileList=" + fileList + ", businessAccountId="
				+ businessAccountId + "]";
	}

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
	public ServiceLevel getServiceLevel() {
		return servicelevel;
	}
	public void setServiceLevel(ServiceLevel servicelevel) {
		this.servicelevel = servicelevel;
	}
	public int getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + businessAccountId;
		result = prime * result + ((fileList == null) ? 0 : fileList.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessAccount other = (BusinessAccount) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (businessAccountId != other.businessAccountId)
			return false;
		if (fileList == null) {
			if (other.fileList != null)
				return false;
		} else if (!fileList.equals(other.fileList))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	
	
}
