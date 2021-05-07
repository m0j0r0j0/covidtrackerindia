<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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


<c:choose>
	<c:when test="${isLocalUrl == true}">
		<c:if test="${isCountEnable ne 'Y'}">
			<script async
				src="https://api.countapi.xyz/hit/prasadtikkas_dev/visits?callback=cb"></script>
		</c:if>
		<c:if test="${isCountEnable eq 'Y'}">
			<script async
				src="https://api.countapi.xyz/get/prasadtikkas_dev/visits?callback=cb"></script>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${isCountEnable ne 'Y'}">
			<script async
				src="https://api.countapi.xyz/hit/prasadtikkas_prod1/visits?callback=cb"></script>
		</c:if>
		<c:if test="${isCountEnable eq 'Y'}">
			<script async
				src="https://api.countapi.xyz/get/prasadtikkas_prod1/visits?callback=cb"></script>
		</c:if>
	</c:otherwise>
</c:choose>

<script>
	function cb(response) {
		document.getElementById('visits').innerText = response.value;
	}	
</script>


<style>
#graphMH {
	width: 137%;
}
</style>

<script data-ad-client="ca-pub-7502259186504959" async
	src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>

</head>
<script type="text/javascript">
$(function() {
	$(".chartContainer").CanvasJSChart({
		title: {
			text: "District Tracker"
		},
		axisY: {
			title: "Count",
			includeZero: false
		},
		axisX: {
			interval: 1
		},
		data: [
		{
			type: "column", //try changing to column, area
			toolTipContent: "{label}: {y} ",
			dataPoints: [
				<c:forEach var="list" items="${districtList}">
					{ label: "${list.district}",  y: <c:out value="${list.active}"/>},
	        	</c:forEach>
				
			]
		}
		]
	});
});
</script>
<body style="background-color: #e6e8f4;">
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<!-- <div class="sidebar-header">
                    <div class="d-flex justify-content-between">
                        <div class="logo">
                            <a href="index.html"><img src="/images/logo/logo.png" alt="Logo" srcset=""></a>
                        </div>
                        <div class="toggler">
                            <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                        </div>
                    </div>
                </div> -->
				<div class="sidebar-menu">
					<ul class="menu">
						<li class="sidebar-title">
							<!-- <p>
								Stay <span class="text-danger"><i
									class="bi bi-heart"></i></span> Safe&nbsp;&nbsp;-&nbsp;&nbsp;<a href="https://twitter.com/M0j0r0j0">P.
									Tikkas</a>
							</p> -->
						</li>

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
						<%-- <c:if test="${isCountEnable eq 'Y'}">
							<li class="sidebar-title">
								<table>
									<tr style="font-size: 11px;">
										<td>Visitor's count : &nbsp;</td>
										<td><div id="visits"></div></td>
									</tr>
								</table>
							</li>
						</c:if> --%>
						<!-- <li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-stack"></i> <span>Components</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="component-alert.html">Alert</a>
								</li>
								<li class="submenu-item "><a href="component-badge.html">Badge</a>
								</li>
								<li class="submenu-item "><a
									href="component-breadcrumb.html">Breadcrumb</a></li>
								<li class="submenu-item "><a href="component-button.html">Button</a>
								</li>
								<li class="submenu-item "><a href="component-card.html">Card</a>
								</li>
								<li class="submenu-item "><a href="component-carousel.html">Carousel</a>
								</li>
								<li class="submenu-item "><a href="component-dropdown.html">Dropdown</a>
								</li>
								<li class="submenu-item "><a
									href="component-list-group.html">List Group</a></li>
								<li class="submenu-item "><a href="component-modal.html">Modal</a>
								</li>
								<li class="submenu-item "><a href="component-navs.html">Navs</a>
								</li>
								<li class="submenu-item "><a
									href="component-pagination.html">Pagination</a></li>
								<li class="submenu-item "><a href="component-progress.html">Progress</a>
								</li>
								<li class="submenu-item "><a href="component-spinner.html">Spinner</a>
								</li>
								<li class="submenu-item "><a href="component-tooltip.html">Tooltip</a>
								</li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-collection-fill"></i> <span>Extra
									Components</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a
									href="extra-component-avatar.html">Avatar</a></li>
								<li class="submenu-item "><a
									href="extra-component-sweetalert.html">Sweet Alert</a></li>
								<li class="submenu-item "><a
									href="extra-component-toastify.html">Toastify</a></li>
								<li class="submenu-item "><a
									href="extra-component-rating.html">Rating</a></li>
								<li class="submenu-item "><a
									href="extra-component-divider.html">Divider</a></li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-grid-1x2-fill"></i> <span>Layouts</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="layout-default.html">Default
										Layout</a></li>
								<li class="submenu-item "><a
									href="layout-vertical-1-column.html">1 Column</a></li>
								<li class="submenu-item "><a
									href="layout-vertical-navbar.html">Vertical with Navbar</a></li>
								<li class="submenu-item "><a href="layout-horizontal.html">Horizontal
										Menu</a></li>
							</ul></li>

						<li class="sidebar-title">Forms &amp; Tables</li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-hexagon-fill"></i> <span>Form
									Elements</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="form-element-input.html">Input</a>
								</li>
								<li class="submenu-item "><a
									href="form-element-input-group.html">Input Group</a></li>
								<li class="submenu-item "><a
									href="form-element-select.html">Select</a></li>
								<li class="submenu-item "><a href="form-element-radio.html">Radio</a>
								</li>
								<li class="submenu-item "><a
									href="form-element-checkbox.html">Checkbox</a></li>
								<li class="submenu-item "><a
									href="form-element-textarea.html">Textarea</a></li>
							</ul></li>

						<li class="sidebar-item  "><a href="form-layout.html"
							class='sidebar-link'> <i
								class="bi bi-file-earmark-medical-fill"></i> <span>Form
									Layout</span>
						</a></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-pen-fill"></i> <span>Form
									Editor</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="form-editor-quill.html">Quill</a>
								</li>
								<li class="submenu-item "><a
									href="form-editor-ckeditor.html">CKEditor</a></li>
								<li class="submenu-item "><a
									href="form-editor-summernote.html">Summernote</a></li>
								<li class="submenu-item "><a
									href="form-editor-tinymce.html">TinyMCE</a></li>
							</ul></li>

						<li class="sidebar-item  "><a href="table.html"
							class='sidebar-link'> <i class="bi bi-grid-1x2-fill"></i> <span>Table</span>
						</a></li>

						<li class="sidebar-item  "><a href="table-datatable.html"
							class='sidebar-link'> <i
								class="bi bi-file-earmark-spreadsheet-fill"></i> <span>Datatable</span>
						</a></li>

						<li class="sidebar-title">Extra UI</li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-pentagon-fill"></i> <span>Widgets</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="ui-widgets-chatbox.html">Chatbox</a>
								</li>
								<li class="submenu-item "><a href="ui-widgets-pricing.html">Pricing</a>
								</li>
								<li class="submenu-item "><a
									href="ui-widgets-todolist.html">To-do List</a></li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-egg-fill"></i> <span>Icons</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a
									href="ui-icons-bootstrap-icons.html">Bootstrap Icons </a></li>
								<li class="submenu-item "><a
									href="ui-icons-fontawesome.html">Fontawesome</a></li>
								<li class="submenu-item "><a href="ui-icons-dripicons.html">Dripicons</a>
								</li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-bar-chart-fill"></i> <span>Charts</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="ui-chart-chartjs.html">ChartJS</a>
								</li>
								<li class="submenu-item "><a
									href="ui-chart-apexcharts.html">Apexcharts</a></li>
							</ul></li>

						<li class="sidebar-item  "><a href="ui-file-uploader.html"
							class='sidebar-link'> <i class="bi bi-cloud-arrow-up-fill"></i>
								<span>File Uploader</span>
						</a></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-map-fill"></i> <span>Maps</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="ui-map-google-map.html">Google
										Map</a></li>
								<li class="submenu-item "><a href="ui-map-jsvectormap.html">JS
										Vector Map</a></li>
							</ul></li>

						<li class="sidebar-title">Pages</li>

						<li class="sidebar-item  "><a href="application-email.html"
							class='sidebar-link'> <i class="bi bi-envelope-fill"></i> <span>Email
									Application</span>
						</a></li>

						<li class="sidebar-item  "><a href="application-chat.html"
							class='sidebar-link'> <i class="bi bi-chat-dots-fill"></i> <span>Chat
									Application</span>
						</a></li>

						<li class="sidebar-item  "><a href="application-gallery.html"
							class='sidebar-link'> <i class="bi bi-image-fill"></i> <span>Photo
									Gallery</span>
						</a></li>

						<li class="sidebar-item  "><a
							href="application-checkout.html" class='sidebar-link'> <i
								class="bi bi-basket-fill"></i> <span>Checkout Page</span>
						</a></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-person-badge-fill"></i>
								<span>Authentication</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="auth-login.html">Login</a>
								</li>
								<li class="submenu-item "><a href="auth-register.html">Register</a>
								</li>
								<li class="submenu-item "><a
									href="auth-forgot-password.html">Forgot Password</a></li>
							</ul></li>

						<li class="sidebar-item  has-sub"><a href="#"
							class='sidebar-link'> <i class="bi bi-x-octagon-fill"></i> <span>Errors</span>
						</a>
							<ul class="submenu ">
								<li class="submenu-item "><a href="error-403.html">403</a>
								</li>
								<li class="submenu-item "><a href="error-404.html">404</a>
								</li>
								<li class="submenu-item "><a href="error-500.html">500</a>
								</li>
							</ul></li>

						<li class="sidebar-title">Raise Support</li>

						<li class="sidebar-item  "><a
							href="https://zuramai.github.io/mazer/docs" class='sidebar-link'>
								<i class="bi bi-life-preserver"></i> <span>Documentation</span>
						</a></li>

						<li class="sidebar-item  "><a
							href="https://github.com/zuramai/mazer/blob/main/CONTRIBUTING.md"
							class='sidebar-link'> <i class="bi bi-puzzle"></i> <span>Contribute</span>
						</a></li>

						<li class="sidebar-item  "><a
							href="https://github.com/zuramai/mazer#donate"
							class='sidebar-link'> <i class="bi bi-cash"></i> <span>Donate</span>
						</a></li> -->

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
					<%-- <c:if test="${localIpAddress eq '/192.168.1.11'}"> --%>
						<div class="buttons">
							<a href="vaccineDashboard" class="btn btn-sm btn-warning">Vaccine Appointment Availability</a>
							<a href="certificate" class="btn btn-sm btn-warning">Download Certificate</a>
						</div>
					<%-- </c:if> --%>
					<a href="http://selfregistration.cowin.gov.in" target="_blank">Click here to book appoinment</a>
					<c:if test="${isCountEnable eq 'Y'}">
						<table>
							<tr style="font-size: 11px;">
								<td>Visitor's count : &nbsp;</td>
								<td><div id="visits"></div></td>
							</tr>
						</table>
					</c:if>

				</p>
			</div>
			<div class="page-content">
				<section class="row">
					<div class="col-12 col-lg-9">
						<div class="row">
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5" style="height: 125px;">
										<div class="row">
											<!-- <div class="col-md-4">
												<div class="stats-icon red">
													<i class="iconly-boldProfile"></i>
												</div>
											</div> -->
											<div class="col-md-12">
												<h6 class="text-muted font-semibold">Last Updated:</h6>
												<h6 class="font-extrabold mb-0"
													style="color: rgb(72, 79, 119);">${state.date}</h6>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5" style="height: 125px;">
										<div class="row">
											<div class="col-md-8">
												<h6 class="text-muted font-semibold">Confirmed</h6>
												<h6 class="font-extrabold mb-0" style="color: blue;">${state.confirmed}</h6>
											</div>
											<div id="chart-europe"
												style="min-height: 95px; margin-top: -24px; margin-left: -5px;"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5" style="height: 125px;">
										<div class="row">
											<!-- <div class="col-md-4">
												<div class="stats-icon green">
													<i class="iconly-boldProfile"></i>
												</div>
											</div> -->
											<div class="col-md-8">
												<h6 class="text-muted font-semibold">Recovered</h6>
												<h6 class="font-extrabold mb-0" style="color: green;">${state.recovered}</h6>
											</div>
											<div id="chart-america"
												style="min-height: 95px; margin-top: -24px; margin-left: -5px;"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5" style="height: 125px;">
										<div class="row">
											<!-- <div class="col-md-4">
												<div class="stats-icon red">
													<i class="iconly-boldProfile"></i>
												</div>
											</div> -->
											<div class="col-md-8">
												<h6 class="text-muted font-semibold">Active</h6>
												<h6 class="font-extrabold mb-0" style="color: red;">${state.active}</h6>
											</div>
											<div id="chart-indonesia"
												style="min-height: 95px; margin-top: -24px; margin-left: -5px;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<jsp:include page="../jsp/tab1.jsp" />
						<jsp:include page="../jsp/tab2.jsp" />

						<div class="row">
							<div class="col-12 col-xl-4">
								<div class="card">
									<div class="card-header">
										<h4>More Coming Soon!</h4>
									</div>
									<!-- <div class="card-body">
										<div class="row">
											<div class="col-6">
												<div class="d-flex align-items-center">
													<svg class="bi text-primary" width="32" height="32"
														fill="blue" style="width: 10px">
                                                        <use
															xlink:href="/vendors/bootstrap-icons/bootstrap-icons.svg#circle-fill" />
                                                    </svg>
													<h5 class="mb-0 ms-3">Europe</h5>
												</div>
											</div>
											<div class="col-6">
												<h5 class="mb-0">862</h5>
											</div>
											<div class="col-12">
												<div id="chart-europe"></div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="d-flex align-items-center">
													<svg class="bi text-success" width="32" height="32"
														fill="blue" style="width: 10px">
                                                        <use
															xlink:href="/vendors/bootstrap-icons/bootstrap-icons.svg#circle-fill" />
                                                    </svg>
													<h5 class="mb-0 ms-3">America</h5>
												</div>
											</div>
											<div class="col-6">
												<h5 class="mb-0">375</h5>
											</div>
											<div class="col-12">
												<div id="chart-america"></div>
											</div>
										</div>
										<div class="row">
											<div class="col-6">
												<div class="d-flex align-items-center">
													<svg class="bi text-danger" width="32" height="32"
														fill="blue" style="width: 10px">
                                                        <use
															xlink:href="/vendors/bootstrap-icons/bootstrap-icons.svg#circle-fill" />
                                                    </svg>
													<h5 class="mb-0 ms-3">Indonesia</h5>
												</div>
											</div>
											<div class="col-6">
												<h5 class="mb-0">1025</h5>
											</div>
											<div class="col-12">
												<div id="chart-indonesia"></div>
											</div>
										</div>
									</div>  -->
								</div>
							</div>
							<!-- <div class="col-12 col-xl-8">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>Latest Comments</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover table-lg">
                                                <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>Comment</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="col-3">
                                                            <div class="d-flex align-items-center">
                                                                <div class="avatar avatar-md">
                                                                    <img src="/images/faces/5.jpg">
                                                                </div>
                                                                <p class="font-bold ms-3 mb-0">Si Cantik</p>
                                                            </div>
                                                        </td>
                                                        <td class="col-auto">
                                                            <p class=" mb-0">Congratulations on your graduation!</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-3">
                                                            <div class="d-flex align-items-center">
                                                                <div class="avatar avatar-md">
                                                                    <img src="/images/faces/2.jpg">
                                                                </div>
                                                                <p class="font-bold ms-3 mb-0">Si Ganteng</p>
                                                            </div>
                                                        </td>
                                                        <td class="col-auto">
                                                            <p class=" mb-0">Wow amazing design! Can you make another
                                                                tutorial for
                                                                this design?</p>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div> -->
						</div>
					</div>
					<div class="col-12 col-lg-3">
						<div class="card">
							<div class="card-body py-4 px-5">
								<div class="d-flex align-items-center">
									<div class="avatar avatar-xl">
										<img src="/images/faces/2.jpg" alt="Face 2">
									</div>
									<div class="ms-3 name">
										<h5 class="font-bold">Prasad Tikkas</h5>
										<h6 class="text-muted mb-0">
											<a href="https://twitter.com/M0j0r0j0" target="_blank">@M0j0r0j0</a>
										</h6>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="card">
                            <div class="card-header">
                                <h4>Recent Messages</h4>
                            </div>
                            <div class="card-content pb-4">
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="/images/faces/4.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">Hank Schrader</h5>
                                        <h6 class="text-muted mb-0">@johnducky</h6>
                                    </div>
                                </div>
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="/images/faces/5.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">Dean Winchester</h5>
                                        <h6 class="text-muted mb-0">@imdean</h6>
                                    </div>
                                </div>
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="/images/faces/1.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">John Dodol</h5>
                                        <h6 class="text-muted mb-0">@dodoljohn</h6>
                                    </div>
                                </div>
                                <div class="px-4">
                                    <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3'>Start
                                        Conversation</button>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <h4>Visitors Profile</h4>
                            </div>
                            <div class="card-body">
                                <div id="chart-visitors-profile"></div>
                            </div>
                        </div> -->
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
	<script>
    var optionsProfileVisit = {
    		annotations: {
    			position: 'back'
    		},
    		dataLabels: {
    			enabled:false
    		},
    		chart: {
    			type: 'bar',
    			height: 300
    		},
    		fill: {
    			opacity:1
    		},
    		plotOptions: {
    		},
    		series: [{
    			name: 'cases',
    			data: [
    				<c:forEach var="list" items="${districtList}">
    					<c:out value="${list.active}"/>,
        			</c:forEach>
    				]
    		}],
    		colors: '#ff7c43',
    		xaxis: {
    			categories: [
    				<c:forEach var="list" items="${districtList}">
    					"${list.district}",
	        		</c:forEach>    					
    				],
    		},
    	}
    var chartProfileVisit = new ApexCharts(document.querySelector("#chart-profile-visit"), optionsProfileVisit);
    chartProfileVisit.render();
    

    var radialGradientOptions = {
      series: [<c:out value="${totalVaccinePercentage}"/>],
      chart: {
        height: 350,
        type: "radialBar",
        toolbar: {
          show: true,
        },
      },
      plotOptions: {
        radialBar: {
          startAngle: -135,
          endAngle: 225,
          hollow: {
            margin: 0,
            size: "70%",
            background: "#fff",
            image: undefined,
            imageOffsetX: 0,
            imageOffsetY: 0,
            position: "front",
            dropShadow: {
              enabled: true,
              top: 3,
              left: 0,
              blur: 4,
              opacity: 0.24,
            },
          },
          track: {
            background: "#fff",
            strokeWidth: "67%",
            margin: 0, // margin is in pixels
            dropShadow: {
              enabled: true,
              top: -3,
              left: 0,
              blur: 4,
              opacity: 0.35,
            },
          },

          dataLabels: {
            show: true,
            name: {
              offsetY: -10,
              show: true,
              color: "#888",
              fontSize: "17px",
            },
            value: {
              formatter: function(val) {
                return parseInt(val);
              },
              color: "#111",
              fontSize: "36px",
              show: true,
            },
          },
        },
      },
      fill: {
        type: "gradient",
        gradient: {
          shade: "dark",
          type: "horizontal",
          shadeIntensity: 0.5,
          gradientToColors: ["#ABE5A1"],
          inverseColors: true,
          opacityFrom: 1,
          opacityTo: 1,
          stops: [0, 100],
        },
      },
      stroke: {
        lineCap: "round",
      },
      labels: ["%"],
    };
    var radialGradient = new ApexCharts(document.querySelector("#radialGradient"), radialGradientOptions);
    radialGradient.render();
    
    

    var optionsEurope = {
    	series: [{
    		name: 'series1',
    		data: [
    			<c:forEach var="list" items="${dailyGraphList}">
					${list.confirmed},
				</c:forEach>
    		]
    	}],
    	chart: {
    		height: 80,
    		type: 'area',
    		toolbar: {
    			show:false,
    		},
    	},
    	colors: ['#287ffc'],
    	stroke: {
    		width: 2,
    	},
    	grid: {
    		show:false,
    	},
    	dataLabels: {
    		enabled: false
    	},
    	xaxis: {
    		type: 'String',
    		categories: [
    			<c:forEach var="list" items="${dailyGraphList}">
					"${list.date}",
				</c:forEach>
    		],
    		axisBorder: {
    			show:false
    		},
    		axisTicks: {
    			show:false
    		},
    		labels: {
    			show:false,
    		}
    	},
    	show:false,
    	yaxis: {
    		labels: {
    			show:false,
    		},
    	},
    	tooltip: {
    		x: {
    			format: 'dd/MM/yy HH:mm'
    		},
    	},
    };

    var optionsAmerica = {
        	series: [{
        		name: 'series1',
        		data: [
        			<c:forEach var="list" items="${dailyGraphList}">
    					${list.recovered},
    				</c:forEach>
        		]
        	}],
        	chart: {
        		height: 80,
        		type: 'area',
        		toolbar: {
        			show:false,
        		},
        	},
        	colors: ['#00FF00'],
        	stroke: {
        		width: 2,
        	},
        	grid: {
        		show:false,
        	},
        	dataLabels: {
        		enabled: false
        	},
        	xaxis: {
        		type: 'String',
        		categories: [
        			<c:forEach var="list" items="${dailyGraphList}">
    					"${list.date}",
    				</c:forEach>
        		],
        		axisBorder: {
        			show:false
        		},
        		axisTicks: {
        			show:false
        		},
        		labels: {
        			show:false,
        		}
        	},
        	show:false,
        	yaxis: {
        		labels: {
        			show:false,
        		},
        	},
        	tooltip: {
        		x: {
        			format: 'dd/MM/yy HH:mm'
        		},
        	},
        };
    
    var optionsIndonesia = {
        	series: [{
        		name: 'series1',
        		data: [
        			<c:forEach var="list" items="${dailyGraphList}">
    					${list.active},
    				</c:forEach>
        		]
        	}],
        	chart: {
        		height: 80,
        		type: 'area',
        		toolbar: {
        			show:false,
        		},
        	},
        	colors: ['#dc3545'],
        	stroke: {
        		width: 2,
        	},
        	grid: {
        		show:false,
        	},
        	dataLabels: {
        		enabled: false
        	},
        	xaxis: {
        		type: 'String',
        		categories: [
        			<c:forEach var="list" items="${dailyGraphList}">
    					"${list.date}",
    				</c:forEach>
        		],
        		axisBorder: {
        			show:false
        		},
        		axisTicks: {
        			show:false
        		},
        		labels: {
        			show:false,
        		}
        	},
        	show:false,
        	yaxis: {
        		labels: {
        			show:false,
        		},
        	},
        	tooltip: {
        		x: {
        			format: 'dd/MM/yy HH:mm'
        		},
        	},
        };

    var chartEurope = new ApexCharts(document.querySelector("#chart-europe"), optionsEurope);
    var chartAmerica = new ApexCharts(document.querySelector("#chart-america"), optionsAmerica);
    var chartIndonesia = new ApexCharts(document.querySelector("#chart-indonesia"), optionsIndonesia);

    chartIndonesia.render();
    chartAmerica.render();
    chartEurope.render();

	
    
    </script>

</body>

</html>