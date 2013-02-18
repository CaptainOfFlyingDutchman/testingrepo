<%--
  Created by IntelliJ IDEA.
  User: manvendra
  Date: 18/2/13
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change Password</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

<g:form controller="register" action="changePasswordAndLoginWithNewPassword" class="form-horizontal" method="post" name="password_reset_form">
    <div class="modal-header">
        <h3><g:message code="security.reset.title" default="Please change your password."/></h3>
    </div>
    <div class="modal-body">

        <div class="control-group">
            <label class="control-label" for="password">${message(code: 'security.password.label', default: 'Password')}</label>
            <div class="controls">
                <input type="password" class="span3" name="password" id="password" placeholder="${message(code: 'security.password.label', default: 'Password')}">
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="confirmPassword">${message(code: 'security.password.confirm.label', default: 'Confirm Password')}</label>
            <div class="controls">
                <input type="password" class="span3" name="confirmPassword" id="confirmPassword" placeholder="${message(code: 'security.password.confirm.label', default: 'Confirm Password')}">
            </div>
        </div>

        <g:hiddenField name="user.id" value="${tokenUser?.id}" />
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary"><g:message code="security.change.label" default="Change Password"/></button>
    </div>
</g:form>

</body>
</html>