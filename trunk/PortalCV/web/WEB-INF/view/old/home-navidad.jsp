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
            <%

                Object obj = request.getAttribute("authInvalid");
                if (obj != null && ((Boolean) obj)) {
                    out.println("eraseCookie('userAuth');");
                }
                Object obj2 = request.getAttribute("authNOCVInvalid");
                if (obj2 != null && ((Boolean) obj2)) {
                    out.println("eraseCookie('NouserAuth');");
                }

            %>
                
                
                // Seteo una cookie guardando la URL de origen
                urlOrigen = document.referrer;
                if (urlOrigen != null){
                    var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');
                    if (fbz == null){
                        createCookie('urlOrigen', document.referrer, 1);
                    }else{
                        createCookie('urlOrigen', 'http://www.google.com', 1);
                    }
                }else{
                    createCookie('urlOrigen', 'http://www.google.com', 1);
                }

                var userAuth = readCookie('userAuth');
                if (userAuth) {
                    //                alert('hay cookie '+userAuth);
                    //                setTimeout("window.location='<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth="+userAuth+"'",1)
                    var url = '<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth='+userAuth;
                    window.location=url;
                }else{
                    //                alert('no hay cookie');
                }
            
                var NouserAuth = readCookie('NouserAuth');
                if (NouserAuth) {
                    var url = '<% out.print(getServletContext().getInitParameter("url").toString() + "/validateNoUserAuth");%>?userAuth='+NouserAuth;
                    window.location=url;
                }else{
                }
			
                create_page('inicio');

                if ((screen.width == 320) && (screen.height == 240) || ((screen.width == 480) && (screen.height == 360))) {
                    create_relative_area('landscape',0.060, 0.534, 0.383, 0.812,"terminosMovil(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>,'terminosP.html')");
                    create_relative_area('landscape',0.620, 0.552, 0.940, 0.803,"terminosMovil(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>,'terminosS.html')");
                } else {

                    create_relative_area('landscape',0.076, 0.639, 0.312, 0.811,"terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>)");
                    create_relative_area('landscape',0.691, 0.639, 0.924, 0.810,"terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>)");

                    create_relative_area('portrait',0.116, 0.610, 0.432, 0.744,"terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>)");
                    create_relative_area('portrait',0.574, 0.612, 0.884, 0.743,"terminos(<% out.print("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>)");

                }
                setTimeout(function () { window.scrollTo(0, window.innerHeight) }, 100);

        </script>
    </body>
</html>