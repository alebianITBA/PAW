<jsp:include page="../header.jsp" />

  <!-- MAIN CONTENT -->
  <div id="content-block">
    <div class="container be-detail-container">
      <div class="row">
        <div class="col-xs-12 col-md-4 left-feild">
          <div class="be-user-block style-3">
            <div class="be-user-detail">
              <a class="be-ava-user style-2" href="page1.html">
                <img src="/img/user_placeholder.jpg" alt="">
              </a>
              <a class="be-ava-left btn color-1 size-2 hover-1"><i class="fa fa-plus"></i>Connect</a>
              <div class="be-ava-right btn btn-message color-4 size-2 hover-7">
                <i class="fa fa-envelope-o"></i>message
                <div class="message-popup">
                  <div class="message-popup-inner container">
                    <div class="row">
                      <div class="col-xs-12 col-sm-8 col-sm-offset-2">
                        <i class="fa fa-times close-button"></i>
                        <h5 class="large-popup-title">Send Message To ${user.firstName}</h5>
                        <div class="form-group">
                          <textarea class="form-input" required="" placeholder="Your message"></textarea>
                        </div>
                        <a class="btn btn-right color-1 size-1 hover-1">send message</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <p class="be-use-name">${user.firstName} ${user.lastName}</p>
              <div class="be-user-info">${user.email}</div>
              <div class="be-text-tags style-2">
                <a href="page1.html">UI/UX</a>,
                <a href="page1.html">Web Design</a>,
                <a href="page1.html">Art Direction</a>
              </div>
              <div class="be-user-social">
                <a class="social-btn color-1" href="page1.html"><i class="fa fa-facebook"></i></a>
                <a class="social-btn color-2" href="page1.html"><i class="fa fa-twitter"></i></a>
                <a class="social-btn color-3" href="page1.html"><i class="fa fa-google-plus"></i></a>
                <a class="social-btn color-4" href="page1.html"><i class="fa fa-pinterest-p"></i></a>
                <a class="social-btn color-5" href="page1.html"><i class="fa fa-instagram"></i></a>
                <a class="social-btn color-6" href="page1.html"><i class="fa fa-linkedin"></i></a>
              </div>
              <a class="be-user-site" href="http://www.phoenix.cool"><i class="fa fa-link"></i> www.phoenix.cool</a>
            </div>
            <div class="be-user-statistic">
              <div class="stat-row clearfix"><i class="stat-icon icon-views-b"></i> Projects views<span class="stat-counter">218098</span></div>
              <div class="stat-row clearfix"><i class="stat-icon icon-like-b"></i>Appreciations<span class="stat-counter">14335</span></div>
              <div class="stat-row clearfix"><i class="stat-icon icon-followers-b"></i>Followers<span class="stat-counter">2208</span></div>
              <div class="stat-row clearfix"><i class="stat-icon icon-following-b"></i>Following<span class="stat-counter">0</span></div>
            </div>
          </div>
          <div class="be-desc-block">
            <div class="be-desc-author">
              <div class="be-desc-label">About Me</div>
              <div class="clearfix"></div>
              <div class="be-desc-text">TODO</div>
            </div>
            <div class="be-desc-author">
              <div class="be-desc-label">My Skills</div>
              <div class="clearfix"></div>
              <div class="be-desc-text">TODO</div>
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-md-8">
                    <div class="tab-wrapper style-1">
                        <div class="tab-nav-wrapper">
                            <div  class="nav-tab  clearfix">
                                <div class="nav-tab-item active">
                                    <span>Projects</span>
                                </div>
                                <div class="nav-tab-item ">
                                    <span>Work In Progress</span>
                                </div>
                            </div>
                        </div>
                        <div class="tabs-content clearfix">
                            <div class="tab-info active">
                <div class="row">
                  <div class="col-ml-12 col-xs-6 col-sm-4">
                    <div class="be-post">
                      <a href="page1.html" class="be-img-block">
                      <img src="/img/user_placeholder.jpg" alt="omg">
                      </a>
                      <a href="page1.html" class="be-post-title">The kitsch destruction of our world</a>
                      <span>
                        <a href="page1.html" class="be-post-tag">Interaction Design</a>,
                        <a href="page1.html" class="be-post-tag">UI/UX</a>,
                        <a href="page1.html" class="be-post-tag">Web Design</a>
                      </span>
                      <div class="author-post">
                        <img src="img/a1.png" alt="" class="ava-author">
                        <span>by <a href="page1.html">Hoang Nguyen</a></span>
                      </div>
                      <div class="info-block">
                        <span><i class="fa fa-thumbs-o-up"></i> 360</span>
                        <span><i class="fa fa-eye"></i> 789</span>
                        <span><i class="fa fa-comment-o"></i> 20</span>
                      </div>
                    </div>
                  </div>

                </div>
                            </div>
                            <div class="tab-info">
                <div class="row">
                  <div class="col-ml-12 col-xs-6 col-sm-4">
                    <div class="be-post">
                      <a href="page1.html" class="be-img-block">
                      <img src="/img/user_placeholder.jpg" alt="omg">
                      </a>
                      <a href="page1.html" class="be-post-title">Racing Queensland</a>
                      <span>
                        <a href="page1.html" class="be-post-tag">Interaction Design</a>,
                        <a href="page1.html" class="be-post-tag">UI/UX</a>,
                        <a href="page1.html" class="be-post-tag">Web Design</a>
                      </span>
                      <div class="author-post">
                        <img src="img/a7.png" alt="" class="ava-author">
                        <span>by <a href="page1.html">Hoang Nguyen</a></span>
                      </div>
                      <div class="info-block">
                        <span><i class="fa fa-thumbs-o-up"></i> 360</span>
                        <span><i class="fa fa-eye"></i> 789</span>
                        <span><i class="fa fa-comment-o"></i> 20</span>
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
  </div>




<jsp:include page="../footer.jsp" />
