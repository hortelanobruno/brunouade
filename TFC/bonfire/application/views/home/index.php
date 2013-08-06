<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <title>TFC</title>
        
        <script type="text/javascript" src="assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="assets/js/swfobject_modified.js"></script>
        <script type="text/javascript" src="assets/js/utils.js"></script>
        
        <link href="assets/css/defaultPageView.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="assets/css/toolkit.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="assets/css/common.css" media="screen" rel="stylesheet" type="text/css" />
        <link href='http://fonts.googleapis.com/css?family=Lato:700,900' rel='stylesheet' type='text/css'/>
        <link href="assets/css/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="claro">

        <div style="display: none" id="hideAll">&nbsp;</div>
        <script type="text/javascript">
            document.getElementById("hideAll").style.display = "block";
            window.onload = function()
            {
                document.getElementById("hideAll").style.display = "none";
            }
        </script>
        <div class="wrapper" id="content_wrapper_index">
            <div class="wrapper">
                <!--header-->
                <div id="header">
                    <div id="logo" class="fleft"><a href="index.html"><img alt="" src="assets/imgs/commons/logo.png" /></a></div>
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

                    <div class="fleft sponsor mt5 ml20">
                        <a class="sponsor-5 fleft mr20" title="" target="_blank" href="">SPONSOR 1</a>
                        <a class="sponsor-6 fleft mr20" title="" target="_blank" href="">SPONSOR 2</a>
                        <a class="sponsor-7 fleft mr20" title="" target="_blank" href="">SPONSOR 3</a>
                        <a class="sponsor-3 fleft mr20" title="" target="_blank" href="">SPONSOR 4</a>
                        <a class="sponsor-8 fleft mr20" title="" target="_blank" href="">SPONSOR 5</a>
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
                                    <li><a href="" >Futbol 7</a></li>
                                    <li><a href="" >Futbol 5</a></li>
                                    <li><a href="" >Torneos anteriors</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="" target="_blank">Fotos</a>
                            </li>
                            <li>
                                <a href="" >Sedes</a>
                            </li>

                            <li>
                                <a href="" >Contactos</a>
                            </li>
                        </ul>
                    </div>
                    <!--end menu-->
                    <!--social-->
                    <div class="fright icosocial">
                        <a href="" target="_blank" title="facebook" class="social-fb fleft mr5">Link a Facebook</a>
                        <a href="" target="_blank" title="twitter" class="social-twitter fleft mr5">Link a Twitter</a>
                        <a href="" target="_blank" title="youtube" class="social-youtube fleft">Link al canal de Youtube</a>
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
                        <div class="scudetto"><img height="30" src="assets/uploads/pages_2d5f74974a92c68008605371d23ef378.png"/></div><li class="mr20"><a href="assets/default/tournament/view/id/38">Liga A clausura  2013</a></li>
                        <div class="scudetto"><img height="30" src="assets/uploads/pages_c8c01b2fda0e0705affe90458b86478b.png"/></div><li class="mr20"><a href="assets/default/tournament/view/id/37">Liga B clausura  2013</a></li>
                        <div class="scudetto"><img height="30" src="assets/uploads/pages_36ec360629ccb7c0033847237c96a1f9.png"/></div><li class="mr20"><a href="assets/default/tournament/view/id/36">Liga C clausura  2013</a></li>
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
            <div class="wrapper bgwhite">
                <div id="content">
                    <div id="content" class="bg-grigio mb20 pb220">
                        <div class="bglight border_light_bottom">
                            <div class="m15">
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="w1000">
                            <script language="javascript" type="text/javascript" src="assets/js/jquery.sudoSlider.min.js"></script>
                            <script language="javascript" type="text/javascript">
                                $(document).ready(function() {
                                    init_slideshow_1();
                                });
                                function init_slideshow_1() {
                                    $('#slider_1').wrap('<a id="link_slider_1" />');
                                    var oldt = 0;
                                    var urls = {"1": "", "2": "", "3": "", "4": "", "5": "http:\/\/www.facebook.com\/brunolidewilde"};
                                    var titles = {"1": "BANNER1", "2": "BANNER2", "3": "BANNER3", "4": "BANNER4", "5": "BANNER5"};
                                    var descriptions = {"1": "DESC1", "2": "DESC2", "3": "DESC3", "4": "DESC4", "5": "DESC5"};
                                    $("#slider_1").sudoSlider({
                                        beforeAniFunc: function(t) {
                                            var width = $(this).width();
                                            var diff = Math.sqrt(Math.abs(oldt - t));
                                            var speed = parseInt(diff * 800);

                                            $('.nextBtn').animate(
                                                    {left: width},
                                            {
                                                queue: false,
                                                duration: speed
                                            }
                                            );
                                            oldt = t;
                                        },
                                        fade: true,
                                        vertical: false,
                                        speed: 5000,
                                        pause: 3000,
                                        auto: true,
                                        continuous: true,
                                        numeric: true,
                                        controlsShow: true,
                                        controlsAttr: 'id="controls_1"',
                                        prevNext: false,
                                        beforeAnyFync: function() {
                                        },
                                        currentFunc: function(t) {
                                            title = titles[t];
                                            description = descriptions[t];
                                            url = urls[t];
                                            html_def = '';

                                            if (url) {
                                                $('#link_slider_1').removeAttr('nohref').attr('href', url);
                                            } else {
                                                $('#link_slider_1').removeAttr('href').attr('nohref', 'nohref');
                                            }


                                            if (url)
                                                html_def = '<a href="' + url + '">';
                                            html_def += '<h1>' + title + '</h1><br /><h2>' + description + '</h2>';
                                            if (url)
                                                html_def += '</a>';

                                            $('div.descrip-text #anistate_1').fadeOut(100, function() {
                                                $('div.descrip-text #anistate_1').html(html_def);
                                            });

                                            $('div.descrip-text #anistate_1').fadeIn(200);
                                        },
                                        uncurrentFunc: function(t) {
                                        },
                                    });
                                }
                            </script>

                            <style type="text/css">
                                .graphic, .prevBtn, .nextBtn		{	margin:0;padding:0;display:block;overflow:hidden;text-indent:-8000px;	}
                                .prevBtn, .nextBtn 					{	display:block;
                                                          width:30px;
                                                          height:77px;
                                                          position:absolute;
                                                          left:-30px;
                                                          top:100px;
                                                          z-index:1000;
                                                          background:url(assets/imgs/slider/btn_prev.gif) no-repeat 0 0;
                                                          cursor:pointer;
                                }	
                                .nextBtn							{	left:742px;	}														
                                .nextBtn 							{	background:url(assets/imgs/slider/btn_next.gif) no-repeat 0 0;	}

                                #slider_1 				{
                                    z-index: 1;
                                    width:742px;
                                    overflow: hidden;
                                    background-color:#FFF;
                                }
                                #slider_1 img			{	border:none; 
                                                  width:742px; 
                                                  height: 280px;
                                }
                                #slider_1 ul, 
                                #slider_1 li			{	margin:0;
                                                 padding:0;
                                                 list-style:none;
                                                 position:relative;
                                                 display:block;
                                }
                                #slider_1 li 			{ 	width:742px;
                                                   overflow:hidden; 
                                }

                                .descrip-text						{	
                                    z-index:9999;  
                                    height: 260px;	
                                    background-color: #333333;


                                }

                                #anistate_1			{	margin: 0;
                                                text-align:left;
                                                margin:5px;	
                                }
                                #anistate_1 h1			{	color: #FFF;
                                                   font-size:2.4em;
                                }

                                #anistate_1 h2			{	color: #FFF;
                                                   font-size:1.8em;
                                                   font-weight:normal;
                                                   margin:0;
                                }

                                #controls_1 			{	
                                    width: 258px;
                                    margin: 0 auto;
                                    position: absolute;
                                    bottom: 0;
                                    right: 0;
                                    z-index:9999;  

                                }

                                #controls_1 ol 		{	text-align:center;
                                                   height: 30px;
                                                   background-color: #333333;  
                                }									

                                #controls_1 li 		{	display: inline-block;
                                                   *display: inline;
                                                   zoom: 1;
                                                   padding: 0 5px 0 0 ;
                                }

                                #controls_1 li a		{	background: none repeat scroll 0 0 #D0D0D0;
                                                    border-radius: 20px 20px 20px 20px;
                                                    box-shadow: 0 0 3px rgba(0, 0, 0, 0.3) inset;
                                                    cursor: pointer;
                                                    display: block;
                                                    height: 11px;
                                                    text-indent: -9999px;
                                                    width: 11px;
                                }
                                #controls_1 li.current a{	background: none repeat scroll 0 0 #009EDE;	}

                            </style>

                            <div class="pr" style="min-height: 280px">
                                <div id="slider_1" class="fleft">
                                    <ul>
                                        <li><img src="assets/uploads/slider_39a6f5b52f459a10f82cd1488fe74aff.jpg" /></li>
                                        <li><img src="assets/uploads/slider_7cf0feb9f4fd56fc2f60be44f749bd74.jpg" /></li>
                                        <li><img src="assets/uploads/slider_706771c3abe79be0c53c0417e9a22405.jpg" /></li>
                                        <li><img src="assets/uploads/slider_db2530a9b7d9061145790c50219ca549.jpg" /></li>
                                        <li><img src="assets/uploads/slider_0a664c5470100063863dcbef3268f15c.jpg" /></li>
                                    </ul>
                                </div>
                                <div class="descrip-text fright w258"><p id="anistate_1"></div>
                            </div>


                        </div>

                        <div class="m25">
                            <div class="sfondo-grigio">
                                <!-- #left column -->
                                <div id="left_column" class="fleft pr25 pt20"> 
                                    <div id="news" class="fleft">
                                        <div class="f20 lato lato900 border_light_bottom mb20 pb5">Ultimas noticias</div>
                                        <div class="news-item">
                                            <div class="w30 mr10 fleft"><img src="assets/uploads/pages_c7cda981b1191d6fc39480949028c130.png" width="26" /></div>
                                            <div class="fleft wp94">
                                                <div class="titolo"><a href="">TITULO1</a></div>
                                                <div class="descrizione"><p><strong><em>MENSAJE1</em></strong></p> </div>
                                                <div style="clear:both;"></div>
                                            </div>
                                        </div>
                                        <div class="news-item">
                                            <div class="w30 mr10 fleft"><img src="assets/uploads/pages_6a7704665d07978123d4c9f151c65a0d.png" width="26" /></div>
                                            <div class="fleft wp94">
                                                <div class="titolo"><a href="">TITULO2</a></div>
                                                <div class="descrizione"><p><em>MENSAJE2</em></p> </div>
                                                <div style="clear:both;"></div>
                                            </div>
                                        </div>
                                        <div class="news-item">
                                            <div class="w30 mr10 fleft"><img src="assets/uploads/pages_8f456623f3dba14e3cf6569487dbdab6.png" width="26" /></div>
                                            <div class="fleft wp94">
                                                <div class="titolo"><a href="">TITULO3</a></div>
                                                <div class="descrizione"><p><strong></strong>MENSAJE3</p>
                                                    </div>
                                                <div style="clear:both;"></div>
                                            </div>
                                        </div>
                                        <div class="news-item">
                                            <div class="w30 mr10 fleft"><img src="assets/uploads/pages_c7cda981b1191d6fc39480949028c130.png" width="26" /></div>
                                            <div class="fleft wp94">
                                                <div class="titolo"><a href="">TITULO4</a></div>
                                                <div class="descrizione"><p>MENSAJE4</p>
                                                   </div>
                                                <div style="clear:both;"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div> 

                                <!-- #center column -->
                            </div>



                            <!-- #right column -->
                            <div id="right_column" class="fleft ml25 pt20">
                                <a href="default/availability.html" class="btn_prenota mb20"></a>
                                <iframe class="mb20" width="210" height="119" frameborder="0" allowfullscreen="" src="https://www.youtube.com/embed/x29GEUjAlTk?autohide=1&amp;showinfo=0&amp;wmode=transparent&amp;rel=0"></iframe>
                                <div id="news" class="fleft">
                                    <div class="f20 lato lato900 border_light_bottom mb20 pb5">Proxima partida</div>
                                    <div class="news-item clearfix mb10">
                                        <div class="w30 mr10 fleft"><img src="assets/uploads/pages_c7cda981b1191d6fc39480949028c130.png" width="26" /></div>
                                        <div class="fleft w170">
                                            <div class="titolo"><a href="">El selectivo - Wilde</a></div>
                                            <div class="descrizione">31/10/2013 22:00 </div>
                                        </div>
                                    </div>
                                    <div style="clear:both;"></div>
<!--                                    <div>
                                        <a class="twitter-timeline" data-dnt="true" href="https://twitter.com/OfficialArend" data-widget-id="265814157817942016">Tweets di @OfficialArend</a>
                                        <script>!function(d, s, id) {
                                                var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/.test(d.location) ? 'http' : 'https';
                                                if (!d.getElementById(id)) {
                                                    js = d.createElement(s);
                                                    js.id = id;
                                                    js.src = p + "://platform.twitter.com/widgets.js";
                                                    fjs.parentNode.insertBefore(js, fjs);
                                                }
                                            }(document, "script", "twitter-wjs");</script>
                                    </div>-->
                                </div>
                            </div>
                        </div>

                        <br clear="all" />
                    </div>
                </div>
            </div>
        </div>
        </div> <!-- #wrapper -->

        <div class="clearfix"></div>
        <!--footer-->
        <div id="footer">
            <div class="wrapper">
                <div class="wp40 fleft">
                    <p>
                        2013 TFC <span class="grey">|</span> TFC <span class="grey">|</span>			
                    </p>
                </div>
                <div class="wp60 fright">  
                    <ul id="footer_links" class="fright">
                        <li class="first"><a href="/">Home</a></li>
                        <li><a href="">Torneos</a></li>
                        <li><a href="">Fotos</a></li>
                        <li><a href="">Sedes</a></li>
                        <li class="last"><a href="">Contactos</a></li>
                    </ul>
                </div>
                <div class="fright mt10">
                    <a href="" target="_blank" title="facebook" class="social-fb fleft mr5">Link a Facebook</a>
                    <a href="" target="_blank" title="twitter" class="social-twitter fleft mr5">Link a Twitter</a>
                    <a href="" target="_blank" title="youtube" class="social-youtube fleft">Link al canal de Youtube</a>
                </div> 
            </div>
        </div>
        <!--end footer-->
    </body>
</html>
