
<div id="my_talks_list" data-role="content" style="padding: 15px">
    <#if myTalksList?has_content>
    <ul data-role="listview" data-divider-theme="b" data-inset="true">
        <li data-role="list-divider" role="heading">
        </li>

        <#list myTalksList as myTalk>


                <li data-theme="c">
                            <a id="${myTalk.talkId}" role="talk"  data-transition="slide">
                                ${myTalk.presentation.title}
                            </a>
                        </li>


            </#list>


    </ul>
    </#if>

</div>

