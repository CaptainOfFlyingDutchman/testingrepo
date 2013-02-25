<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Document Resource</title>
    <meta name="layout" content="kickstart"/>
</head>
<body>
<g:uploadForm controller="resource" action="saveLinkResource" class="form-horizontal" method="post"
        name="save_linkResource_form">
    <div class="modal-body">
        <div class="modal-header">
            <h3><g:message code="resource.create.title"
                           default="Please enter the details for the new document resource."/></h3>
        </div>

        <h1></h1>

        <div class="control-group">
            <label class="control-label"
                   for="creatorName">${message(code: 'resource.creator.label', default: 'Creator')}</label>
            <div class="controls">
                <input type="text" disabled="disabled" required="required" class="span3" name="creatorName" id="creatorName"
                       placeholder="${message(code: 'resource.creator.placeholder.label', default: 'Creator name')}"
                       value="${creator.toString()}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="topicName">${message(code: 'resource.topicName.label', default: 'Topic')}</label>
            <div class="controls">
                <input type="text" disabled="disabled" required="required" class="span3" name="topicName" id="topicName"
                       placeholder="${message(code: 'resource.topicName.placeholder.label', default: 'Topic name')}"
                       value="${topicToAddResourceTo.name}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="title">${message(code: 'resource.title.label', default: 'Title')}</label>
            <div class="controls">
                <input type="text" required="required" class="span3" name="title" id="title"
                       placeholder="${message(code: 'resource.title.placeholder.label', default: 'Title')}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="summary">${message(code: 'resource.summary.label', default: 'Summary')}</label>
            <div class="controls">
                <textarea rows="5" class="span3" name="summary" id="summary"
                          placeholder="${message(code: 'resource.summary.placeholder.label', default: 'Optional summary')}"></textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="fileName">${message(code: 'resource.fileName.label', default: 'File')}</label>
            <div class="controls">
                <input type="file" required="required" class="span3" name="fileName" id="fileName">
            </div>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" class="btn btn-primary"><g:message code="resource.create.label"
                                                                 default="Add resource"/></button>
    </div>

    <g:hiddenField name="creator.id" value="${creator.id}"/>
    <g:hiddenField name="topic.id" value="${topicToAddResourceTo.id}"/>
</g:uploadForm>
</body>
</html>