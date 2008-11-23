<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-html.tld" prefix="html" %>
<%@ taglib uri="/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/struts-logic.tld" prefix="logic" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Zara - Centro de Distribuci&oacute;n</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="js/validaciones.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
</head>
<body>
<div id="logo">
	<h1><a href="#">Zara - Centro de Distribuci&oacute;n</a></h1>
</div>
<div id="menu">
	<%@ include file="top.jsp" %>
</div>
<hr />
<div id="banner"></div>
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<div class="entry">
				<form action="GenerarEnvios.do" id="form2" name="form2" method="post" >
            <h2 class="title">Reponer articulos</h2>
            <p class="title">&nbsp;</p>
            <div id="tablaDatos">			
				<table cellpadding="1" cellspacing="1">
                <thead>
					<tr>
						<th>Solicitud de Reposicion</th>
						<th>Solicitud de Fabricacion</th>
						<th>Fabrica</th>
						<th>Codigo Articulo</th>
						<th>Descripcion Articulo</th>
						<th>Cantidad Pedida</th>
						<th>Cantidad a Fabricar</th>
						<th>Cantidad Recibida Anteriormente</th>
						<th>Cantidad a Reponer</th>
					</tr>
                  </thead>
                    <tbody>
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
                    </tbody>
				</table>
				</div>
			<br><br>	
				<% String boton = request.getAttribute("prenderBoton").toString();
					if(boton.equalsIgnoreCase("si")){
						out.print("<label>No se pudieron atender a todas las solicitudes</label>&nbsp;&nbsp;&nbsp;&nbsp;");
						out.print("<input type='submit' name='Submit2' value='Atender pedidos' />");
					}else{
						out.print("<label class='error'>");
						out.print("Se atendieron a todas las solicitudes");
						out.print("</label>");
					}%>
	      </form>
	      <br><br>
	      <form id="form1" name="form1" method="post" action="index.jsp">
	      <label>
	      <input type="submit" name="Submit" value="Volver" />
          </label>
		</form>
			</div>
			<p class="meta">Posted on November 5, 2007 by <a href="#">Someone</a> &nbsp;|&nbsp; <a href="#">32 comments</a></p>
		</div>
	</div>
	<!-- end content -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end page -->
<div id="footer">
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>
