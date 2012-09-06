<#macro nl2br>
    <#local content>
        <#nested><#t>
    </#local>
${content?replace("\n", "<br />")}
</#macro>
