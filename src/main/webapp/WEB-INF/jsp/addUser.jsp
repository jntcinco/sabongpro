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
		<script type="text/javascript" src="<c:url value='/js/sabongpro.js'/>"></script>
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
                    		<li><a href='<c:url value="/login?logout"/>'>Logout</a></li>
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
              				<h2 class="dark">Add User</h2>
              				<p>&nbsp;</p>
              				<c:url var="addUser" value="/admin/user/add" />
							<form:form modelAttribute="user" action="${addUser}" method="post">
								<table width="95%" cellspacing='0' class="tabler">
									<c:if test="${not empty notificationMessage}">
                    					<tr><td colspan="2" style="text-align:center;">${notificationMessage}</td></tr>
                    				</c:if>
                    				<tr>
                    					<td width="50%" style="text-align:right;">User Name</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:input path="userName" value="" />
											<form:errors path="userName" cssClass="error" />                    						
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Email Address</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:input path="email" value=""/>
                    						<form:errors path="email" cssClass="error" />
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Password</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:password path="password" value=""/>
                    						<form:errors path="password" cssClass="error" />
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Confirm Password</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:password path="confirmPassword" value=""/>
                    						<form:errors path="confirmPassword" cssClass="error" />
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Live Streaming Access?</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:checkbox path="streamAllowed" />
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Status</td>
                    					<td width="50%" style="text-align:left;">
                    						<form:select path="status" >
												<form:option value="INACTIVE">INACTIVE</form:option>
												<form:option value="ACTIVE">ACTIVE</form:option>
											</form:select>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="50%" style="text-align:right;">Role</td>
                    					<td width="50%" style="text-align:left;">
                    						<select name="role" >
												<option value="GUEST">GUEST</option>
												<option value="ADMIN">ADMIN</option>
											</select>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td colspan="2" style="text-align:center;">
											<input type="submit" value="Save" />    					
                    					</td>
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