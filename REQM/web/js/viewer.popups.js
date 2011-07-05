$(document).ready(function(){
    $('input[type=image]').click(function(){
        $('#keycode').val($(this).attr('value'));
    });
    $("a[rel^='prettyPhoto']").prettyPhoto({
        animation_speed: 'normal',
        default_width: 100,
        default_height: 44,
        allow_resize: true,
        opacity: 0.70,
        show_title: true,
        theme: 'facebook'
    });
});


