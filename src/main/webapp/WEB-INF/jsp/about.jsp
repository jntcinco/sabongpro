<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>About - Sabong Pro</title>
		<meta name="designer" content="Rene San Lorenzo"/> 
		<meta name="developer" content="Jose Noel Cinco, Harold Siasat"/>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>

		<!--Used with animate.css -->
		<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#logo').addClass('animated flipInY');
				$('#colLeft').addClass('animated fadeIn');
				$('#colRight').addClass('animated fadeIn');
				//$('#colMid').addClass('animated bounceInUp');
				$('#bub1').addClass('animated flipInX');
				$('#bub2').addClass('animated flipInY');
				$('#bub3').addClass('animated flipInX');
			});
		</script>
	</head>
	<body class="bodyhome2" id="about">
		<div id="headerwide">
			<div id="header">
        		<div id="logo">
					<a href="<c:url value='/sabongpro/guest/home'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
				</div>
				<div id="centrHeader">
    				<a href="<c:url value='/sabongpro/'/>"><div class="loginblck"></div></a>
					<a href="<c:url value='/sabongpro/guest/register'/>"><div class="signupblck"></div></a>
				</div>

				<div id="mainMenu">
    				<ul>
        				<li><a href="<c:url value='/sabongpro/guest/home'/>" id="menuhome">HOME</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/about'/>" id="menuabout">ABOUT</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/schedule'/>" id="menugames">SCHEDULE</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/gallery'/>" id="menugallery">GALLERY</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/contact'/>" id="menucontact">CONTACT</a></li>
    				</ul>
				</div>
    		</div>
		</div>
		<div id="wrapper">
    		<div id="innerWrap">
          		<div id="content" class="margtop20">
            		<div id="userBlock">
              			<h2 class="dark textshadowWhite">About Sabong Pro</h2>
              			<p>Lorem ipsum dolor sit amet, vim ignota commune te, an quem aliquip definiebas pri. Soleat perfecto inimicus eu cum, an eos molestie recusabo, ne error maiestatis vis. Meis disputando vim ea, cu paulo impetus vix, legimus albucius te usu. Ne qui nostrud vivendo, cu cum alterum suavitate qualisque. Vel eu magna mazim oblique, sed sint eius delectus ei, nam justo nonumy ea. Sed cetero inimicus tractatos ea, qui eu habemus expetenda.</p>
              			<p>Sit dolorem molestie incorrupte ei, ex molestie ullamcorper sit. Amet persius consulatu vim et, quidam scaevola eu per. Nibh habeo delenit et has. Noster dictas intellegebat eu eam. Pri eripuit referrentur ad. Mei omnes indoctum adipiscing an, qui mentitum noluisse te.</p>
              			<p>Illum saepe nullam id nec, qui utamur invenire ne, vim no delicata volutpat consetetur. Cu stet inermis postulant nam. Te pro error indoctum, vim mucius equidem accumsan ei. Eos erat gubergren in.</p>
              			<p>Ne pri commodo feugait euripidis, quis equidem vim eu:</p>
              			<ul class="bluorb">
              				<li>Vix ea abhorreant consectetuer</li>
                			<li>Euismod pertinax salutand</li>
                			<li>Legere fastidii splendide</li>
                			<li>Ad vocent electram his. Usu malis vituperatoribus ei.</li>
                			<li>Ordo ad Chao</li>
              			</ul>
              			<p>Ut philosophia consequuntur nam, eam id luptatum adipiscing accommodare. Ei quem elit wisi qui, albucius sententiae his ad.</p>
              			<h2 class="dark textshadowWhite">About Sabong Pro Admins</h2>
              			<p class="dark textshadow1">&nbsp;</p>
              			<img src="<c:url value='/images/ceo.jpg'/>" width="60" height="86" class="imgleft margtopnone" />
              			<h3 class="dark textshadowWhite margtop30">The Head Honcho</h3>
              			<h4 class="dark textshadowWhite">CEO</h4>
              			<p class="margtop50">Lorem ipsum dolor sit amet, vim ignota commune te, an quem aliquip definiebas pri. Soleat perfecto inimicus eu cum, an eos molestie recusabo, ne error maiestatis vis. Meis disputando vim ea, cu paulo impetus vix, legimus albucius te usu. Ne qui nostrud vivendo, cu cum alterum suavitate qualisque. Vel eu magna mazim oblique, sed sint eius delectus ei, nam justo nonumy ea. Sed cetero inimicus tractatos ea, qui eu habemus expetenda.</p>
              			<ul class="eggorb">
                			<li>Entrepreneur</li>
                			<li>Tycoon</li>
                			<li>Professor</li>
                			<li>Banker</li>
              			</ul>
              			<h3>&nbsp;</h3>
              			<img src="<c:url value='/images/teban.jpg'/>" width="60" height="86" class="imgleft margtopnone" />
              			<h3 class="dark textshadowWhite margtop30">Teban</h3>
              			<h4 class="dark textshadowWhite">COO</h4>
              			<p class="margtop50">Lorem ipsum dolor sit amet, vim ignota commune te, an quem aliquip definiebas pri. Soleat perfecto inimicus eu cum, an eos molestie recusabo, ne error maiestatis vis. Meis disputando vim ea, cu paulo impetus vix, legimus albucius te usu. Ne qui nostrud vivendo, cu cum alterum suavitate qualisque. Vel eu magna mazim oblique, sed sint eius delectus ei, nam justo nonumy ea. Sed cetero inimicus tractatos ea, qui eu habemus expetenda.</p>
            		</div> 
          		</div>
      		</div>
		</div>
		<div id="footer">
			<div id="innerfoot">
  				<div id="footleft">
        			<ul>
            			<li><a href="<c:url value='/sabongpro/guest/home'/>">HOME</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/about'/>">ABOUT</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/schedule'/>">SCHEDULE</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/gallery'/>">GALLERY</a></li>
        				<li><a href="<c:url value='/sabongpro/guest/contact'/>">CONTACT</a></li>
        			</ul>
    			</div>
        		<div id="copyright">
           			&copy; 2014 <a href="<c:url value='/sabongpro/guest/home'/>">www.SabongPro.com</a>. All Rights Reserved &reg;
        		</div>
			</div>
		</div>
	</body>
</html>