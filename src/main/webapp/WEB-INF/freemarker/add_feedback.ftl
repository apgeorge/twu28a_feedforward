<!-- A presentation -->



    <div data-role="fieldcontain">
        <fieldset data-role="controlgroup">
            <label for="textinput9">
            </label>
            <input name="feedback" id="textinput9" placeholder="add feedback" value=""
                   type="text">
        </fieldset>
    </div>
    <input type="submit" data-inline="true" data-theme="b" value="Submit"
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




<script>


</script>