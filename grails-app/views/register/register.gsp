
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

    <g:renderErrors bean="${command}" class="alert alert-info"/>

    <g:if test="${!command}">
        <h1>You are automatically signed in. Hurray!!!</h1>
        <p>Go to <g:link uri="/">home page</g:link></p>
    </g:if>

</body>
</html>