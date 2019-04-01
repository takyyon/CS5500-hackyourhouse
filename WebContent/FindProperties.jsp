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
  <a class="btn btn-danger" href="deleteuser?id=<c:out value="${messages.username}"/>">Delete</a>
  <a class="btn btn-warning" href="updatebuyer">Update</a>
  <a class="btn btn-warning" href="reportCrime">Report Crime</a>
  <a class="btn btn-warning" href="allCrimes">All Crimes</a>
  
</nav>

<div class="search container-fluid">
<form action="findproperties" method="post">
<input type="hidden" name="userName" value="${messages.username}" />
<fieldset>
	<div class="row">
   <div class="col-xs-4 col-md-4 col-lg-4">
   		<center><div class="form-group inline-form">
	      <input type="text" class="form-control" id="city" name="city" value="${fn:escapeXml(param.city)}" required  placeholder="City">
	    </div></center>
	    <br>
   </div>
   <div class="col-xs-4 col-md-4 col-lg-4">
   		<center><div class="form-group inline-form">
	      <input type="text" class="form-control" id="state" name="state" value="${fn:escapeXml(param.state)}"  placeholder="State">
	    </div></center>
	    <br>
   </div>
   <div class="col-xs-4 col-md-4 col-lg-4">
   		<center><div class="form-group inline-form">
	      <input type="text" class="form-control" id="zip" name="zip" value="${fn:escapeXml(param.zip)}" placeholder="Zip">
	    </div></center>
	    <br>
   </div> 
</div>
<center><input type="submit" value="Search" class="btn btn-primary"></center>
</fieldset>
</form> 


</div>
	<center><h1>All the listed properties</h1></center>
       
  <br>
  <br>

  <div class="card-deck container-fluid">
  <c:forEach items="${properties}" var="p" >
  	<div class="col-sm-12 col-md-4 col-lg-4">
  		<div class="card mb-4">
			<img className="card-img-top"
				src="https://picsum.photos/300/200"/>
			<div className="card-body">
				<center><h4><c:out value="${p.getPropertyName()}" /></h4></center>
				<hr>
				<center>
					<a class="btn btn-primary" href="propertyReviews?id=<c:out value="${p.getPropertyId()}"/>&userName=<c:out value="${messages.username}"/>">Property Reviews</a>
					<a class="btn btn-warning" href="addReview?id=<c:out value="${p.getPropertyId()}"/>&userName=<c:out value="${messages.username}"/>">Add Review</a>
				</center>
				<hr>
				<center><h4>Broker : <c:out value="${p.getBroker().getUserName()}" /></h4></center>
				<hr>
				<center><h4>Contact : <c:out value="${p.getBroker().getPhone()}" /></h4></center>
				<hr>
				<div class="info">
				<p><b>Start Date: </b><fmt:formatDate value="${p.getStartDate()}" pattern="yyyy-MM-dd"/></p>
				<p><b>End Date: </b><fmt:formatDate value="${p.getEndDate()}" pattern="yyyy-MM-dd"/></p>
				<p><b>Address: </b><c:out value="${p.getStreet1()}" />, <c:out value="${p.getStreet2()}" /></p>
				<p><b>City:</b><c:out value="${p.getCity()}" /></p>
				<p><b>State</b><c:out value="${p.getState()}" /></p>
				<p><b>Zip: </b><c:out value="${p.getZip()}" /></p>
				<p><b>Rent: </b><c:out value="${p.getRent()}" /></p>
				<p><b>Broker Fees:</b><c:out value="${p.getBrokerFees()}" /></p>
				<p><b>Security Deposit:</b><c:out value="${p.getSecurityDeposit()}" /></p>
				<p><b>Area: </b><c:out value="${p.getArea()}" /> (sq ft.)</p>
				<p><b>Heating? </b>
				<c:if test="${p.getHeatingIncluded()}">
    				<i class="fas fa-check" style="color:green"></i>  
				</c:if>
				<c:if test="${not p.getHeatingIncluded()}">
    				<i class="fas fa-times" style="color:red"></i>  
				</c:if></p>
				<p><b>Laundry? </b>
				<c:if test="${p.getLaundryIncluded()}">
    				<i class="fas fa-check" style="color:green"></i>  
				</c:if>
				<c:if test="${not p.getLaundryIncluded()}">
    				<i class="fas fa-times" style="color:red"></i>  
				</c:if></p>
				<p><b>Pets Allowed? </b>: 
				<c:if test="${p.getPetsAllowed()}">
    				<i class="fas fa-check" style="color:green"></i>  
				</c:if>
				<c:if test="${not p.getPetsAllowed()}">
    				<i class="fas fa-times" style="color:red"></i>  
				</c:if></p>
				</div>
			</div>
		</div>
  	</div>
  	</c:forEach>
  </div>
</body>
</html>