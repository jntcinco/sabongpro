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
              				<h2 class="dark">Add Entry</h2>
              				<p>&nbsp;</p>
              				<c:url var="addEntry" value="/admin/entry/add" />
							<form:form modelAttribute="entry" action="${addEntry}" method="post">
								<table width="95%" cellspacing='0' class="tabler">
									<c:if test="${not empty notificationMessage}">
                    					<tr><td colspan="4" style="text-align:center;">${notificationMessage}</td></tr>
                    				</c:if>
                    				<tr>
                    					<td width="50%" style="text-align:right;" colspan="2">Fight Number</td>
                    					<td width="50%" style="text-align:left;" colspan="2">
                    						<form:input path="fightNumber" value=""/>
                    						<form:errors path="fightNumber" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="25%" style="text-align:right;">Entry Name</td>
                    					<td width="25%" style="text-align:left;">    
											<form:input path="entryName" value="" />
											<form:errors path="entryName" cssClass="error"/>               						
                    					</td>
                    					<td width="25%" style="text-align:right;">Entry Name</td>
                    					<td width="25%" style="text-align:left;">    
											<form:input path="opponentEntryName" value="" />
											<form:errors path="opponentEntryName" cssClass="error"/>               						
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="25%" style="text-align:right;">Owner Name</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:input path="ownerName" value=""/>
                    						<form:errors path="ownerName" cssClass="error"/>
                    					</td>
                    					<td width="25%" style="text-align:right;">Owner Name</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:input path="opponentOwnerName" value=""/>
                    						<form:errors path="opponentOwnerName" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="25%" style="text-align:right;">Side</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:select path="side" id="entrySide">
                    							<option value="MERON">Meron</option>
												<option value="WALA">Wala </option>
                    						</form:select>
                    						<form:errors path="side" cssClass="error"/>
                    					</td>
                    					<td width="25%" style="text-align:right;">Side</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:select path="opponentSide" id="entrySide">
                    							<option value="MERON">Meron</option>
												<option value="WALA">Wala </option>
                    						</form:select>
                    						<form:errors path="opponentSide" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="25%" style="text-align:right;">Blood line</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:input path="bloodLine" value=""/>
                    						<form:errors path="bloodLine" cssClass="error"/>
                    					</td>
                    					<td width="25%" style="text-align:right;">Blood line</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:input path="opponentBloodLine" value=""/>
                    						<form:errors path="opponentBloodLine" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td width="25%" style="text-align:right;">Fight Weight</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:input path="fightWeight" value=""/>
                    						<form:errors path="fightWeight" cssClass="error"/>
                    					</td>
                    					<td width="25%" style="text-align:right;">Fight Weight</td>
                    					<td width="25%" style="text-align:left;">
                    						<form:input path="opponentFightWeight" value=""/>
                    						<form:errors path="opponentFightWeight" cssClass="error"/>
                    					</td>
                    				</tr>
                    				<tr>
                    					<td colspan="4" style="text-align:center;">
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