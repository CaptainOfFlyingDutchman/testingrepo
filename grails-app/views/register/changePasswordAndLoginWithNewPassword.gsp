<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change Password</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

    <g:renderErrors bean="${command}" />

    <g:if test="${!command}">
        <h1>Your password is successfully reset.</h1>
    </g:if>

</body>
</html>