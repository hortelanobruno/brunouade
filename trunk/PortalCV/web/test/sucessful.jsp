<%-- 
    Document   : sucessful
    Created on : Apr 19, 2012, 5:57:49 PM
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function close_window() {
                if (confirm("Close Window?")) {
                    close();
                }
            }
        </script>
    </head>
    <body>
        <h1>Hello World!</h1><a href="#" onclick="close_window();return false;">close</a>
    </body>
</html>
