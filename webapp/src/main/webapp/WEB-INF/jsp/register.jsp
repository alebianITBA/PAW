<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="./header.jsp" />

<div class="container-fluid">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">

      <form>
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Email">
        </div>
        <div class="input-group">
          <input type="text" class="form-control" placeholder="First name">
        </div>
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Last name">
        </div>
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
      </form>

    </div>
  </div>
</div>

<jsp:include page="./footer.jsp" />
<script type="text/javascript" src="/script/strength.js"></script>
<link rel="stylesheet" href="/style/strength.css">
<script type="text/javascript">$("#password").strength();</script>
