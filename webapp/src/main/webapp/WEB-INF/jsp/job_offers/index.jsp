<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header.jsp" />
<div class="content">
   <div class="container">
      <c:if test="${skills[0] != null}">
         <div class="col-md-8 praesent">
            <div class="l_g_r biography-into">
               <div class="dapibus">
                  <c:forEach items="${job_offers}" var="offer">
                     <div class="media">
                        <div class="media-left">
                           <a href="/users/${offer.userId}">
                           <img class="user-placeholder" src="/img/user-placeholder.png">
                           </a>
                        </div>
                        <div class="media-body">
                           <h4><a href="/job_offers/${offer.id}">${offer.title}</a></h4>
                           <p>${offer.description}</p>
                           <c:forEach items="${offer.skills}" var="skill">
                              <span class="label label-info">${skill.name}</span>
                           </c:forEach>
                        </div>
                        <div>
                           <div class="apply-button">
                              <button class="btn btn-info">APPLY</button>
                           </div>
                        </div>
                     </div>
                     <hr>
                  </c:forEach>
               </div>
            </div>
         </div>
      </c:if>
      <div class="col-md-4 praesent">
         <div class="l_g_r b-margin">
            <div class="dapibus biography-into">
               <div class="input-group b-margin">
                  <select name="skills" id="skills-select" class="form-control input-lg">
                     <c:forEach items="${skills}" var="skill">
                        <option value="${skill.id}">${skill.name}</option>
                     </c:forEach>
                  </select>
               </div>
               <button id="filter-offers" class="btn btn-info btn-block btn-lg form-control ">FILTER</button>
            </div>
         </div>
         <jsp:include page="../hire.jsp" />
         <div class="clearfix"></div>
      </div>
   </div>
</div>
<jsp:include page="../footer.jsp" />
<script type="text/javascript" src="/script/job_offers.js"></script>