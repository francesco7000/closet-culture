<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, model.*"%>

<%
UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser");
if (currentUser == null) {
		//se l'utente è guest allora si deve prima registrare
		response.sendRedirect("errorPage.jsp");
}
%>



<!DOCTYPE html>
<html lang="en">
<head>
<title>Closet-Culture | Profilo </title>
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
<link rel="stylesheet" type="text/css" media="all"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/vendor.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">
<!-- script
    ================================================== -->
<script src="js/modernizr.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>



</head>
<%@ include file="fragments/header.jsp"%>
<body>

	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

	<section class="site-banner padding-small bg-light-grey">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumbs">
						<span class="item"> <a href="home.jsp">Home /</a>
						</span><span class="item">Gestione Profilo</span>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="single-product padding-large">
		<div class="container">
			<div class="row">
				
				<div class="col-md-12">
					<div class="product-info">
						<div class="element-header">
							<div class="tab-pane fade" id="nav-register" role="tabpanel"
								aria-labelledby="nav-register-tab">
								<form method="post" action="UserServlet">

									<input type="hidden" name="idUtente" value="<%=currentUser.getId()%>">
									<input type="hidden" name="idPersona" value="<%=currentUser.getIdPersona()%>">

									<div class="row">
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artCod">Nome</label> <input
													type="text" name="nome" required
													value="<%=currentUser.getNome()%>">
											</div>
										</div>
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artBarCod">Cognome</label> <input
													type="text" name="cognome"
													class="u-medium-width bg-light mx-2" required
													value="<%=currentUser.getCognome()%>">
											</div>
										</div>
										
								
										<div class="col-md-4 col-sm-12">

											<div class="form-group">
												<label for="artQta">Cellulare</label> <input
													type="number" class="u-small-width bg-light mx-2"  name="cellulare" required
													value="<%=currentUser.getCellulare()%>">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="artNome">Via</label> <input type="text"
											name="via" class="u-full-width bg-light" required
											value="<%=currentUser.getVia()%>">
									</div>

									<div class="form-group">
										<label for="artDescr">Provincia</label> <input
											type="text" name="provincia" class="u-full-width bg-light"
											required value="<%=currentUser.getProvincia()%>">
									</div>


									<div class="row">
										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artPrz">Città</label> <input
													type="text" name="citta" class="u-small-width bg-light"
													required value="<%=currentUser.getCitta()%>">
											</div>
										</div>

										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artSconto">Cap</label> <input
													type="text" name="cap"
													class="u-small-width bg-light" required
													value="<%=currentUser.getCap()%>">
											</div>
										</div>

										<div class="col-md-4 col-sm-12">
											<div class="form-group">
												<label for="artStag">Numero</label> <input
													type="text" name="numero" class="u-small-width bg-light"
													required value="<%=currentUser.getNumero()%>">
											</div>
										</div>
									</div>

									
								
									
									<div class="row">
									
									<div class="action-buttons">

										
										
										
									<!--  	<a href="nuovoArticoloAdmin.jsp"  name="action"  id="add-to-cart"
											class="btn btn-medium btn-dark" value="modifica">
											<span id="add-to-cart">Salva Modifiche</span>
										</a>-->



										<button  style="margin:15px 0px 0px !Important" type="submit" name="action" id="add-to-cart"
											class="btn btn-medium btn-dark" value="modificaprofilo">
											<span id="add-to-cart">Salva Modifiche</span>
										</button> 


									</div>
									</div>
									


								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>


	<%@ include file="fragments/footer.jsp"%>
	<%@ include file="fragments/miniFooter.jsp"%>



	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/script.js"></script>
</body>
</html>
