<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>LIVE STREAM - Sabong Pro</title>
		<meta name="description" content="Official website of SabongPro"/>
		<meta name="keywords" content="Live streaming,Live derby,SabongPro"/>
		<meta name="robots" content="index,follow"/>
		<meta name="DC.title" content="SabongPro"/>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>

		<!--Used with animate.css -->
		<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#logo').addClass('animated lightSpeedIn');
				$('#colLeft').addClass('animated fadeIn');
				$('#colRight').addClass('animated fadeIn');
				//$('#colMid').addClass('animated bounceInUp');
				$('#bub1').addClass('animated flipInX');
				$('#bub2').addClass('animated flipInY');
				$('#bub3').addClass('animated flipInX');
				
				//password field
				$('#current-password-clear').show();
				$('#current-password-password').hide();
				
				$('#new-password-clear').show();
				$('#new-password-password').hide();
				
				$('#confirm-new-password-clear').show();
				$('#confirm-new-password-password').hide();
	
				$('#current-password-clear').focus(function() {
				    $('#current-password-clear').hide();
				    $('#current-password-password').show();
				    $('#current-password-password').focus();
				});
				$('#current-password-password').blur(function() {
				    if($('#current-password-password').val() == '') {
				        $('#current-password-clear').show();
				        $('#current-password-password').hide();
				    }
				});
				
				$('#new-password-clear').focus(function(){
					$('#new-password-clear').hide();
				    $('#new-password-password').show();
				    $('#new-password-password').focus();
				});
				$('#new-password-password').blur(function() {
				    if($('#new-password-password').val() == '') {
				        $('#new-password-clear').show();
				        $('#new-password-password').hide();
				    }
				});
				
				$('#confirm-new-password-clear').focus(function(){
					$('#confirm-new-password-clear').hide();
				    $('#confirm-new-password-password').show();
				    $('#confirm-new-password-password').focus();
				});
				$('#confirm-new-password-password').blur(function() {
				    if($('#confirm-new-password-password').val() == '') {
				        $('#confirm-new-password-clear').show();
				        $('#confirm-new-password-password').hide();
				    }
				});
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
	<body id="games" class="bguser">
	<div id="headerwide">
	<div id="header">
        <div id="logo">
			<a href="<c:url value='/'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
		</div>
		<div id="mainMenu">
    		<ul>
        		<li><a href="<c:url value='/'/>" id="menuhome">HOME</a></li>
        		<li><a href="<c:url value='/about'/>" id="menuabout">ABOUT</a></li>
        		<li><a href="<c:url value='/schedule'/>" id="menugames">SCHEDULE</a></li>
        		<li><a href="<c:url value='/faqs'/>" id="menugames">FAQS</a></li>
	        	<li><a href="<c:url value='/contact'/>" id="menucontact">CONTACT</a></li>
	    	</ul>
		</div>
    </div>
	</div>
	<div id="wrapper">
    	<div id="liveWrap">
        	<div class="statBlock textshadowWhite">
            	<div class="statRight">
                	<ul>
                    	<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
                	</ul>
                </div>
                <ul>
                	<c:choose>
                		<c:when test="${user.profile.firstName} != ''">
                			<li>Welcome ${user.profile.firstName}</li>	
                		</c:when>
                		<c:otherwise>
                			<li>Welcome ${user.userName}</li>
                		</c:otherwise>
                	</c:choose>
                	<li><a href="<c:url value='/guest'/>">profile</a></li>
                </ul>
            </div>
            <div id="content" class="margtop5 margbtm20">
				<div id="userBlock">
					<div class="userLeft">
						<h3 class="dark textshadowWhite">Change Password</h3>
						<div class="crankupcol2 margtop20">
							<c:url var="profile" value="/guest/changepassword"/>
					<form action="${profile}" method="post" class="margleft20">
						<div class="errorBlock2 margbtm10">${notificationMessage}</div>
						<div class="formsingle margleft20">
							<input id="current-password-clear" type="text" class="mediuminput" value="Current Password" autocomplete="off"/>
							<input id="current-password-password" type="password" name="currentPassword"
								id="currentPassword" class="mediuminput" title="Current Password" />
							<div class="errorBlock margbtm10">${currentPasswordError}</div>
						</div>
						<div class="formsingle margleft20">
							<input id="new-password-clear" type="text" class="mediuminput" value="New Password" autocomplete="off"/>
							<input id="new-password-password" type="password" name="newPassword" id="newPassword"
								class="mediuminput" title="New Password" />
							<div class="errorBlock margbtm10">${newPasswordError}</div>
						</div>
						<div class="formsingle margleft20">
							<input id="confirm-new-password-clear" type="text" class="mediuminput" value="Confirm New Password" autocomplete="off"/>
							<input id="confirm-new-password-password" type="password" name="confirmNewPassword"
								id="confirmNewPassword" class="mediuminput"
								title="Confirm New Password" />
							<div class="errorBlock margbtm10">${confirmNewPasswordError}</div>
						</div>
						<div class="formsingle margleft20">
							<input type="submit" class="large orange kool" value="Change"
								title="Change Password" />
						</div>
					</form>
						</div>
					</div>
					<!--eof userLeft -->
					<div class="userRight">
						<div class="blockhead">
							<h5 class="dark textshadowWhite proj">Account Menu</h5>
						</div>
						<div class="sidecnt">
							<ul>
								<li><a href="<c:url value='/guest/livestreaming'/>">Watch live streaming</a></li>
								<li><a href="<c:url value='/guest/editprofile'/>">Edit Profile</a></li>
								<li><a href="<c:url value='/guest/account'/>">Change Password</a></li>
							</ul>
						</div>
					</div><!--eof userRight -->
				</div><!--eof userBlock -->
			</div> <!-- eof content -->
		</div> <!--eof innerWrap -->
	</div>
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
            &copy; 2014 <a href="<c:url value='/'/>">www.SabongPro.com</a>. All Rights Reserved &reg;
        </div>
		</div>
	</div>
	</body>
</html>