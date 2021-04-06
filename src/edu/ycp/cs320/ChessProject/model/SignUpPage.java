package edu.ycp.cs320.ChessProject.model;

public class SignUpPage {
	private String user, password, confirmPassword, sec_question, sec_answer;

	public SignUpPage() {
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPasswordConfirmation(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getPasswordConfirmation() {
		return confirmPassword;
	}
	
	public void setSecurityQuestion(String sec_question) {
		this.sec_question = sec_question;
	}
	
	public String getSecurtyQuestion() {
		return sec_question;
	}
	
	public void setSecurityAnswer(String sec_answer) {
		this.sec_answer = sec_answer;
	}
	
	public String getSecurityAnswer() {
		return sec_answer;
	}

}
