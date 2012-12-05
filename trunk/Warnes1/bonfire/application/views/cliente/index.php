<div class="cliente" align="center">
    <div class="title"><?php echo '<h2>' . strtoupper($cliente['nombre']) . '</h2>'; ?></div>
    <br/>
    <div class="body">
        <div>
            <div class="fotosLabel">Informaci&oacute;n:</div>
            <div class="infoCliente">
                <table border="0" cellpadding="0" cellspacing="0">
                    <?php if ($rubros != null) { ?>
                        <tr>
                            <td style="padding-bottom:5px;width: 130px" valign="top" >Rubros:</td>
                            <td style="padding-bottom:5px">
                                <?php
                                foreach ($rubros as $key => $value):
                                    if (count($rubros) == $key + 1) {
                                        echo $value['descripcion'];
                                    } else {
                                        echo $value['descripcion'] . ', ';
                                    }
                                endforeach
                                ?>
                            </td>

                        </tr>
                    <?php } ?>
                    <?php if ($marcas != null) { ?>
                        <tr>
                            <td style="padding-bottom:5px;width: 130px" valign="top" >Marcas:</td>
                            <td style="padding-bottom:5px">
                                <?php
                                foreach ($marcas as $key => $value):
                                    if (count($marcas) == $key + 1) {
                                        echo $value['descripcion'];
                                    } else {
                                        echo $value['descripcion'] . ', ';
                                    }
                                endforeach
                                ?>
                            </td>

                        </tr>
                    <?php } ?>
                    <?php if ($zonas != null) { ?>
                        <tr>
                            <td style="padding-bottom:5px;width: 130px" valign="top" >Zonas:</td>
                            <td style="padding-bottom:5px">
                                <?php
                                foreach ($zonas as $key => $value):
                                    if (count($zonas) == $key + 1) {
                                        echo $value['descripcion'];
                                    } else {
                                        echo $value['descripcion'] . ', ';
                                    }
                                endforeach
                                ?>
                            </td>

                        </tr>
                    <?php } ?>

                    <!--Rubros:  	Butacas y Asientos - Cinturones de Seguridad - Fundas - Paneles de puertas - Rellenos de Asientos - Tapicería del Automotor - Telas
                    Dirección:  	Casafoust 547
                    Barrio:  	Centro Comercial Warnes
                    Localidad:  	Ciudad de Buenos Aires
                    Provincia:  	Ciudad Autónoma de Buenos Aires
                    Tel.:  	(5411) 4588-3585
                    Id:  	247*835
                    E-mail:  	
                    Enviar E-mail
                    Web:  	http://www.warnestelas.com.ar-->

                    <?php
                    if ($cliente['direccion'] != '') {
                        echo "        <tr><td style='padding-bottom:5px;width: 130px' valign='top'>Direccion:</td><td style='padding-bottom:5px'>" . $cliente['direccion'] . "</td></tr>";
                    }
                    if ($cliente['ciudad'] != '') {
                        echo "        <tr><td style='padding-bottom:5px;width: 130px' valign='top'>Ciudad:</td><td style='padding-bottom:5px'>" . $cliente['ciudad'] . "</td></tr>";
                    }
                    if ($cliente['provincia'] != '') {
                        echo "        <tr><td style='padding-bottom:5px;width: 130px' valign='top'>Provincia:</td><td style='padding-bottom:5px'>" . $cliente['provincia'] . "</td></tr>";
                    }
                    if ($cliente['telefonodeltrabajo'] != '') {
                        echo "        <tr><td style='padding-bottom:5px;width: 130px' valign='top'>Telefono fijo:</td><td style='padding-bottom:5px'>" . $cliente['telefonodeltrabajo'] . "</td></tr>";
                    }
                    if ($cliente['telefonocelular'] != '') {
                        echo "        <tr><td style='padding-bottom:5px;width: 130px' valign='top'>Telefono celular:</td><td style='padding-bottom:5px'>" . $cliente['telefonocelular'] . "</td></tr>";
                    }
                    if ($cliente['mail'] != '') {
                        echo "        <tr><td style='padding-bottom:5px;width: 130px' valign='top'>E-mail:</td><td style='padding-bottom:5px'>" . $cliente['mail'] . "</td></tr>";
                    }
                    if ($cliente['web'] != '') {
                        echo "        <tr><td style='padding-bottom:5px;width: 130px' valign='top'>Web:</td><td style='padding-bottom:5px'><a href='http://" . $cliente['web'] . "' target='blank' >http://" . $cliente['web'] . "</a></td></tr>";
                    }
                    ?>


                </table>
            </div>
        </div>
        <div>
            <?php if ($fotos != null) { ?>
                <div class = "fotosLabel">
                    Fotos:
                </div>
                <div>
                    <?php foreach ($fotos as $foto): ?>
                        <img alt="<!?php echo $foto['nombre'] ?>" src="<?php echo $foto['url'] ?>" width="150" height="150" />
                    <?php endforeach ?>
                </div>
            <?php } ?>
        </div>
        <div class="buttonVolver">
            <A HREF="javascript:javascript:history.go(-1)">Volver</A>
        </div>
        <div class="fin">

        </div>
    </div>

</div>