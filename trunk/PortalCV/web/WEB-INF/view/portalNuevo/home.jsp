<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <script src="/js/modernizr.min.js"></script>
        <script src="/js/jquery.min.js" type='text/javascript'></script>
        <script src="/js/jquery.ui.js" type="text/javascript"></script>
        <script src="/js/jquery.alerts.js" type="text/javascript"></script>
        <script src="/js/funciones.js" type="text/javascript"></script>
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
                var url = '<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth=' + userAuth;
                eraseCookie('userAuth');
                window.location = url;
            } else {
            }
            var NouserAuth = readCookie('NouserAuth');
            if (NouserAuth) {
                var url = '<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth=' + NouserAuth;
                eraseCookie('NouserAuth');
                window.location = url;
            } else {
            }

            <%
                Object obj = request.getAttribute("autoLogin");
                if (obj != null && ((Boolean) obj)) {
                    out.println("var url = '" + getServletContext().getInitParameter("url").toString() + request.getAttribute("autoLoginURL") + "';");
                    out.println("window.location=url;");
                }
            %>

            create_page('portalNuevo/inicio');
            
            if (((screen.width == 320) && (screen.height == 240)) || ((screen.width == 480) && (screen.height == 360))) {

                create_mail('landscape', 'usremail', 0.257, 0.565, 0.750, 0.673, 'form', 'post', '/registernocv');
                create_relative_area('landscape', 0.760, 0.547, 0.850, 0.682, "register('form','usremail')");
                create_relative_area('landscape', 0.157, 0.332, 0.850, 0.444, "go('/loginNOCVFacebook')");

            } else if (((screen.width == 240) && (screen.height == 320))) {
                create_mail('portrait', 'usremail', 0.213, 0.524, 0.783, 0.601, 'form', 'post', '/registernocv');
                create_relative_area('portrait', 0.792, 0.524, 0.900, 0.601, "register('form','usremail')");
                create_relative_area('portrait', 0.096, 0.329, 0.908, 0.406, "go('/loginNOCVFacebook')");
            } else
            {
                create_mail('landscape', 'usremailL', 0.240, 0.385, 0.480, 0.430, 'form', 'post', '/registernocv');
                create_mail('portrait', 'usremailP', 0.365, 0.342, 0.638, 0.377, 'form', 'post', '/registernocv');
                create_relative_area('landscape', 0.445, 0.378, 0.486, 0.435, "register('form','usremailL')");
                create_relative_area('landscape', 0.199, 0.266, 0.413, 0.323, "go('/loginNOCVFacebook')");
                create_relative_area('landscape', 0.430, 0.266, 0.643, 0.323, "go('/loginNOCVGoogle')");
                create_relative_area('landscape', 0.659, 0.266, 0.872, 0.323, "go('/loginNOCVTwitter')");
                create_relative_area('landscape', 0.505, 0.378, 0.781, 0.435, "go('/mundoCLiente')");
                create_relative_area('landscape', 0.805, 0.378, 0.966, 0.435, "go('/oneClick')");
                
                
                create_relative_area('portrait', 0.641, 0.338, 0.693, 0.380, "register('form','usremailP')");
                create_relative_area('portrait', 0.332, 0.22, 0.668, 0.272, "go('/loginNOCVFacebook')");
                create_relative_area('portrait', 0.332, 0.3, 0.668, 0.351, "go('/loginNOCVGoogle')");
                create_relative_area('portrait', 0.332, 0.377, 0.668, 0.429, "go('/loginNOCVTwitter')");
                
            }
            if (((screen.width == 320) && (screen.height == 240))) {
                setTimeout(function() {
                    window.scrollTo(0, window.innerHeight)
                }, 100);
            }

        </script>
    </body>
</html>