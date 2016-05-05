<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../header.jsp" />

<div class="col-md-10 col-md-offset-1">
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="row">
        <div class="media">
          <div class="media-left">
            <img class="user-placeholder-show" src="/img/user-placeholder.png">
          </div>
          <div class="media-body">
            <h1 class="media-heading">${user.firstName} ${user.lastName}</h1>
            <h3 class="media-heading">${user.email}</h3>
            <c:forEach items="${user.skills}" var="skill">
              <span class="label label-info">${skill.name}</span>
            </c:forEach>
          </div>
        </div>
      </div>

      <c:if test="${posts[0] != null}">
        <div class="row">
          <div class="media">
            <div class="media-body">
              <h1 class="media-heading">Posts</h1>
              <c:forEach items="${posts}" var="post">
                <div class="media">
                  <div class="media-body">
                    <h4 class="media-heading">${post.title}</h4>
                    <p>${post.description}</p>
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
        </div>
      </c:if>

      <c:if test="${posts[0] != null}">
        <div class="row">
          <div class="media">
            <div class="media-body">
              <h1 class="media-heading">Job offers created</h1>
              <c:forEach items="${offers}" var="offer">
                <div class="media-body">
                  <h2 class="media-heading"><a href="/job_offers/${offer.userId}">${offer.title}</a></h2>
                  <p><a href="/job_offers/${offer.userId}">${offer.description}</a></p>
                  <c:forEach items="${offer.skills}" var="skill">
                    <span class="label label-info">Skill</span>
                  </c:forEach>
                </div>
                <div class="media-right">
                  <button class="btn btn-primary">Apply</button>
                </div>
                <br>
              </c:forEach>
            </div>
          </div>
        </div>
      </c:if>

    </div>
  </div>
</div>

<jsp:include page="../footer.jsp" />
