<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html><html><head>

	<meta charset="ISO-8859-1">
	<title>Stock Exchange App</title>
	<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/css/charts.css" rel="stylesheet"/>
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>

</head><body>
	
	<!--  HEADER SECTION -->
	<header>
		<div id="header-content">
			<div>
				<div id="logotype">
					<h1><a href="${pageContext.request.contextPath}/">stock exchange app</a></h1>
				</div>
				<nav>
					<c:choose>
					<c:when test="${not empty pageContext.request.userPrincipal}">
						<div id="nickname">
							<sec:authentication property="principal.username" />
						</div>	
						<form:form action="${pageContext.request.contextPath}/logout" method="POST">
							<input class="buttons" type="submit" value="LOGOUT">
						</form:form>
						<input id="userLoggedIn" type="hidden" value="userLoggedIn">
					</c:when>
					<c:otherwise>
						<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
							<input type="text" placeholder="USERNAME" name="username">
							<input type="password" placeholder="PASSWORD" name="password">
							<input class="buttons" type="submit" value='LOGIN'>
						</form:form>
						<form:form action="${pageContext.request.contextPath}/registration" method="GET">
							<input class="buttons" type="submit" value='REGISTER'>
						</form:form>
					</c:otherwise>
					</c:choose>
					<div id="time">LAST UPDATE 0000-00-00</div>
				</nav>
			</div>
		</div>
	</header>
	
	<!-- MAIN CONTENT SECTION -->
	<section>
		<div id="charts">
			<div id="all-chart-inside"  class="show-chart" >
				<canvas id="allChart" width="400" height="400"></canvas>	
			</div>
			<div id="last-20-chart-inside" class="show-chart" >
				<canvas id="last20Chart" width="400" height="400"></canvas>
			</div>
		</div>
	</section>

	<!-- FOOTER SECTION -->
	<footer>
		<div id="footer-content">
			<div id="logotype">
				<h1><a href="${pageContext.request.contextPath}/">stock exchange app</a></h1>
			</div>
			<div id="footer-info">
				<ul>
					<li>CONTACT</li>
					<li>+48 881 209 952</li>
					<li>michalgrzech93@gmail.com</li>
				</ul>
				<ul>
					<li>AUTHOR</li>
					<li>Michal Grzech</li>
				</ul>
			</div>
		</div>
	</footer>
	
	<!-- Popups -->
	<div id="buy-popup-" class="hidden-popup" >
		<div class="buy-container">
	   		<form:form action="${pageContext.request.contextPath}/buyStock" >
	   			<p>How many units do you want to buy?</p>
	   			<p>Only multiple x <span id="multiple-buy"><span></span></span></p>
	   			<input type="text" name="quantity-buy-units">
	   			<div>
	   				<input class="company-stock" name="company-stock" type="hidden">
	   				<input class="hide-popup-button buttons" type="button" value="Cancel">	
	   				<input type="submit" class="buttons" value="Confirm">		
	   			</div>				    			
	   		</form:form>
  		</div>	
	</div>
	
	<div id="sell-popup-" class="hidden-popup" >
		<div class="sell-container">
	   		<form:form action="${pageContext.request.contextPath}/sellStock" >
	   			<p>How many units do you want to sell?</p>
	   			<p>Only multiple x <span id="multiple-sell"><span></span></span></p>
	   			<input type="text" name="quantity-sold-units">
	   			<div>
	   				<input class="company-stock" name="company-stock" type="hidden">
	   				<input class="hide-popup-button buttons" type="button" value="Cancel">	
	   				<input type="submit" class="buttons" value="Confirm">		
	   			</div>				    			
	   		</form:form>
  		</div>	
	</div>
	
		<c:if test="${param.succesLogout != null}">
			<div class="popup-information-wrapper">
				<div class="succes-information">Success logout.</div>
			</div>
		</c:if>
		
		<c:if test="${param.accountCreated != null}">
			<div class="popup-information-wrapper">
				<div class="succes-information">Account Created!</div>
			</div>
		</c:if>
		
		<c:if test="${param.incorrectValue != null}">
			<div class="popup-information-wrapper">
				<div class="bad-credential">Incorect Value!</div>
			</div>
		</c:if>
	
	<c:set var="baseURL" value="${pageContext.request.localName}"/>
	<input id="appUrl" type="hidden" value="baseURL">
	
	<script src="${pageContext.request.contextPath}/js/transactions.js"></script>
	<script src="${pageContext.request.contextPath}/js/getDataRequestRespond.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form-validator.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chart.js"></script>
	
</body></html>