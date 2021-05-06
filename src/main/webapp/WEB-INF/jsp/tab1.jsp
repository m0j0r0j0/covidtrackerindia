<div class="row" id="graphMH">
	<div class="col-md-12">
		<div class="card">
			<!-- <div class="card-header">
										<h5 class="card-title">TRACKER</h5>
									</div> -->
			<div class="card-body">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation"><a
						class="nav-link active" id="home-tab" data-bs-toggle="tab"
						href="#home" role="tab" aria-controls="home" aria-selected="true">Maharashtra</a></li>
					<li class="nav-item" role="presentation"><a class="nav-link"
						id="profile-tab" data-bs-toggle="tab" href="#profile" role="tab"
						aria-controls="profile" aria-selected="false">Maps</a></li>
					<!-- <li class="nav-item" role="presentation"><a
												class="nav-link" id="contact-tab" data-bs-toggle="tab"
												href="#contact" role="tab" aria-controls="contact"
												aria-selected="false">Vaccinated</a></li> -->
				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="home" role="tabpanel"
						aria-labelledby="home-tab">

						<div class="row">
							<div class="col-md-12">
								<div class="card">
									<div class="card-body">
										<h6>Click on bar to see no. of cases</h6>
										<div id="chart-profile-visit"></div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="tab-pane fade" id="profile" role="tabpanel"
						aria-labelledby="profile-tab">
						<div class="row"">
							<div class="col-12">
								<div class="card">
									<!-- <div class="card-header">
																<h4>India</h4>
															</div> -->
									<div class="card-body">
										<div class="col-md-12">

											<div class="card">
												<div class="card-body">
													<div class="row">
														<div class="col-10">
															<div class="tab-content" id="v-pills-tabContent">
																<div class="tab-pane fade show active" id="v-pills-home"
																	role="tabpanel" aria-labelledby="v-pills-home-tab">
																	<jsp:include page="../jsp/maharashtraMap.jsp" />
																</div>
																<div class="tab-pane fade" id="v-pills-profile"
																	role="tabpanel" aria-labelledby="v-pills-profile-tab">
																	<jsp:include page="../jsp/indiaMap.jsp" />
																</div>
																<!-- <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
																	aria-labelledby="v-pills-messages-tab">Integer pretium
																	dolor at sapien laoreet ultricies. Fusce congue et lorem id
																	convallis. Nulla volutpat tellus nec molestie finibus. In nec
																	odio tincidunt eros finibus ullamcorper. Ut sodales, dui nec
																	posuere finibus, nisl sem aliquam metus, eu accumsan lacus felis
																	at odio.</div>
																<div class="tab-pane fade" id="v-pills-settings" role="tabpanel"
																	aria-labelledby="v-pills-settings-tab">Sed lacus quam,
																	convallis quis condimentum ut, accumsan congue massa.
																	Pellentesque et quam vel massa pretium ullamcorper vitae eu
																	tortor.</div> -->
															</div>
														</div>
														<div class="col-2">
															<div class="nav flex-column nav-pills" id="v-pills-tab"
																role="tablist" aria-orientation="vertical">
																<a class="nav-link active" id="v-pills-home-tab"
																	data-bs-toggle="pill" href="#v-pills-home" role="tab"
																	aria-controls="v-pills-home" aria-selected="true">Maharashtra</a>
																<a class="nav-link" id="v-pills-profile-tab"
																	data-bs-toggle="pill" href="#v-pills-profile"
																	role="tab" aria-controls="v-pills-profile"
																	aria-selected="false">India</a>
																<!-- <a class="nav-link" id="v-pills-messages-tab"
																		data-bs-toggle="pill" href="#v-pills-messages" role="tab"
																		aria-controls="v-pills-messages" aria-selected="false">Messages</a>
																	<a class="nav-link" id="v-pills-settings-tab"
																		data-bs-toggle="pill" href="#v-pills-settings" role="tab"
																		aria-controls="v-pills-settings" aria-selected="false">Settings</a> -->
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
					<!-- <div class="tab-pane fade" id="contact" role="tabpanel"
												aria-labelledby="contact-tab">
												<div class="row">
													<div class="col-md-12">
														<div class="card">
															<div class="card-header">
																<h4>Out of total population: 139.11 crores</h4>
															</div>
															<div class="card-body">
																<div id="radialGradient"></div>
															</div>
														</div>
													</div>
												</div>
											</div> -->
				</div>
			</div>
		</div>
	</div>

</div>