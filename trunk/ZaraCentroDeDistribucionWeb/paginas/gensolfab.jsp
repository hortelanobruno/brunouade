<%@ page language="java" %>
<%@ taglib uri="/struts-html.tld" prefix="html" %>
<%@ taglib uri="/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/struts-logic.tld" prefix="logic" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title> .:: Zara - Centro de Distribucion - Generar Solicitud de Fabricacion ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript" src="js/hora.js"></script>
<script type="text/javascript" src="js/validaciones.js"></script>
</head>
<body style="background-color:#f7f8e8;">
	
		<%@ include file="top.jsp" %>	
		
		<!-- VA DE ACA -->
		
		<tr height="500px">
			<td colspan="2">
			<form method="post" action="GenerarSolFab.do" name="form1">
			<table width="100%">
				<tr>
                <td align="center"><h1>Articulos a fabricar</h1></td>
                </tr>
                <tr height="20px">
                </tr>
                <tr>
					<td align="center">
                    <div id="tablaDatos">
						<table width="700px" cellpadding="1" cellspacing="1">
							<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
							<th>Stock actual</th>
							<th>Cantidad pedida</th>
							<th>Cantidad minimio a pedir</th>
							<th>Cantidad a fabricar</th>
							</tr>
                            <logic:iterate id="articulos" name="CargarArtFabForm" property="articulosAFabricar" >
							<tr>
							<td><bean:write name='articulos' property='art.codigo' /><input type="hidden" name="codigo" value="<bean:write name='articulos' property='art.codigo' />" /></td>
							<td><bean:write name="articulos" property="art.descripcion" /><input type="hidden" name="descripcion" value="<bean:write name="articulos" property="art.descripcion" />" /></td>
							<td><bean:write name="articulos" property="art.cantidad" /><input type="hidden" name="stock" value="<bean:write name="articulos" property="art.cantidad" />" /></td>
							<td><bean:write name="articulos" property="cantidadPedida" /><input type="hidden" name="cantPedida" value="<bean:write name="articulos" property="cantidadPedida" />" /></td>
							<td><bean:write name="articulos" property="cantMinAPedir" /><input type="hidden" name="cantMinAPedir" value="<bean:write name="articulos" property="cantMinAPedir" />" /></td>
							<td><input type="text" name="cantAFab" value="0" onblur="comprobarCantAPed()"/></td>
							</tr>
							</logic:iterate>
					  </table>
                      </div>
					</td>
				</tr>
				<tr height="20px">
                </tr>
                <tr>
                <td align="center"><h1>Articulos fabricandose</h1></td>
                </tr>
                <tr height="20px">
                </tr>
                <tr>
					<td align="center">
                    <div id="tablaDatos">
						<table width="700px" cellpadding="1" cellspacing="1">
							<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
							<th>Cantidad pedida</th>
							<th>Cantidad recibida</th>
							</tr>
                            <logic:iterate id="articulos" name="CargarArtFabForm" property="articulosFabricandose" >
							<tr>
							<td><bean:write name='articulos' property='art.codigo' /></td>
							<td><bean:write name="articulos" property="art.descripcion" /></td>
							<td><bean:write name="articulos" property="cantidadAFabricar" /></td>
							<td><bean:write name="articulos" property="cantidadRecibida" /></td>
							</tr>
							</logic:iterate>
					  </table>
                      </div>
					</td>
				</tr>
				<tr height="20px">
                </tr>
                <tr>
					<td align="center">
					<i>Generar solicitud Fabricacion</i>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="Generar" />
					</td>
				</tr>
			</table>
			</form>

			</td>
		</tr>
		
		
		<!-- HASTA ACA -->

		<%@ include file="footer.jsp" %>
	
</body>
</html>