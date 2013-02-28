<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Associated Document Resources</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>
<g:if test="${resources}">
    <h3>Resources for the topic '${topicForAssociatedDocumentResources.name}':</h3>

    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr><th>Title</th><th>Summary</th><th>Document</th><th>Creator</th><th>Mark read/unread</th><th>Delete</th></tr>
        </thead>
        <tbody>
            <g:each in="${resources}" var="resource">
                <tr>
                    <td><g:link controller="resource" action="documentResourceDetails" id="${resource.id}">
                         ${resource.title}</g:link></td>
                    <td>${resource.summary}</td>
                    <td><g:link controller="resource" action="downloadDocumentResource" name="downloadDocumentResource"
                                id="${resource.id}" class="btn btn-small btn-primary">${resource.fileName}</g:link></td>
                    <td>${resource.creator}</td>
                    <td>
                        <g:if test="${resource in readingItems.resource}">
                            <g:link controller="readingItem" action="markRead" name="markRead"
                                    id="${resource.id}" class="btn btn-small btn-info"
                                    style="width: 69px">Mark read</g:link>
                        </g:if>
                        <g:else>
                            <g:link controller="readingItem" action="markUnread" name="markUnread"
                                    id="${resource.id}" class="btn btn-small btn-info">Mark unread</g:link>
                        </g:else>
                    </td>
                    <td><g:link controller="resource" action="deleteDocumentResource" name="deleteDocumentResource"
                                id="${resource.id}" class="btn btn-small btn-danger">Delete</g:link></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <h3>There are no associated document resources for the topic '${topicForAssociatedDocumentResources.name}'.</h3>
</g:else>
</body>
</html>