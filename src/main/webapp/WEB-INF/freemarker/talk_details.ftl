<#escape x as x?html>
<style type="text/css">
    .ui-corner-bottom { white-space: pre-line !important;white-space: -o-pre-wrap !important;white-space: -moz-pre-wrap !important;word-wrap: break-word !important;}
</style>
<div data-role="content" style="padding: 15px">
    <div style="display: none;">
        ${isUpcoming}
    </div>
    <div id="talk_details" data-role="collapsible-set" data-theme="" data-content-theme="">
        <div data-role="collapsible" data-collapsed="<#if isUpcoming =="isAnUpcomingTalk">false<#else> true</#if>">
            <#if talk??>
                <h4 style="word-wrap: break-word;"><span style="font-size: 18px;">${talk.presentation.title}</span> &nbsp; <i style="font-weight: bold;color: grey;">- by <span style="font-size: 18px;" >${talk.presentation.owner}</span></i></h4>
                <div>
                    <div style="float: right;">
                           <a  href="https://twitter.com/share?text=Hey ThoughtWorkers," class="twitter-share-button" data-lang="en">Tweet</a>
                           <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="https://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
                    </div>
                    <p>
                        <b id="description" style="word-wrap: break-word;">
                           &nbsp; &nbsp; &nbsp; ${talk.presentation.description}
                        </b>
                    </p>
                    <p>
                        <b id="venue">
                            <span style="font-size: 18px;font-weight: bold;color: grey;" >
                                Venue &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; &nbsp;
                            </span> ${talk.venue}
                        </b>
                    </p>
                    <p>
                        <b id="date">
                            <span style="font-size: 18px;font-weight: bold;color: grey;" >
                                Date &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; &nbsp;
                            </span> ${talk.dateTime.toString("dd/MM/YYYY")}
                        </b>
                    </p>
                    <p>
                        <b id="time">
                            <span style="font-size: 18px;font-weight: bold;color: grey;" >
                                Time &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: &nbsp;
                            </span> ${talk.dateTime.toString("hh:mm a")}
                        </b>
                    </p>
                    <p>
                        <b id="email">
                            <span style="font-size: 18px;font-weight: bold;color: grey;" > Contact me &nbsp; &nbsp;: &nbsp;</span> <i><a href="mailto:${talk.presentation.owner}@thoughtworks.com">${talk.presentation.owner}@thoughtworks.com</a> </i>
                        </b>
                    </p>
                    <p>
                        <b id="last_modified_time" style="word-wrap: break-word;">
                          <span style="font-size: 18px;font-weight: bold;color: grey;" > Last Modified : &nbsp;</span>${talk.lastModifiedAt.toString("dd MMM YYYY hh:mm a")}
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
<script type="text/javascript" src="static/js/talk_details.js"></script>
</#escape>