<%@ page import="java.util.*"%>

<%
    Enumeration e;
    e = request.getHeaderNames();
    String userAgent = request.getHeader("user-agent");

%>

<html>
    <body bgcolor=#FFFFFF>
        <%out.print("USER AGENT IS " + userAgent);%>
    </body>
</html>
