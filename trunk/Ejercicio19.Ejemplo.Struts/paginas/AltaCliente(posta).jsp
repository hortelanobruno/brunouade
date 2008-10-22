<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>

<html:html>
	<head>
		<title>Alta de un nuevo cliente</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
	</head>

	<body>
		
		<html:form action="Alta.do" focus="name" >

		<table align="left">
		<tr><td colspan="2" class="TitulosTextos">Alta de Cliente</td></tr>
		<tr>
		<td class="textos">Nombre:
		</td>
		<td>
			<html:text property="nombreCliente" size="45" maxlength="45" styleClass="CajaForm"/>
		</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><font color=red>
				<html:errors property="nombreCliente"/>
				</font>
			</td>
		</tr>
		<tr>
			<td class="textos">Dirección:</td>
			<td>
				<html:text property="direccionCliente" size="45" maxlength="45" styleClass="CajaForm"/>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><font color=red>
				<html:errors property="direccionCliente"/>
				</font>
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
		
		</html:form>
		
	</body>
</html:html>