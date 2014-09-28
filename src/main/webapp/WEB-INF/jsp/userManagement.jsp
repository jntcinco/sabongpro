<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sabong Pro</title>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
		
		<script type="text/javascript" src="<c:url value='/js/plugins/jquery-ui-1.10.4.custom/jquery-1.10.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/plugins/jquery-ui-1.10.4.custom/jquery-ui-1.10.4.custom.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/service/sabongpro.js'/>"></script>
		<!-- Input hints on textboxes -->
		<script type="text/javascript" src="<c:url value='/js/jquery.input-hint.js'/>"></script>
		<script type="text/javascript">
			sabong(function() {
				sabong('[title]').inputHint();
				sabong('#test-form-2 input[type=text]').inputHint({using: '+ kbd'});
			});
		</script>

		<!--Used with animate.css -->
		<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/plugins/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.css'/>" />
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		
		<!--Used with animate.css -->
		<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
		<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.css'/>" />
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
	</head>
	<body id="home" class="bguser">
		<div id="headerwide">
			<div id="header">
        		<div id="logo">
					<a href="<c:url value='/guest/home'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
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
                    		<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
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
              				<form id="searchForm" name="searchForm" action='<c:url value="/admin/search"/>' method="post">
              					<input id="search" name="search" type="text" class="smallinput rightFloat margtop10 margright20" title="Search User by email"/>
              				</form>
              				<h2 class="dark">User Manager</h2>
              				<p><a href="<c:url value='/admin/user/add'/>" class="rightFloat margtop10 margbtm10 margright20">Add User</a></p>
              				<table width="95%" cellspacing='0' class="tabler"> <!-- cellspacing='0' is important, must stay -->
                    			<tr>
                    				<th width="40%">Username</th>
                    				<th width="30%">Email</th>
                    				<th width="15%">Role</th>
                    				<th>Streaming Access</th>
                    				<th>Virtual Points</th>
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
                    				<td>${user.virtualPoints}</td>
                    			</tr>
                    			</c:forEach>
              				</table>
						<!-- start activation popup dialog -->
						<div id="streamingActivationDialog"
							class="ui-dialog-titlebar ui-widget-header"
							title="User Activation">
							<c:url var="grantAccess" value="/admin/user/allow/access" />
							<form id="streamingActivationDialogForm" action="${grantAccess}" method="post">
								<input type="hidden" name="userId" id="userId" value="${user.id}" /> 
								<div id="leftColumn" style="float: left; width: 150px;">
									<div class="dark margbtm10">Enter Virtual Points:</div> 
									<div class="dark margbtm10">Activate live streaming access ?</div> 
								</div>
								<div id="rightColumn" style="float: left;">
									<div class="margbtm3">
										<input type="text" name="virtualPoints" id="virtualPoints" value="" class="depth" /><br /> 
									</div>
									<div class="margbtm3">
										<input type="checkbox" id="streamingAccess" value="" />
									</div>
								</div>
							</form>
						</div>

						<!-- div id="addUserDialog"
							class="ui-dialog-titlebar ui-widget-header" title="Add User">
							<c:url var="adminAddUser" value="/admin/user/add" />
							<form id="addUserDialogForm" action="${adminAddUser}"
								method="post">
								<div id="leftColumn" style="float: left; width: 150px;">
									<div class="dark margbtm10">User Name</div>
									<div class="dark margbtm10">Email</div>
									<div class="dark margbtm10">Password</div>
									<div class="dark margbtm10">Confirm Password</div>
									<div class="dark margbtm10">Live Streaming Access?</div>
									<div class="dark margbtm10">Enabled?</div>
									<div class="dark margbtm10">Role</div>
								</div>
								<div id="rightColumn" style="float: left;">
									<div class="margbtm3">
										<input type="text" id="userName" name="userName" /> <span
											class="error" id="userNameError"></span>
									</div>
									<div class="margbtm3">
										<input type="text" id="email" name="email" /> <span
											class="error" id="emailError"></span>
									</div>
									<div class="margbtm3">
										<input type="password" id="password" /> <span class="error"
											id="passwordError"></span>
									</div>
									<div class="margbtm3">
										<input type="password" id="confirmPassword"
											name="confirmPassword" /> <span class="error"
											id="confirmPasswordError"></span>
									</div>
									<div class="margbtm3">
										<input type="checkbox" id="userStreamingAccess" />
									</div>
									<div class="margbtm3">
										<input type="checkbox" id="enabled" />
									</div>
									<div class="margbtm3">
										<select id="userRole" name="userRole">
											<option value="GUEST">GUEST</option>
											<option value="ADMIN">ADMIN</option>
										</select>
									</div>
								</div>
							</form>
							<a id="userManagementLink"
								href="<c:url value='/admin/user/management'/>"></a>
						</div-->

						<script type="text/javascript">
									sabong(document).ready(function(){
										sabongproWidgets.streamingActivationDialog();
										sabongproCommons.bolasScript();
										
										//sabongproWidgets.addUserDialog();
										//sabongproCommons.bolasScript();
										
									});
	  							</script>
						<!-- end popup dialog -->
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