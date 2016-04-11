<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
							<div class="be-large-post-align">
								<div class="row">
									<div class="input-col col-xs-12 col-sm-12">
										<div class="form-group fg_icon focus-2">
											<div class="form-label">Title</div>
											<input class="form-input" type="text" value="Enter title">
										</div>							
									</div>
									<div class="input-col col-xs-12 col-sm-12">
										<div class="form-group focus-2">
											<div class="form-label">Description</div>									
											<input class="form-input" type="text" value="Enter description">
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
							</div>
						</div>
					</div>


				</div>				
			</div>
		</div>
	</div>
	

<jsp:include page="../footer.jsp" />