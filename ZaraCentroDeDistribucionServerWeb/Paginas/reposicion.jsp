<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> .:: Zara - Centro De Distribucion - Reponer Articulos ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript" src="js/hora.js">
</script>
</head>
<body>
	
		<%@ include file="top.jsp" %>
		
		<tr height="500">
			<td colspan="2" align="center">
			<div id="tablaDatos">			
				<table cellpadding="1" cellspacing="1">
					<tr>
						<th>Solicitud de Reposicion</th>
						<th>Solicitud de Fabricacion</th>
						<th>Fabrica</th>
						<th>Codigo</th>
						<th>Descripcion</th>
						<th>Cantidad Pedida</th>
						<th>Cantidad a Fabricar</th>
						<th>Cantidad Recibida</th>
						<th>Cantidad a Reponer</th>
					</tr>
					<tr>
						<td>1</td>
						<td>82</td>
						<td>Lanus</td>
						<td>1000</td>
						<td>Florero chino</td>
						<td>20</td>
						<td>40</td>
						<td>0</td>
						<td>10</td>
					</tr>
				</table>
				</div>
				<table>
				<tr height="20">
		</tr>		
		<tr>
		 <form id="form1" name="form1" method="post" action="indexz.jsp">
		<td>
			      <label>
			      <input type="submit" name="Submit" value="Volver" />
		          </label>

		</td>
		</form>
		<td>
				 <form id="form2" name="form2" method="post" action="atenderpedidos.jsp">
		          <label>
		          <input type="submit" name="Submit2" value="Atender pedidos" />
		          </label>
		
		
			  </td>
		      </form>
		      <p>&nbsp;</p>

		</tr>
				</table>
			</td>	
		</tr>
		
		<%@ include file="footer.jsp" %>
</body>
</html>
