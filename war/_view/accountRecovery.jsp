<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Account Recovery</title>
	</head>
	
<link rel="stylesheet" type="text/css" href="_view/possibleLook.css"/>

	<body>
	
		
				<tr>
					<td class="label">Account Username:</td><br/>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
		<br/>
		
		<a href = "security">
		<input type="Submit" name="submit" value="Access Security Question"/>
		<a/>
		
	</body>
</html>