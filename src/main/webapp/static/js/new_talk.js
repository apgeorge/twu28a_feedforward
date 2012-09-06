function validate_new_talk_form() {
    var date_pattern = new RegExp("[0-3][0-9]\/[0-1][0-9]\/[0-9]{4}");
    var time_pattern = new RegExp("[0-1][0-9]:[0-6][0-9] ((AM)|(PM))");
    var array = new Array();
    $('#title').val($.trim($('#title').val()));
    $('#venue').val($.trim($('#venue').val()));
    if ($('#title').val() == '')
        array['title'] = 'true';
    if (!date_pattern.test($('#datepicker').val()))
        array['datepicker'] = 'true';
    if (!time_pattern.test($('#timepicker').val()))
        array['timepicker'] = 'true';
    if ($('#venue').val() == '')
        array['venue'] = 'true';
    return array;
}
function resetAll() {
    $('#title').css('-moz-box-shadow', ' none');
    $('#title').css('-webkit-box-shadow', 'none');
    $('#title').css('box-shadow', ' none');
    $('#datepicker').css('-moz-box-shadow', ' none');
    $('#datepicker').css('-webkit-box-shadow', 'none');
    $('#datepicker').css('box-shadow', ' none');
    $('#timepicker').css('-moz-box-shadow', ' none');
    $('#timepicker').css('-webkit-box-shadow', 'none');
    $('#timepicker').css('box-shadow', ' none');
    $('#venue').css('-moz-box-shadow', ' none');
    $('#venue').css('-webkit-box-shadow', 'none');
    $('#venue').css('box-shadow', ' none');
}
$('#new_talk_submit').ready(function () {
    $('#new_talk_submit').click(function () {
        var array_validation_result = validate_new_talk_form();
        var flag_error = false;
        resetAll();
        for (element in array_validation_result) {
            if (array_validation_result[element] === 'true') {
                flag_error = true;
                $('#' + element).css('-moz-box-shadow', '0 0 12px red');
                $('#' + element).css('-webkit-box-shadow', '0 0 12px red');
                $('#' + element).css('box-shadow', '0 0 12px red');
            }
        }
        if (flag_error)
            return false;
        var url = "new_talk_submit.html" + "?title=" + $('#title').val() +
            "&description=" + $('#description').val() +
            "&venue=" + $('#venue').val() +
            "&date=" + $('#datepicker').val() +
            "&time=" + $('#timepicker').val();
        $.ajax({
            method:"GET",
            url:url,
            cache:false,
            dataType:"html",
            async:true
        })
            .done(function (data) {

                if (data.indexOf("true") != -1) {
                    $('#my_talks_button').trigger('click', ['New Talk Successfully Created']);
                } else {
                    $('#message_box_error').html('Cannot create talk with duplicate title');
                }
            });
    });
    $("#datepicker").scroller({ preset:'date', dateOrder:'ddmmyy', minDate:new Date(), dateFormat:'dd/mm/yyyy'});
    $("#timepicker").scroller({ preset:'time', stepMinute:5 });
});

            