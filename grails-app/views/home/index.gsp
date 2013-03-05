<html>

<head>
    <title><g:message code="default.welcome.title" args="[meta(name: 'app.name')]"/></title>
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

<section id="intro" class="first">
    <h2>Introduction</h2>

    <p>LinkSharing is an online application for sharing links and
    embedding resources in them. You can subscribe to public topics and
    can also create topic which you can share with people by making them
        <i>public</i> or <i>private</i>. You can also invite other LinkSharing
    and non-LinkSharing users to subscribe your topics.</p>
</section>


<sec:ifLoggedIn>
    <section id="info">
        <div class="row-fluid">
            <span class="input-block-level">Hi ${user.firstName}! Here is the listing of topics you may have interest in...</span>
            <ul id="myTabs" class="nav nav-tabs">
                <li class="active"><a href="#hotTopics">Hot topics</a></li>
                <li><a href="#latestTopics">Latest topics</a></li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane active" id="hotTopics">
                    <table class="table">
                        <thead><tr><th>Name</th></tr></thead>
                        <tbody>
                        <g:each in="${hotTopics}" var="topic">
                            <tr>
                                <td>
                                    <span style="{display: block}">
                                        <g:link controller="user" action="topicDetails"
                                                id="${topic.key.id}">${topic.key}</g:link>
                                    </span>
                                    <span style="display: block">
                                        <g:if test="${topic.key.summary.length() > 50}">
                                            ${topic.key.summary.substring(0, 48)} ...
                                        </g:if>
                                        <g:else>
                                            ${topic.key.summary}
                                        </g:else>
                                    </span>
                                    <span>
                                        <g:link controller="user" action="allResources" id="${topic.key.id}">
                                            Resources</g:link>
                                    </span>
                                    <span style="margin-left: 3px">Total ${topic.key.subscriptions.size()} subscribers</span>
                                </td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>

                <div class="tab-pane" id="latestTopics">
                    <table class="table">
                        <thead><tr><th>Name</th></tr></thead>
                        <tbody>
                        <g:each in="${latestTopics}" var="topic">
                            <tr>
                                <td>${topic.name}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </section>
</sec:ifLoggedIn>

</body>

</html>
