<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sign Up - Sabong Pro</title>
<meta name="designer" content="Rene San Lorenzo"/> 
<meta name="developer" content="Jose Noel Cinco, Harold Siasat"/>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<c:url value='/js/ui/jquery-1.7.1.min.js'/>"></script>

<!--Used with animate.css -->
<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#logo').addClass('animated flipInX');
	$('#colLeft').addClass('animated fadeIn');
	$('#colRight').addClass('animated fadeIn');
	//$('#colMid').addClass('animated bounceInUp');
	$('#bub1').addClass('animated flipInX');
	$('#bub2').addClass('animated flipInY');
	$('#bub3').addClass('animated flipInX');
	
	//password field
	$('#password-clear').show();
	$('#password-password').hide();
	$('#confirm-password-clear').show();
	$('#confirm-password-password').hide();

	$('#password-clear').focus(function() {
	    $('#password-clear').hide();
	    $('#password-password').show();
	    $('#password-password').focus();
	});
	$('#password-password').blur(function() {
	    if($('#password-password').val() == '') {
	        $('#password-clear').show();
	        $('#password-password').hide();
	    }
	});
	
	$('#confirm-password-clear').focus(function(){
		$('#confirm-password-clear').hide();
	    $('#confirm-password-password').show();
	    $('#confirm-password-password').focus();
	});
	$('#confirm-password-password').blur(function() {
	    if($('#confirm-password-password').val() == '') {
	        $('#confirm-password-clear').show();
	        $('#confirm-password-password').hide();
	    }
	});
});
</script>

<!-- Input hints on textboxes -->
<script type="text/javascript" src="<c:url value='/js/ui/jquery.input-hint.js'/>"></script>
<script type="text/javascript">
  $(function() {
    $('[title]').inputHint();
    $('#test-form-2 input[type=text]').inputHint({using: '+ kbd'});
  });
</script>
</head>
<body id="home" class="bodycrank">
<div id="headerwide">
	<div id="header">
        <div id="logo">
	<a href="<c:url value='/'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
</div> <!--eof logo -->
<div id="centrHeader">
    <a href="<c:url value='/login'/>"><div class="loginblck"></div></a>
    <a href="<c:url value='/register'/>"><div class="signupblck"></div></a>
</div> <!--eof centrHeader -->

<div id="mainMenu">
    <ul>
        <li><a href="<c:url value='/'/>" id="menuhome">HOME</a></li>
        <li><a href="<c:url value='/about'/>" id="menuabout">ABOUT</a></li>
        <li><a href="<c:url value='/schedule'/>" id="menugames">SCHEDULE</a></li>
        <li><a href="<c:url value='/faqs'/>" id="menugames">FAQS</a></li>
        <li><a href="<c:url value='/contact'/>" id="menucontact">CONTACT</a></li>
    </ul>
</div> <!--eof mainMenu -->

    </div> <!--eof header -->
</div>
	<div id="wrapper">
    	<div id="innerWrap">
          <div id="content" class="margtop20 margbtm50">
          	<div class="crankupcol1">
           	  <div class="crankupIcon margtop30">
               	<h1 class="center">SIGN-UP</h1>
                	<img src="<c:url value='/images/bf_signup.png'/>" width="254" height="340" />
              </div>
            </div>
            <div class="crankupcol2 margtop20">
            	<c:url var="register" value="/register"/>
				<form:form modelAttribute="user" action="${register}" method="post" class="centered">
					<div class="errorBlock2 margbtm10">${notificationMessage}</div>
					<div class="formsingle">
                   	  	<form:input path="userName" class="mediuminput" title="Desired Username"/>
                        <div class="errorBlock margbtm10"><form:errors path="userName" cssClass="error"/></div>
                    </div>
                  	<div class="formsingle">
                   		<form:input path="email" class="mediuminput" title="Your Email Address"/>
                      	<div class="errorBlock margbtm10"><form:errors path="email" cssClass="error"/></div>
                  	</div>
                    <div class="formsingle">
                    	<input id="password-clear" type="text" class="mediuminput" value="Password" autocomplete="off"/>
                   	  	<form:password id="password-password" path="password" class="mediuminput" title="Password"/>
                        <div class="errorBlock margbtm10"><form:errors path="password" cssClass="error"/></div>
                    </div>
                  	<div class="formsingle">
                  		<input id="confirm-password-clear" type="text" class="mediuminput" value="Confirm Password" autocomplete="off"/>
                   		<form:password id="confirm-password-password" path="confirmPassword" class="mediuminput" title="Confirm Password"/>
                      	<div class="errorBlock margbtm10"><form:errors path="confirmPassword" cssClass="error"/></div>
                  	</div>
                    <div class="formsingle">
                    	<input type="submit" class="large orange kool" value="Sign Up" title="Sign Up"/>    
                 	</div>
				</form:form>
            </div>
          
          </div><!--eof content -->
      </div> <!--eof innerWrap -->
    </div> <!--eof wrapper -->
    <div id="footer">
      	<div id="innerfoot">
  		<div id="footleft">
        <ul>
            <li><a href="<c:url value='/'/>">HOME</a></li>
        	<li><a href="<c:url value='/about'/>">ABOUT</a></li>
        	<li><a href="<c:url value='/schedule'/>">SCHEDULE</a></li>
        	<li><a href="<c:url value='/faqs'/>">FAQS</a></li>
        	<li><a href="<c:url value='/contact'/>">CONTACT</a></li>
        </ul>
    	</div>
        <div id="copyright">
            &copy; 2014 <a href="<c:url value='/guest/home'/>">www.SabongPro.com</a>. All Rights Reserved &reg;
        </div>
	</div><!--eof innerfoot --><!--eof innerfoot -->
    </div> <!--eof footer -->
</body>
</html>