<%-- 
    Document   : admin
    Created on : May 14, 2012, 12:56:04 PM
    Author     : bruno
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FiberZone - Admin</title>
        <script src="/js/jquery-1.4.2.js" type="text/javascript"></script>
        <script src="/js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
        <script src="/js/jquery.validate.js" type="text/javascript"></script>
        <script type="text/javascript">

            $(document).ready(function(){
                $("#adminForm").validate({
                    rules: {
                        quotaVolumeBasic: {
                            number: true,
                            min: 0
                        },
                        quotaTimeBasic: {
                            number: true,
                            min: 0
                        },
                        quotaVolumePremium: {
                            number: true,
                            min: 0
                        },
                        quotaTimePremium: {
                            number: true,
                            min: 0
                        }
                    }
                });
            });
        </script>
    </head>
    <body>
        <h1>Configuracion de parametros!</h1>
        <form id="adminForm" action="<c:url value='adminChanges'/>" method="post">
            <table>
                <tr>
                    <td>Perfil</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>PREMIUM-SERVICE</td>
                    <td>Cuota de tiempo (segundos)</td>
                    <td><input type="text" id="quotaTimePremium" name="quotaTimePremium" value="${quotaTimePremium}"/></td>
                    
                </tr>
                <tr>
                    <td>PREMIUM-SERVICE</td>
                    <td>Cuota de volumen (bytes</td>
                    <td><input type="text" id="quotaVolumePremium" name="quotaVolumePremium" value="${quotaVolumePremium}"/></td>
                </tr>
                <tr>
                    <td>BASIC-SERVICE</td>
                    <td>Cuota de tiempo (segundos)</td>
                    <td><input type="text" id="quotaTimeBasic" name="quotaTimeBasic" value="${quotaTimeBasic}"/></td>
                </tr>
                <tr>
                    <td>BASIC-SERVICE</td>
                    <td>Cuota de volumen (bytes</td>
                    <td><input type="text" id="quotaVolumeBasic" name="quotaVolumeBasic" value="${quotaVolumeBasic}"/></td>
                </tr>
            </table>
                <input type="submit" value="Guardar cambios" />
        </form>
    </body>
</html>
