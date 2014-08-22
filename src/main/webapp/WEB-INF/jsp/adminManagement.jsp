<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin Panel - Sabong Pro</title>
<meta name="designer" content="Rene San Lorenzo"/> 
<meta name="developer" content="Jose Noel Cinco, Harold Siasat"/>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>

<!--Used with animate.css -->
<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#logo').addClass('animated wobble');
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
<body id="home" class="bguser">
<div id="headerwide">
	<div id="header">
        <div id="logo">
	<a href="<c:url value='/'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
</div> <!--eof logo -->


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
            <div class="statBlock textshadowWhite">
            	<div class="statRight">
                <ul>
                	<li><a href="#">Admin Panel</a></li>
                    <li><a href="#">Messages</a></li>
                    <li><a href="#">Notifications</a></li>
                    <li><a href='<c:url value="/logout"/>'>Logout</a></li>
                </ul>
                </div>
                <ul>
                	<li><a href="#">welcome Admin</a></li>
                    <li><a href="#">profile</a></li>
                </ul>
            </div> <!--eof statBlock -->
          <div id="content" class="margtop5 margbtm20">
          	<div id="userBlock">
              <div class="userLeft">
              <h2 class="dark">Administration Panel</h2>
              <!--<div class="profilepic"><img src="images/admin.jpg" width="60" height="86" /></div> -->
					<div class="adminThumbs">
                    	<a href="<c:url value='/admin/user/management'/>"><img src="<c:url value='/images/user.png'/>" width="192" height="200" /></a>
                        User Management
                    </div>
                    <div class="adminThumbs">
                    	<a href="<c:url value='/admin/streaming/config'/>"><img src="<c:url value='/images/vstream.png'/>" width="192" height="200" /></a>
                        Streaming Management
                    </div>
                    <div class="adminThumbs">
                    	<a href="#"><img src="<c:url value='/images/content.png'/>" width="192" height="200" /></a>
                        Content Management
                    </div>
                    <div class="adminThumbs">
                    	<a href="#"><img src="<c:url value='/images/database.png'/>" width="192" height="200" /></a>
                        Live Streaming Panel
                    </div>
                    <div class="adminThumbs">
                    	<a href="#"><img src="<c:url value='/images/vpoints.png'/>" width="192" height="200" /></a>
                        Virtual Points Manager
                    </div>
                    <div class="adminThumbs">
                    	<a href="#"><img src="<c:url value='/images/global.png'/>" width="192" height="200" /></a>
                        Global Configuration
                    </div>
              </div> <!--eof userLeft -->
              <div class="userRight">
              	<div class="blockhead">
               	  <h5 class="dark textshadowWhite frnds">Administration</h5>
              	</div>
                <div class="sidecnt">
                  <ul>
                  	<li><a href="<c:url value='/admin/user/management'/>">User Management</a></li>
                    <li><a href="<c:url value='/admin/streaming/config/management'/>">Streaming Management</a></li>
                    <li><a href="#">Content Management</a></li>
                    <li><a href="<c:url value='/admin/streaming/config'/>">Add Streaming Details</a></li>
                    <li><a href="<c:url value='/guest/livestreaming'/>">Live Streaming Panel</a></li>
                    <li><a href="#">Virtual Point Manager</a></li>
                    <li><a href="#">Global Configuration</a></li>
                  </ul>
                </div>
              </div> <!--eof userRight -->
              
          	</div> <!--eof userBlock -->
          </div> <!--eof content -->
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
</div>
</body>
</html>