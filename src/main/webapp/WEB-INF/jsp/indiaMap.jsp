<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<title>Covid-19 tracker Map</title> 
<head>
<!-- Include jQuery -->
<script type="text/javascript"
	src=" https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- Include fusioncharts core library file -->
<script type="text/javascript"
	src=" https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<!-- Include fusion theme file -->
<script type="text/javascript"
	src=" https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
<!-- Include fusioncharts jquery plugin -->
<script type="text/javascript"
	src=" https://rawgit.com/fusioncharts/fusioncharts-jquery-plugin/develop/dist/fusioncharts.jqueryplugin.min.js"></script>
<style>
	#chart-container {
		height: 500px;
	}
</style>

</head>
<body>
	<script type="text/javascript">
			FusionCharts.ready(function() {
			  var myChart = new FusionCharts({
			    type: "maps/india",
			    renderAt: "chart-container",
			    width: "100%",
			    height: "100%",
			    dataFormat: "json",
			    dataSource: {
						  chart: {
						    caption: "Click on states to see active cases",
						    subcaption: "",
						    legendposition: "BOTTOM",
						    entitytooltext: "$lname: <b>$datavalue </b>cases",
						    legendcaption: "Total Cases",
						    entityfillhovercolor: "#FFCDD2",
						    theme: "fusion"
						  },
						  colorrange: {
						    gradient: "0",
						    color: [
						      {
						        maxvalue: "10000",
						        displayvalue: "upto 10,000",
						        code: "#EF9A9A"
						      },
						      {
						        maxvalue: "50000",
						        displayvalue: "upto 50,000",
						        code: "#FF5C5C"
						      },
						      {
						        maxvalue: "100000",
						        displayvalue: "upto 1,00,000",
						        code: "#E53935"
						      },
						      {
						        maxvalue: "200000",
						        displayvalue: "upto 2,00,000",
						        code: "#C62828"
						      },
						      {
						        maxvalue: "500000",
						        displayvalue: "upto 5,00,000",
						        code: "#8C0000"
						      },
						      {
							        maxvalue: "1000000",
							        displayvalue: "upto 10,00,000",
							        code: "#6D0000"
						      }
						    ]
						  },
						  data: [
						    {
						      data: [
						        <c:forEach var="list" items="${mapStateList}" >  
						        	{
							          id: "${list.id}",
							          value: "${list.active}"
							        },
						        </c:forEach> 
						      ]
						    }
						  ]
						}
			  }).render();
			});



        </script>
	<div id="chart-container">FusionCharts will render here</div>
</body>
</html>