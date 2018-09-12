package com.fdmgroup.documentuploader.pojo;

import com.fdmgroup.documentuploader.enumeratedtypes.ServiceLevels;

public class ServiceLevel {

	private ServiceLevels serviceLevel;
	private int monthlyCost;
	private int documentLimit;
	private int monthlyDocumentUploadLimit;
	private int userLimit;
	private int additionalUserCost;
	private boolean hasAdverts;

	public ServiceLevel() {
		this(ServiceLevels.BRONZE);
	}
	
	public ServiceLevel(ServiceLevels serviceLevel) {
		super();
		this.serviceLevel = serviceLevel;
		assembleServiceLevel(serviceLevel);
	}

	public void changeServiceLevel(ServiceLevels serviceLevel) {
		this.serviceLevel = serviceLevel;
		assembleServiceLevel(serviceLevel);
	}

	private void assembleServiceLevel(ServiceLevels serviceLevel) {
		switch (serviceLevel) {
		default:
		case BRONZE:
			monthlyCost = 0;
			documentLimit = 2;
			monthlyDocumentUploadLimit = 2;
			userLimit = 1;
			additionalUserCost = 0;
			hasAdverts = true;
			break;
		case SILVER:
			monthlyCost = 1;
			documentLimit = 5;
			monthlyDocumentUploadLimit = 10;
			userLimit = 1;
			additionalUserCost = 0;
			hasAdverts = true;
			break;
		case GOLD:
			monthlyCost = 2;
			documentLimit = 20;
			monthlyDocumentUploadLimit = 50;
			userLimit = 2;
			additionalUserCost = 0;
			hasAdverts = false;
			break;
		case UNLIMITED:
			monthlyCost = 5;
			documentLimit = -1;
			monthlyDocumentUploadLimit = -1;
			userLimit = 10;
			additionalUserCost = 1;
			hasAdverts = false;
			break;
		case ENTERPRISE:
			monthlyCost = 15;
			documentLimit = -1;
			monthlyDocumentUploadLimit = -1;
			userLimit = 200;
			additionalUserCost = 1;
			hasAdverts = false;
			break;
		// If limit < 0, no documentUploadLimit
		}
	}

	public ServiceLevels getServiceLevel() {
		return serviceLevel;
	}

	public int getMonthlyCost() {
		return monthlyCost;
	}

	public int getDocumentLimit() {
		return documentLimit;
	}

	public int getMonthlyDocumentUploadLimit() {
		return monthlyDocumentUploadLimit;
	}

	public int getUserLimit() {
		return userLimit;
	}

	public int getAdditionalUserCost() {
		return additionalUserCost;
	}

	public boolean getHasAdverts() {
		return hasAdverts;
	}

}
