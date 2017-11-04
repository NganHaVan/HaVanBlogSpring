package havan.blog.demo.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {
	@NotEmpty
	@Size(min=5,max=30,message="Username must contain at least 5 characters and at last 30 characters")
	private String username;
	
	@NotEmpty
	@Size(min=7,max=30,message="Password must contain at least 7 characters and at last 30 characters")
	private String password;
	
	@NotEmpty
	@Size(min=7,max=30,message="Password must contain at least 7 characters and at last 30 characters")
	private String passwordCheck;
	
	@NotEmpty
	private String email;
	
	@NotEmpty 
	private String role="USER";

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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
