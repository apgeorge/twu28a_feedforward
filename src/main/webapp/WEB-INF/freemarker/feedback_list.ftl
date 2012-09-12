<#include "/macros.ftl">
<#escape x as x?html>
<ul id="list_of_feedbacks" data-role="listview" class="ui-listview" id="feedback-list">
    <#if updated_feedback_list?has_content>
        <#list updated_feedback_list as feedback>
            <span style="display:none;"> --------------------------------------</span>
        <li id="feedback_messages" class="ui-li ui-li-static ui-body-c feedback-item">
            <h4 style="white-space: pre-line; word-wrap: break-word; width: 95%; font-size: 15px; font-style: normal; font-weight: 500;">
                <@nl2br>${feedback.feedbackComment}</@nl2br>&nbsp; &nbsp; &nbsp; <font
                    style="white-space: pre-line; word-wrap: break-word; width: 95%; font-size: 12px; font-style: normal; font-weight: 500;">
                <a href="mailto:${feedback.attendeeMail}">${feedback.attendeeMail}</a> on ${feedback.timeAtCreation.toString("dd MMM YYYY,  hh:mm a")} </font>
            </h4>

        </#list>
    <#else>
        <div style="font-size: 25px; color: gray; text-shadow: 1px 1px 0px #BAD3ED;font-weight: bold;text-align: center;">
            <img src="static/images/sad_cat.png">
            <br>
            No feedback received yet.
        </div>

    </#if>
</ul>
</#escape>