<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> .:: Zara - Centro De Distribucion - Generar envios ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css">
<script type="text/javascript" src="js/hora.js">
</script>
</head>

<body>

		<%@ include file="top.jsp" %>	
        
        <%  %>
        <tr height="500px">
			<td colspan="2" align="center">

           <table cellpadding="10" cellspacing="10">
<tr>
                <td colspan="2" align="center"><h1>Generar envios</h1></td>
             </tr>
              <tr height="20px">
              </tr>
              <tr>
                <td width="200">
                <!-- property="codigoSolicitudDistribucion" -->
                  <select name="listaSolicitudesEnvio" size="10" style="width:220px" id="listaSolicitudesEnvio">
                    <option value="10001">Cod: 10001 - Tienda: Florida</option>
                    <option value="10002">Cod: 10002 - Tienda: Florida</option>
                    <option value="10003">Cod: 10003 - Tienda: Florida</option>
                    <option value="10005">Cod: 10005 - Tienda: Palermo</option>
                  </select>    </td>
                <td width="500" valign="top">
                <div id="tablaDatos">
                <table width="100%">
                  <tr>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Cantidad pedida</th>
                    <th>Cantidad reservada</th>
                    <th>Stock</th>
                    <th>Cantidad enviada</th>
                  </tr>
                  
                  <tr>
                    <!-- property="codigoArticulo" -->
                    <td><label>1000</label></td>
                    <td>Pantalon rosita</td>
                    <td>20</td>
                    <td>10</td>
                    <td>50</td>
                    <td>0</td>
                    <!-- property="cantEnviar" -->
                    <td><input type="text"/></td>
                  </tr>
                </table>
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
           
          </td>
        </tr>
        
        <%@ include file="footer.jsp" %>
</body>
</html>
