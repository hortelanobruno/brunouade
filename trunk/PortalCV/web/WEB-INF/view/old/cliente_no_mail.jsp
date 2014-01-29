<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <script src="/js/modernizr.min.js"></script>
        <script src='/js/jquery.min.js' type='text/javascript' ></script>
        <script src="/js/jquery.ui.js" type="text/javascript"></script>
        <script src="/js/jquery.alerts.js" type="text/javascript"></script>
        <script src="/js/cookie.js" type="text/javascript"></script>
        <script type="text/javascript">
            var a = 0;
            function focusText()
            {document.getElementById('usremail').focus();}
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
                {
                    var cookie = readCookie('aceptoTerminos')
                    if (cookie) {
                        return;
                    } else location.href = <% out.println("'" + getServletContext().getInitParameter("url").toString() + "'"); %>;
                };
            }
            function register(){ 
                if( a == 0){
                    if (document.form.usremail.value!=''&&validateEmail(document.form.usremail.value)){
                        document.forms["form"].submit();
                    }else{
                        alert('Direccion de correo invalida.');
                    }
                }else{
                    if (document.form2.usremail.value!=''&&validateEmail(document.form2.usremail.value)){
                        document.forms["form2"].submit();
                    }else{
                        alert('Direccion de correo invalida.');
                    }
                }
            }
            function validateEmail(email) {
                var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                if( !emailReg.test( email ) ) {
                    return false;
                } else {
                    return true;
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
    <body onLoad="javascript:hideDiv(); javascript:focusText();">
        <div class="landscape" id="landscape">
            <div id="contenedor" style="width:100%; position:relative; top:-10px;" align="center"> <img src="/img/fondoinicio_03.jpg" width="873" height="650" border="0" usemap="#Map" />
                <div id="email" style="position:relative;left:135px; bottom:338px; width:220px; z-index:1">
                    <form id="form" name="form" method="post" action="/registernocv" >
                    <input name="usremail" id="usremail" type="email" style="width:220px; border-color:transparent; position:relative; z-index:1; float:left; height:19.7px; background-color:transparent; border:auto;"/>
                    </form>
                </div>
            </div>
            <map name="Map" id="Map">
                <area shape="rect" coords="709,310,825,341" style="display: block; cursor: pointer" onclick="register();" alt="ingresar" />
                <area shape="rect" coords="459,351,706,381" style="display: block; cursor: pointer" href="/loginNOCVFacebook" alt="ingresar"   />
            </map>
        </div>
        <div class="portrait" id="portrait">
            <div id="contenedor" style="width:100%; position:relative; top:-10px;" align="center"> <img src="/img/vert_fondoinicio03.jpg" width="900" height="1172" border="0" usemap="#Map2" /></div>
            <div id="email" style="position:relative;left:545px; bottom:569px; width:300px; z-index:1">
                <form id="form2" name="form2" method="post" action="/registernocv" >
                <input name="usremail" id="usremail" type="email" style="width:100%; border-color:transparent; position:relative; z-index:1; float:left; height:19.7px; background-color:transparent; border:auto;"/>
                </form>
            </div>
            <map name="Map2" id="Map2">
                <area shape="rect" coords="670,651,835,697" style="display: block; cursor: pointer" onclick="register();" alt="ingresar" />
                <area shape="rect" coords="507,714,833,758" style="display: block; cursor: pointer" href="/loginNOCVFacebook" alt="ingresar"/>
            </map>
        </div>
    </body>
</html>
