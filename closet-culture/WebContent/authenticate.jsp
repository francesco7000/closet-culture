<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

session.setAttribute("currentUser",null);
session.invalidate();

%>



<!DOCTYPE html>
<html lang="en">
<head>
<title>Authenticate | Closet Culture</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="icomoon/icomoon.css">
<link rel="stylesheet" type="text/css" href="css/vendor.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

	<%-- verifica se l'attributo di richiesta "errorMessage" è stato impostato --%>

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

		<section class="site-banner jarallax padding-large"
			style="background: url(images/hero-image.jpg) no-repeat; background-position: top;">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h2 class="page-title">Welcome to Closet Culture</h2>
					</div>
				</div>
			</div>
		</section>

		<section class="login-tabs padding-large no-padding-bottom">
			<div class="container">
				<div class="row">
					<div class="tabs-listing">
						<nav>
							<div class="nav nav-tabs d-flex justify-content-center"
								id="nav-tab" role="tablist">
								<button class="nav-link active" id="nav-sign-in-tab"
									data-bs-toggle="tab" data-bs-target="#nav-sign-in"
									type="button" role="tab" aria-controls="nav-sign-in"
									aria-selected="true">Sign In</button>
								<button class="nav-link" id="nav-register-tab"
									data-bs-toggle="tab" data-bs-target="#nav-register"
									type="button" role="tab" aria-controls="nav-register"
									aria-selected="false">Register</button>
							</div>
						</nav>


						<div class="tab-content" id="nav-tabContent">
							<div class="tab-pane fade active" id="nav-sign-in"
								role="tabpanel" aria-labelledby="nav-sign-in-tab">
								<form method="post" action="Login">
									<div class="form-group">
										<label for="sign-in">Username *</label> <input type="text"
											minlength="2" name="un" placeholder="Il tuo Username"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Password *</label> <input type="password"
											minlength="2" name="pw" placeholder="La tua Password"
											class="u-full-width bg-light" required>
									</div>
									<label> <span class="label-body"><a
											href="home.jsp">Oppure accedi come ospite</a></span>
									</label>
									<button type="submit" name="submit"
										class="btn btn-dark btn-full btn-medium" value="Login">Login</button>
								</form>
							</div>


							<div class="tab-pane fade" id="nav-register" role="tabpanel"
								aria-labelledby="nav-register-tab">
								<form method="post" action="Registration">
									<div class="form-group">
										<label for="register">Username *</label> <input type="text"
											name="un" placeholder="Il tuo Username"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="register">Email *</label> <input type="text"
											name="email" placeholder="La tua Email"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Password *</label> <input type="password"
											name="pw" placeholder="Your Password"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Conferma Password *</label> <input
											type="password" minlength="2" name="cpw"
											placeholder="Conferma Password" class="u-full-width bg-light"
											required>
									</div>
									<div class="form-group">
										<label for="sign-in">Nome</label> <input type="text"
											minlength="2" name="nome" placeholder="Nome"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Cognome</label> <input type="text"
											minlength="2" name="cognome" placeholder="Cognome"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Cellulare</label> <input type="text"
											minlength="2" name="cellulare" placeholder="Cellulare"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Provincia</label> <input type="text"
											minlength="2" name="prov" placeholder="Provincia"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Città</label> <input type="text"
											minlength="2" name="citta" placeholder="Città"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Cap</label> <input type="text"
											minlength="1" name="cap" placeholder="Cap"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Via</label> <input type="text"
											minlength="2" name="via" placeholder="Via"
											class="u-full-width bg-light" required>
									</div>
									<div class="form-group">
										<label for="sign-in">Numero</label> <input type="text"
											minlength="1" name="num" placeholder="Numero"
											class="u-full-width bg-light" required>
									</div>
									
									<label> <span class="label-body"><a
											href="home.jsp">Oppure accedi come ospite</a></span>
									</label>
									<button type="submit" name="submit"
										class="btn btn-dark btn-full btn-medium" value="Registration">Register</button>
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
</body>
</html>