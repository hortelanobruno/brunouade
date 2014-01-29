<%-- 
    Document   : resultWS
    Created on : Jul 3, 2012, 1:21:35 PM
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Params for Result WS!</h1>
        <% 
        
            for(Object key : request.getParameterMap().keySet()){
                out.println(key+": "+request.getParameter(key.toString())); 
            }

          %>
    </body>
</html>
