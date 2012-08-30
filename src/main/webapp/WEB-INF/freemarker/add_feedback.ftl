<!-- A presentation -->


<div data-role="fieldcontain">
    <fieldset data-role="controlgroup" style="text-align: center; width: 100%;">
        <label for="feedback_text">
        </label>
        <textarea name="feedback" id="feedback_text" placeholder="add feedback" value=""
                  style="width: 80%; height: 20%;"
                  type="textArea" rows="9" cols="200"></textarea>
        <br/>
        <input type="submit" data-inline="true" data-theme="b" value="Submit"
               style="padding-bottom: 0.5%; padding-top: 0.5%;"
               data-mini="true">
    </fieldset>
</div>


<div data-role="collapsible" data-collapsed="false">
    <h3>
        Past Feedback
    </h3>

    <ul data-role="listview" class="ui-listview">

    <#list retrieved_feedback_list as feedback>
        <li class="ui-li ui-li-static ui-body-c">
        ${feedback.attendee} (${feedback.attendeeMail})<br>
        ${feedback.timeAtCreation} <br>
        ${feedback.feedbackComment}
        </li>
    </#list>

    </ul>


</div>


<script>


</script>