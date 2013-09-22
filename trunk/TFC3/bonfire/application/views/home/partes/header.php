<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <title>TFC</title>
        <script type="text/javascript" src="/assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="/assets/js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="/assets/js/swfobject_modified.js"></script>
        <script type="text/javascript" src="/assets/js/utils.js"></script>

        <link href="/assets/css/defaultPageView.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="/assets/css/toolkit.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="/assets/css/common.css" media="screen" rel="stylesheet" type="text/css" />
        <link href='http://fonts.googleapis.com/css?family=Lato:700,900' rel='stylesheet' type='text/css'/>
        <link href="/assets/css/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>

        <style>
            html, body, #map-canvas {
                margin: 0;
                padding: 0;
                height: 100%;
            }
        </style>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script><style>
            html, body, #map-canvas {
                margin: 0;
                padding: 0;
                height: 100%;
            }
        </style>
        <script>
            function initialize() {
                var mapOptions = {
                    zoom: 15,
                    center: new google.maps.LatLng(-34.700000, -58.340000),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                }
                var map = new google.maps.Map(document.getElementById('map-canvas'),
                        mapOptions);

                var image = '/assets/imgs/commons/icons/16/star_on.png';
                var myLatLng = new google.maps.LatLng(-34.700219,-58.330389);
                var beachMarker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    icon: image,
                    title: 'Catedral I'
                });
                var image = '/assets/imgs/commons/icons/16/star_on.png';
                var myLatLng = new google.maps.LatLng(-34.694397,-58.349272);
                var beachMarker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    icon: image,
                    title: 'Catedral II'
                });
            }

            google.maps.event.addDomListener(window, 'load', initialize);

        </script>
    </head>
    <body class="claro">
        <div style="display: none" id="hideAll">&nbsp;</div>
        <div class="wrapper" id="content_wrapper_index">
            <div class="wrapper">
                <!--header-->
                <div id="header">
                    <div id="logo" class="fleft"><a href="/"><img alt="" src="/assets/imgs/commons/logo.png" /></a></div>
                    <!--menu sponsor-->
                    <div id="menu_sponsor">
                        <ul>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>	
                    </div>

                    <div class="fleft sponsor mt5 ml20" style="height: 36px;width: 600px;">
<!--                        <a class="sponsor-5 fleft mr20" title="" target="_blank" href="">SPONSOR 1</a>
                        <a class="sponsor-6 fleft mr20" title="" target="_blank" href="">SPONSOR 2</a>
                        <a class="sponsor-7 fleft mr20" title="" target="_blank" href="">SPONSOR 3</a>
                        <a class="sponsor-3 fleft mr20" title="" target="_blank" href="">SPONSOR 4</a>
                        <a class="sponsor-8 fleft mr20" title="" target="_blank" href="">SPONSOR 5</a>-->
                    </div>


                    <!--end menu sponsor-->
                    <!--menu-->
                    <div>
                        <ul id="menu" class="fleft lato lato900">
                            <li>
                                <a href="/" >Inicio</a>
                            </li>
                            <li>
                                <a href="" >Torneos</a>
                                <ul>
                                    <!--                                    <li><a href="" >Futbol 7</a></li>
                                                                        <li><a href="" >Futbol 5</a></li>-->
                                    <?php foreach ($torneos as $torneo): ?>
                                        <li><a href="/torneo/<?php echo $torneo['id'] ?>" ><?php echo $torneo['nombre'] ?></a></li>
                                    <?php endforeach ?>
                                    <li><a href="/torneos" >Torneos anteriors</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="http://www.facebook.com/TfCdelsur/photos_stream" target="_blank">Fotos</a>
                            </li>
                            <li>
                                <a href="/sedes" >Sedes</a>
                            </li>

                            <li>
                                <a href="/contacto" >Contactos</a>
                            </li>
                        </ul>
                    </div>
                    <!--end menu-->
                    <!--social-->
                    <div class="fright icosocial">
                        <a href="http://www.facebook.com/TfCdelsur" target="_blank" title="facebook" class="social-fb fleft mr5">Link a Facebook</a>
                        <a href="" target="_blank" title="twitter" class="social-twitter fleft mr5">Link a Twitter</a>
                        <a href="http://www.youtube.com/user/TFCdelsur?feature=watch" target="_blank" title="youtube" class="social-youtube fleft">Link al canal de Youtube</a>
                    </div> 
                    <div class="clearfix"></div>
                </div>
                <!--end header-->
            </div>
            <!--menu torneo-->
            <div id="menu-torneo">
                <div class="menu-torneo-sx"></div>
                <div class="menu-torneo-center">
                    <ul>
                        <?php foreach ($torneos as $torneo): ?>
                            <div class="scudetto"><img height="30" src="<?php echo $torneo['logo_chico'] ?>"/></div><li class="mr20"><a href="/torneo/<?php echo $torneo['id'] ?>"><?php echo $torneo['nombre'] ?></a></li>
                        <?php endforeach ?>
<!--                        <div class="scudetto"><img height="30" src="/assets/uploads/pages_2d5f74974a92c68008605371d23ef378.png"/></div><li class="mr20"><a href="/assets/default/tournament/view/id/38">Liga A clausura  2013</a></li>
<div class="scudetto"><img height="30" src="/assets/uploads/pages_c8c01b2fda0e0705affe90458b86478b.png"/></div><li class="mr20"><a href="/assets/default/tournament/view/id/37">Liga B clausura  2013</a></li>
<div class="scudetto"><img height="30" src="/assets/uploads/pages_36ec360629ccb7c0033847237c96a1f9.png"/></div><li class="mr20"><a href="/assets/default/tournament/view/id/36">Liga C clausura  2013</a></li>-->
                    </ul>
                </div> 
                <span class="menu-torneo-dx"></span>
            </div>
            <!--end menu torneo-->
            <!--LOGIN-->
            <div class="wrapper">
                <div class="w450 fleft tr bgwhite" style="height: 68px;width: 100%;"></div>
            </div>
            <!--end LOGIN-->