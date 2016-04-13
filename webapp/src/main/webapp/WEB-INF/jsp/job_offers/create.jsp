<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

<div id="content-block">
    <div class="container be-detail-container">
      <div class="row">
        <div class="col-xs-12 col-md-12 _editor-content_">
          <div class="sec" data-sec="basic-information">
            <div class="be-large-post">
              <div class="info-block style-2">
                <div class="be-large-post-align "><h3 class="info-block-label">CREATE JOB OFFER</h3></div>
              </div>
              
              <spring:url value="/job_offers/post" var="actionUrl" />

				<form:form method="post" modelAttribute="jobOffer" action="${actionUrl}">
            
              <div class="be-large-post-align">
                <div class="row">
                  <div class="input-col col-xs-12 col-sm-12">
                    <div class="form-group fg_icon focus-2">
                      <div class="form-label">Title</div>
                      <form:input path="title" required="true" />
                    </div>
                  </div>
                  
                  <div class="input-col col-xs-12 col-sm-12">
                    <div class="form-group focus-2">
                      <div class="form-label">Description</div>
                      <form:input path="description" required="true"/>
                    </div>
                  </div>
                  
                  <div class="input-col col-xs-12 col-sm-6">
                    <div class="form-label">Skills required</div>
                    <div class="be-drop-down icon-none">
                      <span class="be-dropdown-content"> Select a skill</span>
                      <ul class="drop-down-list">
                        <li><a>Skill</a></li>
                        <li><a>Skill</a></li>
                        <li><a>Skill</a></li>
                        <li><a>Skill</a></li>
                        <li><a>Skill</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="row">
                	<input type="submit" value="CREATE"/>
                </div>
              </div>
              </form:form>
            </div>
          </div>


        </div>
      </div>
    </div>
  </div>


<jsp:include page="../footer.jsp" />
