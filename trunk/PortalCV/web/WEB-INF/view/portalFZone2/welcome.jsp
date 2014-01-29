<%
    String ua = request.getHeader("User-Agent").toLowerCase();

    if (ua.indexOf("blackberry") >= 0 || ua.indexOf("nokiac3") >= 0) {
%>
<html>
    <head>
        <base href="portalFZone2/" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="css/style.css" />
        <script src="js/funciones.js" type="text/javascript"></script>
        <script>
            <%
                Boolean saveUrlOriginal = (Boolean) request.getAttribute("saveUrlOriginal");
                if (saveUrlOriginal != null && saveUrlOriginal) {
                    out.println("urlOrigen = document.referrer;");
                    out.println("var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');");
                    out.println("var fbz2 = urlOrigen.match('" + getServletContext().getInitParameter("url").toString() + "');");
                    out.println("var fbz3 = urlOrigen.match('localhost');");
                    out.println("if (fbz == null && fbz2 == null && fbz3 == null) {");
                    out.println("createCookie('urlOrigen', document.referrer, 1);");
                    out.println("} else {");
                    out.println("createCookie('urlOrigen', 'http://www.google.com', 1);");
                    out.println("}");
                }
            %>

            cookieOrigen = readCookie('urlOrigen');
            if (cookieOrigen)
                setTimeout("window.location=cookieOrigen", 4000)
        </script>
    </head>
    <body>
        <div style="text-align: center">
            <br/>
            <img src="img/logo.png" style="width: 25%;"><br/><br/>
            <img src="img/txt_home_s.png" class="facebook">
        </div>
        <div class="footer"><img src="img/logo_fibertel_phone.png" class="footer"></div>
    </body>
</html>

<% } else {%>

<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->

    <head>
        <base href="<% out.print(getServletContext().getInitParameter("url").toString());%>/portalFZone2/" />
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <title>Fibertel Zone</title>

        <!-- If you are using CSS version, only link these 2 files, you may add app.css to use for your overrides if you like. -->
        <link rel="stylesheet" href="css/normalize.css" />
        <link rel="stylesheet" href="css/foundation.css" />

        <!-- If you are using the gem version, you need this only -->
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/funciones.js" type="text/javascript"></script>
        <script src="js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript">var automate_size = true;</script>
        <script>
            <%
                Boolean saveUrlOriginal = (Boolean) request.getAttribute("saveUrlOriginal");
                if (saveUrlOriginal != null && saveUrlOriginal) {
                    out.println("urlOrigen = document.referrer;");
                    out.println("var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');");
                    out.println("var fbz2 = urlOrigen.match('" + getServletContext().getInitParameter("url").toString() + "');");
                    out.println("var fbz3 = urlOrigen.match('localhost');");
                    out.println("if (fbz == null && fbz2 == null && fbz3 == null) {");
                    out.println("createCookie('urlOrigen', document.referrer, 1);");
                    out.println("} else {");
                    out.println("createCookie('urlOrigen', 'http://www.google.com', 1);");
                    out.println("}");
                }
            %>

            cookieOrigen = readCookie('urlOrigen');
            if (cookieOrigen)
                setTimeout("window.location=cookieOrigen", 4000)
        </script>
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
    </head>
    <body>

        <!-- body content here -->
        <div class="row row-home">
            <div class="large-12 columns text-center column-logo">
                <img class="logo" src="img/logo.png" />
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns text-center column-logo">
                <img id="txt_home" class="" src="img/txt_home_s.png" />
            </div>
        </div>

        <div class="footer">
            <img id="img_footer" src="img/logo_fibertel_phone.png" />
        </div>
        <!-- end body content here -->

        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'js/vendor/zepto' : 'js/vendor/jquery') +
                    '.js><\/script>')
        </script>
        <script src="js/foundation/foundation.js"></script>
        <script src="js/foundation/foundation.alerts.js"></script>
        <script src="js/foundation/foundation.clearing.js"></script>
        <script src="js/foundation/foundation.cookie.js"></script>
        <script src="js/foundation/foundation.dropdown.js"></script>
        <script src="js/foundation/foundation.forms.js"></script>
        <script src="js/foundation/foundation.joyride.js"></script>
        <script src="js/foundation/foundation.magellan.js"></script>
        <script src="js/foundation/foundation.orbit.js"></script>
        <script src="js/foundation/foundation.placeholder.js"></script>
        <script src="js/foundation/foundation.reveal.js"></script>
        <script src="js/foundation/foundation.section.js"></script>
        <script src="js/foundation/foundation.tooltips.js"></script>
        <script src="js/foundation/foundation.topbar.js"></script>
        <script src="js/foundation/foundation.interchange.js"></script>
        <script>
            $(document).foundation();
        </script>
        <script src="js/main.js"></script>
    </body>
</html>
<% }%>