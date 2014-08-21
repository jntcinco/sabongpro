<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sabong Pro</title>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />

		<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/plugins/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/sabongpro.js'/>"></script>

		<!--Used with animate.css -->
		<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
		<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.css'/>" />
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
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
					<a href="<c:url value='/sabongpro/guest/home'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
				</div>
				<div id="mainMenu">
    				<ul>
        				<li><a href="<c:url value='/sabongpro/guest/home'/>" id="menuhome">HOME</a></li>
        				<li><a href="#" id="menuabout">ABOUT</a></li>
        				<li><a href="#" id="menugames">SCHEDULE</a></li>
        				<li><a href="#" id="menugallery">GALLERY</a></li>
        				<li><a href="#" id="menucontact">CONTACT</a></li>
    				</ul>
				</div>
    		</div>
		</div>
		<div id="wrapper">
    		<div id="innerWrap">
            	<div class="statBlock textshadowWhite">
            		<div class="statRight">
                		<ul>
                			<li><a href="<c:url value='/sabongpro/admin/management'/>">Admin Panel</a></li>
                    		<li><a href="#">Messages</a></li>
                    		<li><a href="#">Notifications</a></li>
                    		<li><a href='<c:url value="/sabongpro/logout"/>'>Logout</a></li>
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
              				<form id="searchForm" name="searchForm" action='<c:url value="/sabongpro/admin/search"/>' method="post">
              					<input id="search" name="search" type="text" class="smallinput rightFloat margtop10 margright20" title="Search User by email"/>
              				</form>
              				<h2 class="dark">User Manager</h2>
              				<p>&nbsp;</p>
              				<table width="95%" cellspacing='0' class="tabler"> <!-- cellspacing='0' is important, must stay -->
                    			<tr>
                    				<th width="40%">Username</th>
                    				<th width="30%">Email</th>
                    				<th width="15%">Role</th>
                    				<th>Streaming Access</th>
                    			</tr>
                    
                    			<c:forEach var="user" varStatus="loop" items="${users}">
                    			<tr class="${loop.index % 2 == 0 ? 'even' : ''}">
                    				<td><a href="#" onclick="sabongproWidgets.showstreamingActivationDialogForm(${user.id});" style="color:#3E87BC;">${user.userName}</a></td>
                    				<td>${user.email}</td>
                    				<td>${user.userRole.role}</td>
                    				<td id="streamingAccessColumn${user.id}">
                    					<c:choose>
                    						<c:when test="${user.streamAllowed}">Activated</c:when>
                    						<c:otherwise>Deactivated</c:otherwise>
                    					</c:choose>
                    				</td>
                    			</tr>
              					<!-- start popup dialog -->
			  					<div id="streamingActivationDialog" class="ui-dialog-titlebar ui-widget-header" title="User Activation Dialog">
            						<c:url var="grantAccess" value="/sabongpro/admin/user/allow/access" />
									<form id="streamingActivationDialogForm" action="${grantAccess}" method="post">
                    					<input type="hidden" name="userId" id="userId" value="${user.id}"/>
										<span class="dark">Enter Virtual Points: </span>
                						<input type="text" name="virtualPoints" id="virtualPoints" value="" class="depth"/><br/>
										<span class="dark">Activate live streaming access ?</span>
  										<input type="checkbox" id="streamingAccess" value=""/>
									</form>
			  					</div>
			  					<script type="text/javascript">
									$(document).ready(function(){
										sabongproWidgets.streamingActivationDialog();
										sabongproCommons.bolasScript();
									});
	  							</script>
	  						<!-- end popup dialog -->
                    			</c:forEach>
              				</table>
              			</div> 
          			</div>
          		</div>
      		</div>
    	</div>
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
			</div>
		</div>
	</body>
</html>