<#include "/macros.ftl">
<#escape x as x?html>
    <div id = "add_feedback_container">
    <h4 id="feedback_status_message">
    </h4>


    <div data-role="fieldcontain">
        <center>
        <fieldset data-role="controlgroup" style="text-align: center; width: 100%;">
            <label for="feedback_text">
            </label>
            <textarea name="feedback" id="feedback_text" maxlength="500" onInput="textCounter(this,document.getElementById('counter'),500);"  placeholder="add feedback" value="" style="width: 100%; height: 20%;"
                   type="textArea" rows="9" cols="200"></textarea>


            <p style="float: right; font-weight: bold;"><span id="counter" style="color:black;">0</span >/500</p>

            <br>

           </fieldset>
            <input type="submit" id="add_feedback_submit" talk-id="${talk_id}" data-inline="true" data-theme="b" value="Submit" style="padding-bottom: 0.5%; padding-top: 1%;"
                   data-mini="false">

        </center>
    </div>

<div data-role="collapsible" data-collapsed="false">
    <h3>
        Past Feedback
    </h3>

    <ul data-role="listview" class="ui-listview" id="feedback-list">

    <#list retrieved_feedback_list as feedback>
        <li class="ui-li ui-li-static ui-body-c feedback-item" ">
            <h4>
            <@nl2br>
            ${feedback.feedbackComment}
            </@nl2br>
            </h4>
            <p><strong>&nbsp; &nbsp; &nbsp; - ${feedback.attendee}</strong>
                <span>
                    <a href="mailto:${feedback.attendeeMail}">${feedback.attendeeMail}
                    </a>
                </span>
            </p>
            <p class="ui-li-aside"><strong>${feedback.timeAtCreation.toString("dd/MM/YYYY  hh:mm a")}</strong></p>
        </li>
    </#list>


    </div>
     <div id="result">
                    <#if result_message??>
                        <h1>${result_message}</h1>
                        </#if>
      </div>

<script>

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

            </script>
</#escape>