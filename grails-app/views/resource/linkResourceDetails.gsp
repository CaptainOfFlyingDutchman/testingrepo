<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Link Resouce Details</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>
<g:form controller="resource" action="editLinkResourceDetails" class="form-horizontal" method="post"
        name="save_linkResource_form">
    <div class="modal-body">
        <div class="modal-header">
            <h3><g:message code="resource.details.title"
                           default="Details of the '${linkResource.title}'."/></h3>
        </div>

        <h1></h1>

        <div class="control-group">
            <label class="control-label"
                   for="creatorName">${message(code: 'resource.creator.label', default: 'Creator')}</label>
            <div class="controls">
                <input type="text" disabled="disabled" class="span3" name="creatorName" id="creatorName"
                       placeholder="${message(code: 'resource.creator.placeholder.label', default: 'Creator name')}"
                       value="${creator.toString()}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="topicName">${message(code: 'resource.topicName.label', default: 'Topic')}</label>
            <div class="controls">
                <input type="text" disabled="disabled" class="span3" name="topicName" id="topicName"
                       placeholder="${message(code: 'resource.topicName.placeholder.label', default: 'Topic name')}"
                       value="${linkResource.topic.name}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="title">${message(code: 'resource.title.label', default: 'Title')}</label>
            <div class="controls">
                <input disabled="disabled" type="text"class="span3" name="title" id="title"
                       placeholder="${message(code: 'resource.title.placeholder.label', default: 'Title')}"
                        value="${linkResource.title}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="summary">${message(code: 'resource.summary.label', default: 'Summary')}</label>
            <div class="controls">
                <textarea disabled="disabled" rows="5" class="span3" name="summary" id="summary"
                          placeholder="${message(code: 'resource.summary.placeholder.label',
                                  default: 'Optional summary')}">${linkResource.summary}</textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"
                   for="url">${message(code: 'resource.url.label', default: 'Url')}</label>
            <div class="controls">
                <a href="${linkResource.url}" title="Go to ${linkResource.url}" name="url" class="btn"
                       >${linkResource.url}</a>
            </div>
        </div>
    </div>

    <div class="modal-footer">
        <button type="submit" class="btn btn-primary"><g:message code="resource.create.label"
                                                                 default="Edit resource"/></button>
    </div>

    <g:hiddenField name="creator.id" value="${creator.id}"/>
    <g:hiddenField name="linkResource.id" value="${linkResource.id}"/>
</g:form>
</body>
</html>