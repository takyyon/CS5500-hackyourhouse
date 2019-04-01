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
</nav>
<center>
<c:if test="${messages.success!=''}">
   

	<div class="alert alert-dismissible alert-success">
  		<button type="button" class="close" data-dismiss="alert">&times;</button>
  		<strong>${messages.success}</strong>
	</div>
 </c:if>
 <center><h3>Enter the details of the property</h3></center>
<div class="container loginComponent">
	<form action="addproperty" method="post">
	  <fieldset>
	    <div class="input-group">
	    	<span class="input-group-addon">
              <i class="fas fa-user"></i>
          </span>
	      <input type="text" class="form-control" id="propertyname" name="propertyname" value="${fn:escapeXml(param.propertyname)}" required  placeholder="Enter property name">
	    </div>
	    <br>
	    
	    <div class="input-group">
	    	<span class="input-group-addon">
              From
          </span>
	      <input  class="form-control" type="date" name="startdate" value="${fn:escapeXml(param.startdate)}" required  placeholder="Start date">
	    </div>
	    <br>
	    
	    <div class="input-group">
	    	<span class="input-group-addon">
              To
          </span>
	      <input  class="form-control" type="date" name="enddate" value="${fn:escapeXml(param.enddate)}" required  placeholder="Start date">
	    </div>
	    <br>
	    
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-map-marked-alt"></i>
          </span>	
	      <input type="text" class="form-control" id="street1" name="street1" value="${fn:escapeXml(param.street1)}" placeholder="Street1" required>
	    </div>
	    <br>
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-map-marked-alt"></i>
          </span>	
	      <input type="text" class="form-control" id="street2" name="street2" value="${fn:escapeXml(param.street2)}" placeholder="Street2" required>
	    </div>
	    <br>
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-city"></i>
          </span>	
	      <input type="text" class="form-control" id="city" name="city" value="${fn:escapeXml(param.city)}" placeholder="City" required>
	    </div><br>
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-flag-usa"></i>
          </span>	
	      <input type="text" class="form-control" id="state" name="state" value="${fn:escapeXml(param.state)}" placeholder="State" required>
	    </div><br>
	    
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-map-marked-alt"></i>
          </span>	
	      <input type="text" class="form-control" id="zip" name="zip" value="${fn:escapeXml(param.zip)}" placeholder="Zip" required>
	    </div><br>
	    
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-hand-holding-usd"></i>
          </span>	
	      <input type="text" class="form-control" id="rent" name="rent" value="${fn:escapeXml(param.rent)}" placeholder="Rent (in $)" required>
	    </div><br>
	    
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-funnel-dollar"></i>
          </span>	
	      <input type="text" class="form-control" id="brokerfees" name="brokerfees" value="${fn:escapeXml(param.brokerfees)}" placeholder="Broker Fees (in $)" required>
	    </div><br>
	    
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-lock"></i>
          </span>	
	      <input type="text" class="form-control" id="securitydeposit" name="securitydeposit" value="${fn:escapeXml(param.securitydeposit)}" placeholder="Security Deposit (in $)" required>
	    </div><br>
	    
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-square"></i>
          </span>	
	      <input type="text" class="form-control" id="area" name="area" value="${fn:escapeXml(param.area)}" placeholder="Area" required>
	    </div><br>
	    
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-bath"></i>
          </span>	
	      <input type="text" class="form-control" id="baths" name="baths" value="${fn:escapeXml(param.baths)}" placeholder="Total baths" required>
	    </div><br>
	    
	    
	    
	     <div class="form-check">
	      <label class="form-check-label">Are pets allowed?<br>
	         <input type="radio" class="form-check-input" id="yes" name="pets" value="0" required>Yes<br>
			<input type="radio" class="form-check-input" id="no" name="pets" value="1">No<br>
	        </label>
      	</div><br>
      	
      	 <div class="form-check">
	      <label class="form-check-label">Is heating included?<br>
	         <input type="radio" class="form-check-input" id="yes" name="heating" value="0" required>Yes<br>
			<input type="radio" class="form-check-input" id="no" name="heating" value="1">No<br>
	        </label>
      	</div><br>
      	
      	 <div class="form-check">
	      <label class="form-check-label">Is laundry included?<br>
	         <input type="radio" class="form-check-input" id="yes" name="laundry" value="0" required>Yes<br>
			<input type="radio" class="form-check-input" id="no" name="laundry" value="1">No<br>
	        </label>
      	</div><br>
	    <input type="submit" class="btn btn-primary" value="Add">
	    <a type="button" href="dashboard" class="btn btn-secondary">Back</a>
	 </fieldset>
	</form></div></center>
</body>
</html>