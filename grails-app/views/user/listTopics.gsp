<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>List Topics</title>
    <meta name="layout" content="kickstart"/>
</head>

<body>
<div id="topics">
    <div>
        <h3>Your topics</h3>
        <table class="table table-bordered">
            <tr><th>Name</th> <th>Subscribe/Unsubscribe</th></tr>
            <g:each in="${ownedTopics}" var="topic">
                <tr>
                    <td>
                        ${topic.name}
                    </td>
                    <td>

                        <g:link name="subscribe" controller="user" action="subscribe"
                                id="${topic.id}">Subscribe</g:link>
                    </td>
                </tr>
            </g:each>
        </table>
    </div>

    <div>
        <h3>Subscribed topics</h3>
        <table class="table table-bordered">
            <tr><th>Name</th><th>Seriousness</th><th>Send invitation</th><th>Unsubscribe</th></tr>
            <g:each in="${subscriptions}" var="subscription">
                <tr>
                    <td>
                        <g:link name="topicSettings" controller="user" action="changeTopicSettings"
                                id="${subscription.topic.id}">${subscription.topic}</g:link>
                    </td>
                    <td>${subscription.seriousness}</td>
                    <td>
                        <g:link name="sendInvite" controller="user" action="sendInvite"
                                id="${subscription.topic.id}">Send invite</g:link>
                    </td>
                    <td>
                        <g:link name="unsubscribe" controller="user" action="unsubscribe"
                                id="${subscription.topic.id}">Unsubscribe</g:link>
                    </td>
                </tr>
            </g:each>
        </table>
    </div>

    <div>
        <h3>Available public topics</h3>
        <table class="table table-bordered">
            <tr><th>Name</th><th>Subscribe/Unsubscribe</th></tr>
            <g:each in="${publicTopics}" var="topic">
                <tr>
                    <td>${topic.name}</td>
                    <td>
                        <g:link name="subscribe" controller="user" action="subscribe"
                                id="${topic.id}">Subscribe</g:link>
                    </td>
                </tr>
            </g:each>
        </table>
    </div>

</div>
</body>
</html>