<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html><html><head>

	<meta charset="ISO-8859-1">
	<title>Register form</title>
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
					<div class="header"><h3>CREATING NEW USER</h3></div>
					<form:form modelAttribute="user" method="POST">
						<spring:bind path="username">
                			<div class="form-group ${status.error ? 'has-error' : ''}">
                    			<form:input path="username" 
                    						class="form-control"
                    						placeholder="USERNAME"
                    						autofocus="true"
                    						data-validation="length"
                    						data-validation-length="min4"></form:input>
                   				<div class="help-block form-error">
                   					<form:errors path="username"></form:errors>
                   				</div>
               				 </div>
            			</spring:bind>
						<spring:bind path="password_confirmation">
                			<div class="form-group ${status.error ? 'has-error' : ''}">
                    			<form:password path="password_confirmation"
                    				   class="form-control"
                    				   placeholder="PASSWORD"
                    				   data-validation="length"
                    				   data-validation-length="min8"
                    				   name="password_confirmation"></form:password>
                    			<div class="help-block form-error">
                    				<form:errors path="password"></form:errors>
                    			</div>
                			</div>
            			</spring:bind>
						<spring:bind path="password">
                			<div class="form-group ${status.error ? 'has-error' : ''}">
                   				<form:password path="password"
                   					   class="form-control"
                   					   placeholder="PASSWORD CONFIRM"
                   					   data-validation="confirmation"></form:password>
                    			<div class="help-block form-error">
                    				<form:errors path="password_confirmation"></form:errors>
                    			</div>
               				</div>
           	 			</spring:bind>	
           	 			<div id="wallet-balance">WALLET BALANCE[PLN]</div>
						<spring:bind path="balance">
                			<div class="form-group ${status.error ? 'has-error' : ''}">
                			<form:input path="balance"
                    					class="form-control"
                    					data-validation="number"
                    					data-validation-allowing="float"></form:input>
                    			<div class="help-block form-error">
                    				<form:errors path="balance"></form:errors>
                    			</div>
                			</div>
           	 			</spring:bind>	
						<input class="buttons" type="submit" value="Register">
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
	<script>$.validate({ modules : 'security'});</script>
		
</body></html>