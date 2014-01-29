<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="<% out.print(getServletContext().getInitParameter("url").toString());%>/portalFZone2_othervideo/" />
        <script type="text/javascript">
            var ua = navigator.userAgent;
            var url = "bb/index.html";
            if (ua.indexOf("BlackBerry") >= 0)

            {
                if (ua.indexOf("WebKit") >= 0)

                {
                    window.location = url;
                }
            }
        </script>
        <!--<%!
            String detectBlackBerry(HttpServletRequest req) {
                String url = "";
                try {
                    String ua = req.getHeader("user-agent").toLowerCase();

                    if (ua.indexOf("blackberry") >= 0) {
                        if (ua.indexOf("webkit") >= 0) {
                            url = "bb/home.html";
                        }
                    }
                } catch (Exception ex) {
                }
                return url;
            }
        %>
        
        <%

            //Note: separate code block

            String url = detectBlackBerry(request);
            if (url.length() > 0) {
                response.sendRedirect(url);
            }
        %>
        -->

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