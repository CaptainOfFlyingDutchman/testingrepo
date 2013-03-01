<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.intelligrape.linksharing.*" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Dashboard</title>
    <meta name="layout" content="kickstart"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#myTabs a').click(function (e) {
                e.preventDefault();
                $(this).tab('show')
            })

            $(".dropdown-toggle").dropdown();
            $(".btn-group").button();
        })

        function makeToggleSubscribeButtons(buttonIdToToggle, subscribed) {
            if (subscribed == true) {
                $(".btn-group a[href='/LinkSharing/user/subscribe/" + buttonIdToToggle + "']").button('toggle');
                var subscribeLink = $(".btn-group a").attr("/LinkSharing/user/subscribe/" + buttonIdToToggle)
            } else {
                $(".btn-group a[href='/LinkSharing/user/unsubscribe/" + buttonIdToToggle + "']").button('toggle');
                var unsubscribeLink = $(".btn-group a").attr("/LinkSharing/user/subscribe/" + buttonIdToToggle)
            }
        }
    </script>
</head>

<body>
<div>
    <ul id="myTabs" class="nav nav-tabs">
        <li class="active"><a href="#ownedTopics">Your topics</a></li>
        <li><a href="#subscribedTopics">Subscribed topics</a></li>
        <li><a href="#publicTopics">Public topics</a></li>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <li><a href="#allTopics">All topics</a></li>
            <li><a href="#allResources">All resources</a></li>
        </sec:ifAnyGranted>
    </ul>

    <div class="tab-content">
        <div class="tab-pane active" id="ownedTopics">
            <table class="table-striped table-hover table table-bordered">
                <thead><tr><th>Name</th> <th>Unsubscribe/Subscribe</th><th>Delete</th></tr></thead>
                <tbody>
                <g:each in="${ownedTopics}" var="topic">
                    <tr>
                        <td>
                            ${topic.name}
                        </td>
                        <td>
                            <div class="btn-group" data-toggle="buttons-radio">
                                <g:link class="btn btn-small btn-info" name="unsubscribe"
                                        controller="user" action="unsubscribe"
                                        id="${topic.id}">Unsubscribe</g:link>
                                <g:link class="btn btn-small btn-primary" name="subscribe"
                                        controller="user" action="subscribe"
                                        id="${topic.id}">Subscribe</g:link>
                            </div>
                            <g:if test="${Subscription.findBySubscriberAndTopic(user, topic)}">
                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        makeToggleSubscribeButtons("${topic.id}", true);
                                    })
                                </script>
                            </g:if>
                            <g:else>
                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        makeToggleSubscribeButtons("${topic.id}", false);
                                    })
                                </script>
                            </g:else>
                        </td>
                        <td>
                            <g:link controller="user" action="deleteTopic" name="deleteTopic"
                                    id="${topic.id}" class="btn btn-small btn-danger">Delete</g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div style="padding-bottom: 50px" class="tab-pane" id="subscribedTopics">
            <table class="table-striped table-hover table table-bordered">
                <thead><tr><th>Name</th><th>Seriousness</th><th>Create resource</th><th>Associated resources</th>
                    <th>Send invitation</th><th>Unsubscribe</th><th>Change settings</th>
                </tr></thead>
                <tbody>
                <g:each in="${subscriptions}" var="subscription">
                    <tr>
                        <td>
                            <g:link name="topicSettings" controller="user" action="topicDetails"
                                    id="${subscription.topic.id}">${subscription.topic}</g:link>
                        </td>
                        <td>${subscription.seriousness}</td>
                        <td>
                            <div class="dropdown">
                                <a data-target="#" class="btn btn-small dropdown-toggle" data-toggle="dropdown">
                                    Add resource <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu" area-labelledby="dropdownMenu">
                                    <li><g:link name="createLinkResource" controller="resource"
                                                action="createLinkResource"
                                                id="${subscription.topic.id}">Create link resource</g:link></li>
                                    <li><g:link name="createDocumentResource" controller="resource"
                                                action="createDocumentResource"
                                                id="${subscription.topic.id}">Create document resource</g:link></li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <div class="dropdown">
                                <a data-target="#" class="btn btn-small dropdown-toggle" data-toggle="dropdown">
                                    View resources <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu" area-labelledby="dropdownMenu">
                                    <li><g:link controller="resource" action="viewAssociatedLinkResources"
                                                id="${subscription.topic.id}"
                                                name="viewAssociatedLinkResources">View link resource</g:link></li>

                                    <li><g:link controller="resource" action="viewAssociatedDocumentResources"
                                                id="${subscription.topic.id}"
                                                name="viewAssociatedDocumentResources">View document resource</g:link></li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <g:link class="btn btn-small btn-success" name="sendInvitation" controller="user"
                                    action="sendInvitation"
                                    id="${subscription.topic.id}">Send invitation</g:link>
                        </td>
                        <td>
                            <g:link class="btn btn-small btn-info" name="unsubscribe" controller="user"
                                    action="unsubscribe"
                                    id="${subscription.topic.id}">Unsubscribe</g:link>
                        </td>
                        <td>
                            <g:link name="topicSettings" controller="user" action="changeTopicSettings"
                                    class="btn btn-small btn-inverse"
                                    id="${subscription.topic.id}">Settings</g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="tab-pane" id="publicTopics">
            <table class="table-striped table-hover table table-bordered">
                <thead><tr><th>Name</th><th>Subscribe/Unsubscribe</th></tr></thead>
                <tbody>
                <g:each in="${publicTopics}" var="topic">
                    <tr>
                        <td>${topic.name}</td>
                        <td>
                            <g:link class="btn btn-small btn-success" name="subscribe" controller="user"
                                    action="subscribe"
                                    id="${topic.id}">Subscribe</g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <div class="tab-pane" id="allTopics">
                <table class="table-striped table-hover table table-bordered">
                    <thead><tr><th>Name</th><th>Visibility</th><th>Creator</th><th>Delete</th></tr></thead>
                    <tbody>
                    <g:each in="${allTopics}" var="topic">
                        <tr>
                            <td>${topic.name}</td>
                            <td>${topic.visibility.value}</td>
                            <td>${topic.owner.toString()}</td>
                            <td>
                                <g:link class="btn btn-small btn-danger" name="deleteTopic" controller="user"
                                        action="deleteTopic"
                                        id="${topic.id}">Delete</g:link>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>

            <div class="tab-pane" id="allResources">
                <table class="table-striped table-hover table table-bordered">
                    <thead><tr><th>Title</th><th>Topic</th><th>Summary</th><th>Creator</th>
                        <th>Type</th><th>Delete</th></tr></thead>
                    <tbody>
                    <g:each in="${allResources}" var="resource">
                        <tr>
                            <td>${resource.title}</td>
                            <td>${resource.topic.name}</td>
                            <td>${resource.summary}</td>
                            <td>${resource.creator.toString()}</td>
                            <td>
                                <g:if test="${resource.className == LinkResource.class.name}">
                                    <span><a class="btn btn-small btn-primary"
                                             style="width: 55px;"
                                             href="${resource.url}" title="Go to ${resource.url}">Link</a></span>
                                </g:if>
                                <g:else>
                                    <span>
                                        <g:link controller="resource" action="downloadDocumentResource"
                                                name="downloadDocumentResource" id="${resource.id}"
                                                title="Download ${resource.fileName}"
                                                class="btn btn-small btn-primary">Document</g:link></span>
                                </g:else>
                            </td>
                            <td>
                                <g:link class="btn btn-small btn-danger" name="deleteTopic" controller="resource"
                                        action="deleteLinkResource"
                                        id="${resource.id}">Delete</g:link>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </sec:ifAnyGranted>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        fadeMeOut()
    })
</script>
</body>
</html>