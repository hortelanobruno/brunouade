<style type="text/css">
    .titulo_16_black {
        background: url(/assets/imgs/table/bg_fecha_noticias.jpg);
        height: 32px;
        width: 620px;
        line-height: 32px;
        color: white;
        padding-left: 10px;
        font-size: 14px;
        font-weight: bold;
    }

    .fecha_activa {
        color: #39acd7;
        font-weight: bold;
        font-size: 14px;
    }

    .pointer {
        cursor: pointer;
    }
</style>

<?php if ($fixture != null) { ?>
    <div class="titulo_16_black">FIXTURE</div>
    <div class="borde_gris" style="background-color:#f2f2f2; float:left; width:628px;">
        <br clear="all">
        <div id="fixture_zona_192">

            <div class="fechas" style="margin-left:12px; font-size:12px;"><span style="float:left; margin:5px 0px; ">Fechas :</span>
                <ul style="float:left; list-style:none; margin:5px 0px;" id="192">
                    <?php if ($torneo['categoria'] == 1) { ?>
                        <?php for ($i = 1; $i <= $torneo['cantidad_fechas']; $i++): ?>
                            <?php if ($fechaelegida == $i) { ?>
                                <li style="float:left; margin:0px 5px;" class="fechas_menu pointer fecha_activa" onclick="seleccionarFecha($(this),<?php echo $i ?>)"><?php echo $i ?></li>
                            <?php } else { ?>
                                <li style="float:left; margin:0px 5px;" class="fechas_menu pointer" onclick="seleccionarFecha($(this),<?php echo $i ?>)"><?php echo $i ?></li>
                            <?php } ?>
                        <?php endfor ?>
                    <?php } else if ($torneo['categoria'] == 2){ ?>
                        <?php for ($i = 1; $i <= $torneo['cantidad_fechas']; $i++): ?>
                            <?php if ($fechaelegida == $i) { ?>
                                <li style="float:left; margin:0px 5px;" class="fechas_menu pointer fecha_activa" onclick="seleccionarFecha($(this),<?php echo $i ?>)"><?php echo printFechaCopa($i,$torneo['cantidad_fechas']) ?></li>
                            <?php } else { ?>
                                <li style="float:left; margin:0px 5px;" class="fechas_menu pointer" onclick="seleccionarFecha($(this),<?php echo $i ?>)"><?php echo printFechaCopa($i,$torneo['cantidad_fechas']) ?></li>
                            <?php } ?>
                        <?php endfor ?>
                    <?php } ?>

                </ul>
            </div>
            <br clear="all">
            <br clear="all">
            <table border="0" cellpadding="0" cellspacing="0" width="100%" id="zona_192">
                <tbody><tr style="height:30px;">
                        <th width="20">&nbsp;</th>
                        <th width="120">Local</th>
                        <th width="20">&nbsp;</th>
                        <th width="20">&nbsp;</th>
                        <th width="20">&nbsp;</th>
                        <th width="20">&nbsp;</th>
                        <th width="120">Visitante</th>
                        <th>Sede</th>
                        <th>Horario</th>
                        <th></th>
                        <th></th>
                    </tr>
                </tbody>
                <?php foreach ($fixture as $partido): ?>
                    <tbody class="tabla_fecha fecha_<?php echo $partido['fecha_torneo'] ?>" style="display: <?php echo ($fechaelegida == $partido['fecha_torneo'] ? "table-row-group" : "none") ?>;">
                        <tr style="height:30px;" align="center">
                            <td>
                            </td><td align="left">
                                <a href="/equipo/<?php echo $partido['idequipo1'] ?>" class="lg"><?php echo getEquipo($equipos, $partido['idequipo1']) ?></a>
                            </td>
                            <td>
                            </td><td><strong><?php echo getGoles($partido['goles_equipo1']) ?></strong></td>
                            <td><strong><?php echo getGoles($partido['goles_equipo2']) ?></strong></td>
                            <td>
                            </td><td align="left">
                                <a href="/equipo/<?php echo $partido['idequipo2'] ?>" class="lg"><?php echo getEquipo($equipos, $partido['idequipo2']) ?></a>
                            </td>
                            <td><?php echo getSede($sedes, $partido['idsede']) ?></td>
                            <td><?php echo $partido['fecha'] ?></td>
                            <td>
                                <?php
                                if ($partido['jugado'] == true) {
                                    echo "Jugado";
                                } else {
                                    echo "Por jugar";
                                }
                                ?>

                            </td>
                            <td>
                                <?php if ($partido['jugado'] == true) { ?>
                                    <a href="/torneo/<?php echo $partido['idtorneo'] ?>/partido/<?php echo $partido['id'] ?>" >
                                        <img src="/assets/imgs/table/ver_ficha.jpg" border="0">
                                    </a>
                                <?php } ?>
                            </td>
                        </tr>
                    </tbody>

                <?php endforeach ?>

                                                                                                                        <!--                <tbody class="fecha_8_192 partidos_192" style="display: table-row-group;">
                                                                                                                            <tr style="height:30px; background-color:;" align="center">
                                                                                                                                <td>
                                                                                                                                </td><td align="left">
                                                                                                                                    <a href="
                                                                                                                                       http://www.edebafutbol.com.ar/equipo/48/DE-LA-GORRA" class="lg">DE LA GORRA</a>
                                                                                                                                </td>
                                                                                                                                <td>
                                                                                                                                </td><td><strong>0</strong></td>
                                                                                                                                <td><strong>0</strong></td>
                                                                                                                                <td>
                                                                                                                                </td><td align="left">
                                                                                                                                    <a href="http://www.edebafutbol.com.ar/equipo/167/ROSSONERO" class="lg">ROSSONERO</a>
                                                                                                                                </td>
                                                                                                                                <td></td>
                                                                                                                                <td></td>
                                                                                                                                <td>Por jugar</td>
                                                                                                                            </tr>
                                                                                                                        </tbody>

                                                                                                                        <tbody class="fecha_9_192 partidos_192" style="display: none;">
                                                                                                                            <tr style="height:30px; background-color:#FFF;" align="center">
                                                                                                                                <td>
                                                                                                                                </td><td align="left">
                                                                                                                                    <a href="
                                                                                                                                       http://www.edebafutbol.com.ar/equipo/76/HELPY" class="lg">HELPY</a>
                                                                                                                                </td>
                                                                                                                                <td>
                                                                                                                                </td><td><strong>0</strong></td>
                                                                                                                                <td><strong>0</strong></td>
                                                                                                                                <td>
                                                                                                                                </td><td align="left">
                                                                                                                                    <a href="http://www.edebafutbol.com.ar/equipo/54/LA-MAQUINA-F.C" class="lg">LA MAQUINA F.C.</a>
                                                                                                                                </td>
                                                                                                                                <td></td>
                                                                                                                                <td></td>
                                                                                                                                <td>Por jugar</td>
                                                                                                                            </tr>
                                                                                                                        </tbody>-->

            </table>
        </div>
        <br clear="all">  

    </div>
<?php } else { ?>
    No hay informacion de los partidos
<?php } ?>





<?php

function getEquipo($equipo, $idequipo) {
    if (isset($equipo)) {
        foreach ($equipo as $equipo) {
            if ($equipo['id'] === $idequipo)
                return $equipo['nombre'];
        }
    }else {
        return $idequipo;
    }
}

function getGoles($goles) {
    if (empty($goles)) {
        return 0;
    }
    return $goles;
}

function getSede($sedes, $idsede) {
    if (isset($sedes)) {
        foreach ($sedes as $sede) {
            if ($sede['idsede'] === $idsede)
                return $sede['nombre'];
        }
    }else {
        return $idsede;
    }
}

function printFechaCopa($i,$cant_fechas){
    if($cant_fechas==1){
        if($i==1){
            return 'Final';
        }
    } else if($cant_fechas==2){
        if($i==2){
            return 'Final';
        } else if($i==1){
            return 'Semifinal';
        }
    } else if($cant_fechas==3){
        if($i==3){
            return 'Final';
        }else if($i==2){
            return 'Semifinal';
        } else if($i==1){
            return 'Cuartos';
        }
    } else if($cant_fechas==4){
        if($i==4){
            return 'Final';
        }else if($i==3){
            return 'Semifinal';
        } else if($i==2){
            return 'Cuartos';
        } else if($i==1){
            return 'Octavos';
        }
    } else if($cant_fechas==5){
        if($i==5){
            return 'Final';
        }else if($i==4){
            return 'Semifinal';
        } else if($i==3){
            return 'Cuartos';
        } else if($i==2){
            return 'Octavos';
        } else if($i==1){
            return '16 avos';
        }
    } else if($cant_fechas==6){
        if($i==6){
            return 'Final';
        }else if($i==5){
            return 'Semifinal';
        } else if($i==4){
            return 'Cuartos';
        } else if($i==3){
            return 'Octavos';
        } else if($i==2){
            return '16 avos';
        } else if($i==1){
            return '32 avos';
        }
    } else if($cant_fechas==7){
        if($i==7){
            return 'Final';
        }else if($i==6){
            return 'Semifinal';
        } else if($i==5){
            return 'Cuartos';
        } else if($i==4){
            return 'Octavos';
        } else if($i==3){
            return '16 avos';
        } else if($i==2){
            return '32 avos';
        } else if($i==1){
            return '64 avos';
        }
    } else if($cant_fechas==8){
        if($i==8){
            return 'Final';
        }else if($i==7){
            return 'Semifinal';
        } else if($i==6){
            return 'Cuartos';
        } else if($i==5){
            return 'Octavos';
        } else if($i==4){
            return '16 avos';
        } else if($i==3){
            return '32 avos';
        } else if($i==2){
            return '64 avos';
        } else if($i==1){
            return '128 avos';
        }
    }
}
?>