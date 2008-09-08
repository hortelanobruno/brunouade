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
        
        <tr height="500px">
			<td colspan="2" align="center">

           <table>
              <tr>
                <td colspan="2">Tienda &nbsp;&nbsp;&nbsp;&nbsp; 
                      <select name="nn" id="nn">
                        <option value="Florida">Florida</option>
                        <option value="Palermo">Palermo</option>
                      
                      </select>    </td>
              </tr>
              <tr height="20px">
              </tr>
              <tr>
                <td width="200">
                  <select name="listaSolicitudesEnvio" size="10" style="width:150px" id="listaSolicitudesEnvio">
                    <option value="10001">10001</option>
                    <option value="10002">10002</option>
                    <option value="10003">10003</option>
                    <option value="10005">10005</option>
                  </select>    </td>
                <td width="500">
                <div id="tablaDatos">
                <table width="100%">
                  <tr>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Cantidad pedida</th>
                    <th>Cantidad reservada</th>
                    <th>Stock</th>
                    <th>Cantidad enviada</th>
                    <th>Cantidad a enviar</th>
                  </tr>
                  
                  <tr>
                    <td>1000</td>
                    <td>Pantalon rosita</td>
                    <td>20</td>
                    <td>10</td>
                    <td>50</td>
                    <td>0</td>
                    <td>20</td>
                  </tr>
                  
                  <tr>
                    <td>1001</td>
                    <td>Ojotas havaianas</td>
                    <td>300</td>
                    <td>100</td>
                    <td>1000</td>
                    <td>100</td>
                    <td>100</td>
                  </tr>
                  
                  <tr>
                    <td>1005</td>
                    <td>Malla floreada</td>
                    <td>60</td>
                    <td>20</td>
                    <td>120</td>
                    <td>10</td>
                    <td>50</td>
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
