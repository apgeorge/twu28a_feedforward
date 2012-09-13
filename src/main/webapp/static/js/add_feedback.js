function textCounter( field, countfield, maxlimit ) {
    if ( field.value.length > maxlimit ){
        return false;
    }
    else{
        $('#counter').css('color','green');
        var enters = field.value.match(/\n/g);
        var entersLength =  (enters == null)? 0 : enters.length;
        document.getElementById('counter').innerHTML = field.value.length + entersLength ;
    }
}
$('#add_feedback_container').ready(function(){
    $('#add_feedback_submit').click(function(){
        if(!validateFeedback()){
            document.getElementById('counter').innerHTML=0;
            $('#feedback_text').css('-moz-box-shadow', '0 0 12px red');
            $('#feedback_text').css('-webkit-box-shadow', '0 0 12px red');
            $('#feedback_text').css('box-shadow', '0 0 12px red');
            return false;
        }

        ajax_call({type: "POST", url: "add_feedback.html", data: { talk_id: $(this).attr('talk-id'), feedbackComment: $('#feedback_text').val()}},
                  function(data){
                    $('#feedback_text').val('');
                    $('#add_feedback_container').html(data).trigger('create');
                    $('html,body').animate({
                       delay: 800,
                       scrollTop: $("#start_of_feedback_list").offset().top
                    }) ;
                  });
    });


});

function validateFeedback(){
    $('#feedback_text').val($.trim($('#feedback_text').val()));
    return !($('#feedback_text').val()=="");
}

$('#feedback_content').ready(function(){
    current_talk_id= $('#add_feedback_submit').attr('talk-id');
    if(current_talk_id!=undefined){
        setInterval( function reloadFeedbacks(){

            ajax_call({url:"feedback_list.html?talk_id=" + current_talk_id},
                function (data) {
                    $('#feedback_content').html(data).trigger('create');
                });
        },30000);
    }

});
