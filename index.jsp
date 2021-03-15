<!DOCTYPE html>

<html>
	<head>
		<title>Index</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/index" method="POST">

			<input type="Submit" name="Add" value="Add Numbers!">

			<input type="Submit" name="Guess" value="Guessing Game!">

			<input type="Submit" name="Multiply" value="Multiply Numbers!">

		</form>
	</body>
</html>
