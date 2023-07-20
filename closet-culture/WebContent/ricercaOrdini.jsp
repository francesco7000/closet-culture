<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>


<%

UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser");
if (currentUser == null) {
		//se l'utente è guest allora si deve prima registrare
		response.sendRedirect("errorPage.jsp");
}else{
	if (!currentUser.getRuolo().equals("admin")) {
		response.sendRedirect("errorPage.jsp");
	}
}

    String query = request.getParameter("query");
    List<String> userList = new ArrayList<String>();
       
%>
    
    
<!DOCTYPE html>

<html lang="IT">

<head>
  <title>Closet Culture</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="icomoon/icomoon.css">
    <link rel="stylesheet" type="text/css" media="all" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/vendor.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <!-- script
    ================================================== -->
    <script src="js/modernizr.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
      <script>
      $(document).ready(function() {
          $("#search-button").on("click", function() {
              var query = $("#search-input").val();
              console.log(query);
              $.ajax({
        	      type: 'GET',
        	      url: 'AdminServlet',
        	      data: { action: 'ricercaOrdini', query:query},
        	      success: function(data) {
        	        // Aggiorna il contenuto della sezione dei prodotti
        	        $('#user-list').html(data);
        	      }
        	    });
          });
      });
  </script>

    <meta charset="UTF-8">
    <title>Ricerca Ordini</title>
</head>
  <%@ include file="fragments/header.jsp"%>

<body style="margin:15px!important">
    <h1>Ricerca Ordini</h1>
    <input type="text" id="search-input" placeholder="Inserisci l'username dell'utente">
    <button type="button" id="search-button">Cerca</button>
    <h1>Ordini Trovati</h1>
    
    <hr>
    
    <div>
     
     <ul id="user-list">
     
     </ul>
    </div>
     <hr>
        <%@ include file="fragments/footer.jsp"%>
	<%@ include file="fragments/miniFooter.jsp"%>


  
</body>
</html>