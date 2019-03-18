$("#resolution-checkbox").hide();
$("#resolution-button-close").hide();
$("#resolution-button").click(function(){
    $("#resolution-checkbox").slideDown();
    $("#resolution-button").hide();
    $("#resolution-button-close").show();
    $("#resolution-button-close").click(function(){
        $("#resolution-checkbox").slideUp();
        $("#resolution-button-close").hide();
        $("#resolution-button").show();
    })


});