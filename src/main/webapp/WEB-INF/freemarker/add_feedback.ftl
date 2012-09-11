<#include "/macros.ftl">
<#escape x as x?html>
<style type="text/css">
    .ui-btn.ui-submit {
        width: 100% !important;
    }
</style>
<div id="add_feedback_container">
    <h4 id="feedback_status_message">
    </h4>

    <form id="feedback_form">
        <div data-role="fieldcontain">
            <center>
                <fieldset data-role="controlgroup" style="text-align: center; width: 100%;">
                    <label for="feedback_text">
                    </label>
                    <textarea name="feedback" id="feedback_text" maxlength="500"
                              onInput="textCounter(this,document.getElementById('counter'),500);"
                              placeholder="Add feedback"
                              value="" style="width: 100%; height: 20%;"
                              type="textArea" rows="9" cols="200"></textarea>

                    <div>
                        <p style="float: right; font-weight: bold;"><span id="counter" style="color:black;">0</span>/500
                        </p>
                    </div>
                    <br>

                    <div style="margin-top: 50px; width: 100%;">
                        <input type="submit" id="add_feedback_submit" talk-id="${talk_id}" data-inline="true"
                               data-theme="b"
                               value="Submit" style="padding-bottom: 0.5%; padding-top: 1%;"
                               data-mini="false">
                    </div>
                </fieldset>
            </center>
        </div>
    </form>

    <!-- $('#list_of_feedbacks').text().replace(/[ ]/gi,'').replace(/\n\n\n/gi,'\n') -->

    <div id="start_of_feedback_list" data-role="collapsible" data-collapsed="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
        <h3>
            Past Feedback
        </h3>
        <ul id="list_of_feedbacks" data-role="listview" class="ui-listview" id="feedback-list" >
            <#if retrieved_feedback_list?has_content>
            <#list retrieved_feedback_list as feedback>
                <span style="display:none;"> --------------------------------------</span>
                <li id="feedback_messages" class="ui-li ui-li-static ui-body-c feedback-item"
                ">
                <h4 style="white-space: pre-line; word-wrap: break-word; width: 95%;">
                    <p style="font-size: 15px">
                    <@nl2br>${feedback.feedbackComment}</@nl2br>
                    </p>
                </h4>

            <li>
                <p style="font-size: 15px">&nbsp; &nbsp; &nbsp; <a
                        href="mailto:${feedback.attendeeMail}">${feedback.attendeeMail}</a>
                on ${feedback.timeAtCreation.toString("dd MMMM YYYY,  hh:mm a")}
                </p>
                </li>
             </#list>
             <#else>
                 <div style="font-size: 25px; color: gray; text-shadow: 1px 1px 0px #BAD3ED;font-weight: bold;text-align: center;">
                             <img src="static/images/sad_cat.png">
                             <br>
                             No feedback received yet.
                 </div>

            </#if>


         </ul>
    </div>
    <div id="result">
        <#if result_message??>
            <h1>${result_message}</h1>
        </#if>
    </div>
    <script type="text/javascript" src="static/js/add_feedback.js"></script>
</#escape>
