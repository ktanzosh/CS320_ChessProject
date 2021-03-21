<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Account Recovery</title>
		
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
	
		<form action="${pageContext.servletContext.contextPath}/accountRecovery" method="post">
				<tr>
					<td class="label">Account Username:</td><br/>
					<td><input type="label" name="username" size="12" value="${model.username}" required= true/></td>
				</tr>
		<br/>
			
		<input type="Submit" name="submit" value="Access Security Question"/>
		</form>
		
	</body>
</html>