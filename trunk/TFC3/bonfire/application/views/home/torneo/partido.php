<div class="wrapper bgwhite">
    <div id="content">
        <div id="content" class="m25 pt20 pb20">
            <div class="mt10 mb20">
                <a class="backhome" href="/torneo/<?php echo $partido['idtorneo'] ?>">Volver al torneo</a>
            </div>
            <div id="pageView-title">
                <?php echo $torneo['nombre'] ?></div>
            <span class="b i f16"></span><br />
            <?php echo (isset($partido['fecha']) ? $partido['fecha'] : 'Fecha no disponible') ?> / <?php echo (isset($sede) ? $sede['nombre'] : 'Sede no disponible') ?><br />
            <br />
            <table class="w950 mauto border_lightest bggray">
                <tr>
                    <td class="tr wp50 p20">
                        <img class="fleft" style="max-width: 50px;" src="<?php echo getEquipoLogo($equipos, $partido['idequipo1']) ?>" />
                        <div class="tr b f16"><a class="black" href="/equipo/<?php echo $partido['idequipo1'] ?>"><?php echo getEquipo($equipos, $partido['idequipo1']) ?></a></div>
                        <div class=" b f30 w50 tr p5 fright"><?php echo getGoles($partido['goles_equipo1']) ?></div>
                        <!--<br clear="all" />-->
                        <!--                        <div>
                                                    <a href="../../../user/index/id/3124.html"><span class="player-name">Caruso Alessandro</span></a>  (1)<br />
                                                    <a href="../../../user/index/id/600.html"><span class="player-name">Sorrentino Mario</span></a>  (2)<br />
                                                </div>-->
                        <!--<br clear="all" />-->
                    </td>
                    <td class="wp50 p20">
                        <img class="fright" style="max-width: 50px;" src="<?php echo getEquipoLogo($equipos, $partido['idequipo2']) ?>" />
                        <div class="b f16"><a class="black" href="/equipo/<?php echo $partido['idequipo2'] ?>"><?php echo getEquipo($equipos, $partido['idequipo2']) ?></a></div>
                        <div class=" b f30 w50 tl p5"><?php echo getGoles($partido['goles_equipo2']) ?></div>
                        <!--                        <div>
                                                    <a href="../../../user/index/id/2997.html"><span class="player-name">Marino Alessandro</span></a>  (3)<br />
                                                    <a href="../../../user/index/id/3006.html"><span class="player-name">Pellini Pietro</span></a>  (1)<br />
                                                </div>-->
                    </td>
                </tr>
            </table>
            <table class="w950 mauto border_lightest">
                <tr>
                    <td class="vt wp50">
                        <table class="wp100 zebra table-p10">
                            <?php foreach ($jugadores_equipo1 as $jugador): ?>
                                <tr>
                                    <td>
                                        <div class="pl5 pr5">
                                            <div class="fleft w120 tc">
                                                <img style="max-width:100px; max-height:100px" src="/assets/imgs/user/defaultUser.png" />
                                            </div>
                                            <div class="fleft f14 wp70">
                                                <a href=""><span class="player-name"><?php echo $jugador['nombre_completo'] ?></span></a> 									 
                                                <br />
                                                <?php echo printStats($estadisticas_partido, $partido['idequipo1'], $jugador['id'], $jugadores_equipo1) ?>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            <?php endforeach ?>
                        </table>
                    </td>
                    <td class="vt wp50">
                        <table class="wp100 zebra table-p10">
                            <?php foreach ($jugadores_equipo2 as $jugador): ?>
                                <tr>
                                    <td>
                                        <div class="pl5 pr5">
                                            <div class="fleft w120 tc">
                                                <img style="max-width:100px; max-height:100px" src="/assets/imgs/user/defaultUser.png" />
                                            </div>
                                            <div class="fleft f14 wp70">
                                                <a href=""><span class="player-name"><?php echo $jugador['nombre_completo'] ?></span></a> 									 
                                                <br />
                                                <?php echo printStats($estadisticas_partido, $partido['idequipo2'], $jugador['id'], $jugadores_equipo2) ?>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            <?php endforeach ?>
                        </table>
                    </td>
                </tr>
            </table>


        </div>
    </div>
</div>
</div> <!-- #wrapper -->
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

function getEquipoLogo($equipo, $idequipo) {
    if (isset($equipo)) {
        foreach ($equipo as $equipo) {
            if ($equipo['id'] === $idequipo)
                return $equipo['logo_grande'];
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

function printStats($estadisticas_partido, $idequipo, $idjugador, $jugadores) {
    $result = "";
    //Cargo delegado
    foreach ($jugadores as $jugador) {
        if ($jugador['id'] == $idjugador && $jugador['delegado'] == 1) {
            $result = $result . "<img src='/assets/imgs/commons/icons/ico-giocatore.png' alt='*' title='Miglior giocatore' width='20' class='mr5 mt5' style='margin-left: -2px;'>";
        }
    }

    //Cargo estadisticas
    foreach ($estadisticas_partido as $estadistica) {
        if ($estadistica['idequipo'] == $idequipo && $estadistica['idjugador'] == $idjugador) {
            if ($estadistica['cantidad'] > 1) {
                $result = $result . $estadistica['cantidad'] . "x";
            }
            if ($estadistica['accion'] == 1) {
                //gol
                $result = $result . "<img src='/assets/imgs/commons/ico-goal.png' alt='G' title='Gol' width='20' class='mr5 mt5' style='margin-left: -2px;'>";
            } else if ($estadistica['accion'] == 2) {
                //amarilla
                $result = $result . "<img src='/assets/imgs/commons/ico-giallo.png' alt='A' title='Amarilla' width='20' class='mr5 mt5' style='margin-left: -2px;'>";
            } else if ($estadistica['accion'] == 3) {
                //roja
                $result = $result . "<img src='/assets/imgs/commons/ico-rosso.png' alt='R' title='Roja' width='20' class='mr5 mt5' style='margin-left: -2px;'>";
            } else if ($estadistica['accion'] == 4) {
                //en contra
//                $result = $result . "<img src='/assets/imgs/commons/ico-rosso.png' alt='R' title='Espulso' width='20' class='mr5 mt5' style='margin-left: -2px;'>";
            }
        }
    }
    return $result;
}
?>