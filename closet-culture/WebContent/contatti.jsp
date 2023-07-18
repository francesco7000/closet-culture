<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
  <head>
    <title>Closet Culture | Contatti</title>
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
  <body>


    <section class="site-banner jarallax padding-large" style="background: url(images/hero-image.jpg) no-repeat; background-position: top;">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="page-title">Contatti</h1>
            <div class="breadcrumbs">
              <span class="item">
                <a href="home.jsp">Home /</a>
              </span>
              <span class="item">Contattaci</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="contact-information padding-large">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <div class="section-header">
              <h2 class="section-title">Scopri i nostri servizi</h2>
            </div>
            <div class="contact-detail">
              <div class="detail-list">
                <p>Benvenuti nella pagina dei contatti! Se avete domande, dubbi o suggerimenti, non esitate a contattarci utilizzando le informazioni di seguito riportate. Potete inviarci un'email, telefonarci o compilare il modulo di contatto per mettervi in contatto con il nostro team di supporto. Saremo felici di rispondere alle vostre richieste il più rapidamente possibile.</p>
                <ul class="list-unstyled list-icon">
                  <li>
                    <a href="#"><i class="icon icon-phone"></i>+331014567</a>
                  </li>
                  <li>
                    <a href="mailto:info@yourcompany.com"><i class="icon icon-mail"></i>closetculture@gmail.com</a>
                  </li>
                  <li>
                    <a href="#"><i class="icon icon-map-pin"></i>Via Roma 24, Napoli</a>
                  </li>
                </ul>
              </div>
              
            </div>
          </div>
          <div class="col-md-6">
            <div class="contact-information">
              <div class="section-header">
                <h2 class="section-title">Mandaci un messaggio</h2>
              </div>
              <form name="contactform" action="home.jsp" method="post" class="contact-form">
                <div class="form-item">
                  <input type="text" minlength="2" name="name" placeholder="Name" class="u-full-width bg-light" required>
                  <input type="email" name="email" placeholder="E-mail" class="u-full-width bg-light" required>
                  <textarea class="u-full-width bg-light" name="message" placeholder="Message" style="height: 180px;" required></textarea>
                </div>
                <label>
                  <input type="checkbox" required>
                  <span class="label-body"> <a href="#">Accetto i termini e condizioni</a>
                  </span>
                </label>
                <button type="submit" name="submit" class="btn btn-dark btn-full btn-medium">Invia</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="google-map">
      <div class="mapouter">
        <div class="gmap_canvas">
<iframe width="100%" height="500" id="gmap_canvas" src="https://maps.google.com/maps?q=Universit%C3%A0+degli+Studi+di+Salerno&t=&z=15&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>          <a href="https://getasearch.com/fmovies"></a>
          <br>
          <style>
            .mapouter {
              position: relative;
              text-align: right;
              height: 500px;
              width: 100%;
            }
          </style>
          <a href="https://www.embedgooglemap.net">embedgooglemap.net</a>
          <style>
            .gmap_canvas {
              overflow: hidden;
              background: none !important;
              height: 500px;
              width: 100%;
            }
          </style>
        </div>
      </div>
    </section>

    <section id="brand-collection" class="padding-medium bg-light-grey">
      <div class="container">
        <div class="d-flex flex-wrap justify-content-between">
          <img src="images/brand1.png" alt="phone" class="brand-image">
          <img src="images/brand2.png" alt="phone" class="brand-image">
          <img src="images/brand3.png" alt="phone" class="brand-image">
          <img src="images/brand4.png" alt="phone" class="brand-image">
          <img src="images/brand5.png" alt="phone" class="brand-image">
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