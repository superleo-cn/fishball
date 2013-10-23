function showMessage(msg){
	$.mobile.showPageLoadingMsg($.mobile.pageLoadErrorMessageTheme, msg, true);
    setTimeout(function() {
    	$.mobile.hidePageLoadingMsg();
  	}, 3000);
}

function showPopup(msg){
	$.mobile.showPageLoadingMsg($.mobile.pageLoadErrorMessageTheme, msg, true);
}