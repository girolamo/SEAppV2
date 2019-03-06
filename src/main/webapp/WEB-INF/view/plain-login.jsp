<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html><html><head>

	<meta charset="ISO-8859-1">
	<title>Stock Exchange App</title>
	<link href="css/css.css" rel="stylesheet"/>
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

</head><body>
	
	<!--  HEADER SECTION -->
	<header>
		<div id="header-content">
			<div>
				<div id="logotype">
					<h1><a href="${pageContext.request.contextPath}/">stock exchange app</a></h1>
				</div>
				<nav>
					<div id="time">LAST UPDATE 0000-00-00</div>
				</nav>
			</div>
		</div>
	</header>
	
	<!-- MAIN CONTENT SECTION -->
	<section>
		<div id="main-section-content">
			<div id="form-wrapper">
				<div id="form-inner">
					<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
						<div class="header"><h3>ENTER USERNAME AND PASSWORD</h3></div>
						<div id="user-and-password-input">
							<input type="text" placeholder="USERNAME" name="username">
							<input type="password" placeholder="PASSWORD" name="password">
						</div>
						<input class="buttons" type="submit" value='LOGIN'>
					</form:form>
						<div class="header"><h3>OR REGISTER NEW ACCOUNT</h3></div>
						<form:form action="${pageContext.request.contextPath}/registration" method="GET">
					<input class="buttons" type="submit" value='REGISTER'>
				</form:form>
				</div>
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
	<c:if test="${param.succesLogout != null}">
		<div class="popup-information-wrapper">
			<div class="succes-information">Success logout.</div>
		</div>
	</c:if>
	
	<c:if test="${param.error != null }">
		<div class="popup-information-wrapper">
			<div class="bad-credential">Wrong username or password.</div>
		</div>
	</c:if>
		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/getDataRequestRespond.js"></script>
	<script src="js/jquery.form-validator.min.js"></script>
	
</body></html>