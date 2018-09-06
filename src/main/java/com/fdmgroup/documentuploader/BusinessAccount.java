package com.fdmgroup.documentuploader;

import java.util.List;

public class BusinessAccount {

	private UserAccount admin;
	private servicelevel;
	private userAccounts;
	private fileList;
	private int businessAccountId;
	
	ServiceLevel servicelevel = new ServiceLevel();
	List<UserAccount> userAccounts = new List<UserAccount>();
	List<String> fileList = new List<String>();
	
	
	public BusinessAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BusinessAccount(UserAccount admin, int businessAccountId, ServiceLevel servicelevel,
			List<UserAccount> userAccounts, List<String> fileList) {
		super();
		this.admin = admin;
		this.businessAccountId = businessAccountId;
		this.servicelevel = servicelevel;
		this.userAccounts = userAccounts;
		this.fileList = fileList;
	}


	public UserAccount getAdmin() {
		return admin;
	}


	public void setAdmin(UserAccount admin) {
		this.admin = admin;
	}


	public int getBusinessAccountId() {
		return businessAccountId;
	}


	public void setBusinessAccountId(int businessAccountId) {
		this.businessAccountId = businessAccountId;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + businessAccountId;
		result = prime * result + ((fileList == null) ? 0 : fileList.hashCode());
		result = prime * result + ((servicelevel == null) ? 0 : servicelevel.hashCode());
		result = prime * result + ((userAccounts == null) ? 0 : userAccounts.hashCode());
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
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (businessAccountId != other.businessAccountId)
			return false;
		if (fileList == null) {
			if (other.fileList != null)
				return false;
		} else if (!fileList.equals(other.fileList))
			return false;
		if (servicelevel == null) {
			if (other.servicelevel != null)
				return false;
		} else if (!servicelevel.equals(other.servicelevel))
			return false;
		if (userAccounts == null) {
			if (other.userAccounts != null)
				return false;
		} else if (!userAccounts.equals(other.userAccounts))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BusinessAccount [admin=" + admin + ", businessAccountId=" + businessAccountId + ", servicelevel="
				+ servicelevel + ", userAccounts=" + userAccounts + ", fileList=" + fileList + "]";
	}
	
}
