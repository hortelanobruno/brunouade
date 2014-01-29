<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="default/" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <title>::: Fibertel ZONE :::</title>
        <script src="js/funciones.js" type="text/javascript"></script>
    </head>
    <body>
        <center>
            <img src="img/terminos.jpg" width="300" height="223" border="0" usemap="#Map" />
            <map name="Map" id="Map">
                <area shape="rect" coords="166,133,241,159" href="home.jsp" target="_self" />
                <area shape="rect" coords="58,133,135,159" href="javascript:void(0);" onclick="aceptoTerminosMovil(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>)" target="_self" />
                <area shape="rect" coords="47,97,250,119" href="/condiciones" target="_blank" />
            </map>
        </center>
    </body>
</html>
