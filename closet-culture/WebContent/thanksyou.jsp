<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String pdfFileName =(String) session.getAttribute("pdfFileName");

if (pdfFileName== null) {
	//se l'utente è guest allora si deve prima registrare
	response.sendRedirect("errorPage.jsp");
}



%>
<!DOCTYPE html>
<html lang="it">
  <head>
    <title>Closet Culture | Grazie</title>
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
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script src="js/modernizr.js"></script>
  </head>
  <%@ include file="fragments/header.jsp"%>
  
  <body onload="location.href='StampaFattura?parametro=<%=pdfFileName%>'">

   
    <section id="thank-you" class="padding-large bg-light-grey">
      <div class="container">
        <div class="row">
          <div class="page-header col-md-6">
            <div class="section-header">
              <h1 class="page-title">Grazie per l'ordine!</h1>
              <p>Grazie per il tuo acquisto! Apprezziamo la tua scelta e speriamo di rivederti presto.
</p>
            </div>
       <div class="section-header">      
    <form action="StampaFattura" method="POST">
       <input type="hidden" name="fattura" id="fat" value="<%=pdfFileName%>">
    
  <button type="submit" name="submit"
									class="btn btn-dark btn-full btn-medium">Scarica Fattura</button>
</form>
            </div>

          </div>
          <div class="contact-information">
            <div class="col-md-6">
              <div class="section-header">
                <h2 class="section-title">Contattaci</h2>
              </div>
              <div class="row">
                <div class="d-flex flex-wrap bg-light">
                  <div class="col-md-6 border-right border-bottom">
                    <div class="detail">
                      <h3>Cellulare</h3>
                      <ul class="list-unstyled">
                        <li>
                          <i class="icon icon-phone"></i>+33212456
                        </li>
                        <li>
                          <i class="icon icon-phone"></i>+33123456
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="col-md-6 border-bottom">
                    <div class="detail">
                      <h3>Email</h3>
                      <ul class="list-unstyled">
                        <li>
                          <i class="icon icon-envelope"></i>
                          <a href="mailto:info@yourcompany.com">amedeonapoli25@gmail.com</a>
                        </li>
                        <li>
                          <i class="icon icon-envelope"></i>
                          <a href="mailto:info@yourcompany.com">francescoambrosio50@gmail.com</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="col-md-6 border-right">
                    <div class="address detail">
                      <h3>Indirizzo</h3>
                      <ul class="list-unstyled">
                        <li>
                          <i class="icon icon-location"></i>
                          <span>Via Roma 22,Napoli</span>
                        </li>
                      </ul>
                    </div>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
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