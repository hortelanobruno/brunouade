<%-- 
    Document   : macap
    Created on : Aug 2, 2013, 11:52:39 AM
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FiberZone MAC AP</title>
    </head>
    <body>
        <h1>Client IP: <%=request.getAttribute("clientIP")%></h1>
        <h1>Client MAC: <%=request.getAttribute("clientMac")%></h1>
        <h1>AP MAC: <%=request.getAttribute("apMac")%></h1>
        <h1>AP Name: <%=request.getAttribute("apName")%></h1>
        <h1>User-Agent: <%=request.getHeader("user-agent")%></h1>
    </body>
</html>
