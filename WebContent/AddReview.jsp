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
  <h2 class="float-right white">Welcome, ${messages.name}    .</h2>
  <a class="btn btn-primary" href="login" class="float-right">Logout</a>
  <a class="btn btn-danger" href="deleteuser?id=<c:out value="${messages.username}"/>">Delete Account</a>  
</nav>

<div class="search container-fluid">
<form action="addReview" method="post">
	<input type="hidden" name="userName" value="${messages.username}" />
	<input type="hidden" name="propertyId" value="${property.getPropertyId()}" />
	<center>
		<h4>Review for Property <b><i>${property.getPropertyName()}</i></b></h4>
		<textarea name="review" rows="10" cols="50"></textarea>
		<br />
		<input type="submit" value="Add" class="btn btn-primary">
	</center>
</form> 
</div>
<br /><br /><br />
<div class="container-fluid">
	<center>
		<h4>All Preview Reviews</h4><br />
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th>Review</th>
					<th>Created On</th>
					<th>Property Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reviews}" var="r" >
					<tr>
 					<td><c:out value="${r.getReview()}" /></td>
 					<td><c:out value="${r.getCreated()}" /></td>
 					<td><c:out value="${r.getPropertyName()}" /></td>
 					<td>
 						<form action="addReview" method="post">
 							<input type="hidden" name="reviewId" value="${r.getReviewId()}" />
 							<input type="hidden" name="userName" value="${messages.username}" />
	 						<input type="submit" class="btn btn-warning btn-sm" value="Delete">
 						</form>
 					</td>
 				</tr>
				</c:forEach>
			</tbody>
		</table>
	</center>
</div>
</body>
</html>