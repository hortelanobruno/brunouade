<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html> 
    <head> 
        <title></title> 
        <script src="/default/js/jquery.min.js" type='text/javascript'></script>
    </head> 
    <body>
        <script language='javascript' type='text/javascript'>

            function generateRetornFacebook(data) {
//                alert($.param(data));
                return $.param(data);
            }

            var accessToken = window.location.hash.substring(1);
            $.getJSON('https://graph.facebook.com/me?' + accessToken + '&fields=id,name,email,first_name,last_name,gender,link,age_range,birthday,hometown,location,picture,address', function(data) {
                window.location = '<% out.print(getServletContext().getInitParameter("url").toString());%>/login_success_facebook_nocv?'+generateRetornFacebook(data);
            });
        </script>
    </body> 
</html>