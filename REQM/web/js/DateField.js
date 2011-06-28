$(function() {
    $('#dateField').datepick({
        showOnFocus: true,
        showTrigger: '#calImg'
    });
    $('#dateFieldA').datepick({
        showOnFocus: true,
        showTrigger: '#calImg'
    });
    $('#dateFieldB').datepick({
        showOnFocus: true,
        showTrigger: '#calImg'
    });

    $('#dateField').datepick('option',{
        showAnim: 'fold',
        showSpeed: 'slow'
    });
    $('#dateFieldA').datepick('option',{
        showAnim: 'fold',
        showSpeed: 'slow'
    });
    $('#dateFieldB').datepick('option',{
        showAnim: 'fold',
        showSpeed: 'slow'
    });
});
