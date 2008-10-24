<%@ page language="java" %>
<%@ taglib uri="/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/struts-html.tld" prefix="html" %>

<html>
	<head>
		<title>Alta de un nuevo cliente</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
	</head>

	<body>
		
		<form action="Alta.do">
		 <input type="text" name="prueba" value="BrunoliViernes" readonly="readonly" />
		<table align="left">
		<tr><td colspan="2" class="TitulosTextos">Alta de Cliente</td></tr>
		<tr>
		<td class="textos">Nombre:
		</td>
		<td>
			<input type="text" size="45" maxlength="45" name="nombreCliente">
		</td>
		</tr>
		<tr>
			<td class="textos">Dirección:</td>
			<td>
				<input type="text" size="45" maxlength="45" name="direccionCliente"> 
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td align="right"><input type=submit value="Crear Cliente" class="botonAccion"></td>
		</tr>
		<tr>
			<td heigth="10px" colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><p><a href="index.jsp">Volver al menu principal</a></p></td>
		</tr>
		</table>
		</form>
		
	</body>
</html>