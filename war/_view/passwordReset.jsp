<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Password Reset</title>
	</head>
	
<link rel="stylesheet" type="text/css" href="_view/possibleLook.css"/>

	<body>
	
		
				<tr>
					<td class="label">New Password:</td><br/>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
				<br/>
				<tr>
					<td class="label">Confirm New Password:</td><br/>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
		<br/>
		
		<a href = "loginPage">
		<input type="Submit" name="submit" value="Reset Password"/>
		<a/>
		
	</body>
</html>