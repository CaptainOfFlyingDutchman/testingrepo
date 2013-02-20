<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Topic</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

    <sec:ifLoggedIn>
        <g:form controller="user" action="saveTopic" class="form-horizontal" method="post"
                name="topic_create_form">
            <div class="modal-body">

                <div class="control-group">
                    <label class="control-label"
                           for="name">${message(code: 'topic.name.label', default: 'Topic name')}</label>
                    <div class="controls">
                        <input type="text" required="required" class="span3" name="name" id="name"
                               placeholder="${message(code: 'topic.name.placeholder.label', default: 'Topic name')}">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"
                           for="visibilty">${message(code: 'topic.visibilty.label', default: 'Visibility')}</label>
                    <div class="controls">
                        <g:select name="visibility" from="${visibilityConstants}" optionKey="key" optionValue="value" />
                    </div>
                </div>

                <div class="control-group">
                <label class="control-label"
                       for="seriousness">${message(code: 'topic.seriousness.label', default: 'Seriousness')}</label>
                <div class="controls">
                    <g:select name="seriousness" from="${seriousnessConstants}" optionKey="key" optionValue="value" />
                </div>
            </div>
            </div>

            <div class="modal-footer">
                <button type="submit" class="btn btn-primary"><g:message code="topic.create.label"
                                                                         default="Create Topic"/></button>
            </div>

            <div class="modal-header">

                <h3><g:message code="topic.create.title" default="Please specify topic details."/></h3>
            </div>
            
            <g:hiddenField name="user.id" value="${loggedInUser?.id}" />
        </g:form>
    </sec:ifLoggedIn>

    <sec:ifNotLoggedIn>
        <h3>Not signed in.</h3>
        <p>You need to sign in before you can create any topic</p>
    </sec:ifNotLoggedIn>

</body>
</html>