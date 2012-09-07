<#escape x as x?html>
<style type="text/css">
    .ui-corner-bottom {
        white-space: pre-line !important; /* CSS 3.0 */
        white-space: -o-pre-wrap !important; /* Opera 7 */
        white-space: -moz-pre-wrap !important; /* Mozilla */
        word-wrap: break-word !important; /* IE 5+ */
    }
</style>
<div data-role="content" style="padding: 15px">
    <div style="display: none;">
        ${isUpcoming}
    </div>
    <div id="talk_details" data-role="collapsible-set" data-theme="" data-content-theme="">
        <div data-role="collapsible" data-collapsed="<#if isUpcoming =="isAnUpcomingTalk">false<#else> true</#if>">
            <#if talk??>
                <h4 style="word-wrap: break-word;">
                ${talk.presentation.title}  By  ${talk.presentation.owner}
                </h4>
                <div>
                    <p>
                        <b style=" word-wrap: break-word; ">
                        ${talk.presentation.description}
                        </b>
                    </p>
                    <p>
                        <b>
                            Venue : ${talk.venue}
                        </b>
                    </p>
                    <p>
                        <b>
                            Date : ${talk.dateTime.toString("dd/MM/YYYY")}
                        </b>
                    </p>
                    <p>
                        <b>
                            Time : ${talk.dateTime.toString("hh:mm a")}
                        </b>
                    </p>
                    <p>
                        <b>
                            Contact me: <a href="mailto:${talk.presentation.owner}@thoughtworks.com">${talk.presentation.owner}@thoughtworks.com</a>
                        </b>
                    </p>
                </div>
            <#else>
            <h3>
                <p id="noeventmessage">There are no talks at this moment.</p>
            <h3>
            </#if>
        </div>
    </div>
    <div id="feedback_container">
    </div>
</div>
</#escape>