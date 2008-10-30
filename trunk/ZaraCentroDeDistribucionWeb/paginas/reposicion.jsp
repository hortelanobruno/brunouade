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
		<form action="AtenderPedido.do" id="form2" name="form2" method="post" >
		<tr height="500">
			<td colspan="2" align="center">
            <h1>Reponer articulos</h1>
            <br><br><br>
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
					<logic:iterate id="articulos" name="ReposicionForm" property="articulosAReponer" >
					<tr>
					<td><bean:write name='articulos' property='codSolRep' /><input type="hidden" name="codSolRep" value="<bean:write name='articulos' property='codSolRep' />" /></td>
					<td><bean:write name="articulos" property="codSolFab" /><input type="hidden" name="codSolFab" value="<bean:write name="articulos" property="codSolFab" />" /></td>
					<td><bean:write name="articulos" property="fabrica" /><input type="hidden" name="fabrica" value="<bean:write name="articulos" property="fabrica" />" /></td>
					<td><bean:write name="articulos" property="codigoArticulo" /><input type="hidden" name="codigoArticulo" value="<bean:write name="articulos" property="codigoArticulo" />" /></td>
					<td><bean:write name="articulos" property="descripcion" /><input type="hidden" name="descripcion" value="<bean:write name="articulos" property="descripcion" />" /></td>
					<td><bean:write name="articulos" property="cantidadPedida" /><input type="hidden" name="cantidadPedida" value="<bean:write name="articulos" property="cantidadPedida" />" /></td>
					<td><bean:write name="articulos" property="cantidadAFabricar" /><input type="hidden" name="cantidadAFabricar" value="<bean:write name="articulos" property="cantidadAFabricar" />" /></td>
					<td><bean:write name="articulos" property="cantidadRecibida" /><input type="hidden" name="cantidadRecibida" value="<bean:write name="articulos" property="cantidadRecibida" />" /></td>
					<td><bean:write name="articulos" property="cantidadAReponer" /><input type="hidden" name="cantidadAReponer" value="<bean:write name="articulos" property="cantidadAReponer" />" /></td>
					</tr>
					</logic:iterate>
				</table>
				</div>
				<table>
				<tr height="20">
		</tr>		
		<tr>
		
		<td>
          <label>
          <input type="submit" name="Submit2" value="Atender pedidos" />
          </label>
		  </td>
	      </form>
	      <form id="form1" name="form1" method="post" action="indexz.jsp">
		<td>
	      <label>
	      <input type="submit" name="Submit" value="Volver" />
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
