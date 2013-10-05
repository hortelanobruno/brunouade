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
                        width:1000px;
                        overflow: hidden;
                        background-color:#FFF;
                    }
                    #slider_1 img			{	
                        border:none; 
                        width:1000px; 
                        height: 280px;
                    }
                    #slider_1 ul, 
                    #slider_1 li			{	
                        margin:0;
                        padding:0;
                        list-style:none;
                        position:relative;
                        display:block;
                    }
                    #slider_1 li 			{ 	
                        width:1000px;
                        overflow:hidden; 
                    }

                    .descrip-text			{	
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
                                       /*background-color: #333333;*/  
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
                            <li><img src="assets/photos/foto1.jpg" /></li>
                            <li><img src="assets/photos/foto2.jpg" /></li>
                            <li><img src="assets/photos/foto3.jpg" /></li>
                            <li><img src="assets/photos/foto4.jpg" /></li>
                            <li><img src="assets/photos/foto5.jpg" /></li>
                        </ul>
                    </div>
                    <!--<div class="descrip-text fright w258"><p id="anistate_1"></div>-->
                </div>


            </div>

            <div class="m25">
                <div class="sfondo-grigio">
                    <!-- #left column -->
                    <div id="left_column" class="fleft pr25 pt20"> 
                        <div id="news" class="fleft">
                            <a href="http://www.facebook.com/pages/Rock-and-Cuts/253117344781216" target="blank"><img src="/assets/imgs/sponsors/pelu.jpg" border="0" style="width: 211px;"></a>
                            <a href="http://www.heladeriaselpiave.com/home.html" target="blank"><img src="/assets/imgs/sponsors/el_piave.jpg" border="0" style="width: 211px;"></a>
                            <a href="http://www.facebook.com/pages/Carita-De-Angel/321124464661501" target="blank"><img src="/assets/imgs/sponsors/carita_de_angel.jpg" border="0" style="width: 211px;"></a>
                            <a href="http://www.facebook.com/complejo.catedral?fref=ts" target="blank"><img src="/assets/imgs/sponsors/la_catedral.jpg" border="0" style="width: 211px;"></a>
                            <a href="http://www.todopasaremeras.com.ar/" target="blank"><img src="/assets/imgs/sponsors/todo_pasa.jpg" border="0" style="width: 211px;"></a>
                        </div>
                    </div> 
                    <!-- #center column -->
                    <div id="center_column" class="fleft mr25 pt20">
                        <div class="f20 lato lato900 border_light_bottom mb20 pb5">Ultimas noticias</div>
                        <?php foreach ($news as $noticia): ?>
                            <div class="news-item">
                                <!--<div class="w30 mr10 fleft"><img src="assets/uploads/pages_c7cda981b1191d6fc39480949028c130.png" width="26" /></div>-->
                                <div class="fleft wp94">
                                    <div class="titolo"><a href="/noticia/<?php echo $noticia['idnoticias'] ?>"><?php echo $noticia['titulo'] ?></a></div>
                                    <div class="descrizione"><p><?php echo $noticia['fecha'] ?></p>
                                    </div>
                                    <div style="clear:both;"></div>
                                </div>
                            </div>
                        <?php endforeach ?>
                    </div>
                </div>



                <!-- #right column -->
                <div id="right_column" class="fleft ml25 pt20">
                    <a href="/contacto" target=""><img src="/assets/imgs/commons/8-1348243968.jpg" border="0" style="width: 211px;"></a>
                    <iframe class="mb20 mt20" width="210" height="119" src="//www.youtube.com/embed/kezYcCaVCdM?autohide=1&amp;showinfo=0&amp;wmode=transparent&amp;rel=0" frameborder="0" allowfullscreen></iframe>
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
                    </div>
                    <a class="twitter-timeline" href="https://twitter.com/tfcdelsur" data-widget-id="381917573798830080">Tweets por @tfcdelsur</a>
                    <script>!function(d, s, id) {
                            var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/.test(d.location) ? 'http' : 'https';
                            if (!d.getElementById(id)) {
                                js = d.createElement(s);
                                js.id = id;
                                js.src = p + "://platform.twitter.com/widgets.js";
                                fjs.parentNode.insertBefore(js, fjs);
                            }
                        }(document, "script", "twitter-wjs");</script>
                </div>
            </div>

            <br clear="all" />
        </div>
    </div>
</div>
</div>
</div> <!-- #wrapper -->