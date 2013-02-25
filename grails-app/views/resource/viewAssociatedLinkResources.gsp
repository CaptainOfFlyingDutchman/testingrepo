<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Associated Link Resources</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>
<g:if test="${resources}">
    <h3>Resources for the topic '${topicForAssociatedLinkResources.name}':</h3>

    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr><th>Title</th><th>Summary</th><th>Url</th><th>Creator</th></tr>
        </thead>
        <tbody>
            <g:each in="${resources}" var="resource">
                <tr>
                    <td><g:link controller="resource" action="linkResourceDetails" id="${resource.id}">
                         ${resource.title}</g:link></td>
                    <td>${resource.summary}</td>
                    <td><a href="${resource.url}" title="Go to ${resource.url}">${resource.url}</a> </td>
                    <td>${resource.creator}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <h3>There are no associated link resources for the topic '${topicForAssociatedLinkResources.name}'.</h3>
</g:else>
</body>
</html>