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
	#chart-container2 {
		height: 500px;
	}
</style>

</head>
<body>
	<script type="text/javascript">
	

			FusionCharts.ready(function() {
			  var myChart = new FusionCharts({
			    type: "maps/maharashtra",
			    renderAt: "chart-container2",
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
						        maxvalue: "4000",
						        displayvalue: "upto 4000",
						        code: "#f6a1a1"
						      },
						      {
						        maxvalue: "8000",
						        displayvalue: "upto 8000",
						        code: "#FF5C5C"
						      },
						      {
						        maxvalue: "15000",
						        displayvalue: "upto 15,000",
						        code: "#e33641"
						      },
						      {
						        maxvalue: "25000",
						        displayvalue: "upto 25,000",
						        code: "#C62828"
						      },
						      {
						        maxvalue: "45000",
						        displayvalue: "upto 45,000",
						        code: "#8C0000"
						      },
						      {
							        maxvalue: "65000",
							        displayvalue: "upto 65,000",
							        code: "#6D0000"
						      },
						      {
							        maxvalue: "150000",
							        displayvalue: "upto 80,000",
							        code: "#6D0000"
						      },
						      {
							        maxvalue: "150000",
							        displayvalue: "upto 1,20,000",
							        code: "#6D0000"
						      }
						    ]
						  },
						  data: [
						    {
						      data: [
						        <c:forEach var="list" items="${districWiseDataMap}" >  
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
	<div id="chart-container2">FusionCharts will render here</div>
</body>
</html>