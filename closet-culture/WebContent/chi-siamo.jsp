<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
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
  </head>
  <%@ include file="fragments/header.jsp"%>
  <body>


    <section class="site-banner jarallax min-height300 padding-large" style="background: url(images/hero-image.jpg) no-repeat;">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="page-title">Chi Siamo</h1>
            <div class="breadcrumbs">
              <span class="item">
                <a href="index.html">Home /</a>
              </span>
              <span class="item">Chi Siamo</span>
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

    <section id="about-us">
      <div class="container ">
        <div class="row d-flex align-items-center">
          <div class="col-lg-6 col-md-12">
            <div class="image-holder">
              <img src="images/single-image1.jpg" alt="single" class="about-image">
            </div>
          </div>
          <div class="col-lg-6 col-md-12">
            <div class="detail">
              <div class="display-header">
                <h2 class="section-title">Chi Siamo?</h2>
                <p>
                Closet Culture è un'azienda di abbigliamento che offre capi di alta qualità e dal design unico. La nostra missione è quella di offrire ai nostri clienti l'esperienza di acquisto più piacevole possibile, fornendo un servizio clienti eccezionale e offrendo una vasta selezione di abbigliamento alla moda. Ci impegniamo per la sostenibilità e l'etica nella produzione dei nostri prodotti, lavorando solo con fornitori che rispettano gli standard di lavoro equo e che utilizzano materiali sostenibili. Forniamo un'ampia gamma di abbigliamento da uomo e da donna, inclusi abiti, camicie, pantaloni, maglie e molto altro, tutti realizzati con i migliori tessuti e le migliori tecniche di produzione. Siamo orgogliosi di essere un'azienda che si prende cura dei propri clienti e dell'ambiente, e ci impegniamo a fornire capi di abbigliamento che durino nel tempo e che siano sempre alla moda.
                </p>
                <div class="btn-wrap">
                  <a href="ricerca-prodotti.jsp" class="btn btn-dark btn-medium d-flex align-items-center" tabindex="0">Acquista<i class="icon icon-arrow-io"></i>
                  </a>
                </div>
              </div>
            </div>
          </div>
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

	<%@ include file="fragments/footer.jsp"%>

	<%@ include file="fragments/miniFooter.jsp"%>

    

    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>