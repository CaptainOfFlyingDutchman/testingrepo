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
        })
    </script>
</head>

<body>
<div>
    <ul id="myTabs" class="nav nav-tabs">
        <li class="active"><a href="#ownedTopics">Your topics</a></li>
        <li><a href="#subscribedTopics">Subscribed topics</a></li>
        <li><a href="#publicTopics">Public topics</a></li>
    </ul>

    <div class="tab-content">

        <div class="tab-pane active" id="ownedTopics">
            <table class="table-striped table-hover table table-bordered">
                <thead><tr><th>Name</th> <th>Subscribe/Unsubscribe</th></tr></thead>
                <tbody>
                <g:each in="${ownedTopics}" var="topic">
                    <tr>
                        <td>
                            ${topic.name}
                        </td>
                        <td>
                            <g:if test="${Subscription.findBySubscriberAndTopic(user, topic)}">
                                <g:link name="unsubscribe" controller="user" action="unsubscribe"
                                        id="${topic.id}">Unsubscribe</g:link>
                            </g:if>
                            <g:else>
                                <g:link name="subscribe" controller="user" action="subscribe"
                                        id="${topic.id}">Subscribe</g:link>
                            </g:else>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="tab-pane" id="subscribedTopics">
            <table class="table-striped table-hover table table-bordered">
                <thead><tr><th>Name</th><th>Seriousness</th><th>Send invitation</th><th>Unsubscribe</th></tr></thead>
                <tbody>
                <g:each in="${subscriptions}" var="subscription">
                    <tr>
                        <td>
                            <g:link name="topicSettings" controller="user" action="changeTopicSettings"
                                    id="${subscription.topic.id}">${subscription.topic}</g:link>
                        </td>
                        <td>${subscription.seriousness}</td>
                        <td>
                            <g:link name="sendInvitation" controller="user" action="sendInvitation"
                                    id="${subscription.topic.id}">Send invitation</g:link>
                        </td>
                        <td>
                            <g:link name="unsubscribe" controller="user" action="unsubscribe"
                                    id="${subscription.topic.id}">Unsubscribe</g:link>
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
                            <g:link name="subscribe" controller="user" action="subscribe"
                                    id="${topic.id}">Subscribe</g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

    </div>

</div>
</body>
</html>