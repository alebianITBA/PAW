<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
  <head>
    <title>conectOn</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="/img/favicon.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/style/general.css">
  </head>
  <body >

    <div class="header-top">
      <div class="container">
        <div id="logo">
          <a href="index.html"><img src="/img/logo.png" title="logo" height=50 width=250></a>
        </div>
        <span class="menu"></span>
        <div class="top-menu">
          <ul class="cl-effect-16">
            
            <c:if test="${loggedUser == null}">
              <li>
                <form class="navbar-form navbar-left" action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
                  <div class="form-group">
                    <input type="text" name="j_email" class="form-control" placeholder="Email" />
                  </div>
                  <div class="form-group">
                    <input type="password" name="j_password" class="form-control" placeholder="Password" />
                  </div>
                  <button type="submit" class="submit-button" value="Login!">LOG IN</button>
                </form>
              </li>
            </c:if>

            <c:if test="${loggedUser != null}">
               <li><a href="/users" data-hover="USERS">USERS</a></li>
               <li><a href="/job_offers" data-hover="JOB OFFERS">JOB OFFERS</a></li>
               <li><a href="/users/${loggedUser.id}" data-hover="MY PROFILE">MY PROFILE</a></li>
            </c:if>

          </ul>
        </div>
        <!-- script for menu -->
        <script>
        $( "span.menu" ).click(function() {
          $( ".top-menu" ).slideToggle( "slow", function() {
            // Animation complete.
          });
        });
      </script>
      <!-- script for menu -->
        <div class="clearfix"></div>
      </div>
    </div>
