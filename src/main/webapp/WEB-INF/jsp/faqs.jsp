<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FAQ - Sabong Pro</title>
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
<body class="bodyhome2" id="faq">
<div id="headerwide">
	<div id="header">
        <div id="logo">
	<a href="<c:url value='/'/>" title="Sabong Pro"><img src="<c:url value='/images/logo.png'/>" alt="Logo" width="335" height="90" /></a>
</div> <!--eof logo -->
<div id="centrHeader">
    <a href="<c:url value='/authenticate'/>">
        <div class="loginblck"></div>
    </a>

    <a href="<c:url value='/register'/>">
        <div class="signupblck"></div>
    </a>
</div> <!--eof centrHeader -->

<div id="mainMenu">
    <ul>
        <li><a href="<c:url value='/'/>" id="menuhome">HOME</a></li>
        <li><a href="<c:url value='/about'/>" id="menuabout">ABOUT</a></li>
        <li><a href="<c:url value='/schedule'/>" id="menugames">SCHEDULE</a></li>
        <li><a href="<c:url value='/faqs'/>" id="menugallery">FAQS</a></li>
        <li><a href="<c:url value='/contact'/>" id="menucontact">CONTACT</a></li>
    </ul>
</div> <!--eof mainMenu -->

    </div> <!--eof header -->
</div>
	<div id="wrapper">
    	<div id="innerWrap">
          <div id="content" class="margtop20">
           	  
            <div id="userBlock">
              <h2 class="dark textshadowWhite">Frequently Asked Questions</h2>
              <p>Sabongro.com reserves the right to amend these Frequently Asked Questions at any time without prior notification. All editions will show version number and validity update time. It is the responsibility of the customer to regularly review all of them to see whether any changes or updates have been made.</p>
              <h3 class="dark textshadowWhite">1. What is the minimum computer specification required by sabongpro.com?</h3>
              <ul class="bluorb">
              	<li>Pentium 1.0 GHz processor or greater</li>
                <li>Google Chrome, Mozilla Firefox, Internet Explorer 7 or 8 and Safari (with Adobe Flash Player, Javascript and cookies enabled)</li>
                <li>4 GB MB of system memory available</li>
                <li>128 MB of video memory</li>
                <li>1024 x 768 video resolution (16 bit color)</li>
                <li>500 MB of available hard drive space</li>
                <li>Sound Card with speakers</li>
                <li>Internet access with at least 2 Mbps connection speed.</li>
              </ul>
              
              <h3 class="dark textshadowWhite">2. Why do I experience buffering? choppy video?</h3>
              	<ul class="bluorb">
           		  <li>Buffering is caused by slow internet communication from your router or computer to the sabongpro live stream server.</li>
                </ul>
              <h3 class="dark textshadowWhite">3. I am using Internet Explorer 9, some texts on the live game screen are overlapping, I can't see all the odds?</h3>
              <ul class="bluorb">
              <li>If you are using Internet Explorer 9, please set your browser to Compatibility view. See figure below.</li>
              </ul>
              <p>&nbsp;&nbsp;&nbsp;<img src="<c:url value='/images/ie_compatibilityview.jpg'/>" width="764" height="265" /></p>
              <h3 class="dark textshadowWhite">4. Where are these fight matches held?</h3>
              <ul class="bluorb">
              <li>Our fight matches are legal under Philippine Law and is held at Makati Coliseum.</li>
              </ul>
              <h3 class="dark textshadowWhite">5. How do I register?</h3>
              <ul class="bluorb">
              	<li><p class="margleft0">In your browser address, please type www.sabongpro.com and click on the REGISTER button. Complete all the information required by the Registration Form. Click submit when done. Check your email inbox for the confirmation email. You must click on the link provided in the confirmation email for your account at sabongpro.com to be active.<br />
<br />
Please check with your local law before registering. Prohibited to play from the U.S.A. and other prohibited jurisdictions.</p></li>
              </ul>
              
              <h3 class="dark textshadowWhite">6. How do I purchase SabongPro Virtual Points?</h3>
              <ul class="bluorb">
              	<li>MINIMUM PURCHASE: 2000 SabongPro Virtual Points or PHP 2,000.</li>
                <li>PAYMENT VERIFICATION CUT-OFF: Our payment/deposit verification cut-off is every day at 3:00PM GMT +0800 (Manila Time)</li>
                <li>You may use the following money-transfer firms for your payments to purchase your SabongPro Virtual Points.</li>
                <li>For LBC, MoneyGram, M.Lhuillier Kwarta Padala, Cebuana Lhuillier Pera Padala and IRemit, please make your payments to:<br />
                <p class="margleft0">FIRST NAME LAST NAME<br />
				Street Address, City Postal Code<br />
				PHILIPPINES</p>
                </li>
                <li>Other options here:<br />
                  &nbsp;<br />
                  &nbsp;<br />
                </li>
              </ul>
              <h3 class="dark textshadowWhite">7. Do you accept payments through Credit Cards?</h3>
              <ul class="bluorb">
              	<li><p>Due to Sabongpro being a match betting site, and for the protection of all, we are only accepting indemnified funds so that the site can guarantee all payouts. As such, we sincerely ask you that you choose one of the many methods that are already active to fund your SabongPro account.</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">8. What do you mean by "Your Bet has been successfully matched to an opponent?&quot;</h3>
              <ul class="bluorb">
              	<li>
              	  <p>In real-life cockfight betting, each bet would have to find a corresponding match from another person's bet. If for example, you placed a bet on WALA at 1,000 Points at 10/9 odds, sabongpro will automatically help you find a match from another members bet on MERON at 1,000 Points at 9/10 odds. This we called 'NOTE In sabongpro, your opponent is not sabongpro, not the house, but other sabongpro members. If one member account will try to make multiple bets hoping to have them all matched,... they never will.<br />
              	    <br />
              	    NOTE: In sabongpro, your opponent is not sabongpro, not the house, but other sabongpro members. If one member account will try to make multiple bets hoping to have them all matched,... they never will.
              	  </p>
              	</li>
              </ul>
              
              <h3 class="dark textshadowWhite">9. What do you mean by "Your Bet has been accepted but would have to wait for a match?"</h3>
              <ul class="bluorb">
              	<li><p>As in the previous question, you will receive this message when you placed a bet and no match has yet been found. If at any point, while the betting period is still open, another member placed-in a bet, and your bet and the incoming bet is 'ideally match' , these bets will automatically be matched. Bets are processed in the order they are received.</p></li>
              </ul>
              <h3 class="dark textshadowWhite">10. I made a multiple Bet on 'MERON' at odds 10/10 and on 'WALA' at odds 10/10, how come it did not match?</h3>
              <ul class="bluorb">
              	<li><p>By the term <strong><em>'opponent'</em></strong> we refer to it as <strong><em>'person other than yourself'</em></strong>. So if we are to match your bet to an opponent, sabongpro.com will search for a different person's bet, opposite of yours.</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">11. What are the odds available?</h3>
              <ul class="bluorb">
              	<li><p>The odds available for the crowds favourite <strong>'LLIAMADO'</strong> are 10/10, 9/10 and 8/10. While the odds available for the underdog <strong>'DEHADO'</strong> are 10/10, 10/9 and 10/8.</p>
              	</li>
              </ul>
              
              <h3 class="dark textshadowWhite">12. How is the NET PAYOUT computed?</h3>
              <ul class="bluorb">
              	<li><p>All winnings are subject to 10% House Commission as disclosed in the Terms and Conditions. The Net Payout is computed using the following formula:<br />
              	  <br />
              	Net Payout = (BET x ODDS) less 10% House Commission (placada)<br />
              	  i.e. Bet on MERON of 1000 points at 9/10 odds.<br />
              	  Net Payout = (1000 x (9/10)) - ((1000 x (9/10)) x (10/100))<br/>
              	  = (1000 x .9) - ((1000 x .9) x .1)<br />
              	  = 900 - (900 x .1)<br />
              	  = 900 - 90<br />
              	  Net Payout = 810 Points</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">13. Can I delete my bet?</h3>
              <ul class="bluorb">
              	<li><p>A Bet may still be deleted under the conditions that the bet has not yet been matched to an opponent's bet and the betting period for the active match is still OPEN.</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">14. What will happen to my bet if there is no match-opponent-bet found?</h3>
              <ul class="bluorb">
              	<li><p>At the release of the cocks, we will close the betting period. All unmatched bet tickets will be marked as 'UNMATCHED', and all bet points are returned to the customers' credit without any deductions.</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">15. I have already placed my bet, but the match did not start and later was called-off, what happens to my bet points then?</h3>
              <ul class="bluorb">
              	<li><p>If such is the case, the match will be declared CANCELLED, all bet tickets will be rendered VOID, and bet points are returned to the customers' credit without any deductions.</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">16. In the event that a particular match result is a DRAW, what will happen to all the bets?</h3>
              <ul class="bluorb">
              	<li><p>Should a fight match results to a DRAW, all bet tickets with matched bets will be marked on the fight result as 'DRAW', all bet points are returned to the customer's credit without any deduction.</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">17. How do I cash-out my SabongPro Virtual Points?</h3>
              <ul class="bluorb">
              	<li><p>You may cash-out your SabongPro Virtual Points in the same manner you purchased it.<br />
<br />
PICK YOUR CHOICE: Western Union, LBC, MoneyGram, M.Lhuillier, XOOM, IRemit and/or Bank Transfer methods. Send us an email with the subject "CASHOUT REQUEST" and send it to support@sabongpro.com or use the SUPPORT page to send your request.. Mention the amount, transfer method and recipient details/Bank Info. We may require you to provide us copy via fax / scanned copy of your valid ID e.i. Drivers License, Travel Passport and complete address and bank account details (for bank transfer) for fraud protection. We, in-turn, will provide you via email scanned copy proof of the fund transfer and your latest statement of account.</p>
              	</li>
              </ul>
              <h3 class="dark textshadowWhite">18. Do you allow transfer of SabongPro Virtual Points to other members?</h3>
              <ul class="bluorb">
              	<li><p>We do not allow transfer of points to other members.</p></li>
              </ul>
              <h3 class="dark textshadowWhite">19. How do I file dispute over a particular fight or match number?</h3>
              <ul class="bluorb">
              	<li>
              	  <p>Should you wish to file a dispute over a match or a fight, please send us an email at support@sabongpro.com with subject as DISPUTE ON FIGHT No. #. Indicate precisely the details like the Date of the derby, Fight No., Bet Ticket, and details under dispute. We will conduct an investigation based on the information you have provided. It is difficult to conduct an investigation while there is an on-going derby. Please consider two (2) days to process your request.</p>
              	</li>
              </ul>
            </div><!--eof userBlock -->
          </div> <!--eof content -->
      </div> <!--eof innerWrap -->
      <!--eof footer -->
</div> <!--eof wrapper -->
<div id="footer">
	<div id="innerfoot">
  		<div id="footleft">
        <ul>
            <li><a href="<c:url value='/'/>">HOME</a></li>
            <li><a href="<c:url value='/about'/>">ABOUT</a></li>
            <li><a href="<c:url value='/schedule'/>">SCHEDULES</a></li>
            <li><a href="<c:url value='/faqs'/>">FAQS</a></li>
            <li><a href="<c:url value='/contact'/>">CONTACT</a></li>
        </ul>
    	</div>
        <div id="copyright">
            &copy; 2014 <a href="<c:url value='/'/>">www.SabongPro.com</a>. All Rights Reserved &reg;
        </div>
	</div><!--eof innerfoot --><!--eof innerfoot -->
</div> <!--eof footer -->
</body>
</html>