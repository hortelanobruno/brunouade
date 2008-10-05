<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> .:: Zara - Centro de Distribucion - Generar Solicitud de Fabricacion ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript" src="js/hora.js">
</script>
</head>
<body style="background-color:#f7f8e8;">
	
		<%@ include file="top.jsp" %>	
		
		<!-- VA DE ACA -->
		
		<tr height="500px">
			<td colspan="2">
			<form action="gensolfab.jsp" method="post">
			<table width="100%">
				<tr>
                <td align="center"><h1>Articulos a fabricar</h1></td>
                </tr>
                <tr height="20px">
                </tr>
                <tr>
					<td align="center">
                    <div id="tablaDatos">
						<table width="700px" cellpadding="1" cellspacing="1">
							<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
							<th>Stock actual</th>
							<th>Stock pedido</th>
							<th>Stock recibido</th>
							<th>Stock minimio a pedir</th>
							<th>Cantidad a fabricar</th>
							</tr>
							<tr>
							<!-- property="codigo" -->
                            <td><label>1000</label></td>
                            <td>Pantalon Azul</td>
                            <td>0</td>
                            <td>50</td>
                            <td>0</td>
                            <td>100</td>
                            <!-- property="cantidad" -->
                            <td><input type="text" name="cantFab/"></td>
							</tr>
                            <tr>
                            <td>1001</td>
                            <td>Remera manga larga</td>
                            <td>0</td>
                            <td>20</td>
                            <td>0</td>
                            <td>40</td>
                            <td><input type="text" name="cantFab/"></td>
							</tr>
                            <tr>
                            <td>1005</td>
                            <td>Sweater escote en v</td>
                            <td>0</td>
                            <td>5</td>
                            <td>0</td>
                            <td>10</td>
                            <td><input type="text" name="cantFab/"></td>
							</tr>
                            <tr>
                            <td>1010</td>
                            <td>Bufanda lo lobo marino</td>
                            <td>0</td>
                            <td>1</td>
                            <td>0</td>
                            <td>2</td>
                            <td><input type="text" name="cantFab/"></td>
							</tr>
					  </table>
                      </div>
					</td>
				</tr>
				<tr height="20px">
                </tr>
                <tr>
					<td align="center">
					<i>Generar solicitud Fabricacion</i>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="Generar"/>
					</td>
				</tr>
			</table>
			

			</td>
		</tr>
		
		
		<!-- HASTA ACA -->

		<%@ include file="footer.jsp" %>
	
</body>
</html>