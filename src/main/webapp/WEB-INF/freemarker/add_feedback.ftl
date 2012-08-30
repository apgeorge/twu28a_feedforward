<!-- A presentation -->


    <div id = "add_feedback_container">
    <h4 id="feedback_status_message">
    </h4>


    <div data-role="fieldcontain">
        <center>
        <fieldset data-role="controlgroup" style="text-align: center; width: 100%;">
            <label for="feedback_text">
            </label>
            <textarea name="feedback" id="feedback_text" placeholder="add feedback" value="" style="width: 80%; height: 20%;"
                   type="textArea" rows="9" cols="200"></textarea>
            <br/>
            <input type="submit" data-inline="true" data-theme="b" value="Submit" style="padding-bottom: 0.5%; padding-top: 0.5%;"
                       data-mini="true">
        </fieldset>
        </center>
    </div>
    <input type="submit" id="add_feedback_submit" data-inline="true" data-theme="b" value="Submit"
           data-mini="true">


    <div data-role="collapsible" data-collapsed="false">
        <h3>
            Past Feedback
        </h3>
        <ul data-role="listview" data-divider-theme="b" data-inset="true">


        <#--<#list ${retrieved_feedback_list} as feedback>-->
        <#--<li>-->
            <#--<a data-transition="slide">-->
               <#--${feedback.feedbackComment}  --->
               <#--<a href="mailto:gohan@dragon.ball">Email Gohan</a>-->
               <#--${feedback.attendee}(${feedback.attendeeMail})-->
            <#--</a>-->
        <#--</li>-->
        <#--</#list>-->


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

            </script>