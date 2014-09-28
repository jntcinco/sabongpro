//Tool scripts for the superfly pages.
// Use sabong variable instead using jQuery or sabong to avoid conflict.
var sabong = jQuery.noConflict();

// start bolas script
function ShowDialog(modal) {
	sabong("#overlay").show();
	sabong("#dialog").fadeIn(300);
	sabong("#overlay").unbind("click");
}

function HideDialog() {
	sabong("#overlay").hide();
	sabong("#dialog").fadeOut(300);
}

var sabongproCommons = {
	bolasScript : function() {
		sabong('#logo').addClass('animated wobble');
		sabong('#colLeft').addClass('animated fadeIn');
		sabong('#colRight').addClass('animated fadeIn');
		//sabong('#colMid').addClass('animated bounceInUp');
		sabong('#bub1').addClass('animated flipInX');
		sabong('#bub2').addClass('animated flipInY');
		sabong('#bub3').addClass('animated flipInX');

		sabong('#search').bind('keypress', function(e) {
			if (e.keyCode == 13) {
				sabong("#searchForm").submit();
			}
		});

		sabong("#btnClose").click(function(e) {
			HideDialog();
			e.preventDefault();
		});
	},
	populateElementsWithValues : function(response) {
		sabong("#meronEntryName").html(response.entryName);
		sabong("#meronOwnerName").html(response.ownerName);
		sabong("#meronBloodLine").html(response.bloodLine);
		sabong("#meronFightWeight").html(response.fightWeight + " kg");
		sabong("#walaEntryName").html(response.opponentEntryName);
		sabong("#walaOwnerName").html(response.opponentOwnerName);
		sabong("#walaBloodLine").html(response.opponentBloodLine);
		sabong("#walaFightWeight").html(response.opponentFightWeight + " kg");
		sabong("#fightNumber").html(response.fightNumber);
	}
}
// end bolas script

// used for jquery popup dialog
var userId;
var configId;
var streamingAccess;
var sabongproAjax = {
	activateStreamingAccess : function() {
		var virtualPoints = sabong('input#virtualPoints').val();
		var json = {"userId" : userId, "streamingAccess" : streamingAccess, "virtualPoints" : virtualPoints};
		sabong.ajax({
			url : sabong("#streamingActivationDialogForm").attr("action"),
			data : JSON.stringify(json),
			type : "POST",
			contentType: "application/json",
			dataType: "json",
			success : function(response) {
				var columnId = "#streamingAccessColumn"+userId;
				if(response.streamingAccess == 1) {
					sabong(columnId).html("Activated");
				} else {
					sabong(columnId).html("Deactivated");
				}
				alert(response.message );
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	updateStreamingStatus : function(description,streamUrl,streamStatus) {
		var json = {"configId":configId, "description":description, "streamUrl":streamUrl, "streamStatus":streamStatus};
		sabong.ajax({
			url : "/sabongpro/admin/streaming/config/update",
			data : JSON.stringify(json),
			type : "POST",
			contentType: "application/json",
			dataType: "json",
			success : function(response) {
				var config = response.config;
				var columnId = "#streamStatus"+configId;
				var descriptionVal = '<a href="#" onclick="sabongproWidgets.showUpdateStreamingStatusDialogForm('+configId+');">'+config.description+'</a>';
				sabong("#description"+configId).html(descriptionVal);
				sabong("#streamUrl"+configId).html(config.url);
				if(config.streamOnline) {
					sabong(columnId).html("Online");
				} else {
					sabong(columnId).html("Offline");
				}
				alert(response.message);
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	getStreamDetails : function() {
		sabong.ajax({
			url : "/sabongpro/services/getStreamDetails",
			data : {configId : configId},
			type : "GET",
			success : function(response) {
				sabong("#description").val(response.config.description);
				sabong("#streamUrl").val(response.config.url);
				var isStreamOnline = response.config.streamOnline;
				if(isStreamOnline) {
					sabong("#streamStatus").prop("checked", true);
				} else {
					sabong("#streamStatus").prop("checked", false);
				}
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	getUserStreamingAccess : function() {
		sabong.ajax({
			url : "/sabongpro/services/getUserStreamingAccess",
			data : {userId : userId},
			type : "GET",
			success : function(response) {
				var isAccess = response.userStreamingAccess;
				var columnId = "#streamingAccess";
				if(isAccess) {
					sabong(columnId).prop("checked", true);
				} else {
					sabong(columnId).prop("checked", false);
				}
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	getStreamUrl : function() {
		sabong.ajax({
			url : "/sabongpro/services/streamUrl",
			type : "GET",
			success : function(response) {
				var streamUrl = response.streamUrl;
				sabong('iframe#embededFrame').attr('src',streamUrl);
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	getBettingInfo : function(userName, side) {
		var odd = sabong("#oddsId").val();
		var betAmount = sabong("#betAmount").val();
		sabong.ajax({
			url : "/sabongpro/bettingServices",
			data : {userName:userName,side:side,odds:odd,betAmount:betAmount},
			type : "GET",
			success : function(response) {
				if(response) {
					alert("Betting is close.");
				}
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	closeBetting : function() {
		sabong.ajax({
			url : "/sabongpro/bettingServices/closeBetting",
			type : "GET",
			success : function(response) {
				sabong("#betStatus").html("CLOSE");
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	betAmounts : function() {
		sabong.ajax({
			url : "/sabongpro/bettingServices/betAmounts",
			type : "GET",
			success : function(response) {
				sabong("#meronAmount").html(response.meronTotal);
				sabong("#walaAmount").html(response.walaTotal);
				sabong("#nineTenMeronAmount").html(response.nineTenMeronTotal);
				sabong("#nineTenWalaAmount").html(response.nineTenWalaTotal);
				sabong("#eightTenMeronAmount").html(response.eightTenMeronTotal);
				sabong("#eightTenWalaAmount").html(response.eightTenWalaTotal);
				sabong("#tenNineMeronAmount").html(response.tenNineMeronTotal);
				sabong("#tenNineWalaAmount").html(response.tenNineWalaTotal);
				sabong("#tenEightMeronAmount").html(response.tenEightMeronTotal);
				sabong("#tenEightWalaAmount").html(response.tenEightWalaTotal);
				if(response.isLocked) {
					sabong("#betStatus").html("CLOSE");
				}
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	declareWinner : function() {
		var winner = sabong('input:radio[name=winner]:checked').val();
		sabong.ajax({
			url : "/sabongpro/bettingServices/declareWinner",
			data : {winner:winner},
			type : "GET",
			success : function(response) {
				alert("new game.");
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	getEntryMatch : function() {
		sabong.ajax({
			url : "/sabongpro/bettingServices/getEntryMatch",
			type : "GET",
			success : function(response) {
				sabongproCommons.populateElementsWithValues(response);
			},
			error : function(xhr) {
				alert("Error code: "+xhr.status);
			}
		});
	},
	getCurrentMatch : function() {
		sabong.ajax({
			url : "/sabongpro/bettingServices/getCurrentMatch",
			type : "GET",
			success : function(response) {
				sabongproCommons.populateElementsWithValues(response);
			},
			error : function(xhr) {
				alert("Error code: "+xhr.status);
			}
		});
	}
//	saveNewUser : function(userName,email,password,isStreaming,isEnabled,role){
//		sabong.ajax({
//			url : "/sabongpro/admin/user/add",
//			type : "POST",
//			data : {userName:userName, email:email, password:password, isStreaming:isStreaming, isEnabled:isEnabled, role:role},
//			success: function(response){
//				//alert(response.message);
//			},
//			error: function(xhr) {
//				alert("Error Code: " +xhr.status);
//			}
//		});
//	}
}

var sabongproWidgets = {
	streamingActivationDialog : function() {
		sabong("#streamingActivationDialog").dialog({
			resizable: true,
			autoOpen:false,
			modal: true,
			width:400,
			height:200,
			buttons: {
				'Activate': function() {
					var isStreaming = sabong('#streamingAccess').prop('checked');
					if(isStreaming) {
						streamingAccess = 1;
					} else {
						streamingAccess = 0;
					}
					sabongproAjax.activateStreamingAccess();
					sabong(this).dialog('close');
				},
				'Cancel': function() {
					sabong(this).dialog('close');
				}
			}
		});
	},
	updateStreamingStatusDialog : function() {
		sabong("#updateStreamingStatusDialog").dialog({
			resizable: true,
			autoOpen:false,
			modal: true,
			width:400,
			height:200,
			buttons: {
				'Save': function() {
					var description = sabong("#description").val();
					var streamUrl = sabong("#streamUrl").val();
					var streamStatus;
					var isStreaming = sabong('#streamStatus').prop('checked');
					if(isStreaming) {
						streamStatus = 1;
					} else {
						streamStatus = 0;
					}
					sabongproAjax.updateStreamingStatus(description,streamUrl,streamStatus);
					sabong(this).dialog('close');
				},
				'Cancel': function() {
					sabong(this).dialog('close');
				}
			}
		});
	},
	sintencyaDialog : function() {
		sabong("#sintencyaDialog").dialog({
			resizable: true,
			autoOpen:false,
			modal: true,
			width:300,
			height:150,
			position: ["center",80],
			buttons: {
				'Ok': function() {
					sabongproAjax.declareWinner();
					sabong(this).dialog('close');
				},
				'Cancel': function() {
					sabong(this).dialog('close');
				}
			}
		});
	},
	showstreamingActivationDialogForm : function(id) {
		userId = id;
		sabongproAjax.getUserStreamingAccess();
		sabong('#streamingActivationDialog').dialog('open');
	},
	showUpdateStreamingStatusDialogForm : function(id) {
		configId = id;
		sabongproAjax.getStreamDetails();
		sabong('#updateStreamingStatusDialog').dialog('open');
	},
	showSintencyaDialogForm : function(){
		sabong('#sintencyaDialog').dialog('open');
	}
}

//function isValidUser(userName,email,password,confirmPassword){
//	var noError = true;
//	
//	if(userName == ''){
//		sabong('#userNameError').text('required');
//		noError = false;
//	}
//	if(email == ''){
//		sabong('#emailError').text('required');
//		noError = false;
//	}
//	if(password == ''){
//		sabong('#passwordError').text('required');
//		noError = false;
//	}
//	if(confirmPassword == ''){
//		sabong('#confirmPasswordError').text('required');
//		noError = false;
//	}
//
//	if(email!=''){
//		if(!isValidEmailAddress(email)){
//			sabong('#emailError').text('invalid');
//			noError = false;
//		}
//	}
//	
//	if(password!='' && confirmPassword!=''){
//		if(password != confirmPassword){
//			sabong('#passwordError').text('not match');
//			sabong('#confirmPasswordError').text('not match');
//			noError = false;
//		}
//	}
//	
//	return noError;
//}
//
//function isValidEmailAddress(emailAddress) {
//    var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
//    return pattern.test(emailAddress);
//}
