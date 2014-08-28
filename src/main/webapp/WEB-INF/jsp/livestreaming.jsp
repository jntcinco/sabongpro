<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>LIVE STREAM - Sabong Pro</title>
		<meta name="description" content="Official website of SabongPro"/>
		<meta name="keywords" content="Live streaming,Live derby,SabongPro"/>
		<meta name="robots" content="index,follow"/>
		<meta name="DC.title" content="SabongPro"/>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/sabongpro.js'/>"></script>

		<!--Used with animate.css -->
		<link href="<c:url value='/css/animate.css'/>" media="screen" rel="stylesheet"/>
		<script type="text/javascript">
			sabong(document).ready(function() {
				sabong('#logo').addClass('animated lightSpeedIn');
				sabong('#colLeft').addClass('animated fadeIn');
				sabong('#colRight').addClass('animated fadeIn');
				//$('#colMid').addClass('animated bounceInUp');
				sabong('#bub1').addClass('animated flipInX');
				sabong('#bub2').addClass('animated flipInY');
				sabong('#bub3').addClass('animated flipInX');
			});
		</script>

		<!-- Input hints on textboxes -->
		<script type="text/javascript" src="<c:url value='/js/jquery.input-hint.js'/>"></script>
		<script type="text/javascript">
			sabong(function() {
				sabong('[title]').inputHint();
				sabong('#test-form-2 input[type=text]').inputHint({using: '+ kbd'});
  			});
		</script>

		<!-- Used for Fancybox popup -->
		<script type="text/javascript" src="<c:url value='/fancybox/jquery.mousewheel-3.0.4.pack.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/fancybox/jquery.fancybox-1.3.4.pack.js'/>"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value='/fancybox/jquery.fancybox-1.3.4.css'/>" media="screen" />
		<script type="text/javascript">
			sabong(document).ready(function() {
				sabong("a.fancythis").fancybox({
					'overlayShow'	: true,
					'transitionIn'	: 'elastic',
					'transitionOut'	: 'elastic',
					'overlayColor'  : '#000',
					'overlayOpacity': 0.5
				});

				/* The following is used for grouped images if you like */
				sabong("a[rel=group1]").fancybox({
					'transitionIn'		: 'elastic',
					'transitionOut'		: 'elastic',
					'titlePosition' 	: 'outside',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});

				sabong("a[rel=group2]").fancybox({
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
    	<div id="liveWrap">
        	<div class="statBlock textshadowWhite">
            	<div class="statRight">
                	<ul>
                    	<li><a href='<c:url value="/authenticate/logout"/>'>Logout</a></li>
                	</ul>
                </div>
                <ul>
                	<c:choose>
                		<c:when test="${user.profile.firstName} != ''">
                			<li>Welcome ${user.profile.firstName}</li>	
                		</c:when>
                		<c:otherwise>
                			<li>Welcome ${user.userName}</li>
                		</c:otherwise>
                	</c:choose>
                    <li><a href="<c:url value='/guest'/>">profile</a></li>
                </ul>
            </div>
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
                					<a href="#" class="button button-green blacktext margleft20">BET ON MERON</a>
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
                					<iframe id="embededFrame" width="520" height="315" src="" frameborder="0" allowfullscreen class="margtop3"></iframe>
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
               					<a href="#" class="button button-red blacktext margleft20">BET ON WALA</a>
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
			<script type="text/javascript">
				sabong(document).ready(function(e){
					sabongproAjax.getStreamUrl();
				});
			</script>
	</body>
</html>