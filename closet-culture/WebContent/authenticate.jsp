<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Authenticate | Closet Culture</title>
	<link rel="stylesheet" href="css/authenticate.css">
</head>
<body>

			<%-- verifica se l'attributo di richiesta "errorMessage" è stato impostato --%>
<% if (request.getAttribute("errorMessage") != null) { %>
   <script>
        alert("${requestScope['errorMessage']}");
    </script></div>
<% 
} 
%>
	
	<div class="container">
		
		<h2>Welcome to Closet Culture</h2>
		
		<form method="post" action="Login">
			
			<label for="username">Username:</label>
			<input type="text" id="username" name="un" required>
			
			<label for="password">Password:</label>
			<input type="password" id="password" name="pw" required>
			
			<input type="submit" value="Login">
			
		</form>
		
		<div id="signup-link">
			<a href="#">Don't have an account? Sign up here</a>
		</div>
		
	</div>
	
	<!-- Aggiungi i tuoi script JavaScript -->
	<script src="script.js"></script>
	  
	  <footer>
  <%@ include file = "fragments/footer.jsp" %>
  </footer>
</body>
</html>