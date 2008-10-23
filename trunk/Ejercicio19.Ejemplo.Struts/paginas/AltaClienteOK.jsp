<%@ page language="java" %>
<%@ taglib uri="/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/struts-html.tld" prefix="html" %>
<%@ taglib uri="/struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<title>Alta de un cliente</title>
		<script type="text/javascript" src="script.js"></script> 
	</head>
	<body>
	<form action="Cargar.do" method="get">
		<h3>El cliente <bean:write name="AltaClienteForm" property="nombreCliente"/> ha sido creado correctamente.</h3>
		<p>Propiedades del cliente creado:</p>
		<ul>
			<li>Nombre: <bean:write name="AltaClienteForm" property="nombreCliente"/> </li>
			<li>Direccion: <bean:write name="AltaClienteForm" property="direccionCliente"/></li>
		</ul>
      	<logic:iterate id="element" name="AltaClienteForm" property="books" > 
	    <label name="title"><bean:write name="element" property="title" /></label><br /> 
	    <label name="loca"><bean:write name="element" property="bruno.nombre" /></label><br />
	   </logic:iterate> 
	   <label id="title1">Ahorasi</label>
	   <input type="text" size="45" maxlength="45" name="edad">
	   <input type="button" value="Cargar" class="botonAccion" onclick="llamarAjax()">
   </form>
		<p><a href="AltaCliente.jsp">Alta de un nuevoCliente.</a></p>
		<p><a href="index.jsp">Volver al menú principal.</a></p>
	</body>
</html:html>
