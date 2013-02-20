
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Forgot Password</title>
    <meta name="layout" content="kickstart" />
</head>
<body>
    <g:renderErrors bean="${command}" class="alert alert-info"/>

    <g:if test="${!command && !flash.message}">
        <h1>Your password reset link have been sent to your mail box.</h1>
        <h3>Check it out there and login again.</h3>
    </g:if>
</body>
</html>