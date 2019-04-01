<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<link href="https://fonts.googleapis.com/css?family=Dancing+Script" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Cabin+Condensed" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <img class="logo" src="${pageContext.request.contextPath}/logo.png"/>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Hack Your House <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
    <a class="btn btn-primary" href="/HackYourHouse/login" class="float-right">Logout</a>
  	<a class="btn btn-danger" href="deleteuser?id=<c:out value="${messages.username}"/>">Delete Account</a>
  	<a class="btn btn-warning" href="reportCrime">Report Crime</a>
  
</nav>

<center>
<div class="container loginComponent">
<c:if test="${messages.success != ''}">
   

	<div class="alert alert-dismissible alert-success">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>${messages.success}</strong>
 
	</div>
 </c:if>
 
 	<table class="table">
 		<thead class="thead-light">
 			<tr>
	 			<th>Total Crimes</th>
	 			<th>Crime Type</th>
	 			<th>Longitude</th>
	 			<th>Latitude</th>
	 			<th>Action</th>
 			</tr>
 		</thead>
 		<tbody>
 			<c:forEach items="${crimes}" var="c" >
 				<tr>
 					<td><c:out value="${c.getTotalCrimes()}" /></td>
 					<td><c:out value="${c.getType().name()}" /></td>
 					<td><c:out value="${c.getLongitude()}" /></td>
 					<td><c:out value="${c.getLatitude()}" /></td>
 					<td>
 						<form action="allCrimes" method="post">
 							<input type="hidden" name="crimeId" value="${c.getCrimeId()}" />
	 						<input type="submit" class="btn btn-warning btn-sm" value="Delete">
 						</form>
 					</td>
 				</tr>
 			</c:forEach>
 		</tbody>
 	</table>
</center>
</body>
</html>