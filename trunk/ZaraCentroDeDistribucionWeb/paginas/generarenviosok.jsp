<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Zara - Centro de Distribuci&oacute;n</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
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
	<!-- start sidebar -->
	<div id="mensaje">
		<ul>
			<li>
				<h2>Generar envios</h2>
				<p>Se genero la solicitud de envio correctamente.</p>
				<% String tienda1 = (String)request.getAttribute("tienda1");
				String tienda2 = (String)request.getAttribute("tienda2");
				String tienda1cod = (String) request.getAttribute("tienda1cod");
				String tienda2cod = (String) request.getAttribute("tienda2cod");
				if((tienda1 != null)&&(tienda1.equalsIgnoreCase("ok"))){
					out.print("<label style='color: black'>Tienda "+tienda1cod+" no reporto errores</label>");	
				}else if(tienda1 != null){
					out.print("<label style='color: red'>Tienda "+tienda1cod+"  reporto error</label>");
				}
				if((tienda2 != null)&&(tienda2.equalsIgnoreCase("ok"))){
					out.print("<label style='color: black'>Tienda "+tienda2cod+" no reporto errores</label>");	
				}else if(tienda2 != null){
					out.print("<label style='color: red'>Tienda "+tienda2cod+"  reporto error</label>");
				}
				%>	
			</li>
		</ul>
	</div>
	<!-- end sidebar -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end page -->
<div id="footer">
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>