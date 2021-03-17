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
					<td class="label">What was your first car?</td><br/>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
		<br/>
		
		<a href = "passwordReset">
		<input type="Submit" name="submit" value="Create a New Password"/>
		<a/>
		
	</body>
</html>