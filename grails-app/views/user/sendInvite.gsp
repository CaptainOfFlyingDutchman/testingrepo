<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Send Invite</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>
<g:form controller="user" action="sendInvitationMail" class="form-horizontal" method="post"
        name="send_invite_form">
    <div class="control-group">
        <h1>${topicToSendInvitation.name}</h1>
        <label class="control-label"
               for="emails">${message(code: 'topic.emails.label', default: 'Comma separated emails')}</label>

        <div class="controls">
            <input type="text" required="required" class="span3" name="emails" id="emails"
                   placeholder="${message(code: 'topic.email.placeholder.label', default: 'Emails')}">
        </div>

        <div class="modal-footer">
            <button type="submit" class="btn btn-primary"><g:message code="topic.sendInvite.label"
                                                                     default="Send Invitation"/></button>
        </div>

        <g:hiddenField name="user.id" value="${user.id}" />
        <g:hiddenField name="topic.id" value="${topicToSendInvitation.id}" />
    </div>

</g:form>
</body>
</html>