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
			<h2 class="title"><a href="#">Centro de Distribucion Uruguay</a></h2>
			<div class="entry">
				<form name="form1" action="GenerarSolEnvio.do" method="post">
           <table cellpadding="10" cellspacing="10">
			<tr>
                <td colspan="2" align="center"><h1>Generar envios</h1></td>
             </tr>
              <tr height="20px">
              </tr>
              <tr>
               <td width="200">
		            <select name="idsoldis" onchange="fillSolDis()">
		            <option value=""></option>
		            <logic:iterate id="articulos" name="GenerarEnviosForm" property="codigosSolDist" >
		            <option value="<bean:write name='articulos'/>"><bean:write name='articulos'/></option>
					</logic:iterate>
		            </select>
				</td>
                <td width="500" valign="top">
                <div id="tablaDatos">
                </div>
                </td>
             </tr>
              <tr height="20px">
              </tr>
              <tr>
                <td colspan="2">
                  <input name="buttonEnvio" type="submit" id="buttonEnvio" value="Generar solicitud de envio" />
                </td>
              </tr>
            </table> 
           </form>
			</div>
			<p class="meta">Posted on November 5, 2007 by <a href="#">Someone</a> &nbsp;|&nbsp; <a href="#">32 comments</a></p>
		</div>
	</div>
	<!-- end content -->
	<!-- start sidebar -->
	<div id="sidebar">
		<ul>
			<li>
				<h2>Etiam dolore</h2>
				<p>Nulla pellentesque dolor non tellus. Duis vel elit sit <a href="#">amet ipsum vehicula</a> varius. Vestibulum ante ipsum primis in faucibus orci luctus.</p>
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
