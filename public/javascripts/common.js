function showMessage(msg){
	$.mobile.showPageLoadingMsg($.mobile.pageLoadErrorMessageTheme, msg, true);
    setTimeout(function() {
    	$.mobile.hidePageLoadingMsg();
  	}, 2000);
}

function showPopup(msg){
	$.mobile.showPageLoadingMsg($.mobile.pageLoadErrorMessageTheme, msg, true);
}

function clearForm(form){
    $('input',form).not('[type="button"]').val(''); // clear inputs except buttons, setting value to blank
    $('select',form).val(''); // clear select
    $('textarea',form).val(''); // set text area value to blank
}

function resetEmployeeList(){
    currentPage = 0;
    isLastPage = false;
    $('#list').empty();
    loadEmployeeList();
}

function loadEmployeeList(){
    $.ajax({
        url: "/employees/list",
        dataType: "json",
        data:{"queryName" : $("#queryName").val(), "pagination.currentPage" : currentPage},
        async: true,
        type: "post",
        beforeSend: function() {
            $.mobile.showPageLoadingMsg(true);
        },
        complete: function() {
            $.mobile.hidePageLoadingMsg();
        },
        success: function (data) {
            if(data && data.recordList && data.recordList.length > 0){
                $(data.recordList).each(function(i){
                    var html = "<li>";
                    html += "<a href='/employees/view/" + this.id + "' >";
                    html += "<h3>" + this.realname + "</h3>";
                    html += "<p class='topic'><strong></strong></p>";
                    html += "<p class='ui-li-aside'><strong>Status:" + this.status ? "Active" : "Inactive" + "</strong></p>";
                    html += "</a>";
                    html += "<a href='javascript:deleteDialog(" + this.id + ")' class='delete'>Delete</a>";
                    html += "</li>";
                    $("#list").append(html);
                });
                $('#list').listview('refresh');
                currentPage++;
                isLastPage = false;
            }else{
                isLastPage = true;
            }

        },
        error: function (request,error) {
            //alert('Network error has occurred please try again!');
        }
    });

}

function saveEmployee(form){

    var that = form;
    $.post("/employees/store", form.serialize(), function (response) {
        //window.location.href = "@{Employees.index()}";
        var editMode= $('#id',that).val();
        if(!editMode)
            clearForm(that);
        showMessage('Successfully save employee');
    });
}