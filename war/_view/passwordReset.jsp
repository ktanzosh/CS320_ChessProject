<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Password Reset</title>
	
		<link rel = "stylesheet" type="text/css" href="_view/possibleLook.css"/>
        <style type="text/css">
            .error {
                color: red;
            }
			.form {
                text-align: right;
            }
			a{							/* Hyperlink color */
	  			color: lightblue;
			}
        </style>

	</head>

	<body>
        
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/passwordReset" method="post">
	
		
				<tr>
					<td class="label">New Password:</td><br/>
					<td><input type="password" name="newPassword" size="12" value="${model.newPassword}" required= true/></td>
				</tr>
				<br/>
				<tr>
					<td class="label">Confirm New Password:</td><br/>
					<td><input type="password" name="checkPassword" size="12" value="${model.checkPassword}" required= true/></td>
				</tr>
		<br/>
		
		<input type="Submit" name="submit" value="Reset Password"/>
		</form>
		
	</body>
</html>