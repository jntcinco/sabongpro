<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login - Sabong Pro</title>
<meta name="designer" content="Rene San Lorenzo"/> 
<meta name="developer" content="Jose Noel Cinco, Harold Siasat"/>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>

<!--Used with animate.css -->
<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#logo').addClass('animated bounceInDown');
	$('#colLeft').addClass('animated fadeIn');
	$('#colRight').addClass('animated fadeIn');
	//$('#colMid').addClass('animated bounceInUp');
	$('#bub1').addClass('animated flipInX');
	$('#bub2').addClass('animated flipInY');
	$('#bub3').addClass('animated flipInX');
});
</script>

<!-- Input hints on textboxes -->
<script type="text/javascript" src="<c:url value='/js/jquery.input-hint.js'/>"></script>
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
	<a href="<c:url value='/sabongpro/guest/home'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
</div> <!--eof logo -->
<div id="centrHeader">
    <a href="<c:url value='/sabongpro/'/>">
        <div class="loginblck"></div>
    </a>

    <a href="<c:url value='/sabongpro/guest/register'/>">
        <div class="signupblck"></div>
    </a>
</div> <!--eof centrHeader -->

<div id="mainMenu">
    <ul>
        <li><a href="<c:url value='/sabongpro/guest/home'/>" id="menuhome">HOME</a></li>
        <li><a href="#" id="menuabout">ABOUT</a></li>
        <li><a href="#" id="menugames">SCHEDULE</a></li>
        <li><a href="#" id="menugallery">GALLERY</a></li>
        <li><a href="#" id="menucontact">CONTACT</a></li>
    </ul>
</div> <!--eof mainMenu -->

    </div> <!--eof header -->
</div>
	<div id="wrapper">
    	<div id="innerWrap">
          <div id="content" class="margtop30 margbtm20">
          	<div class="crankupcol1">
           	  <div class="crankupIcon margtop50">
               	<img src="<c:url value='/images/bfLock.png'/>" width="254" height="340" />
              </div>
            </div>
            <div class="crankupcol2 margtop50">
            	<h1>LOG-IN</h1>
            	<div class="errorBlock margbtm20">${notificationMessage}</div>
            	<c:url var="login" value="/sabongpro/login" />
				<form:form modelAttribute="userSession" action="${login}" method="post" class="centered">
					<div class="formsingle">
						<form:input path="userName" class="biginput" title="Username"/>
                        <div class="errorBlock margbtm20"><form:errors path="userName" cssClass="error"/></div>
                    </div>
                    <div class="formsingle">
                    	<form:password path="password" class="biginput" title="Password"/>
                      	<div class="errorBlock"><form:errors path="password" cssClass="error"/></div>
                  	</div>
                  	<div class="formsingle">
                    	<input type="submit" class="large orange kool" value="Log-in" title="Log-in"/>
                    	<a href="#" class="medium blue kool">Forgot Password?</a>
                    	<a href="<c:url value="/sabongpro/user"/>" class="medium green kool">Sign-up</a>
                    </div>
					<form:hidden path="email"/>
					<form:hidden path="confirmPassword"/>
				</form:form>
            </div>
          
          </div><!--eof content -->
      </div> <!--eof innerWrap -->
    </div> <!--eof wrapper -->
<div id="footer">
	<div id="innerfoot">
  		<div id="footleft">
        <ul>
            <li><a href="<c:url value='/sabongpro/guest/home'/>">HOME</a></li>
            <li><a href="#">ABOUT</a></li>
            <li><a href="#">SCHEDULES</a></li>
            <li><a href="#">GALLERY</a></li>
            <li><a href="#">CONTACT</a></li>
        </ul>
    	</div>
        <div id="copyright">
            &copy; 2014 <a href="<c:url value='/sabongpro/guest/home'/>">www.SabongPro.com</a>. All Rights Reserved &reg;
        </div>
	</div><!--eof innerfoot --><!--eof innerfoot -->
</div> --><!--eof footer -->
</body>
</html>