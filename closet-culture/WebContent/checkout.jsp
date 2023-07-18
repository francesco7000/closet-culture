<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,model.*"%>




<%
	UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser");
	ArrayList<PagamentoBean> tipiPag = (ArrayList<PagamentoBean>) request.getAttribute("tipiPag");

	if (currentUser == null) {
		Boolean isGuest = (Boolean) session.getAttribute("guest");

		
		
		
		if (isGuest != null && isGuest) {

			//se l'utente è guest allora si deve prima registrare
			response.sendRedirect("authenticate.jsp");

		} else {
			
			//se l'utente non è guest e non è nemmeno loggato allora viene riportato alla pagina di autenticazione
			response.sendRedirect("authenticate.jsp");

		}
	} else {
		//se le categorie sono vuote allora chiamo la servlet per ottenerle
		if (tipiPag == null) {
			//redirect alla servlet come parametro getCategorie per dirgli cosa deve fare
			response.sendRedirect("PagamentiServlet?action=getTipiPag");
		} else {
			//reset attributo per le prossime chiamate
			request.setAttribute("tipiPag", null);
		}
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Checkout | Closet Culture</title>
<meta charset="utf-8">

<link rel="stylesheet" type="text/css" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="icomoon/icomoon.css">
<link rel="stylesheet" type="text/css" href="css/vendor.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- script
    ================================================== -->
<script src="js/modernizr.js"></script>
</head>
<%@ include file="fragments/header.jsp"%>

<body>

	<section class="site-banner padding-small bg-light-grey">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumbs">
						<span class="item"> <a href="home.jsp">Home /</a>
						</span> <span class="item"> <a href="ricerca-prodotti.jsp">Shop
								/</a>
						</span> <span class="item">Checkout</span>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="shopify-cart checkout-wrap padding-large">
		<div class="container">
			<form class="form-group" action="GeneraFattura">
				<div class="row d-flex flex-wrap">
					<div class="col-lg-6">
						<h2 class="section-title">Indirizzo di Spedizione</h2>
						<div class="billing-details">
						<% if(currentUser != null) { %>
							<label for="fname">Provincia</label> <input type="text"
								id="fname" name="provincia" class="form-control"
								value="<%=currentUser.getProvincia()%>"> <label class="list-group-item d-flex">
							</label> 
							<label for="fname">Città</label> <input type="text"
								id="fname" name="citta" class="form-control" 
								value="<%=currentUser.getCitta()%>"> <label class="list-group-item d-flex">
							</label> 
							<label for="fname">Cap</label> <input type="text"
								id="fname" name="cap" class="form-control"
								value="<%=currentUser.getCap()%>"> <label class="list-group-item d-flex">
							</label> 
							<label for="fname">Via</label> <input type="text"
								id="fname" name="via" class="form-control"
								value="<%=currentUser.getVia()%>"> <label class="list-group-item d-flex">
							</label> 
							<label for="fname">Numero</label> 
							<input type="text" id="fname" name="numero" class="form-control" value="<%=currentUser.getNumero()%>"> <label class="list-group-item d-flex">
							</label> 
						</div>
						<% } %>
					</div>
					<div class="col-lg-6">
						<h2 class="section-title">Ulteriori informazioni</h2>
						<div class="billing-details">
							<label for="fname">Note ordine(opzionali)</label>
							<textarea class="form-control"
								placeholder="Scrivi le tue note addizionali qui..."></textarea>
						</div>

						<div class="your-order">
							<h2 class="section-title">Totale Carrello</h2>
							<div class="total-price">
          						<table class="table" aria-describedby="totali">
									<tbody>
										<tr class="subtotal">
											<th>Subtotale</th>
											<td data-title="Subtotal"><span
												class="price-amount amount text-primary"> <bdi>
													<span class="price-currency-symbol">$</span>2,370.00 </bdi>
											</span></td>
										</tr>
										<tr class="order-total">
											<th>Totale</th>
											<td data-title="Total"><span
												class="price-amount amount text-primary"> <bdi>
													<span class="price-currency-symbol">$</span>2,370.00 </bdi>
											</span></td>
										</tr>
									</tbody>
								</table>
								<div class="list-group mt-5 mb-3">


									<%
										if (tipiPag != null) {
											for (PagamentoBean tipoPag : tipiPag) {
									%>
									<label class="list-group-item d-flex"> <input
										class="form-check-input flex-shrink-0" type="radio"
										name="tipoPag" id="fpag" value="<%=tipoPag.getTp_descrizione()%>" checked>
										<div>
											<strong><%=tipoPag.getTp_nome()%></strong>
											<p><%=tipoPag.getTp_descrizione()%></p>
										</div>
									</label>

									<%
										}
										}
									%>

								</div>
								<button type="submit" name="submit"
									class="btn btn-dark btn-full btn-medium">Procedi all'ordine</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</section>


	<section id="shipping-information">
		<hr>
		<div class="container">
			<div
				class="row d-flex flex-wrap align-items-center justify-content-between">
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-truck"></i>
						<h4 class="block-title">
							<strong>Spedizione</strong> in 2/3 giorni lavorativi
						</h4>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-return"></i>
						<h4 class="block-title">
							<strong>Rimborso</strong> in giorni lavorativi
						</h4>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-tags1"></i>
						<h4 class="block-title">
							<strong>Acquista i nostri prodotti</strong> migliori
						</h4>
					</div>
				</div>
				<div class="col-md-3 col-sm-6">
					<div class="icon-box">
						<i class="icon icon-help_outline"></i>
						<h4 class="block-title">
							<strong> Hai altre domande?</strong> contatta un esperto
						</h4>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</section>

	<%@ include file="fragments/footer.jsp"%>

	<%@ include file="fragments/miniFooter.jsp"%>


	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/script.js"></script>
</body>
</html>