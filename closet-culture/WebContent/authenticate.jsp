<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authenticate | Closet Culture</title>
<link rel="stylesheet" href="css/authenticate.css">
</head>
<body>

	<%-- verifica se l'attributo di richiesta "errorMessage" è stato impostato --%>

	<div class="container">
		<h2>Welcome to Closet Culture</h2>

<div id="custom-alert" class="alert-danger">
	<%
	if (request.getAttribute("errorMessage") != null) {
	%>
	<h2><%=request.getAttribute("errorMessage")%></h2>
	<%
	}
	%>
</div>
		<form method="post" action="Login">

			<label for="username">Username:</label> 
			<input type="text" id="username" name="un">
			<label for="password">Password:</label>
			<input type="password" id="password" name="pw" required> 
			
			<input type="submit" value="Login">

		</form>

		<div id="signup-link">
			<a href="#">Don't have an account? Sign up here</a>
		</div>

	</div>

	<footer>
		<%@ include file="fragments/footer.jsp"%>
	</footer>
</body>
</html>