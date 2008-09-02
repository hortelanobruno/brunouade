<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.:: Zara - Centro de Distribución ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript">

function getFecha_Hora()
{
	var ahora = new Date()  
	document.write(ahora.getDate())  
	document.write("/")  
	document.write(ahora.getMonth() + 1)  
	document.write("/")  
	document.write(ahora.getFullYear())  
	document.write("<br>")
	document.write(ahora.getHours())
	document.write(":")  
	document.write(ahora.getMinutes())
	document.write(":") 
	document.write(ahora.getSeconds()) 
}

</script>
</head>
<body>
	<table border="1" width="100%" height="100%">
	
		<%@ include file="top.jsp" %>
		
		<tr height="500">
			<td colspan="2" align="center">aa</td>
		</tr>
		
		<%@ include file="footer.jsp" %>
	</table>
</body>
</html>