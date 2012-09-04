<#escape x as x?html>
<div data-role="content" style="padding: 15px">
    <div data-role="collapsible-set" data-theme="" data-content-theme="">
        <div data-role="collapsible" data-collapsed="true">
            <#if talk??>
                <h3>
                ${talk.presentation.title}  By  ${talk.presentation.owner}
                </h3>

                <div>
                    <p>
                        <b>
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