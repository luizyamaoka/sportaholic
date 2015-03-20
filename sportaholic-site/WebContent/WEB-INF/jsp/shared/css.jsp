<!-- jQuery -->
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/themes/smoothness/jquery-ui.css" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<style>
/* Space out content a bit */
body {
  padding-top: 20px;
  padding-bottom: 20px;
}
.header {
  padding-right: 15px;
  padding-left: 15px;
  margin-bottom: 10px;
}

#logo {
  line-height: 40px;
  margin-bottom: 10px;
}

.nav-pills > li > a {
    font-size: 14px;
    color: #4d4d4d;
    border: 1px solid #fff;
    border-radius: 5px 5px 5px 5px;
    padding: 10px;
}

.nav-pills > li > a:hover {
    background-color: #fff;
    color: #ff6600;
    border: 1px solid #ff6600;
}
.nav-pills > li > a:focus {
    background-color: #fff;
    color: #ff6600;
    border: 1px solid #ff6600;
}

.icon-bar {
  display: block;
  width: 22px;
  height: 2px;
  background-color: #4d4d4d;
  border-radius: 1px;
  margin-bottom: 3px;
}
.alert {
  margin: 10px 0;
}

.btn-big {
  font-size: 16px;
  color: #ff6600;
  font-weight: bold;
  border: 1px solid #ff6600;
  border-radius: 5px 5px 5px 5px;
  padding: 15px 40px 15px 40px;
}

.btn-big:hover{
  font-size: 16px;
  color: #fff;
  font-weight: bold;
  background-color: #ff6600;
  text-decoration: none;
}

.btn-medium {
  font-size: 14px;
  color: #ff6600;
  font-weight: bold;
  border: 1px solid #ff6600;
  border-radius: 5px 5px 5px 5px;
  padding: 10px 30px;
}

.btn-medium:hover{
  font-size: 14px;
  color: #fff;
  font-weight: bold;
  background-color: #ff6600;
  text-decoration: none;
}
.thumbnail {
  padding-bottom: 30px;
  height: 400px;
}
.thumbnail > .caption {
  overflow: hidden;
  height: 300px;
  margin-bottom: 30px;
}
.thumbnail > .caption > h4 {
  height: 80px;
}
.thumbnail > .caption > .author-name {
  height: 40px;
}
.thumbnail > .caption > .article-beginning {
  text-align: justify;
  margin: 40px 0px;
  overflow: hidden;
}

.product-carousel {
  height: 400px;
  display: block;
  padding: 4px 4px 20px 4px;
  margin-bottom: 20px;
  line-height: 1.42857143;
  background-color: #fff;
  border-radius: 4px;
  transition: all .2s ease-in-out;
}

.product-carousel > .product-image {
  max-width: 200px;
  max-height: 200px;
  display: block;
  margin-right: auto;
  margin-left: auto;
}

.product-carousel > .product-info > h4 {
  text-align: center;
  padding-bottom: 10px;
  font-size: 14px;
}

.product-carousel > .product-info > h4 > .brand {
  font-weight: bold;
}

.product-carousel > .product-info > p {
  text-align: center;
  margin-bottom: 20px;
}

.jumbotron {
  padding-bottom: 70px;  
}
.center-form {
  max-width: 600px;
  margin: 0 auto;
}

// Artigos

.article > h3 {
  color: #777777;
}

.article > .author {
  font-size: 14px;
}

.article > .content {
  font-size: 16px;
  line-height: 2em;
}

.article > .content > p > strong {
  color: #ff6600;
}

.article > .content > p {
  margin-bottom: 20px;
}

.article > .secondary-text {
  color: #999999;
  font-size: 16px;
  line-height: 2em;
}

.comment {
  font-size: 16px;
  border-radius: 5px 5px 5px 5px;
  background-color: #eeeeee;
  padding: 20px;
  margin: 10px 0;
}

.breadcrumb {
  background-color: #fff;
  margin-bottom: 10px;
}
.list-group > a:hover {
  background-color: #ff6600;
  color: #fff;
}
.list-group > a:focus {
  background-color: #ff6600;
  color: #fff;
}
.list-group > div > a:hover {
  background-color: #ff6600;
  color: #fff;
}
.list-group > a:hover > h4 {
  background-color: #ff6600;
  color: #fff;
}
.list-group > a:focus > h4 {
  background-color: #ff6600;
  color: #fff;
}
.read-more {
  font-size: 14px;
  padding: 20px 0;
}

.comment > .client {
  font-size: 14px;
}



// Client show page

.container > .center-form > .form-group > .client-field-name {
  text-align: right;
  color: #ff0000;
}
@media screen and (max-width: 768px){
  .container > .center-form > .form-group > .client-field-name {
    text-align: left; 
  }
}

.carousel-caption > p {
  font-size: 21px;
  line-height: 1.4;
}
#main-menu {
  	background-color: #ff6600;
  	height: 30px;
  	margin-bottom: 5px;
  }
#main-menu > .container > a {
	line-height: 30px;
	font-size: 15px;
	color: #ffffff;
	margin: 0 10px;
	font-weight: bold;
}
#main-menu > .container > a:hover {
	text-decoration: none;
	color: #4d4d4d;
}

#about-us {
  text-align: center;
}
#about-us > .row {
  margin-bottom: 20px;
}
#about-us > h3 {
  margin-bottom: 20px;
}
#about-us > p {
  font-size: 16px;
  line-height: 2em;
  text-align: left;
}
#about-us > p > strong {
  color: #ff6600;
}
#footer-box {
  margin-top: 20px;
  border-top: 1px solid #eee;
  padding-top: 20px;
}
#footer-box > .row > div > h4 {
  padding-left: 40px;
}
#footer-box > .row > div > ul {
  list-style-type: none;
}
#footer-social {
  float: left;
}
#footer-social > li {
  float: left;
  margin: 0 6px;
}
#footer-social > li > a {
  float: left;
}
.brand-logo {
  height: 100px;
  position: relative;
}
.brand-logo > a > img {
  display: block;
  margin-right: auto;
  margin-left: auto;
  position: absolute;
  margin: auto;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
#brand-info {
  text-align: left;
}
@media screen and (max-width: 48em){
    #brand-info{
        text-align: center;
    }
}

.brands-list > .brand-item {
  text-align: center;
  margin-bottom: 10px;
}

.product-main-image {
  height: 400px;
  position: relative;
}
.product-main-image > img {
  display: block;
  margin-right: auto;
  margin-left: auto;
  position: absolute;
  margin: auto;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
.product-description > h1 {
  font-size: 24px;
  font-weight: bold;
}
.product-description > h2 {
  font-size: 20px;
}

@media screen and (max-width: 48em){
    .product-description{
        text-align: center;
    }
}

</style>