package edu.ycp.cs320.ChessProject.model;

// model class for Numbers
// only the controller should be allowed to call the set methods
// the JSP will call the "get" and "is" methods implicitly
// when the JSP specifies game.min, that gets converted to
//    a call to model.getMin()
// when the JSP specifies if(game.done), that gets converted to
//    a call to model.isDone()
public class SignUpPage {
	private String user, password, confirmPassword, sec_question, sec_answer;
	private boolean storeInfo, passwordMatch;

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
	
	public void setStoreInfo(boolean storeInfo) {
		this.storeInfo = storeInfo;
	}
	
	public boolean getStoreInfo() {
		return storeInfo;
	}
	
	public void setCheckPasswordMatch(boolean passwordMatch) {
		this.passwordMatch = passwordMatch;
	}
	
	public boolean getCheckPasswordMatch() {
		return passwordMatch;
	}
}
