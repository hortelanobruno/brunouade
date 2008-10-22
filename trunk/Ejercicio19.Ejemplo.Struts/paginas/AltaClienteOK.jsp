<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<title>Alta de un cliente</title>
	</head>
	<body>
		<h3>El cliente <bean:write name="AltaClienteForm" property="nombreCliente"/> ha sido creado correctamente.</h3>
		<p>Propiedades del cliente creado:</p>
		<ul>
			<li>Nombre: <bean:write name="AltaClienteForm" property="nombreCliente"/></li>
			<li>Direccion: <bean:write name="AltaClienteForm" property="direccionCliente"/></li>
		</ul>
      	<logic:iterate id="element" name="AltaClienteForm" property="books" > 
	     Element Value: <bean:write name="element" property="title" /><br /> 
	   </logic:iterate> 
		<p><a href="AltaCliente.jsp">Alta de un nuevoCliente.</a></p>
		<p><a href="index.jsp">Volver al men� principal.</a></p>
	</body>
</html:html>
