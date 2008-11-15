<%@ page language="java" %>
<%@ taglib uri="/struts-html.tld" prefix="html" %>
<%@ taglib uri="/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/struts-logic.tld" prefix="logic" %> 
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
		<form action="GenerarEnvios.do" id="form2" name="form2" method="post" >
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
					<td><bean:write name="articulos" property="codSolRep" /><input type="hidden" name="codSolRep" value="codSolRep" /></td>
					<td><bean:write name="articulos" property="codSolFab" /><input type="hidden" name="codSolFab" value="codSolFab" /></td>
					<td><bean:write name="articulos" property="fabrica" /><input type="hidden" name="fabrica" value="fabrica" /></td>
					<td><bean:write name="articulos" property="codigoArticulo" /><input type="hidden" name="codigoArticulo" value="codigoArticulo" /></td>
					<td><bean:write name="articulos" property="descripcion" /><input type="hidden" name="descripcion" value="descripcion" /></td>
					<td><bean:write name="articulos" property="cantidadPedida" /><input type="hidden" name="cantidadPedida" value="cantidadPedida" /></td>
					<td><bean:write name="articulos" property="cantidadAFabricar" /><input type="hidden" name="cantidadAFabricar" value="cantidadAFabricar" /></td>
					<td><bean:write name="articulos" property="cantidadRecibida" /><input type="hidden" name="cantidadRecibida" value="cantidadRecibida" /></td>
					<td><bean:write name="articulos" property="cantidadAReponer" /><input type="hidden" name="cantidadAReponer" value="cantidadAReponer" /></td>
					</tr>
					</logic:iterate>
				</table>
				</div>
				<table>
				<tr height="20">
		</tr>		
		<tr>
		<td><% String boton = request.getAttribute("prenderBoton").toString();
					if(boton.equalsIgnoreCase("si")){
						out.print("<label>No se pudieron atender a todas las solicitudes</label>");
						out.print("<input type='submit' name='Submit2' value='Atender pedidos' />");
					}else{
						out.print("<label>");
						out.print("Se atendieron a todas las solicitudes");
						out.print("</label>");
					}%></td>
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
