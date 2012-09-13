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

    $('#export_feedback_button').click(function(){
            ajax_call({type: "POST", url: "export_feedback.html", data: { talk_id: $(this).attr('talk-id')}},
                      function(data){
                          if (data.indexOf("isExported") != -1) {
                              $('#message_box_success_feedback').html('Feedback has been emailed to you.');
                          } else {
                              $('#message_box_error_feedback').html('Unable to email feedback.');
                          }
                          $.mobile.hidePageLoadingMsg();
          });
     });
});



function validateFeedback(){
    $('#feedback_text').val($.trim($('#feedback_text').val()));
    return !($('#feedback_text').val()=="");
}
