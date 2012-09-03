   <#include "/macros.ftl">
    <script type="text/javascript" src="static/js/add_feedback.js" />
    <div id="add_feedback_container">
    <h4 id="feedback_status_message" />

    <div data-role="fieldcontain">
        <center>
        <fieldset data-role="controlgroup" style="text-align: center; width: 100%;">
            <label for="feedback_text">
            </label>
            <textarea name="feedback" id="feedback_text" maxlength="500" onInput="textCounter(this,document.getElementById('counter'),500);"  placeholder="add feedback" value="" style="width: 100%; height: 20%;"
                   type="textArea" rows="9" cols="200"></textarea>

            <br />
            <p style="float: right; font-weight: bold;">  <span id="counter" style="color:black;">0</span>/500
            </p>

            <br />
            <input type="submit" id="add_feedback_submit" data-inline="true" data-theme="b" value="Submit" style="padding-bottom: 0.5%; padding-top: 1%;"
                       data-mini="false" />
           </fieldset>


        </center>
    </div>

    <div data-role="collapsible" data-collapsed="false">

    <h3>Past Feedback</h3>

    <ul data-role="listview" class="ui-listview" id="feedback-list">

    <#list retrieved_feedback_list as feedback>
        <li class="ui-li ui-li-static ui-body-c feedback-item" ">
            <h4><@nl2br>${feedback.feedbackComment}</@nl2br></h4>
            <p>
                <strong>&nbsp; &nbsp; &nbsp; - ${feedback.attendee}</strong>
                <span>
                    <a href="mailto:${feedback.attendeeMail}">${feedback.attendeeMail}</a>
                </span>
            </p>
            <p class="ui-li-aside"><strong>${feedback.timeAtCreation.toString("dd/MM/YYYY  KK:mm a")}</strong></p>
        </li>
    </#list>
    </div>
    <div id="result">
        <#if result_message??>
            <h1>${result_message}</h1>
        </#if>
    </div>

