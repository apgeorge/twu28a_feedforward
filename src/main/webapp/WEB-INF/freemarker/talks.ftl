<#escape x as x?html>
<div id="talks_container" data-role="content" style="padding: 15px">
    <#if talksList?has_content>
        <ul data-role="listview" data-divider-theme="b" data-inset="true">
            <li data-role="list-divider" role="heading">
                Talks
            </li>
            <#list talksList as recentTalk>
                <li data-theme="c">
                    <a id="${recentTalk.talkId}" role="talk" data-transition="slide" >
                        <span style="font-size: 18px;" > ${recentTalk.presentation.title} </span>
                        <br />
                     	<i style="font-weight: bold;color: grey;">
                     	   &nbsp; &nbsp; &nbsp; - by <span style="font-size: 18px;" >${recentTalk.presentation.owner} </span>
                     	</i>
                    </a>
                </li>
            </#list>
        </ul>
    <#else >
        <div style="font-size: 25px; color: gray; text-shadow: 1px 1px 0px #BAD3ED;font-weight: bold;text-align: center;">
            <img src="static/images/sad_cat.png">
            <br>
            No talks happening at the moment.
        </div>
    </#if>
</div>
</#escape>