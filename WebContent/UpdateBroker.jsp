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
  <a class="btn btn-danger" href="deleteuser?id=<c:out value="${messages.username}"/>">Delete</a>
  
</nav>

<center>
<div class="container loginComponent">
<c:if test="${messages.success != ''}">
   

	<div class="alert alert-dismissible alert-success">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>${messages.success}</strong>
 
	</div>
 </c:if>
	<form action="updatebroker" method="post">
	  <fieldset>
	    <div class="input-group">
	    	<span class="input-group-addon">
              <i class="fas fa-user"></i>
          </span>
	      <input type="text" class="form-control" id="username" name="username" value="${user.getUserName()}" readonly="">
	    </div>
	    <br>
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-unlock-alt"></i>
          </span>	
	      <input type="password" class="form-control" id="password" name="password" value="${user.getPassword()}" placeholder="Password" required>
	    </div>
	    <br>
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-signature"></i>
          </span>	
	      <input type="text" class="form-control" id="firstname" name="firstname" value="${user.getFirstName()}" placeholder="Firstname" required>
	    </div>
	    <br>
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-signature"></i>
          </span>	
	      <input type="text" class="form-control" id="lastname" name="lastname" value="${user.getLastName()}" placeholder="Lastname" required>
	    </div><br>
	    <div class="input-group">
	      <span class="input-group-addon">
              <i class="fas fa-envelope"></i>
          </span>	
	      <input type="email" class="form-control" id="email" name="email" value="${user.getEmail()}" placeholder="Email" required>
	    </div><br>
	    
	     <div class="input-group">
	    	<span class="input-group-addon">
              <i class="fas fa-mobile"></i>
          </span>
	      <input type="tel" class="form-control" id="phone" name="phone" value="${broker.getPhone()}" required>
	    </div><br>
	   
	    
	    <input type="submit" class="btn btn-primary" value="Save Changes">
	    <a type="button" href="findproperties" class="btn btn-secondary">Back</a>
	 </fieldset>
	</form></div></center>
</body>
</html>