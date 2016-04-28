<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../header.jsp" />

<div class="col-md-6 col-md-offset-1">
  <div class="panel panel-default">
    <div class="panel-body">
      <c:forEach items="${job_offers}" var="offer">
        <a href="/users/1">
          <div class="media">
            <div class="media-left">
              <a href="/users/${offer.userId}">
                <img class="user-placeholder" src="/img/user-placeholder.png">
              </a>
            </div>
            <div class="media-body">
              <h2 class="media-heading"><a href="/job_offers/${offer.userId}">${offer.title}</a></h2>
              <p><a href="/job_offers/${offer.userId}">${offer.description}</a></p>
              <c:forEach items="${offer.skills}" var="skill">
                <span class="label label-info">${skill.name}</span>
              </c:forEach>
            </div>
            <div class="media-right">
              <button class="btn btn-primary">Apply</button>
            </div>
          </div>
        </a>
        <div class="line-separator"></div>
      </c:forEach>
    </div>
  </div>
</div>

<div class="col-md-4">
  <div class="panel panel-default">
    <div class="panel-body">
      <form>
        <div class="input-group">
          <select name="skills">
            <option value="skill_1">Skill 1</option>
            <option value="skill_2">Skill 2</option>
            <option value="skill_3">Skill 3</option>
            <option value="skill_4">Skill 4</option>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">Filter</button>
      </form>
    </div>
  </div>

  <jsp:include page="../hire.jsp" />
</div>

<jsp:include page="../footer.jsp" />
