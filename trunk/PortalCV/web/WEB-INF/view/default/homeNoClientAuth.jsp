<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <base href="default/" />
        <script src="js/modernizr.min.js"></script>
        <script src="js/jquery.min.js" type='text/javascript'></script>
        <script src="js/jquery.ui.js" type="text/javascript"></script>
        <script src="js/jquery.alerts.js" type="text/javascript"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>::: Fibertel ZONE :::</title>
    </head>
    <script>
        <%
            Boolean saveUrlOriginal = (Boolean) request.getAttribute("saveUrlOriginalNoCliente");
            if (saveUrlOriginal != null && saveUrlOriginal) {
                out.println("createCookie('urlOrigen', document.referrer, 1);");
            }
        %>

        function changeToPremium() {
            eraseCookie('NouserAuth');
            var url = <% out.print("'" + getServletContext().getInitParameter("url").toString() + "/changeToPremium'");%>;
            terminos(url);
        }
    </script>
    <body>
        <script>
            // Seteo una cookie guardando la URL de origen
            urlOrigen = readCookie("urlOrigen");

            create_page('cookieNC');

            if (((screen.width == 320) && (screen.height == 240))) {
                create_relative_area('landscape', 0.080, 0.502, 0.920, 0.673, "changeToPremium()");
                create_relative_area('landscape', 0.363, 0.704, 0.630, 0.839, "terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/autoLogin'");%>)");
            } else {

                create_relative_area('landscape', 0.243, 0.480, 0.756, 0.581, "changeToPremium()");
                create_relative_area('landscape', 0.413, 0.643, 0.582, 0.728, "terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/autoLogin'");%>)");

                create_relative_area('portrait', 0.158, 0.537, 0.842, 0.613, "changeToPremium()");
                create_relative_area('portrait', 0.389, 0.660, 0.609, 0.723, "terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/autoLogin'");%>)");

            }
            setTimeout(function() {
                window.scrollTo(0, window.innerHeight)
            }, 100);

        </script>
    </body>
</html>