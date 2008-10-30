<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/struts-html.tld" prefix="html" %>
<%@ taglib uri="/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/struts-logic.tld" prefix="logic" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> .:: Zara - Centro De Distribucion - Generar envios ::. </title>
<link rel="StyleSheet" href="estilos.css" type="text/css" />
<script type="text/javascript" src="js/hora.js"></script>
<script type="text/javascript" src="js/validaciones.js"></script>
</head>

<body>

		<%@ include file="top.jsp" %>	
        
        <%  %>
        <tr height="500px">
			<td colspan="2" align="center">
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
		            <option value="">-SELECT–</option>
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
          </td>
        </tr>
        
        <%@ include file="footer.jsp" %>
</body>
</html>
