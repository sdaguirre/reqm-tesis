$(document).ready(function(){
    $('input[type=image]').click(function(){
        $('#keycode').val($(this).attr('value'));
    });
    $("#report tr:odd").addClass("odd");
    $("#report tr:not(.odd)").hide();
    $("#report tr:first-child").show();

    $("#report tr.odd").click(function(){
        $(this).next("tr").toggle();
        $(this).find(".arrow").toggleClass("up");
    });
    //$("#report").jExpand();
});