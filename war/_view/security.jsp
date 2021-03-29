<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Add Numbers</title>
	
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
	
		<form action="${pageContext.servletContext.contextPath}/security" method="post">
				<tr>
					<td class="label">What was your first car?</td><br/>
					<td><input type="text" name="securityAnswer" size="12" value="${model.securityAnswer}" required= true/></td>
				</tr>
		<br/>
		<input type="Submit" name="submit" value="Create a New Password"/>
		</form>
		
	</body>
</html>