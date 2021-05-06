<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<head>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>


<script>
	$(document).ready(function() {
		$('#example').DataTable();
	});
</script>
</head>

<html>
<body>
	<div class="row" id="graphMH">
		<div class="col-md-12">
			<div class="card">
				<!-- <div class="card-header">
										<h5 class="card-title">TRACKER</h5>
									</div> -->
				<div class="card-body">
					<ul class="nav nav-tabs" id="myTab" role="tablist">

						<li class="nav-item" role="presentation"><a class="nav-link active"
							id="profile2-tab" data-bs-toggle="tab" href="#profile2"
							role="tab" aria-controls="profile2" aria-selected="false">Vaccine Administered</a>
						</li>

						<li class="nav-item" role="presentation"><a
							class="nav-link" id="home2-tab" data-bs-toggle="tab"
							href="#home2" role="tab" aria-controls="home2"
							aria-selected="true">State</a></li>

						<!-- <li class="nav-item" role="presentation"><a class="nav-link"
							id="contact-tab" data-bs-toggle="tab" href="#contact" role="tab"
							aria-controls="contact" aria-selected="false">Vaccinated</a></li> -->
					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="profile2" role="tabpanel"
							aria-labelledby="profile2-tab">
							<div class="row">
								<div class="col-md-12">
									<div class="card">											
										<div class="card-header">
											<h6>${totalVaccine} Out of total population: 139.11 crores</h6>
										</div>
										<div class="card-body">
											<div id="radialGradient"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="home2" role="tabpanel"
							aria-labelledby="home2-tab">

							<div class="row" id="table-inverse">
								<div class="col-12">
									<div class="card">
									<br>
										<div class="card-content">
										<div class="table-responsive">
											<table id="example"
												class="table table-striped table-bordered"
												style="width: 100%">
												<thead>
													<tr>
														<th>State/UT</th>
														<th>CONFIRMED</th>
														<th>ACTIVE</th>
														<th>RECOVERED</th>
														<th>LAST UPDATE TIME - IST</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="list" items="${mapStateList}">
														<tr>
															<td>${list.state}</td>
															<td>
																<table>
																	<tr>
																		<td style="font-size: 11px; color: red;">
																			<c:if test="${list.todaysConfirmed ne '0'}">
																				&#x2191; ${list.todaysConfirmed}
																			</c:if>
																			
																		</td>
																	</tr>
																	<tr>
																		<td>${list.confirmed}</td>
																	</tr>
																</table>
															</td>
															<td>
																<table>
																	<tr>
																		<td style="font-size: 11px;">&nbsp;</td>
																	</tr>
																	<tr>
																		<td>${list.active}</td>
																	</tr>
																</table>
															</td>
															<td>
																<table>
																	<tr>
																		<td style="font-size: 11px; color: #0cbb0c;">
																			<c:if test="${list.todaysRecovered ne '0'}">
																				&#x2191; ${list.todaysRecovered}
																			</c:if>
																		</td>
																	</tr>
																	<tr>
																		<td>${list.recovered}</td>
																	</tr>
																</table>
															</td>
															<td>
																<table>
																	<tr>
																		<td style="font-size: 11px;">&nbsp;</td>
																	</tr>
																	<tr>
																		<td>${list.date}</td>
																	</tr>
																</table>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- <div class="tab-pane fade" id="contact" role="tabpanel"
							aria-labelledby="contact-tab"></div> -->

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
