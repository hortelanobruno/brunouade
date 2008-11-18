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
				<form name="form1" action="GenerarSolEnvio.do" method="post">
                <h1>Generar envios</h1>
                <div class="left">
		            <select name="idsoldis" onChange="fillSolDis()">
		            <option value=""></option>
		            <logic:iterate id="articulos" name="GenerarEnviosForm" property="codigosSolDist" >
		            <option value="<bean:write name='articulos'/>"><bean:write name='articulos'/></option>
					</logic:iterate>
		            </select>
		            </div>
                <div id="tablaDatos">
                </div>
                  <input name="buttonEnvio" type="submit" id="buttonEnvio" value="Generar solicitud de envio" />
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
