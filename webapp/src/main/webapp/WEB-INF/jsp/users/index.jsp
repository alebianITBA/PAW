<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../header.jsp" />

<div class="col-md-10 col-md-offset-1">
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="row">
        <div class="col-sm-6 col-md-4">
          <c:forEach items="${users}" var="user">
            <div class="thumbnail">
              <a href="/users/${user.id}">
                <img class="user-placeholder-index" src="/img/user-placeholder.png">
              </a>
              <div class="caption">
                <h3><a href="/users/${user.id}">${user.firstName} ${user.lastName}</a></h3>
                <p><a href="/users/${user.id}">${user.email}</a></p>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../footer.jsp" />
