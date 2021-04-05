<!DOCTYPE html>

<c:choose>
	<c:when test="${! empty securityQAnswereds}">
		The security Question has been answered.
	</c:when>
	<c:when test="${! empty usernameFounds}">
	  The username was found.
	</c:when>
	<c:otherwise>
	  Enter Username.
	</c:otherwise>
  </c:choose>