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
                    	<c:if test="${userSession.userRole.role == 'ADMIN'}">
                			<li><a href="<c:url value="/admin/management"/>">Admin Panel</a></li>
                    		<li><a href="#" onclick="sabongproAjax.closeBetting();">Close Betting</a></li>
                    		<li><a href="#" onclick="sabongproAjax.declareWinner();">Declare Winner</a></li>
                    	</c:if>
                    	<li><a href='<c:url value="/authenticate/logout"/>'>Logout</a></li>
                	</ul>
                </div>
                <ul>
                	<c:choose>
                		<c:when test="${user.profile.firstName} != ''">
                			<li>Welcome ${user.profile.firstName}</li>	
                		</c:when>
                		<c:otherwise>
                			<li>Welcome ${userSession.userName}</li>
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
                					<h2><div id="meronStatus">OPEN</div></h2>
                					<p>&nbsp;</p>
                					<button id="meronBtn" onclick="sabongproAjax.getBettingInfo('${userSession.userName}','MERON');" class="button button-green blacktext margleft20">BET ON MERON</button>
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
                				<h2><div id="meronStatus">OPEN</div></h2>
                				<p>&nbsp;</p>
                				<button id="walaBtn" onclick="sabongproAjax.getBettingInfo('${userSession.userName}','WALA');" class="button button-red blacktext margleft20">BET ON WALA</button>
              				</div>
          				</div>
          				<div id="bettingHistory">
          					<table id="allBets" class="tableAll" align="left">
          						<thead>
          							<tr>
          								<th class="colBets headerAll" colspan="3">ALL BETS</th>
          							</tr>
          						</thead>
          						<thead>
          							<tr>
          								<th class="colBets" id="headerMeron">MERON</th>
          								<th class="colBets headerMid">ODDS</th>
          								<th class="colBets" id="headerWala">WALA</th>
          							</tr>
          						</thead>
          						<tbody>
          							<tr>
          								<td class="colBets" align="center" id="meronAmount">0</td>
          								<td class="colBets" align="center">10/10</td>
          								<td class="colBets" align="center" id="walaAmount">0</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center" id="nineTenMeronAmount">0</td>
          								<td class="colBets" align="center">9/10</td>
          								<td class="colBets" align="center" id="nineTenWalaAmount">0</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center" id="eightTenMeronAmount">0</td>
          								<td class="colBets" align="center">8/10</td>
          								<td class="colBets" align="center" id="eightTenWalaAmount">0</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center" id="tenNineMeronAmount">0</td>
          								<td class="colBets" align="center">10/9</td>
          								<td class="colBets" align="center" id="tenNineWalaAmount">0</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center" id="tenEightMeronAmount">0</td>
          								<td class="colBets" align="center">10/8</td>
          								<td class="colBets" align="center" id="tenEightWalaAmount">0</td>
          							</tr>
          						</tbody>
          					</table>
          					<table id="betCat" class="tableAll" align="left">
          						<thead>
          							<tr>
          								<th class="colBets headerAll" colspan="2">SELECT ODDS AND BET AMOUNT</th>
          							</tr>
          						</thead>
          						<tbody>
          							<tr>
          								<td class="colBets" align="center" width="50%">
          									Odds: 
          									<select class="amountOption" id="oddsId">
												<option value="TEN_TEN">10/10</option>
												<option value="NINE_TEN">9/10 </option>
												<option value="EIGHT_TEN">8/10</option>
												<option value="TEN_NINE">10/9</option>
												<option value="TEN_EIGHT">10/8 </option>
											</select>
          								</td>
          								<td class="colBets" align="center" width="50%"> 
          									Bet Amount:
          									<select class="amountOption" id="betAmount">
												<option>200</option>
												<option>500 </option>
												<option>1000</option>
												<option>2000</option>
												<option>5000 </option>
												<option>10000</option>
											</select>
          								</td>
          							</tr>
          						</tbody>
          					</table>
          					<table id="allBets" class="tableAll">
          						<thead>
          							<tr>
          								<th class="colBets headerAll" colspan="3">BET HISTORY</th>
          							</tr>
          						</thead>
          						<thead>
          							<tr>
          								<th class="colBets headerAll">FIGHT NO.</th>
          								<th class="colBets headerAll">SIDE</th>
          								<th class="colBets headerAll">RESULT</th>
          							</tr>
          						</thead>
          						<tbody>
          							<tr>
          								<td class="colBets" align="center">1</td>
          								<td class="colBets" align="center">WALA</td>
          								<td class="colBets" align="center">L</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">2</td>
          								<td class="colBets" align="center">WALA</td>
          								<td class="colBets" align="center">W</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">3</td>
          								<td class="colBets" align="center">MERON</td>
          								<td class="colBets" align="center">L</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">4</td>
          								<td class="colBets" align="center">MERON</td>
          								<td class="colBets" align="center">L</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">5</td>
          								<td class="colBets" align="center">MERON</td>
          								<td class="colBets" align="center">L</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">6</td>
          								<td class="colBets" align="center">MERON</td>
          								<td class="colBets" align="center">D</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">7</td>
          								<td class="colBets" align="center">CANCEL</td>
          								<td class="colBets" align="center"></td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">8</td>
          								<td class="colBets" align="center">WALA</td>
          								<td class="colBets" align="center">W</td>
          							</tr>
          							<tr>
          								<td class="colBets" align="center">9</td>
          								<td class="colBets" align="center">WALA</td>
          								<td class="colBets" align="center">W</td>
          							</tr>
          						</tbody>
          					</table>
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
					setInterval(function() {
						sabongproAjax.betAmounts();
			        }, 5000);
				});
			</script>
	</body>
</html>