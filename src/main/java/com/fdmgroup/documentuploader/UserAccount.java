package com.fdmgroup.documentuploader;

import java.util.Map;

public class UserAccount {
	private String username;
	private String lastName;
	private String firstName;
	private String password;
	private String emailAddress;
	Map<SecurityQuestion,String> mapQA;
	

	public UserAccount() {
		super();
		
	}
	public UserAccount(String username, String lastName, String firstName, String password, String emailAddress,
			Map<SecurityQuestion, String> mapQA) {
		super();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.mapQA = mapQA;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Map<SecurityQuestion, String> getMapQA() {
		return mapQA;
	}
	public void setMapQA(Map<SecurityQuestion, String> mapQA) {
		this.mapQA = mapQA;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mapQA == null) ? 0 : mapQA.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UserAccount other = (UserAccount) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mapQA == null) {
			if (other.mapQA != null)
				return false;
		} else if (!mapQA.equals(other.mapQA))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAccount [username=").append(username).append(", password=").append(password)
				.append(", emailAddress=").append(emailAddress).append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(", mapQA=").append(mapQA).append("]");
		return builder.toString();
	}
	
	
	
}
