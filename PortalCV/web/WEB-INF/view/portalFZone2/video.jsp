<%
    String ua = request.getHeader("User-Agent").toLowerCase();

    if (ua.indexOf("blackberry") >= 0 || ua.indexOf("nokiac3") >= 0) {
%>
<html>
    <head>
        <base href="portalFZone2/" />
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
        <script>
            urlRedireccion = "<% out.print(getServletContext().getInitParameter("url").toString() + "/welcome");%>";
        </script>
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
    <body>

        <!-- body content here -->
        <div class="row row-video">
            <div class="large-12 columns text-center">
                <img src="img/txt_video.png" />
            </div>
        </div>

        <div class="row row-video-video">
            <div class="large-12 columns text-center">
                
                <div id="video_envuelto">
                    
                </div>
                
                <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="800" height="500" id="video_flash" align="middle" class="video_flash">
				<param name="movie" value="video/video.swf" />
				<param name="quality" value="high" />
				<!--<param name="bgcolor" value="#ffffff" />-->
				<param name="play" value="true" />
				<param name="loop" value="true" />
				<param name="wmode" value="window" />
				<param name="scale" value="exactFit" />
				<param name="menu" value="true" />
				<param name="devicefont" value="false" />
				<param name="salign" value="" />
				<param name="allowScriptAccess" value="sameDomain" />
				<!--[if !IE]>-->
				<object type="application/x-shockwave-flash" data="video/video.swf" width="800" height="500" class="video_flash">
					<param name="movie" value="video/video.swf" />
					<param name="quality" value="high" />
					<!--<param name="bgcolor" value="#ffffff" />-->
					<param name="play" value="true" />
					<param name="loop" value="true" />
					<param name="wmode" value="window" />
					<param name="scale" value="exactFit" />
					<param name="menu" value="true" />
					<param name="devicefont" value="false" />
					<param name="salign" value="" />
					<param name="allowScriptAccess" value="sameDomain" />
				<!--<![endif]-->
				<!--[if !IE]>-->
				</object>
				<!--<![endif]-->
			</object>
                
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
        <script type="text/javascript">
            
            var hasFlash = false;
            try {
              var fo = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
              if(fo) hasFlash = true;
            }catch(e){
              if(navigator.mimeTypes ["application/x-shockwave-flash"] != undefined) hasFlash = true;
            }
            
            if (!hasFlash){
                $("#video_envuelto").html('<video id="example_video_1" width="480" height="320" class="" controls="" autoplay=""><source src="video/video.mp4" type="video/mp4" /><source src="video/video.m4v" type="video/mp4" /><source src="video/video.webm" type="video/webm" /><source src="video/video.ogv" type="video/ogg" /><source src="video/video.mp4" /></video>');
                //$("#example_video_1").remove();
            }
                
            
            if(/Windows Phone/.test(navigator.userAgent)){
                setTimeout(function(){
                    window.location.href = "<% out.print(getServletContext().getInitParameter("url").toString() + "/welcome");%>";
                }, 20000);
            }
            
        </script>
    </body>
</html>
<% }%>