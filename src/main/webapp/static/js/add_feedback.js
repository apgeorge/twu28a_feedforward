function textCounter( field, countfield, maxlimit ) {
    if ( field.value.length > maxlimit )
    {
        return false;
    }
    else
    {
        $('#counter').css('color','green');
        var enters = field.value.match(/\n/g);
        var entersLength =  (enters == null)? 0 : enters.length;
        document.getElementById('counter').innerHTML = field.value.length + entersLength ;
    }
}
$('#add_feedback_container').ready(function(){

    $('#add_feedback_submit').click(function(){
        if(validateFeedback()==false){
            return false;
        }
        $.ajax({
            type: "POST",
            url: "add_feedback.html",
            cache: false,
            dataType: "html",
            async: true,
            data: { talkId: $(this).attr('talk-id'), feedbackComment: $('#feedback_text').val()}
        })
            .done(function(data){
                $('#feedback_text').val('');
                $('#add_feedback_container').html(data).trigger('create');
            });

    });


});
function validateFeedback(){
    $('#feedback_text').val($.trim($('#feedback_text').val()));
    return !($('#feedback_text').val()=="");
}
