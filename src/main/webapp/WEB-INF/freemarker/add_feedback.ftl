<!-- A presentation -->


    <div id = "add_feedback_container">
    <h4 id="feedback_status_message">
    </h4>


    <div data-role="fieldcontain">
        <center>
        <fieldset data-role="controlgroup" style="text-align: center; width: 100%;">
            <label for="feedback_text">
            </label>
            <textarea name="feedback" id="feedback_text" placeholder="add feedback" value="" style="width: 100%; height: 20%;"
                   type="textArea" rows="9" cols="200"></textarea>
            <br/>
            <input type="submit" id="add_feedback_submit" data-inline="true" data-theme="b" value="Submit" style="padding-bottom: 0.5%; padding-top: 0.5%;"
                       data-mini="true">
           </fieldset>
        </center>
    </div>

<div data-role="collapsible" data-collapsed="false">
    <h3>
        Past Feedback
    </h3>

    <ul data-role="listview" class="ui-listview">

    <#list retrieved_feedback_list as feedback>
        <li class="ui-li ui-li-static ui-body-c">
        ${feedback.attendee} (

             <span>
                <a href="mailto:${feedback.attendeeMail}">
                ${feedback.attendeeMail}
                </a>
             </span>
                )<br>
        ${feedback.timeAtCreation} <br>
        ${feedback.feedbackComment}
        </li>
    </#list>


    </div>
     <div id="result">
                    <#if result_message??>
                        <h1>${result_message}</h1>
                        </#if>
      </div>




<script>
                $('#add_feedback_container').ready(function(){

                 $('#add_feedback_submit').click(function(){

                    $.ajax({
                               type: "POST",
                               url: "add_feedback.html",
                               cache: false,
                               dataType: "html",
                               async: true,
                               data: { talkId: "0", feedbackComment: $('#feedback_text').val() }
                               })
                         .done(function(data){
                                $('#feedback_text').val('');
                                $('#add_feedback_container').html(data).trigger('create');



                                   });

                 });


                });

            </script>
