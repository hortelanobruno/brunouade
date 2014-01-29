<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <script src="/js/modernizr.min.js"></script>
        <script src="/js/jquery.min.js" type="text/javascript"></script>
        <script src="/js/jquery.ui.js" type="text/javascript"></script>
        <script src="/js/jquery.alerts.js" type="text/javascript"></script>
        <script src="/js/cookie.js" type="text/javascript"></script>
        <script type="text/javascript">
            <%
            
                Object obj = request.getAttribute("authInvalid");
                if(obj!=null&&((Boolean)obj)){
                    out.println("eraseCookie('userAuth');");
                }
            
            %>
                
//                alert("reffee:"+document.referrer);
            createCookie('urlOrigen', document.referrer, 1);
            var userAuth = readCookie('userAuth');
            if (userAuth) {
//                alert('hay cookie '+userAuth);
//                setTimeout("window.location='<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth="+userAuth+"'",1)
                var url = '<% out.print(getServletContext().getInitParameter("url").toString() + "/validateUserAuth");%>?userAuth='+userAuth;
                window.location=url;
            }else{
//                alert('no hay cookie');
            }
                
            function Arranque()
            {
                
                if (Modernizr.canvas) 
                {
                }else
                {
                    if (document.getElementById) 
                    { 
                        document.getElementById('portrait').style.visibility = 'hidden'; 
                        document.getElementById('Label1').style.visibility = 'hidden'; 
                        document.getElementById('CheckBoxLand').style.visibility = 'visible'; 
                    }	 
                }
            }
            function terminos(URL){
                (function ($) {
                    $.alerts.okButton = 'Acepto';
                    $.alerts.cancelButton = 'No Acepto';
                    var cookie = readCookie('aceptoTerminos')
                    if (cookie) {
                        location.href = URL;
                    } else jConfirm('Para acceder a este servicio deberás aceptar los <a href="/condiciones" title="Términos y Condiciones" target="_blank"> Términos y Condiciones</a> ', 'Fibertel Zone', function (r) {
                        if (r == true) {
                            createCookie('aceptoTerminos', 'Accept', 30)
                            location.href = URL;
                        } else {
                            return false;                    
                        }
                    });
                })(jQuery);
            };
        </script> 
        <title>::: Fibertel ZONE :::</title>
        <style type="text/css">
            <!--
            .Estilo1 {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                color:#666;
                font-weight: bold;
            }
            a:link {
                text-decoration: none;
                color:#666;
            }
            a:visited {
                text-decoration: none;
                color:#666;
            }
            a:hover {
                text-decoration: none;
            }
            a:active {
                text-decoration: none;
            }
            -->
            #popup_container {
                font-family: Arial, sans-serif;
                font-size: 12px;
                min-width: 300px;
                /* Dialog will be no smaller than this */
                max-width: 600px;
                /* Dialog will wrap after this width */
                background: #e2f1f6;
                border: solid 5px #666;
                color: #000;
                -moz-border-radius: 5px;
                -webkit-border-radius: 5px;
                border-radius: 5px;
            }
            #popup_title {
                font-size: 14px;
                font-weight: bold;
                text-align: center;
                line-height: 1.75em;
                color: #FFF;
                background: #01ace1 url() top repeat-x;
                border: solid 1px #fff;
                border-bottom: solid 1px #999;
                cursor: default;
                padding: 0em;
                margin: 0em;
                -moz-border-radius: 5px;
                -webkit-border-radius: 5px;
            }
            #popup_content {
                background: 16px 16px no-repeat;
                padding: 1em 1.75em;
                margin: 0em;

            }
            #popup_content.alert {
                background-image: url();
            }
            #popup_content.confirm {
                //background-image: url();
                background-image: none;
            }
            #popup_content.prompt {
                background-image: url();
            }
            #popup_message {
                // padding-left: 48px;
            }
            #popup_panel {
                text-align: center;
                margin: 1em 0em 0em 1em;
            }
            #popup_prompt {
                margin: .5em 0em;
            }
        </style>
        <link rel="stylesheet" media="all and (orientation:portrait)" href="/css/portrait.css"/>
        <link rel="stylesheet" media="all and (orientation:landscape)" href="/css/landscape.css"/>
    </head>
    <body onload="Arranque()">
        <div class="landscape" id="landscape">
            <div id="contenedor" style="width:100%; height:650px; top:-10px; position:relative" align="center"> <img src="/img/fondoinicio_01.jpg" width="873" height="650" border="0" usemap="#Map2" /> </div>
            <map name="Map2" id="Map2">
                <area shape="rect" coords="76,454,285,566" style="display: block; cursor: pointer" onclick="terminos(<% out.println("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>)"/>
                <area shape="rect" coords="583,452,798,562" style="display: block; cursor: pointer" onclick="terminos(<% out.println("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>)"/>
            </map>
        </div>
        <div class="portrait" id="portrait">
            <div id="contenedor" style="width:100%; position:relative; top:-10px;" align="center"> <img src="/img/vert_fondoinicio01.jpg" width="900" height="1172" border="0" usemap="#Map3" />
                <map name="Map3" id="Map3">
                    <area shape="rect" coords="63,787,363,948" style="display: block; cursor: pointer" onclick="terminos(<% out.println("'" + getServletContext().getInitParameter("url").toString() + "/connectCV'");%>)"/>
                    <area shape="rect" coords="537,786,835,944" style="display: block; cursor: pointer" onclick="terminos(<% out.println("'" + getServletContext().getInitParameter("url").toString() + "/connectNOCV'");%>)"/>
                </map>
            </div>
    </body>
</html>