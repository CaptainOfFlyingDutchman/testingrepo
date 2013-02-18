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
    %{--<g:renderErrors bean="${user}" />--}%
<g:hasErrors bean="${user}">
    <ul>
        <g:eachError bean="${user}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">
                data-field-id="${error.field}"
            </g:if>><g:message error="${error}"/> </li>
        </g:eachError>
    </ul>
</g:hasErrors>
    <p><g:link uri="/">Go to home page</g:link></p>
</body>
</html>