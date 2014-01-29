<%
    String ua = request.getHeader("User-Agent").toLowerCase();

    if (ua.indexOf("blackberry") >= 0 || ua.indexOf("nokiac3") >= 0) {
%>
<html>
    <head>
        <base href="portalFZone2_othervideo/" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="css/style.css" />
        <script>
            urlRedireccion = "<% out.print(getServletContext().getInitParameter("url").toString() + "/welcome");%>";
        </script>
    </head>
    <body>
        <div style="text-align: center">
            <br/>
            <img src="video/video.gif" style="width: 73%;" /><br/>
            <a href="<% out.print(getServletContext().getInitParameter("url").toString() + "/welcome");%>"><img src="img/botonSiguiente.png" class="google"></a>
        </div>
        <div class="footer"><img src="img/logo_fibertel_phone.png" class="footer"></div>
    </body>
</html>

<% } else {%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>
        <base href="<% out.print(getServletContext().getInitParameter("url").toString());%>/portalFZone2_othervideo/" />
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <title>Fibertel Zone</title>

        <!-- If you are using CSS version, only link these 2 files, you may add app.css to use for your overrides if you like. -->
        <link rel="stylesheet" href="css/normalize.css" />
        <link rel="stylesheet" href="css/foundation.css" />
        <script>
            urlRedireccion = "<% out.print(getServletContext().getInitParameter("url").toString() + "/welcome");%>";
        </script>
        <!-- If you are using the gem version, you need this only -->
        <link rel="stylesheet" href="css/main.css" />
        <!--[if IE 8]>
                    <link rel="stylesheet" href="css/main_ie8.css" />
                <![endif]-->
        <!--[if lt IE 8]>
                    <link rel="stylesheet" href="css/main_ie8.css" />
                <![endif]-->
        <script src="js/vendor/custom.modernizr.js"></script>
        <script type="text/javascript">var automate_size = true;</script>
        <script src="js/jquery.js"></script>
        <script src="js/mediaelement-and-player.min.js"></script>
        <link rel="stylesheet" href="css/mediaelementplayer.min.css" />
        <style>
            <!--.wrapper { width: 90% !important; min-height: 30% !important; }-->

            @media only screen and (orientation : landscape) {
                .wrapper {
                    margin-left: auto;
                    margin-right: auto;
                    max-width: 60% !important;
                    height:auto !important;
                }
            }
            @media only screen and (orientation : portrait) {
                .wrapper {
                    margin-left: auto;
                    margin-right: auto;
                    max-width: 80% !important;
                    height:auto !important;
                }
            }
        </style>
    </head>
    <body>
        <div class="row row-video">
            <div class="large-12 columns text-center"> <img src="img/txt_video.png" /> </div>
        </div>
        <div class="wrapper">
            <video id="v1" style="width: 100%; height: 100%;" poster="ftzone.png" controls="controls" preload="auto">

                <%
                    Date dNow = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("ss");
                    String segundos = ft.format(dNow);
                    Integer segundosInt = Integer.parseInt(segundos);
                    if (segundosInt % 2 == 0) {
                %>
                <source src="video/video.mp4" type="video/mp4" />

                <%} else {%>

                <source src="video/video2.mp4" type="video/mp4" />
                <%}%>

                <object style="width: 100%; height: 100%;" type="application/x-shockwave-flash" data="video/flashmediaelement.swf">
                    <param name="movie" value="video/flashmediaelement.swf" />
                    <param name="flashvars" value="controls=true&poster=logo.png&file=video/video.mp4" />
                    <img src="logo.png" width="640" height="360" title="No video playback capabilities" />
                </object>
            </video>
        </div>
        <div class="footer"> <img id="img_footer" src="img/logo_fibertel_phone.png" /> </div>
        <!-- end body content here --> 

        <script type = "text/javascript">
            document.write('<script src=' + ('__proto__' in {} ? 'js/vendor/zepto' : 'js/vendor/jquery') + '.js><\/script>')
            $(document).ready(function() {
                myplayer = new MediaElementPlayer('#v1', {
                    features: ['playpause', 'progress'],
                    translationSelector: false,
                    success: function(me) {
                        me.addEventListener('ended', function(e) {
                            window.location = "<% out.print(getServletContext().getInitParameter("url").toString() + "/welcome");%>";
                        }, false);
                    }
                });
                myplayer.play();
                old = myplayer.media.setCurrentTime; //<-- store the native setCurrentTime temporarily
                myplayer.media.setCurrentTime = function(time) { //<-- override it with our own method
                    if (time <= this.currentTime) {
                        old.apply(this, [time]); //<-- call the stored method if our conditions are met
                    }
                };
            });

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