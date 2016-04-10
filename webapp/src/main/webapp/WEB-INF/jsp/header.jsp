<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
	<head>
		<title>NGRNetwork</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="shortcut icon" href="/img/favicon.png">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="/style/bootstrap.min.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="/style/icon.css">
		<link rel="stylesheet" href="/style/idangerous.swiper.css">
		<link rel="stylesheet" href="/style/jquery-ui.css">
		<link rel="stylesheet" href="/style/stylesheet.css">
	    <link rel="stylesheet" href="/style/loader.css">

</head>
<body >


<div class="be-loader">
	<div class="spinner">
			<img src="/img/logo-loader.png"  alt="">
			<p class="circle">
			  <span class="ouro">
			    <span class="left"><span class="anim"></span></span>
			    <span class="right"><span class="anim"></span></span>
			  </span>
			</p>
		</div>
    </div>

	<!-- THE HEADER -->
	<header>
		<div class="container-fluid custom-container">
			<div class="row no_row row-header">
				<div class="brand-be">
					<a href="index.html">
						<img class="logo-c active be_logo" src="/img/logo.png" alt="logo">
					</a>
				</div>
				<div class="header-menu-block">
					<button class="cmn-toggle-switch cmn-toggle-switch__htx"><span></span></button>
					<ul class="header-menu" id="one">
						<li><a href="activity.html">Activity</a></li>
						<li><a href="search.html">Job Offers</a>
							<ul>
								<li><a href="search.html">Filter 1</a></li>
								<li><a href="people.html">Filter 2</a></li>
								<li><a href="gallery.html">Filter 3</a></li>
							</ul>
						</li>
						<li><a href="author-login.html">My Portfolio</a></li>
						<li id="ad-work-li"><a id="add-work-btn" class="btn color-1" href="work.html">Add Work </a></li>
					</ul>
				</div>
				<div class="login-header-block">
					<div class="login_block">
						<a class="btn-login btn color-1 size-2 hover-2" href="" ><i class="fa fa-user"></i>
						Log in</a>
					</div>	
				</div>
			</div>
		</div>
	</header>