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
            if (document.referrer != '') {
                urlOrigen = document.referrer;
                var fbz = urlOrigen.match('fbzauth.fibertel.com.ar');
                var fbz2 = urlOrigen.match('<% out.print(getServletContext().getInitParameter("url").toString());%>');
                var fbz3 = urlOrigen.match('localhost');
                if (fbz == null && fbz2 == null && fbz3 == null) {
                    var fbz = document.referrer.match('blackberry.com/select/wifiloginsuccess/');
                    if (fbz == null) {
                        createCookie('urlOrigen', document.referrer, 1);
                    } else {
                        createCookie('urlOrigen', 'http://www.google.com', 1);
                    }
                } else {
                    createCookie('urlOrigen', 'http://www.google.com', 1);
                }
            } else {
                createCookie('urlOrigen', 'http://www.google.com', 1);
            }
        </script>
    </head>
    <body>
        <img src="img/logo_estandar_bb.png" class="logo" id="logo">
        <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/premium");%>"><img src="img/premium.png" class="facebook"></a>
        <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/standard");%>"><img src="img/standard.png" class="google"></a>
        <a href="tel:08101229663"><img src="img/tel.png" class="publicidad"></a>
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
        <!--[if IE 8]>
            <link rel="stylesheet" href="css/main_ie8.css" />
        <![endif]-->
        <script src="js/funciones.js" type="text/javascript"></script>
        <script src="js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript">var automate_size = true;</script>
        <script>
            if (document.referrer != '') {
                urlOrigen = document.referrer;
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
        </script>
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
    </head>
    <body>

        <!-- body content here -->
        <div class="row titulo">
            <div class="large-12 columns text-center">
                <img id="img_titulo" src="img/titulo.png" />
            </div>
        </div>

        <div class="row vertical_middle_web">
            <div class="large-6 columns text-center column-logo">
                <img class="logo" src="img/logo.png" />
            </div>
            <div id="buttons_social" class="large-6 columns column-buttons">
                <div class="buttons">
                    <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/premium");%>"><img src="img/premium.png" /></a><br/>
                    <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/standard");%>"><img src="img/standard.png" /></a><br/>
                    <a href="tel:08101229663" style="cursor: default; text-align: center;"><img class="tel" src="img/tel.png" /></a>
                    <br/><br/>
                </div>
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