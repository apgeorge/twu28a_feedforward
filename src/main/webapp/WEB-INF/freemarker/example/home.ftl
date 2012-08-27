<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html>

<head>
    <title>TWU Demo</title>
</head>

<body>

<#--<p>Your principal object is....: ${request.userPrincipal}</p>-->
<p>Your principal object is....: <@security.authentication property = "principal.nickname">

</@security.authentication></p>

<#--<p>Welcome back <sec:authentication property="principal.nickname"/>.</p>-->



<#if user??>
        <h1>Hallo ${user.name}</a>
    <#else>
        <#if username??>
            <h1>Sorry, didn't find a user called "${username}"</h1>
        </#if>
        <p><a href="?username=bill">Try me</a></p>
    </#if>
</h1>

</body>
</html>
