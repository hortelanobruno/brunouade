<?php echo "<script type='text/javascript'>" ?>

<?php echo "var activeId = '0';
    var lCont = 0;
	
    $(document).ready(function() {
        var tab= 0;		if(tab==1)
        {
            switchFeature(0);
        }	 
    });" ?>

<?php echo "var arrayf = new Array(" ?>
<?php foreach ($fotos as $foto): ?>
    <?php echo "'" . $foto['url'] . "'," ?>
<?php endforeach ?>
<?php echo "'');" ?>
<?php echo "var arrayt = new Array(" ?>
<?php foreach ($fotos as $foto): ?>
    <?php echo "'" . $foto['titulo'] . "'," ?>
<?php endforeach ?>
<?php echo "'');" ?>

<?php echo " var arrayv = new Array('');var arrayvt = new Array('');var arrayvfoto = new Array('');	
    function showTest()
    {
        Modalbox.show('fichatech.php?id=836'); return false;
    }
	
	
    function slideImgs(dir)
    {
		
        if(arrayf==undefined)
        {
            return;
        }
        var lImg = 0;
        if(dir==1){if((lCont + 5)>=arrayf.length-1)return;lCont=lCont+5;}
        if(dir==0){if(lCont<=0)return;lCont=lCont-5;}
		
        for(var i = lCont; i < arrayf.length;i++)
        {
            if(arrayf[i]!=''){
                document.getElementById('imgthumb' + lImg).style.display='';
                document.getElementById('imgthumb' + lImg).src = '' + arrayf[i];
                document.getElementById('imgthumb' + lImg).title = arrayt[i];
            }
            else{
                document.getElementById('imgthumb' + lImg).style.display='none';
            }
            lImg++;	
            if(lImg>=5)
                break;			
        }	
    }

	
    function hideImgs()
    {
        for(var i = 0; i < arrayf.length-1;i++)
        {
            if(arrayf[i]!='')
            {
                document.getElementById('imgthumb' + lImg).style.display='none';

            }
        }	
    }
	
    function showImg(Id)
    {
        if(activeId !='')
        {
            $('#l' + activeId).removeClass('factive');
        }
			
        document.getElementById('mainImg').src = document.getElementById('imgthumb' + Id).src + '';
        $('#l' + Id).addClass('factive');
        activeId = Id;
        $('#fotocopete p').text(document.getElementById('imgthumb' + Id).title);
    }	" ?>
<?php echo "</script>" ?>


<div class="content">
    <div class="noticia-header-marco">
        <div class="noticia-header" style="margin-bottom:20px; background:#e0dfdf">
            <div class="noticia-categoria-holder">
                <div style="margin-top:8px;margin-left:5px;float:left;">
                    <?php echo $noticia['fechanoticia'] ?>			</div>
                <div class="noticia-categoria" style="background-color:#e0dfdf">
                    <a href="#" class="categoria">Noticia</a> </div>
                <div class="clear"></div>
            </div><!-- noticia-categoria-holder -->


            <div class="red-ribbon main-ribbon"></div>
            <div class="noticia-titulo">
                <span style="font-size:36px" class="titulos" id="mainTitle"><?php echo $noticia['titulo'] ?></span>
                <div style="margin-top:6px;"><?php echo $noticia['epigrafe'] ?></div>
            </div>
        </div>
    </div>
    <div>
        <div id="left-col">
            <!-- NOTICIA -->
            <div class="noticia-wrapper interna">
                <div class="noticia interna">
                    <div class="noticia-content">
                        <div id="noticia-video-selector">
                            <div class="noticia-btn"><img id="imgBTN" src="/images/noticias/fotos-on-btn.png" onclick="switchFeature(1);"><img id="videoBTN" src="/images/noticias/voffbtn.png" onclick="switchFeature(0);" style="display: none; "></div>
                            <div id="imageVisor" class="noticia-imagen">
                                <div id="fotocopete">
                                    <p><?php echo $fotos[0]['titulo'] ?></p>		
                                </div>								
                                <img id="mainImg" width="578" height="434" src="<?php echo $fotos[0]['url'] ?>">

                            </div>
                            <div id="selector-footer">

                                <div id="imageSlider">
                                    <a class="selector-flecha" href="javascript:void(0);" onclick="javascript:slideImgs(0);"></a>
                                    <?php $count = 0;
                                    
                                        while ($count<5 && count($fotos) > $count){
                                            if($count==0){
                                                echo "<div class='selector-thumb'><a id='l0' onclick='showImg(".$count.");' href='javascript:void(0);' class='factive'><img style='' id='imgthumb0' width='96px' height='71px' src='".$fotos[$count]['url']."' title='".$fotos[$count]['titulo']."'></a></div>";
                                            }else{
                                                echo "<div class='selector-thumb'><a id='l0' onclick='showImg(".$count.");' href='javascript:void(0);'><img id='imgthumb".$count."' style='' id='imgthumb0' width='96px' height='71px' src='".$fotos[$count]['url']."' title='".$fotos[$count]['titulo']."'></a></div>";
                                            }
                                            $count = $count + 1;
                                        }
                                    
                                    ?>
                                 
                                    <a class="selector-flecha fderecha" href="javascript:void(0);" onclick="javascript:slideImgs(1);"></a>
                                </div>

                            </div>
                        </div>		


<!--                        <p></p><p class="MsoNormal"><span style="font-family: Arial;">En una nota anterior de nuestros corresponsales en Brasil les habíamos mostrado unos renders de lo que imaginábamos podría llegar a ser el Chevrolet Spin, un vehículo eminentemente familar. Unos meses después ya podemos ver imágenes oficiales,&nbsp; porque General Motors ya lo presentó en ese país.</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">&nbsp;</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">El Spin es un “vehículo multipropósito” (MPV), según clasificación oficial, con doble configuración de butacas, porque albergará a cinco personas o, con una tercera fila de asientos, a siete pasajeros. De ahí que su nombre en código del proyecto haya sido PM7.</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">&nbsp;</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">Está fabricado en la planta paulista de San Caetano del Sur sobre la plataforma Gamma II, la misma del Cobalt y del futuro Enjoy, producto que competirá en el segmento dominado por el Ford Ecosport. Es un producto enteramente desarrollado en Brasil por el equipo de Diseño liderado por Carlos Barba y probado en cuatro continentes.</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">&nbsp;</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">Dadas sus características, esta novedad reemplazará al mismo tiempo a dos productos, Meriva y Zafira, en una estrategia de conquistar el gusto de públicos similares.</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">&nbsp;</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">El Spin, que a la Argentina estará llegando hacia fin de año, está propulsado por el conocido 1.8 naftero de cuatro cilindros en línea y ocho válvulas que entrega 106 caballos de potencia. Tiene un torque de 16 kgm con una curva bien amplia: el 90 por ciento está entre 2.500 y 4.700 r.p.m. </span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">&nbsp;</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">Esa planta impulsora está adosada a dos opciones de transmisión. Una es manual de cinco marchas y la otra es la automática de seis ya incorporada en productos anteriores, como el Cruze y el Sonic.</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">&nbsp;</span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">General Motors Brasil informa que viene con dos niveles de equipamiento (LT y LTZ), y de serie incorpora ABS con EBD, doble airbag y alzacristales eléctricos, entre otros elementos. El tope de gama será la opción de siete plazas, que obviamente tiene un espacio de carga reducido. Un dato de color y curioso es que acepta como máximo un total de 23 configuraciones diferentes de asientos. </span></p>
                        <p class="MsoNormal"><span style="font-family: Arial;">&nbsp;</span></p><p></p>-->
<?php echo $noticia['descripcion'] ?>
                        <div>
                            <table>
                                <tr>
                                    <td style="width: 490px;">
                                        <a href="javascript:javascript:history.go(-1)" >Volver</a>    
                                    </td>
                                    <td>
                                        <div id="print-this">
                                            <a href="javascript:void(0)" onclick="window.print();">Imprimir esta nota</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                    </div>



                </div><!-- noticia -->
            </div>
            <!-- noticia-wrapper -->
            <!-- FIN NOTICIA -->
            <div class="clear"></div>
        </div>
    </div>
</div>
