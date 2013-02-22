
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Invitation Send</title>
    <meta name="layout" content="kickstart" />
</head>
<body>
    <g:renderErrors bean="${command}" class="alert alert-info"/>

    <g:if test="${!command && !flash.message}">
        <h1>Your invitation is sent successfully.</h1>
    </g:if>
</body>
</html>