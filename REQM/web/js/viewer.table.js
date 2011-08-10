$(document).ready(function(){
    /*$("body").css("display", "none");
    $("body").fadeIn(2000);

    $("a").click(function(event){
        event.preventDefault();
        linkLocation = this.href;
        $("body").fadeOut(1000, redirectPage);
    });
    function redirectPage() {
        window.location = linkLocation;
    }*/

    $('input[type=image]').click(function(){
        $('#keycode').val($(this).attr('value'));
    });
    $("#report tr:odd").addClass("odd");
    $("#report tr:not(.odd)").hide();
    $("#report tr:first-child").show();

    $("#report tr.odd").click(function(){
        $(this).next("tr").toggle("normal");
        $(this).find(".arrow").toggleClass("up");
    });
    //$("#report").jExpand();
});