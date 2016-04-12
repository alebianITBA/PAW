<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../header.jsp" />

  <!-- MAIN CONTENT -->
<div id="content-block" class="gallery-block">
    <div class="container custom-container be-detail-container">
      <div class="row">

        <c:forEach items="${job_offers}" var="job">

          <div class="gallery-box clearfix">
            <a class="gallery-a" href="#"><img src="/img/job_offer_placeholder.png" alt="img"></a>
            <div class="gallery-info">
              <h3>${job.title}<base></base></h3>
              <p>TODO: SKILLS</p>
            </div>
            <div class="gallery-btn">
              <a class="btn-login btn color-1 size-2 hover-2" href="/job_offers/${job.id}">+ INFO</a>
              <a class="btn-login btn color-1 size-2 hover-2" href="/job_applications/new?jobOfferId=${job.id}">Apply</a>
            </div>
          </div>

        </c:forEach>

      </div>
    </div>
  </div>


<jsp:include page="../footer.jsp" />
