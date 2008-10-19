<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> .:: Zara - Centro de Distribucion - No hay Articulos a Fabricar ::. </title>
</head>

<body>

	<%@ include file="top.jsp" %>	
        
    <tr height="500px">
        <td colspan="2">
            <form action="index.jsp"  method="get">
            <h1>
            No hay articulos a fabricar.
            </h1>
            <input type="submit" value="Volver" />
            </form>
        </td>
    </tr>
    
    <%@ include file="footer.jsp" %>
        
</body>
</html>
