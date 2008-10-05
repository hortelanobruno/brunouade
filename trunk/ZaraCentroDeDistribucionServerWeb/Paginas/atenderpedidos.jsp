<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> .:: Zara - Centro De Distribucion - Atender Pedidos ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript" src="js/hora.js">
</script>
</head>
<body>
		<%@ include file="top.jsp" %>
		
		<tr height="500">
			<td colspan="2" align="center">
            <h1>Pedidos atendidos</h1>
            <br><br><br>
				<div id="tablaDatos">
                <table>
                <tr>
                <td>Articulo: </td>
                <td>Codigo:</td>
                <td><label>1000</label></td>
                <td>Stock:</td>
                <td><label>10</label></td>
                <td>Llego:</td>
                <td><label>20</label></td>
                <td>Suficiente</td>
                <td><label>NO</label></td>
                </tr>
                <tr>
                <td>Solicitud:</td>
                <td><label>1001</label></td>
                <td>Pedido</td>
                <td><label>20</label></td>
                <td>Cantidad:</td>
                <td><input type="text" /></td>
                </tr>
                <tr>
                <td>Solicitud:</td>
                <td><label>1002</label></td>
                <td>Pedido</td>
                <td><label>5</label></td>
                <td>Cantidad:</td>
                <td><input type="text" /></td>
                </tr>
                </table>
                
                </div>
			</td>
		</tr>
		
		<%@ include file="footer.jsp" %>
</body>
</html>
