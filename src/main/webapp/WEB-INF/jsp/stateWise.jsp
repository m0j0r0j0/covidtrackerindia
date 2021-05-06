<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<!DOCTYPE html>
<title>Covid-19 tracker state-wise</title> 
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
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
</head>
<body>
<div class="chartContainer" style="height: 300px; width: 100%;"></div>
</body>
</html>
