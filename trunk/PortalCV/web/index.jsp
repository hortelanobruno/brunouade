<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="<% out.print(getServletContext().getInitParameter("url").toString());%>/portalFZone2/" />
        <script type="text/javascript">
            document.write("<meta http-equiv=\"Refresh\" content=\"2;url=<% out.print(getServletContext().getInitParameter("url").toString() + "/home");%>\" />")
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <title>::: Fibertel ZONE :::</title>
    </head>
    <style type="text/css">
        <!--
        .Estilo1 {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 16px;
            color:#666;
            font-weight: bold;
        }
        a:link {
            text-decoration: none;
            color:#666;
        }
        a:visited {
            text-decoration: none;
            color:#666;
        }
        a:hover {
            text-decoration: none;
        }
        a:active {
            text-decoration: none;
        }
    </style>
    <body>
        <center>
            <div class="logo" style="width:10%"> <img width="100%" src="img/logo.png" border="hidden"/> </div>
            <br/>
            <span class="Estilo1">Redirecting...<br/>
                <br/>
                Si despu&eacute;s de 2 segundos no es redirigido, por favor haga click <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/home");%>">aqu&iacute;</a></span>
        </center>
    </body>
</html>