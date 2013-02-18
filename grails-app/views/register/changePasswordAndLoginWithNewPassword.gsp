<%--
  Created by IntelliJ IDEA.
  User: manvendra
  Date: 18/2/13
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change Password</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

    <g:renderErrors bean="${command}" />

    <g:if test="!${command}">
        <h1>Your password is successfully reset.</h1>
    </g:if>

</body>
</html>