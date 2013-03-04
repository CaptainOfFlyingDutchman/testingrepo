<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.intelligrape.linksharing.*" %>
<html>
<head>
    <title>Topic Details</title>
    <meta name="layout" content="kickstart"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#viewSubscribers").bind("click", function () {
                $("#subscribersList").fadeIn(400)
            })
        })
    </script>
</head>

<body>
<div>
    <g:form controller="user" action="changeTopicSettings" class="form-horizontal" method="post"
            name="topic_details_form">
        <div class="modal-body">
            <div class="modal-header">
                <h3><g:message code="topic.title"
                               default="Details of the topic '${topic.name}':"/></h3>
            </div>

            <h1></h1>

            <div class="control-group">
                <label class="control-label"
                       for="visibility">Visibility</label>

                <div class="controls">
                    <span class="span3 uneditable-input" name="visibility" id="visibility">${topic.visibility}</span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="owner">Owner</label>

                <div class="controls">
                    <span class="span3 uneditable-input" name="owner" id="owner">${topic.owner}</span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="subscriptions">Total Subscriptions</label>

                <div class="controls">
                    <span class="span2 uneditable-input" name="subscriptions"
                          id="subscriptions">${topic.subscriptions.size()}</span>
                    <button href="#SubscriptionsModal" class="btn btn-small btn-success" role="button"
                            data-toggle="modal" title="View subscriptions">
                        <i class="icon-eye-open icon-white"></i> View
                    </button>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="resources">Total Resources</label>

                <div class="controls">
                    <span class="span2 uneditable-input" name="resources"
                          id="resources">${topic.resources.size()}</span>
                    <button href="#ResourcesModal" class="btn btn-small btn-success" role="button"
                            data-toggle="modal" title="View resources">
                        <i class="icon-eye-open icon-white"></i> View
                    </button>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"
                       for="summary">Summary</label>

                <div class="controls">
                    <textarea disabled="disabled" name="summary" rows="7"
                              id="summary">${topic.summary}</textarea>
                </div>
            </div>
        </div>

    </g:form>
</div>

<div class="modal hide" id="SubscriptionsModal">
    <g:form controller="register" action="forgotPassword" class="form-horizontal" method="post" name="register_form">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">x</button>

            <h3>Following people have subscribed to this topic:</h3>
        </div>

        <div class="modal-body">
            <table class="table">
            <g:each in="${topic.subscriptions}" var="subscription">
                <tr><td><h5>${subscription.subscriber.toString()}</h5></td></tr>
            </g:each>
            </table>
        </div>

        <div class="modal-footer">
            <button type="submit" class="btn btn-primary" data-dismiss="modal">
                <i class="icon-remove icon-white"></i>Close
            </button>
        </div>
    </g:form>
</div>

<div class="modal hide" id="ResourcesModal">
    <g:form controller="register" action="forgotPassword" class="form-horizontal" method="post" name="register_form">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">x</button>

            <h3>Following are the resources for this topic:</h3>
        </div>

        <div class="modal-body">
            <table class="table">
            <g:each in="${resources}" var="resource">
                <tr><td><h5>${resource.title}</h5></td><td>
                    <g:if test="${resource.className == LinkResource.class.name}">
                        <td style="vertical-align:middle;">It's a link resource</td>
                    </g:if>
                    <g:else>
                        <td style="vertical-align:middle;">It's a document resource</td>
                    </g:else>
                </td></tr>
            </g:each>
            </table>
        </div>

        <div class="modal-footer">
            <button type="submit" class="btn btn-primary" data-dismiss="modal">
                <i class="icon-remove icon-white"></i>Close
            </button>
        </div>
    </g:form>
</div>
</body>
</html>