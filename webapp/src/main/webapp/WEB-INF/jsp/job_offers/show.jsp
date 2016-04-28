<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

    <div id="content-block">
    <div class="container be-detail-container">
      <div class="row">
        <div class="col-xs-12 col-md-3 left-feild">
          <div class="be-vidget hidden-xs hidden-sm affix-top" id="scrollspy" style="position: relative;">
            <h3 class="letf-menu-article">
              Navigation
            </h3>
            <div class="creative_filds_block">
              <ul class="ul nav">
                <li class="edit-ln ac"><a href="#job-description">Job description</a></li>
                <li class="edit-ln"><a href="#skills-required">Skills required</a></li>
                <li class="edit-ln"><a href="#apply-now">Application</a></li>
              </ul>

                <div class="be-user-statistic">
              <div class="stat-row clearfix">
                <i class="stat-icon icon-followers-b"></i>Applicants so far
                <span class="stat-counter">${quantityApplications}</span>
              </div>
            </div>
            </div>
          </div>
        </div>

        <div class="col-xs-12 col-md-9 _editor-content_">
          <div class="sec" data-sec="job-description">
            <div class="be-large-post">
              <div class="info-block style-2">
                <div class="be-large-post-align "><h3 class="info-block-label">${job.title}</h3></div>
              </div>
              <div class="be-large-post-align">
                <div class="row">
                  <div class="input-col col-xs-12">
                    ${job.description}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="sec" data-sec="skills-required">
            <div class="be-large-post">
              <div class="info-block style-2">
                <div class="be-large-post-align "><h3 class="info-block-label">SKILLS REQUIRED</h3></div>
              </div>
              <div class="be-large-post-align">
                <div>
                  <ul>
                    <c:forEach items="${jobOfferSkills}" var="skill">
                      <li class="skill"><span>${skill.name}<span></li>
                    </c:forEach>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class=" sec" data-sec="apply-now">
            <spring:url value="/job_offers/${job.id}/apply" var="actionUrl" />
      <form:form method="post" modelAttribute="userApply" action="${actionUrl}">
        <div class="be-large-post">
              <div class="info-block style-2">
                <div class="be-large-post-align"><h3 class="info-block-label">APPLY FOR THIS POSITION</h3></div>
              </div>
              <div class="be-large-post-align">
                <div class="row">
                  <div class="input-col col-xs-12 col-sm-10">
                    <div class="form-group focus-2">
                      <div class="form-label">Email</div>
                      <form:input class="form-input" path="email" type="text" placeholder="Enter email" />
                    </div>
                  </div>
                  <div class="col-xs-12 col-sm-2">
                    <input type="submit" class="btn full btn-input color-1 size-4 hover-1" value="APPLY">
                  </div>
                </div>
              </div>
            </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </div>


<jsp:include page="../footer.jsp" />