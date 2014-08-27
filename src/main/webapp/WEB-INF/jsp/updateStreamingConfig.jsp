<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sabong Pro</title>
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
					<a href="<c:url value='/'/>" title="Sabong Pro">
						<img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" />
					</a>
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
    		<div id="innerWrap">
            	<div class="statBlock textshadowWhite">
            		<div class="statRight">
                		<ul>
                			<li><a href="<c:url value='/admin/management'/>">Admin Panel</a></li>
                    		<li><a href="#">Messages</a></li>
                    		<li><a href="#">Notifications</a></li>
                    		<li><a href="#">Logout</a></li>
                		</ul>
                	</div>
                	<ul>
                		<li><a href="#">welcome Admin</a></li>
                    	<li><a href="#">profile</a></li>
                	</ul>
            	</div>
          		<div id="content" class="margtop5 margbtm20">
          			<div id="userBlock">
              			<div class="userFull">
              				<h2 class="dark">Streaming Management</h2>
              				<p>&nbsp;</p>
            				<c:url var="configure" value="/admin/streaming/config/update" />
              				<form:form modelAttribute="config" action="${configure}">
              					<form:hidden path="id"/>
              					<table width="95%" cellspacing='0' class="tabler"><!-- cellspacing='0' is important, must stay -->
                    				<c:if test="${not empty notificationMessage}">
                    					<tr><td colspan="2">${notificationMessage}</td></tr>
                    				</c:if>
                    				<tr class="${loop.index % 2 == 0 ? 'even' : ''}">
                    					<td width="50%" style="text-align:right;">Description:</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:input path="description" value=""/>
                    						<form:errors path="description" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Live streaming url:</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:input path="url"  value=""/>	
                    						<form:errors path="url" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Status:</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:select path="status">
                    							<form:option value="" label="--- Select value ---"/>
                    							<form:option value="Showing" label="Showing"/>
                    							<form:option value="Coming" label="Coming"/>
                    						</form:select>
                    						<form:errors path="url" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td colspan="2"><input type="submit" value="Configure"/></td>
                    				</tr>
              					</table>
              				</form:form>
              			</div>
          			</div>
          		</div>
      		</div>
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