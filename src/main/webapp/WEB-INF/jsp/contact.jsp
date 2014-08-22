<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contact Us - Sabong Pro</title>
<meta name="designer" content="Rene San Lorenzo"/> 
<meta name="developer" content="Jose Noel Cinco, Harold Siasat"/>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>

<!--Used with animate.css -->
<link href="css/animate.css" media="screen" rel="stylesheet"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#logo').addClass('animated rubberBand');
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
<body id="contact" class="bodycrank">
<div id="headerwide">
	<div id="header">
        <div id="logo">
	<a href="<c:url value='/'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
</div> <!--eof logo -->
<div id="centrHeader">
    <a href="<c:url value='/authenticate'/>"><div class="loginblck"></div></a>
    <a href="<c:url value='/register'/>"><div class="signupblck"></div></a>
</div> <!--eof centrHeader -->

<div id="mainMenu">
    <ul>
        			<li><a href="<c:url value='/'/>" id="menuhome">HOME</a></li>
        			<li><a href="<c:url value='/about'/>" id="menuabout">ABOUT</a></li>
        			<li><a href="<c:url value='/schedule'/>" id="menugames">SCHEDULE</a></li>
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
               	<h1 class="center">Contact Us</h1>
                	<img src="<c:url value='/images/bfmail.png'/>" width="254" height="340" />
              </div>
            </div>
            <div class="crankupcol2 margtop30">
            	<form action="signupresult.html" method="get" class="centered">
                  <div class="formsingle">
                   	  <input name="name" type="text" class="mediuminput" title="Your Name"/>
                      <div class="errorBlock margbtm10"></div>
                  </div>
                  <div class="formsingle">
                   	  <input name="email" type="text" class="mediuminput" title="Your Email Address"/>
                      <div class="errorBlock margbtm10"></div>
                  </div>
                    <div class="formsingle">
                   	  <input name="subject" type="text" class="mediuminput" title="Subject"/>
                        <div class="errorBlock margbtm10"></div>
                    </div>
                  <div class="formsingle">
                   	  <textarea name="message" rows="5" class="mediumtextarea" title="Type your message"></textarea>
                      <div class="errorBlock margbtm10"></div>
                  </div>
                    <div class="formsingle">
                    	<input type="submit" class="large orange kool" value="Send" title="Send">
                        
                    </div>
                </form>
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
        			<li><a href="<c:url value='/contact'/>">CONTACT</a></li>
        </ul>
    	</div>
        <div id="copyright">
            	&copy; 2014 <a href="<c:url value='/'/>">www.SabongPro.com</a>. All Rights Reserved &reg;
        </div>
	</div><!--eof innerfoot --><!--eof innerfoot -->
    </div> <!--eof footer -->
</body>
</html>