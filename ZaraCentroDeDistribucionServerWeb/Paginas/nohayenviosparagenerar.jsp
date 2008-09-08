<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> .:: Zara - Centro De Distribucion - No hay Envios para Generar ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript" src="js/hora.js">
</script>
<body>

		<%@ include file="top.jsp" %>	
        
        <tr height="500px">
			<td colspan="2" align="center">
        		<form action="index.jsp"  method="get">
                <h1>
                No hay envios para generar.
                </h1>
                <input type="submit" value="Volver" />
                </form>
            </td>
        </tr>
        
        <%@ include file="footer.jsp" %>
</body>
</html>
