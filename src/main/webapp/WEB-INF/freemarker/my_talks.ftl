<#escape x as x?html>
<div id="my_talks_list" data-role="content" style="padding: 15px">
    <#if myTalksList?has_content>
        <ul data-role="listview" data-divider-theme="b" data-inset="true">
            <li data-role="list-divider" role="heading">
            </li>
            <#list myTalksList as myTalk>
                <li data-theme="c">
                    <a id="${myTalk.talkId}" role="talk" data-transition="slide">
                    ${myTalk.presentation.title}
                    </a>
                </li>
            </#list>
        </ul>
    <#else >
        <div style="font-size: 25px; color: gray; text-shadow: 1px 1px 0px #BAD3ED;font-weight: bold;text-align: center;">
            You haven't created a talk yet.
        </div>
    </#if>
</div>
</#escape>
