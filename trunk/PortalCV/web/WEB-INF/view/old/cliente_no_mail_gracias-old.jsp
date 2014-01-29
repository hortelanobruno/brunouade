<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <script src="/js/modernizr.min.js"></script>
        <script type="text/javascript">

            function hideDiv() 
            { 
                if (Modernizr.canvas) 
                {
                }else
                {
                    if (document.getElementById) 
                    { 
                        document.getElementById('portrait').style.visibility = 'hidden'; 
                    }	 
                }

   
            }
        </script>
        <title>::: Fibertel ZONE :::</title>
        <style type="text/css">
            <!--
            .Estilo1 {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                color: #38BADA;
            }
            -->
        </style>
        <link rel="stylesheet" media="all and (orientation:portrait)" href="/css/portrait.css" />
        <link rel="stylesheet" media="all and (orientation:landscape)" href="/css/landscape.css" />
    </head>
    <body onLoad="javascript:hideDiv();">
        <div class="landscape" id="landscape">
            <div id="contenedor" style="width:100%; position:relative; top:-10px;" align="center">
                <img src="/img/fondoinicio_07.jpg" width="873" height="650" border="0" />          
            </div>
        </div>
        <div class="portrait" id="portrait">
            <div id="contenedor" style="width:100%; position:relative; top:-10px;" align="center">
                <img src="/img/vert_fondoinicio10.jpg" width="900" height="1172" border="0"/>
            </div>
        </div>
    </body>
</html>
