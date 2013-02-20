<%--
  Created by IntelliJ IDEA.
  User: manvendra
  Date: 19/2/13
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Topic</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

    <g:form controller="register" action="changePasswordAndLoginWithNewPassword" class="form-horizontal" method="post" name="topic_create_form">
        <div class="modal-header">
            <h3><g:message code="topic.create.title" default="Please specify topic details."/></h3>
        </div>
        <div class="modal-body">

            <div class="control-group">
                <label class="control-label" for="name">${message(code: 'topic.name.label', default: 'Topic name')}</label>
                <div class="controls">
                    <input type="text" class="span3" name="name" id="name" placeholder="${message(code: 'topic.name.placeholder.label', default: 'Topic name')}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="visibilty">${message(code: 'topic.visibilty.label', default: 'Visibility')}</label>
                <div class="controls">
                    <g:select name="visibility" from="${visiblityConstants}" />
                </div>
            </div>

            %{--<g:hiddenField name="user.id" value="${tokenUser?.id}" />--}%
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary"><g:message code="topic.create.label" default="Create Topic"/></button>
        </div>
    </g:form>

</body>
</html>