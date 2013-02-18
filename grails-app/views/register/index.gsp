<%--
  Created by IntelliJ IDEA.
  User: manvendra
  Date: 17/2/13
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>
<div >
    %{--<g:hasErrors bean="${user}">
        <ul>
            <g:eachError bean="${user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">
                    data-field-id="${error.field}"
                </g:if>><g:message error="${error}"/> </li>
            </g:eachError>
        </ul>
    </g:hasErrors>--}%
</div>
<div id="RegisterModal">
    <g:form controller="register" action="register" class="form-horizontal" method="post" name="registerForm">
        <div class="modal-header">
            %{--<button type="button" class="close" data-dismiss="modal">x</button>--}%
            <h3><g:message code="security.register.title"/></h3>
        </div>

        <div class="modal-body">
            <div class="control-group">
                <label class="control-label"
                       for="firstName">${message(code: 'security.firstname.label', default: 'Firstname')}</label>

                <div class="controls">
                    <input type="text" class="span3" name="firstName" id="firstName"
                           placeholder="${message(code: 'security.firstname.label', default: 'Firstname')}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="lastName">${message(code: 'security.lastname.label', default: 'Lastname')}</label>

                <div class="controls">
                    <input type="text" class="span3" name="lastName" id="lastName"
                           placeholder="${message(code: 'security.lastname.label', default: 'Lastname')}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="username">${message(code: 'security.email.label', default: 'Email')}</label>

                <div class="controls">
                    <input type="text" class="span3" name="username" id="username"
                           placeholder="${message(code: 'security.email.label', default: 'Email')}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="password">${message(code: 'security.password.label', default: 'Password')}</label>

                <div class="controls">
                    <input type="password" class="span3" name="password" id="password"
                           placeholder="${message(code: 'security.password.label', default: 'Password')}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="confirmPassword">${message(code: 'security.password.confirm.label', default: 'Confirm')}</label>

                <div class="controls">
                    <input type="password" class="span3" name="confirmPassword" id="confirmPassword"
                           placeholder="${message(code: 'security.password.confirm.label', default: 'Confirm')}">
                </div>
            </div>

            %{--<div class="control-group">
                <%--			<label class="control-label" for="agreement">${message(code: 'security.agreement.label', default: 'I have read and agree with the Terms of Use.')}</label>--%>
                <div class="controls">
                    <label class="checkbox" for="agreement">
                        <g:checkBox name="agreement" id="agreement" />
                        --}%%{--<input type="checkbox" value="" name="agreement" id="agreement">--}%%{--
                        ${message(code: 'security.agreement.label', default: 'I have read and agree with the Terms of Use.')}
                    </label>
                </div>
            </div>--}%
        </div>

        <div class="modal-footer">
            <button type="submit" class="btn btn-primary"><g:message code="security.register.label"/></button>
        </div>
    </g:form>
</div>

</body>
</html>