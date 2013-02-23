<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Send Invitation</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>
<g:form controller="user" action="sendInvitationMail" class="form-horizontal" method="post"
        name="send_invite_form">
    <div class="modal-body">
        <div class="modal-header">
            <h3><g:message code="topic.invite.title"
                           default="Please specify emails to send invitation for topic '${topicToSendInvitation.name}'."/></h3>
        </div>

        <h1></h1>

        <div class="control-group">
            <label class="control-label"
                   for="emails">${message(code: 'topic.emails.label', default: 'Emails')}</label>

            <div class="controls">
                <input type="text" required="required" class="span3" name="emails" id="emails"
                       placeholder="${message(code: 'topic.email.placeholder.label', default: 'Comma Separted Emails')}">
            </div>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" class="btn btn-primary"><g:message code="topic.sendInvite.label"
                                                                 default="Send Invitation"/></button>
    </div>

    <g:hiddenField name="user.id" value="${user.id}"/>
    <g:hiddenField name="topic.id" value="${topicToSendInvitation.id}"/>

</g:form>
</body>
</html>