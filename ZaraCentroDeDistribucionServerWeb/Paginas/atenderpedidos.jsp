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
                <table width="100%">
                  <tr>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Cantidad pedida</th>
                    <th>Cantidad reservada</th>
                    <th>Stock</th>
                    <th>Cantidad enviada</th>
                    <th>Cantidad a enviar</th>
                  </tr>
                  
                  <tr>
                    <td>1000</td>
                    <td>Pantalon rosita</td>
                    <td>20</td>
                    <td>10</td>
                    <td>50</td>
                    <td>0</td>
                    <td>20</td>
                  </tr>
                  
                  <tr>
                    <td>1001</td>
                    <td>Ojotas havaianas</td>
                    <td>300</td>
                    <td>100</td>
                    <td>1000</td>
                    <td>100</td>
                    <td>100</td>
                  </tr>
                  
                  <tr>
                    <td>1005</td>
                    <td>Malla floreada</td>
                    <td>60</td>
                    <td>20</td>
                    <td>120</td>
                    <td>10</td>
                    <td>50</td>
                  </tr>
                </table>
                </div>
			</td>
		</tr>
		
		<%@ include file="footer.jsp" %>
</body>
</html>
