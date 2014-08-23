// start bolas script
function ShowDialog(modal) {
	$("#overlay").show();
	$("#dialog").fadeIn(300);
	$("#overlay").unbind("click");
}

function HideDialog() {
	$("#overlay").hide();
	$("#dialog").fadeOut(300);
}

var sabongproCommons = {
	bolasScript : function() {
		$('#logo').addClass('animated wobble');
		$('#colLeft').addClass('animated fadeIn');
		$('#colRight').addClass('animated fadeIn');
		//$('#colMid').addClass('animated bounceInUp');
		$('#bub1').addClass('animated flipInX');
		$('#bub2').addClass('animated flipInY');
		$('#bub3').addClass('animated flipInX');

		$('#search').bind('keypress', function(e) {
			if (e.keyCode == 13) {
				$("#searchForm").submit();
			}
		});

		$("#btnClose").click(function(e) {
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
		$.ajax({
			url : "/sabongpro/sabongpro/admin/user/allow/access",
			/*url : /sabongpro/admin/user/allow/access",*/
			type : "POST",
			data : {userId : userId, streamingAccess : streamingAccess},
			success : function(response) {
				var columnId = "#streamingAccessColumn"+userId;
				if(response.streamingAccess == 1) {
					$(columnId).html("Activated");
				} else {
					$(columnId).html("Deactivated");
				}
				alert(response.message);
			},
			error : function(xhr) {
				alert("Error Code: "+xhr.status);
			}
		});
	},
	updateStreamingStatus : function(description,streamUrl,streamStatus) {
		$.ajax({
			url : "/sabongpro/sabongpro/admin/streaming/config/update",
			/*url : /sabongpro/streaming/config/update",*/
			type : "POST",
			data : {configId:configId, description:description, streamUrl:streamUrl, streamStatus:streamStatus},
			success : function(response) {
				var columnId = "#streamStatus"+configId;
				if(response.streamStatus == 1) {
					$(columnId).html("Online");
				} else {
					$(columnId).html("Offline");
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
		$("#streamingActivationDialog").dialog({
			resizable: true,
			autoOpen:false,
			modal: true,
			width:400,
			height:200,
			buttons: {
				'Activate': function() {
					var isStreaming = $('#streamingAccess').prop('checked');
					if(isStreaming) {
						streamingAccess = 1;
					} else {
						streamingAccess = 0;
					}
					sabongproAjax.activateStreamingAccess();
					$(this).dialog('close');
				},
				'Cancel': function() {
					$(this).dialog('close');
				}
			}
		});
	},
	updateStreamingStatusDialog : function() {
		$("#updateStreamingStatusDialog").dialog({
			resizable: true,
			autoOpen:false,
			modal: true,
			width:400,
			height:200,
			buttons: {
				'Activate': function() {
					var description = $("#description").val();
					var streamUrl = $("#streamUrl").val();
					var streamStatus;
					var isStreaming = $('#streamStatus').prop('checked');
					if(isStreaming) {
						streamStatus = 1;
					} else {
						streamStatus = 0;
					}
					sabongproAjax.updateStreamingStatus(description,streamUrl,streamStatus);
					$(this).dialog('close');
				},
				'Cancel': function() {
					$(this).dialog('close');
				}
			}
		});
	},
	showstreamingActivationDialogForm : function(id) {
		userId = id;
		$('#streamingActivationDialog').dialog('open');
	},
	showUpdateStreamingStatusDialogForm : function(id) {
		configId = id;
		$('#updateStreamingStatusDialog').dialog('open');
	}
}