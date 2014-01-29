<html>
    <head>
        <base href="default/" />
        <script src="js/modernizr.min.js"></script>
        <script src='js/jquery.min.js' type='text/javascript'></script>
        <script src="js/jquery.ui.js" type="text/javascript"></script>
        <script src="js/jquery.alerts.js" type="text/javascript"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <title>::: Fibertel ZONE :::</title>
    </head>
    <body onLoad="create_page('fin')">
        <script>
            <%
                Object obj = request.getAttribute("saveCookieLogged");
                if (obj != null && ((Boolean) obj)) {
                    obj = request.getAttribute("authCookie");
                    if (obj != null) {
                        out.println("createCookie('userAuth', '" + request.getAttribute("authCookie") + "', 30);");
                    }
                    obj = request.getAttribute("NOCVauthCookie");
                    if (obj != null) {
                        out.println("createCookie('NouserAuth', '" + request.getAttribute("NOCVauthCookie") + "', 30);");
                    }
                    
                    
                    out.println("urlOrigen=readCookie('urlOrigen');");
                    out.println("if(urlOrigen!=null && urlOrigen != '' && urlOrigen != '/'){");
                    out.println("   var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');");
                    out.println("   if (fbz == null){");
                    out.println("       setTimeout('window.location=urlOrigen', 100);");
                    out.println("   }else{");
                    out.println("       createCookie('urlOrigen', 'http://www.google.com', 1);");
                    out.println("       setTimeout('window.location=http://www.google.com', 100);");
                    out.println("   }");
                    out.println("}else{");
                    out.println("   createCookie('urlOrigen', 'http://www.google.com', 1);");
                    out.println("   setTimeout('window.location=http://www.google.com', 100);");
                    out.println("}");
                    
                    //out.println("urlOrigen=readCookie('urlOrigen');");
                    //out.println("if (urlOrigen) setTimeout('window.location=urlOrigen', 100)");
                } else {
                    //out.println("urlOrigen=readCookie('urlOrigen');");
                    //out.println("if (urlOrigen) setTimeout('window.location=urlOrigen', 5000)");
                    out.println("urlOrigen=readCookie('urlOrigen');");
                    out.println("if(urlOrigen!=null && urlOrigen != '' && urlOrigen != '/'){");
                    out.println("   var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');");
                    out.println("   if (fbz == null){");
                    out.println("       setTimeout('window.location=urlOrigen', 5000);");
                    out.println("   }else{");
                    out.println("       createCookie('urlOrigen', 'http://www.google.com', 1);");
                    out.println("       setTimeout('window.location=http://www.google.com', 5000);");
                    out.println("   }");
                    out.println("}else{");
                    out.println("   createCookie('urlOrigen', 'http://www.google.com', 1);");
                    out.println("   setTimeout('window.location=http://www.google.com', 5000);");
                    out.println("}");
                }

            %>
        </script>
    </body>
</html>