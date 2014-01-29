<%-- 
    Document   : facenocv2.jsp
    Created on : Aug 30, 2012, 5:28:19 PM
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html> 
    <head> 
        <title>Client-side OAuth Example</title> 
        <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    </head> 
    <body> 
        <script> 
            function displayUser(user) {
                var userName = document.getElementById('userName');
                var greetingText = document.createTextNode('Greetings, '
                    + user.name + '.');
                userName.appendChild(greetingText);
            }

            var appID = 475180742505708;
            if (window.location.hash.length == 0) {
                var path = 'https://www.facebook.com/dialog/oauth?';
                var queryParams = ['client_id=' + appID,
                    'redirect_uri=' + window.location,
                    'response_type=token'];
                var query = queryParams.join('&');
                var url = path + query;
                window.location = url;
            } else {
                var accessToken = window.location.hash.substring(1);
                $.getJSON("https://graph.facebook.com/me?"+accessToken+"&fields=email", function(data){
                    alert(data["email"]);
                });
            }
        </script> 
        <p id="userName"></p> 
    </body> 
</html>