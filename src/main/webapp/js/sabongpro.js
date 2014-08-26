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
				var columnId = "#streamStatus"+configId;
				if(response.streamStatus == 1) {
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
		sabong('#streamingActivationDialog').dialog('open');
	},
	showUpdateStreamingStatusDialogForm : function(id) {
		configId = id;
		sabong('#updateStreamingStatusDialog').dialog('open');
	}
}