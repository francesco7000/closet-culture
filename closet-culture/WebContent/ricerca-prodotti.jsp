<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import="java.util.*, model.*"%>

<%
UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));

if ((currentUser == null) || (!currentUser.isValid())) {
	session.setAttribute("guest", true);

}

//ArrayList<ArticoloBean> obj = (ArrayList<ArticoloBean>) request.getAttribute("articoli");
request.setAttribute("articoli", null);


%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Ultras - Clothing Store eCommerce Store HTML Website Template</title>
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

    <div class="search-popup">
      <div class="search-popup-container">


        <form role="search" method="get" class="search-form" action="">
          <input type="search" id="search" name="search" class="search-field" placeholder="Type and press enter" value=""/>
          <button onclick="search()" type="submit" class="search-submit"><a href="#"><i class="icon icon-search"></i></a></button>
        </form>
        
        <script>
</script>

        <h5 class="cat-list-title">Browse Categories</h5>
        
        <ul class="cat-list">
          <li class="cat-list-item">
            <a href="shop.html" title="Men Jackets">Men Jackets</a>
          </li>
          <li class="cat-list-item">
            <a href="shop.html" title="Fashion">Fashion</a>
          </li>
          <li class="cat-list-item">
            <a href="shop.html" title="Casual Wears">Casual Wears</a>
          </li>
          <li class="cat-list-item">
            <a href="shop.html" title="Women">Women</a>
          </li>
          <li class="cat-list-item">
            <a href="shop.html" title="Trending">Trending</a>
          </li>
          <li class="cat-list-item">
            <a href="shop.html" title="Hoodie">Hoodie</a>
          </li>
          <li class="cat-list-item">
            <a href="shop.html" title="Kids">Kids</a>
          </li>
        </ul>
      </div>
    </div>
  
    <section class="site-banner jarallax min-height300 padding-large" style="background: url(images/hero-image.jpg) no-repeat; background-position: top;">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="page-title">Shop page</h1>
            <div class="breadcrumbs">
              <span class="item">
                <a href="home.jsp">Home /</a>
              </span>
              <span class="item">Shop</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <div class="shopify-grid padding-large">
      <div class="container">
        <div class="row">

          <section id="selling-products" class="col-md-9 product-store">
            <div class="container">
              <ul class="tabs list-unstyled">
                <li data-tab-target="#all" class="active tab">All</li>
                <li data-tab-target="#shoes" class="tab">Shoes</li>
                <li data-tab-target="#tshirts" class="tab">Tshirts</li>
                <li data-tab-target="#pants" class="tab">Pants</li>
                <li data-tab-target="#hoodie" class="tab">Hoodie</li>
                <li data-tab-target="#outer" class="tab">Outer</li>
                <li data-tab-target="#jackets" class="tab">Jackets</li>
                <li data-tab-target="#accessories" class="tab">Accessories</li>
              </ul>
              <div class="tab-content">
                <div id="all" data-tab-content class="active">
                  <div class="row d-flex flex-wrap">
                  
                  
                    <%
			// Il for crea una variabile del tipo ProdottoBean ed ad ogni iterazione va ad assegnare a quella variabile il contenuto di obj all'i-esima posizione 
			if (obj != null)
				for (ArticoloBean var : obj) {
					
					
			%>
                  
                  
                  
                    <div class="product-item col-lg-4 col-md-6 col-sm-6">
                      <div class="image-holder">
                        <img src="images/selling-products1.jpg" alt="Books" class="product-image">
                      </div>
                      <div class="cart-concern">
                        <div class="cart-button d-flex justify-content-between align-items-center">
                          <button type="button" class="btn-wrap cart-link d-flex align-items-center">add to cart <i class="icon icon-arrow-io"></i>
                          </button>
                          <button type="button" class="view-btn tooltip
                              d-flex">
                            <i class="icon icon-screen-full"></i>
                            <span class="tooltip-text">Quick view</span>
                          </button>
                          <button type="button" class="wishlist-btn">
                            <i class="icon icon-heart"></i>
                          </button>
                        </div>
                      </div>
                      <div class="product-detail">
                        <h3 class="product-title">
                          <a href="single-product.html">  <%=var.getNome()%></a>
                        </h3>
                        <div class="item-price text-primary"><%=var.getPrezzo()%></div>
                      </div>
                    </div>
                    
                    
                      
        <%
					
				}
			%>
                    
                    
                    
                   </div>
                   </div>
                   </div>
            
              <nav class="navigation paging-navigation text-center padding-medium" role="navigation">
                <div class="pagination loop-pagination d-flex justify-content-center">
                  <a href="#" class="pagination-arrow d-flex align-items-center">
                    <i class="icon icon-arrow-left"></i>
                  </a>
                  <span aria-current="page" class="page-numbers current">1</span>
                  <a class="page-numbers" href="#">2</a>
                  <a class="page-numbers" href="#">3</a>
                  <a href="#" class="pagination-arrow d-flex align-items-center">
                    <i class="icon icon-arrow-right"></i>
                  </a>
                </div>
              </nav>
            </div>
          </section>

          <aside class="col-md-3">
            <div class="sidebar">
              <div class="widgets widget-menu">
                <div class="widget-search-bar">
                  <form role="search" method="get" class="d-flex">
                    <input class="search-field" placeholder="Search" type="text">
                    <button class="btn btn-dark"><i class="icon icon-search"></i></button>
                  </form>
                </div> 
              </div>
              <div class="widgets widget-product-tags">
                <h5 class="widget-title">Tags</h5>
                <ul class="product-tags sidebar-list list-unstyled">
                  <li class="tags-item">
                    <a href="">White</a>
                  </li>
                  <li class="tags-item">
                    <a href="">Cheap</a>
                  </li>
                  <li class="tags-item">
                    <a href="">Branded</a>
                  </li>
                  <li class="tags-item">
                    <a href="">Modern</a>
                  </li>
                  <li class="tags-item">
                    <a href="">Simple</a>
                  </li>
                </ul>
              </div>
              <div class="widgets widget-product-brands">
                <h5 class="widget-title">Brands</h5>
                <ul class="product-tags sidebar-list list-unstyled">
                  <li class="tags-item">
                    <a href="">Nike</a>
                  </li>
                  <li class="tags-item">
                    <a href="">Adidas</a>
                  </li>
                  <li class="tags-item">
                    <a href="">Puma</a>
                  </li>
                  <li class="tags-item">
                    <a href="">Spike</a>
                  </li>
                </ul>
              </div>
              <div class="widgets widget-price-filter">
                <h5 class="widget-title">Filter By Price</h5>
                <ul class="product-tags sidebar-list list-unstyled">
                  <li class="tags-item">
                    <a href="">Less than $10</a>
                  </li>
                  <li class="tags-item">
                    <a href="">$10- $20</a>
                  </li>
                  <li class="tags-item">
                    <a href="">$20- $30</a>
                  </li>
                  <li class="tags-item">
                    <a href="">$30- $40</a>
                  </li>
                  <li class="tags-item">
                    <a href="">$40- $50</a>
                  </li>
                </ul>
              </div>
            </div>
          </aside>
          
        </div>        
      </div>      
    </div>

 

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