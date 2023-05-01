package model;

public class UserBean {

	private String username;
	private String password;
	private String email;
	private boolean valid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username =  username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}

}
