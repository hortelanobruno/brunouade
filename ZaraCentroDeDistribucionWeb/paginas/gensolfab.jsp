<%@ page language="java" %>
<%@ taglib uri="/struts-html.tld" prefix="html" %>
<%@ taglib uri="/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/struts-logic.tld" prefix="logic" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
				<form method="post" action="GenerarSolFab.do" name="form1">
					<h2 class="title">Articulos a fabricar</h2>
					<p class="title">&nbsp;</p>
					<div id="tablaDatos">
						<table width="700px" cellpadding="1" cellspacing="1">
                        <thead>
							<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
							<th>Stock actual</th>
							<th>Cantidad pedida</th>
							<th>Cantidad minimio a pedir</th>
							<th>Cantidad a fabricar</th>
							</tr>
                          </thead>
                            <tbody>
                            <logic:iterate id="articulos" name="CargarArtFabForm" property="articulosAFabricar" >
							<tr>
							<td><bean:write name='articulos' property='art.codigo' /><input type="hidden" name="codigo" value="<bean:write name='articulos' property='art.codigo' />" /></td>
							<td><bean:write name="articulos" property="art.descripcion" /><input type="hidden" name="descripcion" value="<bean:write name="articulos" property="art.descripcion" />" /></td>
							<td><bean:write name="articulos" property="art.cantidad" /><input type="hidden" name="stock" value="<bean:write name="articulos" property="art.cantidad" />" /></td>
							<td><bean:write name="articulos" property="cantidadPedida" /><input type="hidden" name="cantPedida" value="<bean:write name="articulos" property="cantidadPedida" />" /></td>
							<td><bean:write name="articulos" property="cantMinAPedir" /><input type="hidden" name="cantMinAPedir" value="<bean:write name="articulos" property="cantMinAPedir" />" /></td>
							<td><input type="text" name="cantAFab" value="0" onBlur="comprobarCantAPed()"/></td>
							</tr>
							</logic:iterate>
                            </tbody>
					  </table>
                  </div>
					<h2 class="title">Articulos fabricandose</h2>
					<p class="title">&nbsp;</p>
					<div id="tablaDatos">
						<table width="700px" cellpadding="1" cellspacing="1">
                        <thead>
							<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
							<th>Cantidad pedida</th>
							<th>Cantidad recibida</th>
							</tr>
                          </thead>
                            <tbody>
                            <logic:iterate id="articulos" name="CargarArtFabForm" property="articulosFabricandose" >
							<tr>
							<td><bean:write name='articulos' property='art.codigo' /></td>
							<td><bean:write name="articulos" property="art.descripcion" /></td>
							<td><bean:write name="articulos" property="cantidadAFabricar" /></td>
							<td><bean:write name="articulos" property="cantidadRecibida" /></td>
							</tr>
							</logic:iterate>
                            <t/body>
					  </table>
                  </div>
					<i>Generar solicitud Fabricacion</i>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="Generar" />
			</form>
			</div>
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
