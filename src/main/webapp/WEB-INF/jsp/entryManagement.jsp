<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sabong Pro</title>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
		
		<script type="text/javascript" src="<c:url value='/js/plugins/jquery-ui-1.10.4.custom/jquery-1.10.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/plugins/jquery-ui-1.10.4.custom/jquery-ui-1.10.4.custom.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/sabongpro.js'/>"></script>

		<!--Used with animate.css -->
		<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/plugins/jquery-ui-1.10.4.custom/css/smoothness/jquery-ui-1.10.4.custom.css'/>" />
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

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
                    		<li><a href="<c:url value="/authenticate/logout"/>">Logout</a></li>
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
              				<form action="">
              					<input name="search" type="text" class="smallinput rightFloat margtop10 margright20" title="Search Users"/>
              				</form>
              				<h2 class="dark">Entries</h2>
              				<p><a href="<c:url value='/admin/entry/prep/add'/>" class="rightFloat margtop10 margbtm10 margright20">Add Entry</a></p>
              				<table width="95%" cellspacing='0' class="tabler"><!-- cellspacing='0' is important, must stay -->
                    			<tr>
                    				<th width="10%">Fight Number</th>
                    				<th width="30%">Entry Name</th>
                    				<th width="30%">Owner Name</th>
                    				<th width="10%">Fight Weight</th>
                    			</tr>
                    
                    			<c:forEach var="entry" varStatus="loop" items="${entries}">
                    			<tr class="${loop.index % 2 == 0 ? 'even' : ''}">
                    				<td id=""><a href="#" onclick="" style="color:#3E87BC;">${entry.fightNumber}</a></td>
                    				<td id="">${entry.entryName}</td>
                    				<td id="">${entry.ownerName}</td>
                    				<td id="">${entry.fightWeight}</td>
                    			</tr>
                    			<!-- start popup dialog -->
			  					<div id="updateStreamingStatusDialog" class="ui-dialog-titlebar ui-widget-header" title="Update streaming status dialog">
            						<c:url var="url" value="/admin/streaming/config/update/prep?id=${config.id}" />
									<form id="streamingActivationDialogForm" action="${url}" method="post">
                    					<input type="hidden" name="configId" id="configId" value="${config.id}"/>
                    					<div id="leftColumn" style="float: left; width: 150px;">
                    						<div class="dark margbtm10">Description: </div>
                    						<div class="dark margbtm10">Live streaming url:</div>
                    					</div>
										<div id="rightColumn" style="float: left;">
											<div class="margbtm3">
                								<input type="text" name="description" id="description" value="" class="depth"/><br/>
                							</div>
                							<div class="margbtm3">
                								<input type="text" name="streamUrl" id="streamUrl" value="" class="depth"/><br/>
                							</div>
                							<div class="margbtm3">
												<input type="checkbox" id="streamStatus"/><span class="dark">Online</span>
											</div>
										</div>
									</form>
			  					</div>
			  					<script type="text/javascript">
			  						sabong(document).ready(function(){
										sabongproWidgets.updateStreamingStatusDialog();
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
