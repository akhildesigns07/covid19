<!-- chart.jsp-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
window.onload = function() {
 
var dps = [[]];
var dpss = [[]];
var dpsss = [[]];
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "dark2", // "light1", "light2", "dark1"
	animationEnabled: true,
	title: {
		text: "Confirmed Cases in india"
	},
	axisY: {
		title: "People",
		labelFontSize: 10
		
	},
	data: [{
		type: "column",
		yValueFormatString: "#0",
		indexLabel: "{y}",
		dataPoints: dps[0]
	}]
});

var yValue;
var label;
 
<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">	
	<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue
		});		
	</c:forEach>	
</c:forEach> 
chart.render();
chart={};
console.log(dps);
var chart2 = new CanvasJS.Chart("chartContainer2", {
	theme: "dark2", // "light1", "light2", "dark1"
	animationEnabled: true,
	title: {
		text: "Death"
	},
	axisY: {
		title: "People"
		
	},
	data: [{
		type: "column",
		yValueFormatString: "#0",
		indexLabel: "{y}",
		dataPoints: dpss[0]
	}]
});

<c:forEach items="${dataPointsList2}" var="dataPointst" varStatus="loopp">	
	<c:forEach items="${dataPointst}" var="dataPointt" varStatus="innerLoop">
	
	yValuee = parseFloat("${dataPointt.y}");
	labell = "${dataPointt.label}";
		dpss[parseInt("${loopp.index}")].push({
			label : labell,
			y : yValuee
		});		
	</c:forEach>	
</c:forEach> 

 chart2.render();
 chart={};
 var chart3 = new CanvasJS.Chart("chartContainer3", {
		theme: "dark2", // "light1", "light2", "dark1"
		animationEnabled: true,
		title: {
			text: "Discharge"
		},
		axisY: {
			title: "People"
			
		},
		data: [{
			type: "column",
			yValueFormatString: "#0",
			indexLabel: "{y}",
			dataPoints: dpsss[0]
		}]
	});

	<c:forEach items="${dataPointsList3}" var="dataPointstt" varStatus="looppp">	
		<c:forEach items="${dataPointstt}" var="dataPointtt">
		yValuee = parseFloat("${dataPointtt.y}");
		labellll = "${dataPointtt.label}";
			dpsss[parseInt("${looppp.index}")].push({
				label : labellll,
				y : yValuee
			});		
		</c:forEach>	
	</c:forEach> 
	
	 chart3.render();
	 chart={};

 
}
</script>
</head>
<body>
<div style="float:left;width:100%;border:1px solid;">
	<div id="chartContainer"></div>
	</div>
	<br>
	<div style="float: left;width:100%;border:1px solid;padding-top:40%;">
	<div id="chartContainer2"></div>
	</div>
	<div style="float: left;width:100%;border:1px solid;padding-top:40%;">
	<div id="chartContainer3"></div>
	</div>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<%-- ${dataPointsList} --%>
</body>
</html>      
<%-- <body>
<c:forEach items="${response}" var="data">
<c:out value="${data.updatedDate}"/>
<c:out value="${data.confirmedCasesIndian}"/>
<c:out value="${data.loc}"/><br>
</c:forEach>
</body> --%>
</html>