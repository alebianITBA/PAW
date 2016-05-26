<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="java.util.*" %>

<jsp:include page="../header.jsp"/>

<div class="content">
    <div class="container">
      <div class="row">
        <c:forEach items="${users}" var="user">
            <div class="col-md-3 praesent center biography-into">
                <div class="l_g_r">
                    <div class="dapibus">
                        <a href="<c:url value='/users/${user.id}'/>">
                            <img class="user-image" src="${user.gravatar}"></a>
                            <div class="caption">
                                <h3>
                                    <a href="<c:url value='/users/${user.id}'/>">${user.firstName} ${user.lastName}</a>
                                </h3>
                                <p class="center">
                                    <a href="<c:url value='/users/${user.id}'/>"><i>${user.email}</i></a>
                                </p>
                                <p class="skill-container">
                                    <c:forEach items="${user.skills}" var="skill">
                                      <a class="no-underline" href="<c:url value='/job_offers?skill_id=${skill.id}'/>"><span class="label label-info">${skill.name}</span></a>
                                    </c:forEach>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
          </div>

          <div class="row">
            <div id="pagination" data-value="${item_count}"></div>
          </div>

        </div>
    </div>

    <jsp:include page="../footer.jsp" />

    <script type="text/javascript" src="<c:url value='/script/pagination.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/script/general.js'/>"></script>
