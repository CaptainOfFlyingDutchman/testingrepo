<%--
  Created by IntelliJ IDEA.
  User: manvendra
  Date: 17/2/13
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

    <g:renderErrors bean="${command}" class="alert alert-info"/>

    <g:if test="${!command}">
        <p>You are automatically signed in. Hurray!!!</p>
        <h1>Go to <g:link uri="/">home page</g:link></h1>
    </g:if>

</body>
</html>