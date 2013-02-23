<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Change Topic Settings</title>
    <meta name="layout" content="kickstart"/>

</head>

<body>
<div>
    <g:form controller="user" action="saveTopicSettings" class="form-horizontal" method="post" name="register_form">
        <div class="modal-header">
            <h3><g:message code="topic.change.title" default="Change subscription settings for following topic"/></h3>
        </div>

        <div class="modal-body">
            <div class="control-group">
                <label class="control-label"
                       for="topicName">${message(code: "topic.name", default: 'Topic name')}</label>

                <div class="controls">
                    <input type="text" required="required" readonly="readonly" class="span3" name="topicName"
                           id="topicName" value="${topicToChangeSettings.name}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"
                       for="subscriberName">${message(code: "topic.subscriber", default: 'Subscriber name')}</label>

                <div class="controls">
                    <input type="text" required="required" readonly="readonly" class="span3" name="subscriberName"
                           id="subscriberName" value="${subscriber.fullName}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"
                       for="seriousness">${message(code: "topic.seriousness", default: 'Subscription seriousness')}</label>

                <div class="controls">
                    <g:select name="seriousness" from="${seriousnessConstants}" optionKey="key" optionValue="value" value="${subscription.seriousness.key}"/>
                </div>
            </div>

            <g:hiddenField name="user.id" value="${subscriber?.id}" />
            <g:hiddenField name="topic.id" value="${topicToChangeSettings?.id}" />
        </div>

        <div class="modal-footer">
            <button type="submit" class="btn btn-primary"><g:message code="topic.change.settings"
                                                                     default="Change Settings"/></button>
        </div>
    </g:form>
</div>
</body>
</html>