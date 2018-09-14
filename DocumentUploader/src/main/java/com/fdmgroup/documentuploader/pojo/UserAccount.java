package com.fdmgroup.documentuploader.pojo;

import java.util.List;

public class UserAccount {

	private String username;
	private String lastName;
	private String firstName;
	private String password;
	private String userEmail;
	private List<Questions> listQA;

	public UserAccount() {
		super();
	}

	public UserAccount(String username, String lastName, String firstName, String password, String userEmail) {
		super();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.userEmail = userEmail;

		this.listQA = null;
	}

	public UserAccount(String username, String lastName, String firstName, String password, String userEmail,
			List<Questions> listQA) {
		super();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.userEmail = userEmail;
		this.listQA = listQA;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public List<Questions> getListQA() {
		return listQA;
	}

	public void setListQA(List<Questions> listQA) {
		this.listQA = listQA;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
				.append(", emailAddress=").append(userEmail).append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(listQA).append("]");
		return builder.toString();
	}
}