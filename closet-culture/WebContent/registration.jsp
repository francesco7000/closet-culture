<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
   
   
    <!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrati | Closet Culture</title>
	<link rel="stylesheet" href="css/auth.css">
</head>
<body>
	<div class="container">
	
	<div id="custom-alert" class="alert-danger">
	<%
	if (request.getAttribute("errorMessage") != null) {
	%>
	<h2><%=request.getAttribute("errorMessage")%></h2>
	<%
	}
	%>
</div>

		<h2>Registrati a Closet Culture</h2>
		<form method="post" action="Registration">
			<label for="username">Username:</label>
			<input type="text" id="username" name="un" required>

			<label for="password">Password:</label>
			<input type="password" id="password" name="pw" required>

			<label for="confirm-password">Conferma Password:</label>
			<input type="password" id="confirm-password" name="cpw" required>

			<label for="email">Email:</label>
			<input type="email" id="email" name="email" required>

			<label for="nome">Nome:</label>
			<input type="text" id="nome" name="nome" required>

			<label for="cognome">Cognome:</label>
			<input type="text" id="cognome" name="cognome" required>

			<label for="cellulare">Cellulare:</label>
			<input type="tel" id="cellulare" name="cellulare" required>

			<input type="submit" value="Registrati">
		</form>

		<div id="signup-link">
			<a href="Login">Hai già un account? Accedi qui</a>
		</div>
	</div>

	<footer>
		<%@ include file="fragments/footer.jsp"%>
	</footer>
</body>
</html>