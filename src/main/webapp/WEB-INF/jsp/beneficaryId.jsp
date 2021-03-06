<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Covid-19 Tracker</title>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/bootstrap.css">

<link rel="stylesheet" href="/vendors/iconly/bold.css">

<link rel="stylesheet"
	href="/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet"
	href="/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="/css/app.css">
<link rel="shortcut icon" href="/images/favicon.svg" type="image/x-icon">



<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.css">

<link rel="stylesheet" href="vendors/apexcharts/apexcharts.css">

<link rel="stylesheet"
	href="vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet"
	href="vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="css/app.css">
<link rel="shortcut icon" href="images/favicon.svg" type="image/x-icon">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style>
#graphMH {
	width: 137%;
}

#pageloader {
	background: rgba(255, 255, 255, 0.8);
	visibility: hidden;
	height: 100%;
	position: fixed;
	width: 100%;
	z-index: 9999;
}

#pageloader p {
	left: 50%;
	margin-left: -32px;
	margin-top: -32px;
	position: absolute;
	top: 50%;
}
}
</style>

<script>
	$(document).ready(function() {
		$("#myform").on("submit", function() {
			$("#pageloader").css('visibility', 'visible');
		});
	});
</script>

<script data-ad-client="ca-pub-7502259186504959" async
	src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>

</head>
<script type="text/javascript">
	function download() {
		var mobileNo = document.getElementById("mobileNo").value;
		alert(mobileNo);
		$.ajax({
			type : 'GET',
			dataType : "json",
			url : 'http://localhost:8080/sendOTP',
			data : {
				'mobileNo' : mobileNo
			},
			success : function(result) {
				alert(result);
				document.getElementById("txnId").value = result;
			}
		});
	}
</script>
<body style="background-color: #e6e8f4;">

	<div id="pageloader">
		<p style="color: black; font-size: 30px;">Please Wait...</p>
		<!-- <img
			src="http://cdnjs.cloudflare.com/ajax/libs/semantic-ui/0.16.1/images/loader-large.gif"
			alt="processing..." /> -->
	</div>

	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">

				<div class="sidebar-menu">
					<ul class="menu">

						<li class="sidebar-item active "><a href="/"
							class='sidebar-link'> <i class="bi bi-grid-fill"></i> <span>Dashboard</span>
						</a></li>

						<br>
						<li class="sidebar-title">Support</li>
						<li class="sidebar-item  "><a
							href="https://twitter.com/M0j0r0j0" target="_blank"
							class='sidebar-link'> <i class="bi bi-telephone-fill"></i> <span>Contact
									Developer</span>
						</a></li>
					</ul>
				</div>
				<button class="sidebar-toggler btn x">
					<i data-feather="x"></i>
				</button>
			</div>
		</div>
		<div id="main">
			<header class="mb-3">
				<a href="#" class="burger-btn d-block d-xl-none"> <i
					class="bi bi-justify fs-3"></i>
				</a>
			</header>

			<div class="page-heading">
				<h3>COVID-19 Tracker</h3>
				<p>
					Stay <span class="text-danger"><i class="bi bi-heart"></i></span>
					Safe&nbsp;&nbsp;-&nbsp;&nbsp; <a
						href="https://twitter.com/M0j0r0j0">by Prasad Tikkas</a>
				</p>
				<div class="buttons">
					<a href="/" class="btn btn-sm btn-warning">Back</a>
				</div>
			</div>
			<div class="page-content">
				<section class="row">
					<div class="col-md-12">
						<div class="card">
							<!-- <div class="card-header">
								<h5 class="card-title">Check your nearest vaccination
									center and slots availability</h5>
							</div> -->
							<div class="card-body">
								<ul class="nav nav-tabs" id="myTab" role="tablist">
									<li class="nav-item" role="presentation"><a
										class="nav-link active" id="home-tab" data-bs-toggle="tab"
										href="#home" role="tab" aria-controls="home"
										aria-selected="true">Download Certificate</a></li>
								</ul>
								<div class="tab-content" id="myTabContent">
									<div class="tab-pane fade show active" id="home"
										role="tabpanel" aria-labelledby="home-tab">
										<section id="basic-horizontal-layouts">
											<div class="row match-height">
												<div class="col-md-6 col-12">
													<div class="card">
														<div class="card-content">
															<div class="card-body">
																<form class="form form-horizontal" id="createReport"
																	action="/downloadCertificate" method="get">
																	<input type="hidden" value="${token}" id="token"
																		name="token" />
																	<div class="form-body">
																		<div class="row">
																			<div class="col-md-4">
																				<label>Beneficiary Id</label>
																			</div>
																			<div class="col-md-8 form-group">
																				<input type="text" id="beneficiaryId"
																					name="beneficiaryId" class="form-control"
																					placeholder="Enter Beneficiary Id" value="">
																			</div>
																			<div class="col-sm-4 form-group">
																				<input type="submit" id="download" name="download"
																					value="Download" class="btn btn-primary me-1 mb-1" />
																			</div>
																		</div>
																	</div>
																</form>
															</div>
														</div>
													</div>
												</div>
											</div>
										</section>

									</div>

								</div>
							</div>
						</div>
					</div>
			</div>
			</section>
		</div>

		<footer>
			<div class="footer clearfix mb-0 text-muted">
				<div class="float-start">
					<p>2021 &copy; Prasad Tikkas</p>
				</div>
				<div class="float-end">
					<!-- <p>
							Crafted with <span class="text-danger"><i
								class="bi bi-heart"></i></span> by <a href="http://ahmadsaugi.com">A.
								Saugi</a>
						</p> -->
				</div>
			</div>
		</footer>
	</div>
	</div>

	<!-- Radial chart -->
	<script src="vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>

	<script src="vendors/dayjs/dayjs.min.js"></script>
	<script src="vendors/apexcharts/apexcharts.js"></script>
	<script src="js/pages/ui-apexchart.js"></script>

	<script src="js/main.js"></script>
	<!-- end -->


	<script src="/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="/js/bootstrap.bundle.min.js"></script>

	<script src="/vendors/apexcharts/apexcharts.js"></script>
	<script src="/js/pages/dashboard.js"></script>
	<script src="/js/main.js"></script>

</body>

</html>