
<!DOCTYPE html>

<html>
	<head>
		<title>Log-In</title>									<!--Page Banner Title-->

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
        
		<form action="${pageContext.servletContext.contextPath}/loginPage" method="post">
				<tr>
					<td class="label">Username:</td></br>
					<td><input type="text" name="user" size="12" value="${model.user}" required = true/></td></tr></br>
				</tr>
				<tr>
					<td class="label">Password:</td></br>
					<td><input type="password" name="password" size="12" value="${model.password}" required = true /></td></tr></br>
				</tr>
            <input type="Submit" name="submit" value="Log In">
        </form>

    	<!--Reset Password Link-->
		<span class="link">Forgot password? <a href="accountRecovery">Reset Password</a></span> <br>

		<!--New Account Link-->
		<span class="link">Don't have an account? <a href="signupPage">Sign up</a></span>
		
	</body>
</html>
