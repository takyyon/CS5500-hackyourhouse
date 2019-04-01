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
   
    <h2 class="float-right white">Welcome, ${messages.name}    .</h2>
     <a class="btn btn-primary" href="" class="float-right">Logout</a>
       <a class="btn btn-danger" href="deleteuser?id=<c:out value="${messages.username}"/>">Delete</a>
  	   <a class="btn btn-warning" href="updatebroker">Update</a>
  </div>
  
</nav>
	<br>
	<center><h1>Your listed properties</h1></center>
	<br>
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      		<th scope="col">PropertyName</th>
                <th scope="col">StartDate</th>
                <th scope="col">EndDate</th>
                <th scope="col">Street1</th>
                <th scope="col">Street2</th>
                <th scope="col">City</th>
                <th scope="col">State</th>
                <th scope="col">Zip</th>
                <th scope="col">Rent</th>
                <th scope="col">BrokerFees</th>
                
                <th scope="col">SecurityDeposit</th>
                <th scope="col">Area</th>
                <th scope="col">HeatingIncluded</th>
                <th scope="col">PetsAllowed</th>
                <th scope="col">LaundryIncluded</th>
                <th scope="col">Delete</th>
                <th scope="col">Update</th>
	    </tr>
	  </thead>
	  <tbody>
	      <c:forEach items="${properties}" var="p" >
                <tr>
                    <td><c:out value="${p.getPropertyName()}" /></td>
                    <td><fmt:formatDate value="${p.getStartDate()}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${p.getEndDate()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${p.getStreet1()}" /></td>
                    <td><c:out value="${p.getStreet2()}" /></td>
                    <td><c:out value="${p.getCity()}" /></td>
                    <td><c:out value="${p.getState()}" /></td>
                    <td><c:out value="${p.getZip()}" /></td>
                    <td><c:out value="${p.getRent()}" /></td>
                    <td><c:out value="${p.getBrokerFees()}" /></td>
                    <td><c:out value="${p.getSecurityDeposit()}" /></td>
                    <td><c:out value="${p.getArea()}" /></td>
                    <td><c:out value="${p.getHeatingIncluded()}" /></td>
                    <td><c:out value="${p.getPetsAllowed()}" /></td>
                    <td><c:out value="${p.getPropertyId()}" /></td>
                    <td><a href="deleteproperty?id=<c:out value="${p.getPropertyId()}"/>">Delete</a></td>
                    <td><a href="updateproperty?id=<c:out value="${p.getPropertyId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
	   </tbody>
	 </table>
	 
       
      <center><a href="AddProperty.jsp" class="btn btn-primary">Add a property</a></center>
</body>
</html>