<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
<div>
<table style="width:75%; margin-left:15%; 
    margin-right:15%;">  
<tr> 
  <th>id</th>
  <th>loc</th> 
  <th>discharged</th>
  <th style="color: red;">deaths</th>
  <th>confirmedCasesForeign</th>
  <th>totalConfirmed</th> 
  </tr>
	<c:forEach items="${lists}" var="namedata">
	<tr>
	<th><c:out value="${namedata.id}"/></th>
    <th><a href="${namedata.loc}"><c:out value="${namedata.loc}"/></a></th>
    <th><c:out value="${namedata.discharged}"/></th>
    <th style="color: red;"><c:out value="${namedata.deaths}"/></th>  
      <th><c:out value="${namedata.confirmedCasesForeign}"/></th> 
      <th><c:out value="${namedata.totalConfirmed}"/></th> 
    </tr>
    </c:forEach>   
   
  </table>
  </div>

</body>
</html>