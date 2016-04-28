<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="./header.jsp" />

<div class="col-md-6 col-md-offset-1">
  <div class="panel panel-default">
    <div class="panel-body">
      <a href="/users/1">
        <div class="media">
          <div class="media-left">
            <a href="/users/1">
              <img class="user-placeholder" src="/img/user-placeholder.png">
            </a>
          </div>
          <div class="media-body">
            <h2 class="media-heading">Usuario 1 (Link hardcodeado)</h2>
            <p>Esta es la descripcion hardcodeada de el usuario</p>
          </div>
        </div>
      </a>
    </div>
  </div>

  <div class="panel panel-default">
    <div class="panel-body">
      <h3>Share a post!</h3>
      <form>
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Title">
        </div>
        <div class="input-group">
          <input type="textarea" class="form-control" placeholder="Description">
        </div>
        <button type="submit" class="btn btn-primary">Post</button>
      </form>
    </div>
  </div>

  <div class="panel panel-default">
    <div class="panel-body">
      <c:choose>
        <c:when test="${fn:length(posts) gt 0}">
          <c:forEach items="${posts}" var="post">
            <div class="media">
              <div class="media-left">
                <a href="/users/${post.userId}">
                  <img class="media-object post-user-image" alt="64x64" src="/img/user-placeholder.png" data-holder-rendered="true">
                </a>
              </div>
              <div class="media-body">
                <h4 class="media-heading">${post.title}</h4>
                <p>${post.description}</p>
              </div>
            </div>
            <div class="line-separator"></div>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <p align="center"><b>We don't have posts loaded into our system. <br />Be the first to post something!</b></b</p>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>

<div class="col-md-4">
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="row">
        <div class="col-md-offset-1">
          <div class="media">
            <div class="media-left">
              <a href="/users/${offers[0].userId}">
                <img class="media-object post-user-image" alt="64x64" src="/img/user-placeholder.png" data-holder-rendered="true">
              </a>
            </div>
            <div class="media-body">
              <h4 class="media-heading">${offers[0].title}</h4>
              <p>${offers[0].description}</p>
              <c:forEach items="${offers[0].skills}" var="skill">
                <span class="label label-info">${skill.name}</span>
              </c:forEach>
            </div>
            <br>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4 col-md-offset-5">
          <button class="btn btn-primary">Apply</button>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="./hire.jsp" />
</div>

<jsp:include page="./footer.jsp" />
