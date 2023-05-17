
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,model.*"%>


<%
	UserBean currtUser = (UserBean) (session.getAttribute("currentSessionUser"));

	boolean admin = false;
	if ((currtUser != null) && (currtUser.isValid()) && currtUser.getRuolo().equals("admin")) {

		admin = true;

	}
%>


<header id="header">
	<nav class="primary-nav padding-small">
		<div class="container">
			<div class="row d-flex align-items-center">
				<div class="col-lg-2 col-md-2">
					<div class="main-logo">
						<a href="home.jsp"> <img src="images/logo3.png" alt="logo">
						</a>
					</div>
				</div>
				<div class="col-lg-10 col-md-10">
					<div class="navbar">

						<div id="main-nav"
							class="stellarnav d-flex justify-content-end right">
							<ul class="menu-list">

								<li><a href="home.jsp"
									class="item-anchor active d-flex align-item-center"
									data-effect="Home">Home</a></li>


								<li><a href="chi-siamo.jsp" class="item-anchor"
									data-effect="About">Chi Siamo</a></li>

								<li class="menu-item has-sub"><a href="shop.html"
									class="item-anchor d-flex align-item-center" data-effect="Shop">Prodotti<i
										class="icon icon-chevron-down"></i></a>
									<ul class="submenu">
										<li><a href="ricerca-prodotti.jsp" class="item-anchor">Ricerca</a></li>

										<li><a href="cart.jsp" class="item-anchor">Carrello<span
												class="text-primary"></span></a></li>
									</ul></li>



								<%
									if (admin) {
								%>

								<li class="menu-item has-sub"><a href="#"
									class="item-anchor d-flex align-item-center"
									data-effect="Pages">Modalità<i
										class="icon icon-chevron-down"></i></a>
									<ul class="submenu">
										<li><a href="AdminServlet?action=utente" class="item-anchor">Utente<span
												class="text-primary"></span></a></li>
										<li><a href="AdminServlet?action=admin" class="item-anchor">Amministratore<span
												class="text-primary"></span></a></li>
									</ul></li>

								<%
									}
								%>




								<li><a href="contact.html" class="item-anchor"
									data-effect="Contact">Contatti</a></li>
									
									<li><a href="authenticate.jsp" class="item-anchor"
									data-effect="Contact">Logout</a></li>
							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
	</nav>
</header>