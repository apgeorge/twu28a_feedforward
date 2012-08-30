<!-- A presentation -->


    <div id = "add_feedback_container">
    <h4 id="feedback_status_message">
    </h4>


    <div data-role="fieldcontain">
        <fieldset data-role="controlgroup">
            <label for="feedback_text">
            </label>
            <input name="feedback" id="feedback_text" placeholder="add feedback" value=""
                   type="text">
        </fieldset>
    </div>
    <input type="submit" id="add_feedback_submit" data-inline="true" data-theme="b" value="Submit"
           data-mini="true">

    <div data-role="collapsible" data-collapsed="false">
        <h3>
            Past Feedback
        </h3>
        <ul data-role="listview" data-divider-theme="b" data-inset="true">


        <#--  <#list ListName as feedback>
        <li>
            <a data-transition="slide">
               ${feedback.feedbackComment}  -
               <a href="mailto:gohan@dragon.ball">Email Gohan</a>
               ${feedback.attendee}(${feedback.attendeeMail})
            </a>
        </li>
        </#list>-->


            <li data-theme="c">
                <a href="#page3" data-transition="slide">
                    I like this presentation because it is awesome
                </a>
            </li>

            <li data-theme="c">
                <a href="#page3" data-transition="slide">
                    The presentation needs more pictures
                </a>
            </li>


        </ul>
    </div>
    </div>



<script>
                $('#add_feedback_container').ready(function(){
                 $('#add_feedback_submit').click(function(){

                    $.ajax({
                               method: "POST",
                               url: "add_feedback.html",
                               cache: false,
                               dataType: "html",
                               async: true,
                               data: { talkId: "1", feedbackComment: $('#feedback_text').val() }
                               })
                         .done(function(data){
                             $('#feedback_status_message').html(data).trigger('create');
                             $('#feedback_text').val('');
                 });
                });

            </script>