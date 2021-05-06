<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>

<head>
<style>
#container {
    height: 500px; 
    min-width: 310px; 
    max-width: 800px; 
    margin: 0 auto; 
}
.loading {
    margin-top: 10em;
    text-align: center;
    color: gray;
}
</style>
</head>
<script src="js/highmaps.js"></script>
<script src="js/exporting.js"></script>
<script src="js/in-all.js"></script>

<div id="container"></div>

<script>
//Prepare demo data
//Data is joined to map using value of 'hc-key' property by default.
//See API docs for 'joinBy' for more info on linking data and map.
var data = [
 
 <c:forEach var="list" items="${stateList}" >  
 	['in-${list.state_code}', ${list.active}],	
</c:forEach> 
];

//Create the chart
Highcharts.mapChart('container', {
 chart: {
     map: 'countries/in/in-all'
 },

 title: {
     text: 'Highmaps basic demo'
 },

 subtitle: {
     text: 'Source map: <a href="map/in-all.js">India</a>'
 },

 mapNavigation: {
     enabled: true,
     buttonOptions: {
         verticalAlign: 'bottom'
     }
 },

 colorAxis: {
     min: 0
 },

 series: [{
     data: data,
     name: 'Random data',
     states: {
         hover: {
             color: '#BADA55'
         }
     },
     dataLabels: {
         enabled: true,
         format: '{point.name}'
     }
 }]
});


</script>

