<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Topic Details</title>
    <meta name="layout" content="kickstart"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#viewSubscribers").bind("click", function() {
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
                    &nbsp; &nbsp;<a id="viewSubscribers" class="btn btn-small btn-success" data-target="#">View</a>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"
                       for="resources">Total Resources</label>

                <div class="controls">
                    <span class="span2 uneditable-input" name="resources"
                          id="resources">${topic.resources.size()}</span>
                    &nbsp; &nbsp;<a id="viewResources" class="btn btn-small btn-success" data-target="#">View</a>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            %{--<button type="submit" class="btn btn-primary"><g:message code="topic.settings.label"--}%
                                                                     %{--default="Change settings"/></button>--}%
        </div>

    %{--<g:hiddenField name="user.id" value="${user.id}"/>--}%
    <g:hiddenField name="topic.id" value="${topic.id}"/>

    </g:form>
</div>
</body>
</html>