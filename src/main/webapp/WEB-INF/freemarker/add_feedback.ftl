<!-- A presentation -->


    <div id = "add_feedback_container">
    <h4 id="feedback_status_message">
    </h4>


    <div data-role="fieldcontain">
        <center>
        <fieldset data-role="controlgroup" style="text-align: center; width: 100%;">
            <label for="feedback_text">
            </label>
            <textarea name="feedback" id="feedback_text" onkeypress="textCounter(this,document.getElementById('counter'),500);" placeholder="add feedback" value="" style="width: 100%; height: 20%;"
                   type="textArea" rows="9" cols="200"></textarea>

            <br>
            <p style="float: right; font-weight: bold;">  <span id="counter" style="color:green;">500</span> Characters remaining.
            </p>

            <br/>
            <input type="submit" id="add_feedback_submit" data-inline="true" data-theme="b" value="Submit" style="padding-bottom: 0.5%; padding-top: 1%;"
                       data-mini="false">
           </fieldset>


        </center>
    </div>

<div data-role="collapsible" data-collapsed="false">
    <h3>
        Past Feedback
    </h3>

    <ul data-role="listview" class="ui-listview" id="feedback-list">

    <#list retrieved_feedback_list as feedback>
        <li class="ui-li ui-li-static ui-body-c feedback-item">
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

                        function textCounter( field, countfield, maxlimit ) {
                          if ( field.value.length > maxlimit )
                          {
                            field.value = field.value.substring( 0, maxlimit );
                            $('#counter').css('color','red');
                            return false;
                          }
                          else
                          {
                             $('#counter').css('color','green');
                            document.getElementById('counter').innerHTML = maxlimit - field.value.length;
                          }
                        }
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
