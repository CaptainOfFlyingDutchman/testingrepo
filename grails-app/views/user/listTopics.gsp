<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
   <title>List Topics</title>
   <meta name="layout" content="kickstart" />
</head>
<body>
       <div>
           <ul>
               <g:each in="${topics}" var="topic">
                   <li>${topic.name}</li>
               </g:each>
           </ul>
       </div>
</body>
</html>