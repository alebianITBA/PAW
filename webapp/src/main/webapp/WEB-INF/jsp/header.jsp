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
    <c:url value="/" var="loginUrl" />
		<nav class="navbar navbar-default header-navbar">
		  <div class="container-fluid">
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
				    <div class="navbar-header">
							<a class="navbar-brand" href="/index">
				        <img id="logo" src="/img/logo.png"/>
				      </a>
				    </div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="collapse navbar-collapse">

              <c:if test="${loggedUser == null}">
                <form class="navbar-form navbar-left" action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
                  <div class="form-group">
                    <input type="text" name="j_email" class="form-control" placeholder="Email" />
                  </div>
                  <div class="form-group">
  									<input type="password" name="j_password" class="form-control" placeholder="Password" />
  								</div>
                  <button type="submit" class="btn btn-primary" value="Login!">Log in</button>
                </form>
              </c:if>

            </div>
					</div>

          <c:if test="${loggedUser != null}">
            <div class="col-md-2">
              <div class="collapse navbar-collapse">
                <div class="media">
                  <div class="media-body">
                    <c:if test="${loggedUser == null}">
                     <p>nadie logueado</p>
                    </c:if>
                    <c:if test="${loggedUser != null}">
                     <p>se logueo ${loggedUser.email}</p>
                    </c:if>
                  </div>
                  <div class="media-right">
                    <a href="/users/1">
                      <img class="user-placeholder-header" src="/img/user-placeholder.png">
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </c:if>

        </div>
			</div>
		</nav>

    <c:if test="${loggedUser != null}">
      <nav class="navbar navbar-inverse">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-2 col-md-offset-5">
              <a href="/users" class="navbar-text navbar-left">Users</a>
              <a href="/job_offers" class="navbar-text navbar-right">Job offers</a>
            </div>
          </div>
        </div>
      </nav>
    </c:if>
