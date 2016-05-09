<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="./header.jsp" />
<div class="content">
   <div class="container">
      <c:if test="${offers[0] != null}">
         <div class="col-md-4 praesent f-right">
            <div class="l_g_r">
               <div class="dapibus">
                  <h2><a href="/job_offers/${offers[0].id}">${offers[0].title}</a></h2>
                  <p class="adm">Posted by <a href="/users/${offers[0].userId}">${offers[0].userId}</a></p>
                  <p>${offers[0].description}</p>
                  <p class="skill-container">
                     <c:forEach items="${offers[0].skills}" var="skill">
                        <span class="label label-info">${skill.name}</span>
                     </c:forEach>
                  </p>
                  <button class="btn btn-info btn-block btn-lg">Apply</button>
               </div>
            </div>
         </div>
      </c:if>
      <div class="col-md-8 praesent f-left">
         <div class="l_g_r biography-into">
            <div class="dapibus">
               <h4>SHARE A POST!</h4>
               <form>
                  <div class="form-group">
                     <input type="email" class="form-control input-lg" id="exampleInputEmail1" placeholder="Title">
                  </div>
                  <div class="form-group">
                     <textarea class="form-control input-lg post-textarea" placeholder="content"></textarea>
                  </div>
                  <button type="submit" class="btn btn-info btn-block btn-lg">Submit</button>
               </form>
               <c:choose>
                  <c:when test="${fn:length(posts) gt 0}">
                     <c:forEach items="${posts}" var="post">
                        <hr>
                        <h4>${post.title}</h4>
                        <p class="adm">Posted by <a href="/users/${post.userId}">${post.user.firstName} ${post.user.lastName}</a></p>
                        <p>${post.description}</p>
                        <!--<a href="details.html" class="link">Read More</a>-->
                     </c:forEach>
                  </c:when>
                  <c:otherwise>
                     <hr>
                     <p style="text-align:center;"><b>We don't have posts loaded into our system. <br />Be the first to post something!</b></b</p>
                  </c:otherwise>
               </c:choose>
            </div>
         </div>
      </div>
      <div class="col-md-4 praesent f-right">
         <jsp:include page="./hire.jsp" />
      </div>
   </div>
</div>
</div>
<jsp:include page="./footer.jsp" />