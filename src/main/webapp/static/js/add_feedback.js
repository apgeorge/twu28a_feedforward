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
        if(validateFeedback()==false){
            return false;
        }
        ajax_call({type: "POST", url: "add_feedback.html", data: { talkId: $(this).attr('talk-id'), feedbackComment: $('#feedback_text').val()}},
                  function(data){
                    $('#feedback_text').val('');
                    $('#add_feedback_container').html(data).trigger('create');
                    $('html,body').animate({
                       delay: 800,
                       scrollTop: $("#start_of_feedback_list").offset().top
                    }) ;
                  });
    });

//    $('#export_feedback_button').click(function(){
//            ajax_call({type: "POST", url: "export_feedback.html", data: { talkId: $(this).attr('talk-id')}},
//                      function(data){
//                          if (data.indexOf("true") != -1) {
//                                    $('#add_feedback_container').html(data).trigger('create', ['Worked!']);
////                              $('#my_talks_button').trigger('click', ['New Talk Successfully Created']);
//                          } else {
//                              $('#message_box_error').html('Cannot create talk with duplicate title');
//                          }
//                      });
//
//
//                      });
//        });
});


function validateFeedback(){
    $('#feedback_text').val($.trim($('#feedback_text').val()));
    return !($('#feedback_text').val()=="");
}
