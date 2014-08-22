<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Game Schedule - Sabong Pro</title>
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
				<a href="<c:url value='/'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
		</div> <!--eof logo -->
	<div id="centrHeader">
    	<a href="<c:url value='/authenticate'/>"><div class="loginblck"></div></a>
    	<a href="<c:url value='/register'/>"><div class="signupblck"></div></a>	
	</div> <!--eof centrHeader -->

<div id="mainMenu">
    <ul>
        			<li><a href="<c:url value='/'/>" id="menuhome">HOME</a></li>
        			<li><a href="<c:url value='/about'/>" id="menuabout">ABOUT</a></li>
        			<li><a href="<c:url value='/schedule'/>" id="menugames">SCHEDULE</a></li>
        			<li><a href="<c:url value='/contact'/>" id="menucontact">CONTACT</a></li>
    </ul>
</div> <!--eof mainMenu -->

    </div> <!--eof header -->
</div>
	<div id="wrapper">
    	<div id="innerWrap">
            
          <div id="content" class="margtop20 margbtm20">
          	<div id="userBlock">
              <div class="userLeft">
                <h3 class="dark textshadowWhite">Currently Searching: All Types</h3>
                <div class="vidThumbs">
                	<div class="vidBox">
                   	  <a href="<c:url value='/guest/livestreaming'/>">
                      	<img src="<c:url value='/images/sample_derby_192_tn.jpg'/>" width="192" height="147" />
                      </a>
                    </div>
                    <p class="center margtop10">Sample Video Only<br />
                    Aug 23, 2014</p>
                </div>
                <div class="vidThumbs">
                	<div class="vidBox">
                   	  <a href="<c:url value='/guest/livestreaming'/>">
                      	<img src="<c:url value='/images/sample_derby_192_tn.jpg'/>" width="192" height="147" />
                      </a>
                    </div>
                    <p class="center margtop10">Sample Video Only<br />
                    Aug 23, 2014</p>
                </div>
                <div class="vidThumbs">
                	<div class="vidBox">
                   	  <a href="<c:url value='/guest/livestreaming'/>">
                      	<img src="<c:url value='/images/sample_derby_192_tn.jpg'/>" width="192" height="147" />
                      </a>
                    </div>
                    <p class="center margtop10">Sample Video Only<br />
                    Aug 23, 2014</p>
                </div>
                
              </div> <!--eof userLeft -->
              <div class="userRight">
              	<div class="blockhead">
               	  <h5 class="dark textshadowWhite proj">Games</h5>
              	</div>
                <div class="sidecnt">
                  <p>You have no bets</p>
                  <ul>
                  	<li><a href="#">Choose a Game</a></li>
                    <li><a href="#">Manage Games</a></li>
                    <li><a href="#">Find Games</a></li>
                  </ul>
                </div>
                <div class="blockhead">
               	  <h5 class="dark textshadowWhite frnds">Friends</h5>
              	</div>
                <div class="sidecnt">
                  <p>You don't have any friends</p>
                  <ul>
                  	<li><a href="#">Find Friends</a></li>
                    <li><a href="#">View Friends</a></li>
                  </ul>
                </div>
                
              </div> <!--eof userRight -->
              
          	</div> <!--eof userBlock -->
          </div> <!--eof content -->
      </div> <!--eof innerWrap -->
    </div> <!--eof wrapper -->
<div id="footer">
	<div id="innerfoot">
  		<div id="footleft">
        <ul>
            		<li><a href="<c:url value='/'/>">HOME</a></li>
        			<li><a href="<c:url value='/about'/>">ABOUT</a></li>
        			<li><a href="<c:url value='/schedule'/>">SCHEDULE</a></li>
        			<li><a href="<c:url value='/contact'/>">CONTACT</a></li>
        </ul>
    	</div>
        <div id="copyright">
            	&copy; 2014 <a href="<c:url value='/'/>">www.SabongPro.com</a>. All Rights Reserved &reg;
        </div>
	</div><!--eof innerfoot --><!--eof innerfoot -->
</div>
</body>
</html>