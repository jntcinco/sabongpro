<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Sabong Pro</title>
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
	<body class="bodyhome" id="home">
		<div id="headerwide">
			<div id="header">
        		<div id="logo">
					<a href="<c:url value='/'/>" title="Sabong Pro">
						<img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" />
					</a>
				</div>
				<div id="centrHeader">
    				<a href="<c:url value='/login'/>"><div class="loginblck"></div></a>
    				<a href="<c:url value='/register'/>"><div class="signupblck"></div></a>
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
            	<div id="banner">
            		<div class="centercocks"></div>
           			<img src="<c:url value='/images/banner.jpg'/>" width="960" height="301" />
            	</div>

          		<div id="content">
           	  		<div class="home3Cols">
                		<img src="<c:url value='/images/bubble01.png'/>" width="271" height="124" id="bub1" />
                		<p>To enable user to bet and watch online, One must need to register and must have a minimum of 2000 virtual points.</p>
                		<p><a href="<c:url value='/faqs'/>">Read more...</a></p>
           	  		</div>
            		<div class="home3Cols margleft73">
              			<img src="<c:url value='/images/bubble02.png'/>" width="271" height="124" id="bub2" />
              			<p>Share this site with your friends in facebook, twitter, and other social networks to enjoy watching the live derby.</p>
                		<p><a href="<c:url value='/faqs'/>">Read more...</a></p>
            		</div>
              		<div class="home3Cols margleft73">
                		<img src="<c:url value='/images/bubble03.png'/>" width="271" height="124" id="bub3" />
                		<p>Enjoy watching online and bet together with our fellow sabongeros. Please read the instructions on how to bet online found in our FAQS page.</p>
                		<p><a href="<c:url value='/faqs'/>">Read more...</a></p>
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