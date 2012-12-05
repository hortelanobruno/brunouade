<div class="colmask threecol">
    <div class="colmid">
        <div class="colleft">
            <div class="col5">
                <div class="noticiascuadro">
                    <h3>Archivo</h3>
                    <ul>
                         <?php foreach ($archivos as $archivo): ?>
                <li><a href="/noticias/archivo/<?php echo $archivo['mes'] ?>-<?php echo $archivo['anio'] ?>" title="<?php echo $archivo['mes'] ?> <?php echo $archivo['anio'] ?>"><?php echo $archivo['mes'] ?> <?php echo $archivo['anio'] ?></a></li>
                <?php endforeach ?>
                        
<!--                        <li><a href="http://leahyetter.wordpress.com/2012/06/" title="June 2012">June 2012</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2012/05/" title="May 2012">May 2012</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2012/04/" title="April 2012">April 2012</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2012/03/" title="March 2012">March 2012</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2012/02/" title="February 2012">February 2012</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2012/01/" title="January 2012">January 2012</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/12/" title="December 2011">December 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/11/" title="November 2011">November 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/10/" title="October 2011">October 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/09/" title="September 2011">September 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/08/" title="August 2011">August 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/07/" title="July 2011">July 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/06/" title="June 2011">June 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/05/" title="May 2011">May 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/04/" title="April 2011">April 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/03/" title="March 2011">March 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/02/" title="February 2011">February 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2011/01/" title="January 2011">January 2011</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/12/" title="December 2010">December 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/11/" title="November 2010">November 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/10/" title="October 2010">October 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/09/" title="September 2010">September 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/08/" title="August 2010">August 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/07/" title="July 2010">July 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/06/" title="June 2010">June 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/05/" title="May 2010">May 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/04/" title="April 2010">April 2010</a></li>
                        <li><a href="http://leahyetter.wordpress.com/2010/03/" title="March 2010">March 2010</a></li>-->
                    </ul>
                </div>
<!--                <div class="noticiascuadro">

                </div>-->
            </div>
            <div class="col4">
                <?php foreach ($noticias as $noticia): ?>
                <div class="noticia-wrapper">
                    <div class="noticia" style="height:410px;">
                        <div class="noticia-header">
                            <div class="noticia-categoria-holder"> 
                                <div style="margin-top:8px;margin-left:5px;float:left;"></div>						
                                <div class="noticia-categoria">
                                    <a href="" class="categoria">Noticia</a> </div>
                                <div class="clear"></div>
                            </div><!-- noticia-categoria-holder -->
                            <div class="red-ribbon"></div>
                        </div><!-- noticia-header -->

                        <div class="noticia-titulo">
                            <a href="/noticias/<?php echo $noticia['idnoticia'] ?>" class="titulos"><?php echo $noticia['titulo'] ?></a>
                        </div>
                        <div class="noticia-thumb">
                            <a href="/noticias/<?php echo $noticia['idnoticia'] ?>"><img width="260px" height="195px" src="<?php echo $noticia['url'] ?>"></a>
                        </div>
                        <div class="noticia-bajada texto"><a href="/noticias/<?php echo $noticia['idnoticia'] ?>"><?php echo $noticia['epigrafe'] ?></a></div>
                    </div><!-- noticia -->
                </div>
                <?php endforeach ?>
                
                
            </div>
            <div class="col6">
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
            </div>
        </div>
    </div>
</div>