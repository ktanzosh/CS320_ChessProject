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
		
		<c:if test="${! empty errorMessageInvalidC}">
			<div class="error">${errorMessageInvalidC}</div>
		</c:if>

		<c:choose>
			<c:when test="${securityQAnswered == true}">
				<!--The Third Step </br> -->
				
				<form action="${pageContext.servletContext.contextPath}/accountRecovery" method="post">
					<tr>
						<td class="label">Account Username:</td><br/>
						<td><input type="label" name="username" size="12" value="${username}" required= true/></td>
					</tr>
					<br/>

					<tr>
						<div class="label">${securityQuestion}</div>
						<td><input type="text" name="securityAnswer" size="12" value="${securityAnswer}" required= true/></td>
					</tr>
					<br/>
			
					<tr>
						<td class="label">New Password:</td><br/>
						<td><input type="label" name="newPassword" size="12" value="${newPassword}" required= true/></td>
					</tr>
					<br/>

					<tr>
						<td class="label">Confirm New Password:</td><br/>
						<td><input type="label" name="checkPassword" size="12" value="${checkPassword}" required= true/></td>
					</tr>
					<br/>
					
					<input type="Submit" name="submit" value="Reset Password"/>
				</form>

			</c:when> 

			<c:when test="${usernameFound == true}">
				<!-- The Second Step </br> -->
				
				<form action="${pageContext.servletContext.contextPath}/accountRecovery" method="post">
					<tr>
						<td class="label">Account Username:</td><br/>
						<td><input type="label" name="username" size="12" value="${username}" required= true/></td>
					</tr>
					<br/>

					<tr>
						<div class="label">${securityQuestion}</div>
						<td><input type="text" name="securityAnswer" size="12" value="${securityAnswer}" required= true/></td>
					</tr>
					<br/>
						
					<input type="Submit" name="submit" value="Create a new Password"/>

				</form>

			</c:when>

			<c:otherwise>    <!-- else condition -->
				<!-- The First Step </br> -->

				<form action="${pageContext.servletContext.contextPath}/accountRecovery" method="post">
					<tr>
						<td class="label">Account Username:</td><br/>
						<td><input type="label" name="username" size="12" value="${username}" required= true/></td>
					</tr>
					<br/>
						
					<input type="Submit" name="submit" value="Access Security Question"/>
				</form>

			</c:otherwise>
	</c:choose>
	</body>
</html>