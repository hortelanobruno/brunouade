<%
    String ua = request.getHeader("User-Agent").toLowerCase();

    if (ua.indexOf("blackberry") >= 0 || ua.indexOf("nokiac3") >= 0) {
%>
<!--Codigo para BB-->
<html>
    <head>
        <base href="portalFZone2/" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body class="premium">
        <img src="img/logo_premium_bb.png" class="logo" id="logo">
        <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/loginFacebook");%>"><img src="img/facebookBlack.png" class="facebook"></a>
        <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/loginGoogle");%>"><img src="img/googleBlack.png" class="google"></a>
        <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/loginFibertel");%>"><img src="img/btn_ing_fib.png" class="google"></a>
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
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Fibertel Zone</title>

        <!-- If you are using CSS version, only link these 2 files, you may add app.css to use for your overrides if you like. -->
        <link rel="stylesheet" href="css/normalize.css" />
        <link rel="stylesheet" href="css/foundation.css" />

        <!-- If you are using the gem version, you need this only -->
        <link rel="stylesheet" href="css/main.css" />
        <!--[if IE 8]>
            <link rel="stylesheet" href="css/main_ie8.css" />
        <![endif]-->

        <script src="js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript">var automate_size = true;</script>
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
    </head>
    <body class="premium">

        <!-- body content here -->
        <div class="row titulo">
            <div class="large-12 columns text-center">
                <img id="img_titulo_premium" src="img/tituloBlack.png" />
            </div>
        </div>

        <div class="row vertical_middle_web">
            <div class="large-6 columns text-center column-logo">
                <img class="logo" src="img/logo.png" />
            </div>
            <div id="buttons_social" class="large-6 columns column-buttons">
                <div class="buttons buttons_black">
                    <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/loginFacebook");%>"><img src="img/facebookBlack.png" /></a>
                    <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/loginGoogle");%>"><img src="img/googleBlack.png" /></a>
                    <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/loginFibertel");%>"><img src="img/btn_ing_fib.png" /></a>
                    <br/><br/><br/><br/>
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