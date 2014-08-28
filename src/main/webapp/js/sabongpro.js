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
				$('iframe#embededFrame').attr('src',streamUrl);
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	}
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
				'Activate': function() {
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
}