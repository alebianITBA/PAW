<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

<div class="col-md-10 col-md-offset-1">
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="row">
        <div class="media">
          <div class="media-body">
            <h1 class="media-heading">${job.title} <span class="label label-info">${quantityApplications} applications</span></h1>
            <h3 class="media-heading">${job.description}</h3>
            <c:forEach items="${job.skills}" var="skill">
              <h2><span class="label label-info">Skill</span></h2>
            </c:forEach>
            <button class="btn btn-primary">Apply</button>
          </div>
          <div class="media-right">
            <a href="/users/${job.userId}">
              <img class="user-placeholder" src="/img/user-placeholder.png">
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../footer.jsp" />
