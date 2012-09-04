<#escape x as x?html>
<div data-role="content" style="padding: 15px">
      <#if talksList?has_content>
                <ul data-role="listview" data-divider-theme="b" data-inset="true">
                    <li data-role="list-divider" role="heading">
                    </li>

                    <#list talksList as recentTalk>


                            <li data-theme="c">
                                        <a id="${recentTalk.talkId}" role="talk"  data-transition="slide">
                                            ${recentTalk.presentation.title}
                                        </a>
                                    </li>


                        </#list>


                </ul>
                </#if>
  </div>
</#escape>