<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Add Numbers</title>
	</head>
	
<link rel="stylesheet" type="text/css" href="_view/possibleLook.css"/>

	<body>
	
		
				<tr>
					<td class="label">Username:</td><br/>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
				<tr>
				<br/>
					<td class="label">Password:</td><br/>
					<td><input type="text" name="second" size="12" value="${model.second}" /></td>
				</tr>
		<br/>
		
		<a href = "index">
		<input type="Submit" name="submit" value="Log In"/>
		<a/>
		
		<br/>
		Forgot password?
		<br/>
		<a href = "accountRecovery">
		<input type="Submit" name="submit" value="Reset Password"/>
		<br/>
		<a/>
		Don't have an account?
		<br/>
		<a href = "signupPage">
		<input type="Submit" name="submit" value="Sign up"/>
	</body>
</html>