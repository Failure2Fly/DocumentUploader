package com.fdmgroup.pojo;

public class ServiceLevel {

	private ServiceLevels serviceLevel;
	private int documentUploadLimit;
	private boolean hasAdverts;

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
			documentUploadLimit = 2;
			hasAdverts = true;
			break;
		case SILVER:
			documentUploadLimit = 5;
			hasAdverts = true;
			break;
		case GOLD:
			documentUploadLimit = 20;
			hasAdverts = false;
			break;
		case UNLIMITED:
		case ENTERPRISE:
			documentUploadLimit = -1;
			hasAdverts = false;
			break;
		// If limit < 0, no documentUploadLimit
		}
	}

	public ServiceLevels getServiceLevel() {
		return serviceLevel;
	}

	public int getDocumentUploadLimit() {
		return documentUploadLimit;
	}

	public boolean getHasAdverts() {
		return hasAdverts;
	}

}
