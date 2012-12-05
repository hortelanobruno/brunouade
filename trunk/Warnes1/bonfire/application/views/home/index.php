
<div class="colmask threecol">
    <div class="colmid">
        <div class="colleft">
            <div class="col1">
                <div class="bienvenida" >
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tbody><tr>
                                <td>
                                    <p class="style2">
                                        ¡BIENVENIDOS A NUESTRO SITIO!</p>
                                    <p class="style1">Somos el nuevo portal del mundo automotor,con toda la información de Fabricantes, Distribuidores, Servicios Especializados y Marcas. Ademas de contar con Noticias de su interés y el calendario de carreras de todas las categorías Nacionales e Internacionales.<br>
                                        <br></p>
                                    <p class="style1">También puede mantenerse actualizado mediante las redes sociales y hacer sus consultas por ese medio las cuales serán resueltas a la brevedad, lo esperamos! puede seguirnos en <strong> <a target="blank"  href="https://twitter.com/#!/BambooGroupAr" class="Estilo1">TWITTER</a></strong> y <strong><a target="blank"  href="http://www.facebook.com/Bamboogroupar" class="Estilo1">FACEBOOK</a></strong>, ver videos en nuestro canal de <strong> <a target="blank"  href="http://www.youtube.com/user/bamboogroupar" class="Estilo1">YOUTUBE</a></strong>, o realizar cualquier CONSULTA mediante nuestro <strong> <a href="/contacto" class="Estilo1">MAIL</a></strong> de contacto.</p>
                                    <p class="style1">¡Lo invitamos a seguir disfrutando!</p>
                                    <span class="style4">Mundo Motorizado</span>
                                </td>
                            </tr>
                        </tbody></table>
                    <br/>
<!--                    <img alt="publicidad" src="/images/imageshome/bannerejemplo.jpg" />
                    <img alt="publicidad" src="/images/imageshome/bannerejemplo.jpg" />
                    <img alt="publicidad" src="/images/imageshome/bannerejemplo.jpg" />-->
                    <?php foreach ($banners_middle as $banner): ?>
                    <img alt="<?php echo $banner['nombre'] ?>" src="<?php echo $banner['url'] ?>" />
                <?php endforeach ?>
                </div>
            </div>
            <div class="col2">
                <?php $this->load->view('home/acordion'); ?>
            </div>
            <div class="col3">
                <?php foreach ($banners_side as $banner): ?>
                    <img alt="<?php echo $banner['nombre'] ?>" src="<?php echo $banner['url'] ?>" width="150" height="150" />
                <?php endforeach ?>
<!--                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />-->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function() {
        $( "#accordion" ).accordion({ autoHeight: false, active: 0 });
    });
</script>