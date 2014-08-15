<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<<<<<<< HEAD
<!--Used with animate.css -->
<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>

<style type="text/css">

.web_dialog_overlay
{
   position: fixed;
   top: 0;
   right: 0;
   bottom: 0;
   left: 0;
   height: 100%;
   width: 100%;
   margin: 0;
   padding: 0;
   background: #000000;
   opacity: .15;
   filter: alpha(opacity=15);
   -moz-opacity: .15;
   z-index: 101;
   display: none;
}
.web_dialog
{
   display: none;
   position: fixed;
   width: 380px;
   height: 200px;
   top: 50%;
   left: 50%;
   margin-left: -190px;
   margin-top: -100px;
   background-color: #ffffff;
   border: 2px solid #336699;
   padding: 0px;
   z-index: 102;
   font-family: Verdana;
   font-size: 10pt;
}
.web_dialog_title
{
   border-bottom: solid 2px #336699;
   background-color: #336699;
   padding: 4px;
   color: White;
   font-weight:bold;
}
.web_dialog_title a
{
   color: White;
   text-decoration: none;
}
.align_right
{
   text-align: right;
}

</style>

<script type="text/javascript">
	$(document).ready(function() {
		$('#logo').addClass('animated wobble');
		$('#colLeft').addClass('animated fadeIn');
		$('#colRight').addClass('animated fadeIn');
		//$('#colMid').addClass('animated bounceInUp');
		$('#bub1').addClass('animated flipInX');
		$('#bub2').addClass('animated flipInY');
		$('#bub3').addClass('animated flipInX');

		$('#search').bind('keypress', function(e) {
			if (e.keyCode == 13) {
				$("#searchForm").submit();
			}
		});

		$("#btnClose").click(function(e) {
			HideDialog();
			e.preventDefault();
		});
	});

	function ShowDialog(modal) {
		$("#overlay").show();
		$("#dialog").fadeIn(300);
		$("#overlay").unbind("click");
	}

	function HideDialog() {
		$("#overlay").hide();
		$("#dialog").fadeOut(300);
	}
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
	<a href="<c:url value='/sabongpro/guest/home'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
</div> <!--eof logo -->


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
            </div> <!--eof statBlock -->
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
                    <th width="15%">Status</th>
                    </tr><!-- Table Header -->
                    
                    <c:forEach var="user" varStatus="loop" items="${users}">
                    	<tr class="${loop.index % 2 == 0 ? 'even' : ''}">
                    		<td><a href="javascript:ShowDialog(true);">${user.userName}</a></td>
                    		<td>${user.email}</td>
                    		<td>${user.userRole.role}</td>
                    		<td>${user.status }</td>
                    	</tr>
                    </c:forEach>
              </table>
              </div> <!--eof userFull -->
              
          	</div> <!--eof userBlock -->
          </div> <!--eof content -->
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
</div>

<!-- pop up dialog -->
<div id="output"></div>
<div id="overlay" class="web_dialog_overlay"></div>
<div id="dialog" class="web_dialog">
   <table style="width: 100%; border: 0px;" cellpadding="3" cellspacing="0">
      <tr>
         <td class="web_dialog_title">Online Survey</td>
         <td class="web_dialog_title align_right">
            <a href="#" id="btnClose">Close</a>
         </td>
      </tr>
      <tr>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
      </tr>
      <tr>
         <td colspan="2" style="padding-left: 15px;">
            <b>Choose your favorite mobile brand? </b>
         </td>
      </tr>
      <tr>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
      </tr>
      <tr>
         <td colspan="2" style="padding-left: 15px;">
            <div id="brands">
               <input id="brand1" name="brand" type="radio" checked="checked" value="Nokia" /> Nokia
               <input id="brand2" name="brand" type="radio" value="Sony" /> Sony 
               <input id="brand3" name="brand" type="radio" value="Motorola" /> Motorola
            </div>
         </td>
      </tr>
      <tr>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
      </tr>
      <tr>
      <td colspan="2" style="text-align: center;">
           <input type="submit" class="medium orange kool" value="Update" title="Update"/>
         </td>
      	 
      </tr>
   </table>
</div>
</body>
=======
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
					<a href="<c:url value='/sabongpro/guest/home'/>" title="Sabong Pro">
						<img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" />
					</a>
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
              				<form action="">
              					<input name="search" type="text" class="smallinput rightFloat margtop10 margright20" title="Search Users"/>
              				</form>
              				<h2 class="dark">User Manager</h2>
              				<p>&nbsp;</p>
              				<table width="95%" cellspacing='0' class="tabler"><!-- cellspacing='0' is important, must stay -->
                    			<tr>
                    				<th width="40%">Username</th>
                    				<th width="30%">Email</th>
                    				<th width="15%">Last Visit</th>
                    				<th width="15%">Status</th>
                    			</tr>
                    
                    			<c:forEach var="user" varStatus="loop" items="${users}">
                    			<tr class="${loop.index % 2 == 0 ? 'even' : ''}">
                    				<td>${user.userName}</td>
                    				<td>${user.email}</td>
                    				<td>${user.userRole.role}</td>
                    				<td>${user.status }</td>
                    			</tr>
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
>>>>>>> 89d3feb4086c781341de1808555a2453679f0a99
</html>