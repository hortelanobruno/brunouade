<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> .:: Zara - Centro De Distribucion - No hay articulos a reponer ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript" src="js/hora.js">
</script>
</head>
<body>
		<%@ include file="top.jsp" %>
		
		<tr height="500">
			<td colspan="2" align="center">
				<h1>No hay articulos para reponer.</h1>
				<form action="index.jsp" method="post">
				<input type="submit" name="btnVolver" value="Volver"/>
				</form>
			</td>
		</tr>
		
		<%@ include file="footer.jsp" %>
</body></html>
