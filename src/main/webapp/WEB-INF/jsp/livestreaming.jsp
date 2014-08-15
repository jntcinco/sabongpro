<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LIVE STREAM - Sabong Pro</title>
<meta name="designer" content="Rene San Lorenzo"/> 
<meta name="developer" content="Jose Noel Cinco, Harold Siasat"/>
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

<!-- Used for Fancybox popup -->
<script type="text/javascript" src="<c:url value='/fancybox/jquery.mousewheel-3.0.4.pack.js'/>"></script>
<script type="text/javascript" src="<c:url value='/fancybox/jquery.fancybox-1.3.4.pack.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/fancybox/jquery.fancybox-1.3.4.css'/>" media="screen" />
<script type="text/javascript">
	$(document).ready(function() {
		/*
		*   Examples - images
		*/
		$("a.fancythis").fancybox({
			'overlayShow'	: true,
			'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic',
			'overlayColor'  : '#000',
			'overlayOpacity': 0.5
		});

		/* The following is used for grouped images if you like */
		$("a[rel=group1]").fancybox({
				'transitionIn'		: 'elastic',
				'transitionOut'		: 'elastic',
				'titlePosition' 	: 'outside',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});

		$("a[rel=group2]").fancybox({
				'transitionIn'		: 'elastic',
				'transitionOut'		: 'elastic',
				'titlePosition' 	: 'outside',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
	});
</script>

</head>
<body id="games" class="bguser">
<div id="headerwide">
	<div id="header">
        <div id="logo">
	<a href="<c:url value='/sabongpro/guest/home'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
</div> <!--eof logo -->


<div id="mainMenu">
    <ul>
        			<li><a href="<c:url value='/sabongpro/guest/home'/>" id="menuhome">HOME</a></li>
        			<li><a href="<c:url value='/sabongpro/guest/about'/>" id="menuabout">ABOUT</a></li>
        			<li><a href="<c:url value='/sabongpro/guest/schedule'/>" id="menugames">SCHEDULE</a></li>
        			<li><a href="<c:url value='/sabongpro/guest/gallery'/>" id="menugallery">GALLERY</a></li>
        			<li><a href="<c:url value='/sabongpro/guest/contact'/>" id="menucontact">CONTACT</a></li>
    </ul>
</div> <!--eof mainMenu -->

    </div> <!--eof header -->
</div>
	<div id="wrapper">
    	<div id="liveWrap">
          <div class="statBlock textshadowWhite">
            	<div class="statRight">
                <ul>
                	<li><a href="#">Account</a></li>
                    <li><a href="#">Add Virtual Points</a></li>
                    <li><a href="#">FAQs</a></li>
                    <li><a href="#">Messages</a></li>
                    <li><a href='<c:url value="/sabongpro/logout"/>'>Logout</a></li>
                </ul>
                </div>
                <ul>
                	<li><a href="#">welcome Teban</a></li>
                    <li><a href="#">profile</a></li>
                </ul>
            </div>
       
       <c:choose>
       	<c:when test="${isStreamAllowed}">
       	     
            
          <div id="content2" class="margtop20 margbtm20">
          	<div class="liveBlock">
              <div id="liveLeft">
              	<div id="meron">
                	<p>ENTRY</p>
                	<h1>MERON</h1>
                	<p>OWNER</p>
                	<h5>OWNER OF MERON</h5>
                	<p>BET STATUS</p>
                	<h2>OPEN</h2>
                	<p>&nbsp;</p>
                	<a href="http://vroom.club/room/156/charter" class="button button-green blacktext margleft20">BET ON MERON</a>
              	</div>
              </div>
              <div id="liveCenter">
              	<div id="topCentral">
                   <table width="520" border="0">
                      <tr>
                        <td width="77" rowspan="2">FIGHT NO.</td>
                        <td width="69" rowspan="2"><h2>5</h2></td>
                        <td width="132">BET COUNT:</td>
                        <td width="224" rowspan="2">93 [Gross Points: 48800]</td>
                     </tr>
                      <tr>
                        <td>WINNER: MERON</td>
                      </tr>
                   </table>

                </div>
                <div id="liveStream">
                	<iframe width="520" height="315" src="//www.youtube.com/embed/-OBNr6h-7t0" frameborder="0" allowfullscreen class="margtop3"></iframe>
                </div>
              </div>
              <div id="wala">
                   <p>ENTRY</p>
                	<h1>WALA</h1>
                	<p>OWNER</p>
                	<h5>OWNER OF WALA</h5>
                	<p>BET STATUS</p>
                	<h2>OPEN</h2>
                	<p>&nbsp;</p>
               	<a href="http://vroom.club/room/156/charter" class="button button-red blacktext margleft20">BET ON WALA</a>
              </div>
              <!--<div class="userLeft">
                <h3 class="dark textshadowWhite">LIVE STREAM</h3>
                <p class="dark textshadowWhite center">You must be logged in to view this content.</p>
                <p class="center">
                  <iframe width="560" height="315" src="//www.youtube.com/embed/-OBNr6h-7t0" frameborder="0" allowfullscreen></iframe>
                </p>
                <h3 class="center dark">SAMPLE DERBY ONLY</h3>
                <p class="center">AUG 23, 2012</p>
                <p>Lorem ipsum dolor sit amet, vim ignota commune te, an quem aliquip definiebas pri. Soleat perfecto inimicus eu cum, an eos molestie recusabo, ne error maiestatis vis. Meis disputando vim ea, cu paulo impetus vix, legimus albucius te usu. Ne qui nostrud vivendo, cu cum alterum suavitate qualisque. Vel eu magna mazim oblique, sed sint eius delectus ei, nam justo nonumy ea. Sed cetero inimicus tractatos ea, qui eu habemus expetenda.</p>
              </div> -->
              <!--eof userLeft -->
              <!--eof userRight -->
          	</div>
          	<!--eof userBlock -->
          </div> <!--eof content -->
      
      </c:when>
      <c:otherwise>
      	<h3>You are not subribed to view the streaming!</h3>
      	</c:otherwise>
      </c:choose>
      
      </div> <!--eof innerWrap -->
    </div> <!--eof wrapper -->
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
	</div><!--eof innerfoot --><!--eof innerfoot -->
</div>
</body>
</html>