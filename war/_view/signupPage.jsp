
<!DOCTYPE html>

<html>
	<head>
		<title>Sign-Up</title>
		
		<link rel = "stylesheet" type="text/css" href="_view/possibleLook.css"/>
        <style type="text/css">
            .error {
                color: red;
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
        
		<form action="${pageContext.servletContext.contextPath}/signupPage" method="post">
				<tr>
					<td class="label">Username:</td></br>
					<td><input type="label" name="user" size="12" value="${model.user}" required=true /></td></br>
				</tr>

				<tr>
					<td class="label">Password:</td></br>
					<td><input type="label" name="password" size="12" value="${model.password}" /></td></br>
				</tr>

				<tr>
					<td class="label">Confirm Password:</td></br>
					<td><input type="label" name="confirmPassword" size="12" value="${model.getPasswordConfirmation()}" /></td></br>
				</tr>

				<td class="label">Security Question:</td></br>
				<select name="sec_question" id="sec_question">
					<!--<option value="base">-Select-</option>-->
					<option value="Who was your favorite teacher?">Who was your favorite teacher?</option>
					<option value="What was your first car?">What was your first car?</option>
					<option value="Who was your first pet?">Who was your first pet?</option>
				</select></br>
		
				<tr>
					<td class="label">Security Answer:</td></br>
					<td><input type="label" name="sec_answer" size="12" value="${model.getSecurityAnswer()}" required=true/></td></br>
				</tr>

				<input type="Submit" name="submit" value="Create an Account"></br>

			</form>

		<!--New Account Link-->
		<span class="link">Already have an account? <a href="loginPage">Log in</a></span>

	</body>
</html>