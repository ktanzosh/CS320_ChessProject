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
				<label for="securityq">Security Question:</label> <br>

		<select name="secrityq" id="secrityq">
			<option value="base">-Select-</option>
			<option value="question1">Who was your favorite teacher?</option>
			<option value="question2">What was your first car?</option>
			<option value="question3">Who was your first pet?</option>
		</select>
		
		<br>
				<tr>
				<br/>
					<td class="label">Security Answer:</td><br/>
					<td><input type="text" name="second" size="12" value="${model.second}" /></td>
				</tr>
				
		<br/>
		
		<a href = "loginPage">
		<input type="Submit" name="submit" value="Submit"/>
		<a/>
		
		<br/>
		Already have an account?
		<br/>
		<a href = "loginPage">
		<input type="Submit" name="submit" value="Sign in"/>
		<br/>
		<a/>
	</body>
</html>