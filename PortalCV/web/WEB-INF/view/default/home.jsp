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
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <title>::: Fibertel ZONE :::</title>
    </head>
    <body>
        <script>
            // Seteo una cookie guardando la URL de origen
            urlOrigen = document.referrer;
            if (urlOrigen != null) {
                var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');
                var fbz2 = urlOrigen.match('<% out.print(getServletContext().getInitParameter("url").toString());%>');
                var fbz3 = urlOrigen.match('localhost');
                if (fbz == null && fbz2 == null && fbz3 == null) {
                    createCookie('urlOrigen', document.referrer, 1);
                } else {
                    createCookie('urlOrigen', 'http://www.google.com', 1);
                }
            } else {
                createCookie('urlOrigen', 'http://www.google.com', 1);
            }
            
            var userAuth = readCookie('userAuth');
            if (userAuth) {
                var url = '<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth='+userAuth;
                eraseCookie('userAuth');
                window.location=url;
            }else{
            }
            var NouserAuth = readCookie('NouserAuth');
            if (NouserAuth) {
                var url = '<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth='+NouserAuth;
                eraseCookie('NouserAuth');
                window.location=url;
            }else{
            }
            
            <%
                Object obj = request.getAttribute("autoLogin");
                if (obj != null && ((Boolean) obj)) {
                    out.println("var url = '"+getServletContext().getInitParameter("url").toString() + request.getAttribute("autoLoginURL")+"';");
                    out.println("window.location=url;");
                }
            %>

            create_page('inicio');

            if ((screen.width == 320) && (screen.height == 240) || ((screen.width == 480) && (screen.height == 360))) {
                create_relative_area('landscape', 0.060, 0.543, 0.383, 0.807, "terminosMovil(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>,<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/terminosP'");%>)");
                create_relative_area('landscape', 0.620, 0.543, 0.943, 0.807, "terminosMovil(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>,<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/terminosS'");%>)");
            } else {

                create_relative_area('landscape', 0.117, 0.682, 0.373, 0.867, "terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>)");
                create_relative_area('landscape', 0.630, 0.682, 0.883, 0.863, "terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>)");

                create_relative_area('portrait', 0.063, 0.659, 0.404, 0.798, "terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>)");
                create_relative_area('portrait', 0.594, 0.659, 0.932, 0.798, "terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>)");

            }
            setTimeout(function() {
                window.scrollTo(0, window.innerHeight)
            }, 100);

        </script>
    </body>
</html>