<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script>
            <%
                Boolean saveUrlOriginal = (Boolean) request.getAttribute("saveUrlOriginal");
                if (saveUrlOriginal != null && saveUrlOriginal) {
                    out.println("createCookie('urlOrigen', document.referrer, 1);");
                }
            %>

            function redirect(url) {
                try {
                    window.location = url;
                } catch (err) {
                    window.location = url;
                }
            }
        </script>
    </head>
    <body onLoad="create_page('fin')">
        <%
            Boolean obj = (Boolean) request.getAttribute("saveCookieLogged");
        %>
        <c:choose>
            <c:when test="${obj!=null && obj}">
                <script>
                    <%
                        Object obj2 = request.getAttribute("authCookie");
                        if (obj2 != null) {
                            out.println("createCookie('userAuth', '" + request.getAttribute("authCookie") + "', 30);");
                        }
                        obj2 = request.getAttribute("NOCVauthCookie");
                        if (obj2 != null) {
                            out.println("createCookie('NouserAuth', '" + request.getAttribute("NOCVauthCookie") + "', 30);");
                        }
                    %>
            urlOrigen = readCookie('urlOrigen');
            if (urlOrigen !== null && urlOrigen !== '' && urlOrigen !== '/') {
                var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');
                if (fbz === null) {
                    setTimeout(function() {
                        redirect(urlOrigen)
                    }, 2000);
                } else {
                    createCookie('urlOrigen', 'http://www.google.com', 1);
                    setTimeout(function() {
                        redirect('http://www.google.com')
                    }, 2000);
                }
            } else {
                createCookie('urlOrigen', 'http://www.google.com', 1);
                setTimeout(function() {
                    redirect('http://www.google.com')
                }, 2000);
            }
                </script>
            </c:when>
            <c:otherwise>
                <script>
                    urlOrigen = readCookie('urlOrigen');
                    if (urlOrigen !== null && urlOrigen !== '' && urlOrigen !== '/') {
                        var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');
                        if (fbz === null) {
                            setTimeout(function() {
                                redirect(urlOrigen)
                            }, 5000);
                        } else {
                            createCookie('urlOrigen', 'http://www.google.com', 1);
                            setTimeout(function() {
                                redirect('http://www.google.com')
                            }, 5000);
                        }
                    } else {
                        createCookie('urlOrigen', 'http://www.google.com', 1);
                        setTimeout(function() {
                            redirect('http://www.google.com')
                        }, 5000);
                    }
                </script>
            </c:otherwise>
        </c:choose>
    </body>
</html>
