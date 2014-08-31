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
	}
}
// end bolas script

// used for jquery popup dialog
var userId;
var configId;
var streamingAccess;
var sabongproAjax = {
	activateStreamingAccess : function() {
		sabong.ajax({
			url : "/sabongpro/admin/user/allow/access",
			type : "POST",
			data : {userId : userId, streamingAccess : streamingAccess},
			success : function(response) {
				var columnId = "#streamingAccessColumn"+userId;
				if(response.streamingAccess == 1) {
					sabong(columnId).html("Activated");
				} else {
					sabong(columnId).html("Deactivated");
				}
				alert(response.message);
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	updateStreamingStatus : function(description,streamUrl,streamStatus) {
		sabong.ajax({
			url : "/sabongpro/admin/streaming/config/update",
			type : "POST",
			data : {configId:configId, description:description, streamUrl:streamUrl, streamStatus:streamStatus},
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
				var streamUrl = "//" + response.streamUrl;
				sabong('iframe#embededFrame').attr('src',streamUrl);
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
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
//	addUserDialog : function(){
//		sabong('#addUserDialog').dialog({
//			resizable: true,
//			autoOpen:false,
//			modal: true,
//			width:450,
//			height:280,
//			buttons: {
//				'Add': function() {
//					var userName = sabong("#userName").val();
//					var email = sabong("#email").val();
//					var password = sabong("#password").val();
//					var confirmPassword = sabong("#confirmPassword").val();
//					var isStreaming = sabong("#userStreamingAccess").val();
//					var isEnabled = sabong("#enabled").val();
//					var role = sabong("#userRole").val();
//					
//					if(isValidUser(userName,email,password,confirmPassword)){
//						sabongproAjax.saveNewUser(userName,email,password,isStreaming,isEnabled,role);
//						sabong(this).dialog('close');
//
//						sabong("#userManagementLink").trigger("click");
//					}
//				},
//				'Cancel': function() {
//					sabong(this).dialog('close');
//				}
//			}
//		});
//	},
	showstreamingActivationDialogForm : function(id) {
		userId = id;
		sabongproAjax.getUserStreamingAccess();
		sabong('#streamingActivationDialog').dialog('open');
	},
	showUpdateStreamingStatusDialogForm : function(id) {
		configId = id;
		sabongproAjax.getStreamDetails();
		sabong('#updateStreamingStatusDialog').dialog('open');
	}
//	showAddUserDialogForm : function(){
//		sabong('#addUserDialog').dialog('open');
//	}
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
