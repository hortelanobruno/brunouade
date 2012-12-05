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