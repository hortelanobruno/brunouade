<div class="wrapper bgwhite">

    <div id="content">
        <div id="content" class="m25 pt20 pb20">
            <div id="pageView-title">
                <?php echo $equipo['nombre'] ?>		</div>

            <div class="clearfix tournament-home">

                <div class="bggray w550 fleft mr40 f14 border_lightest">

                    <div class="mt20">
                        <div class="fleft w100 pr20 pl20"><img width="100px" src="<?php echo $equipo['logo_grande'] ?>" /></div>
                        <div class="fleft w400 pb20 pl10">
                            <div class="player-name pb25">
                                <div class="w200 fleft"><b>Ciudad</b></div>
                                <div class="w200 fleft"><?php
                                    if (is_null($equipo['ciudad']) || $equipo['ciudad'] === "NULL") {
                                        echo "No disponible";
                                    } else {
                                        echo $equipo['ciudad'];
                                    }
                                    ?></div>
                            </div>
                            <div class="player-name pb25">
                                <div class="w200 fleft"><b>Sito web</b></div>
                                <div class="w200 fleft"><?php
                                    if (is_null($equipo['pagina_web']) || $equipo['pagina_web'] === "NULL") {
                                        echo "No disponible";
                                    } else {
                                        echo $equipo['pagina_web'];
                                    }
                                    ?></div>
                            </div>
                            <div class="player-name pb25">
                                <div class="w200 fleft"><b>Delegado</b></div>
                                <div class="w200 fleft"><?php echo getDelegado($delegado) ?></div>
                            </div>
                            <br />
                            <br />
                            <br />
                            <br />
                            <br />
                        </div>
                        <br clear="all" />
                    </div>
                </div>

                <div class="mt20">
                    <div class="w300 tr fright">
                        <img style="max-width: 300px;" class="shadow" src="<?php echo $equipo['foto'] ?>" />
                    </div>
                </div>

                <br clear="all" />

                <div class="mt20 fleft w450 mr40">
                    <!--giocatori-->
                    <div class="w450 fleft f14 mr20">
                        <div class="border_light_bottom pb5 mb10 lato f20 b">Jugadores</div>
                        <div>
                            <?php foreach ($jugadores as $jugador): ?>

                                <div class="pb10">
                                    <div class="w300 mr fleft"><a href=""><span class="player-name"><?php echo $jugador['nombre_completo'] ?></span></a></div>
                                    <div class="w12 fright"></div>
                                    <div class="clearfix"></div>
                                </div>
                            <?php endforeach ?>
                        </div>
                    </div>
                    <!--end giocatori-->
                </div>

                <div class="mt20 fleft w450">

                    <!--prossime partite-->
                    <div class="w450 fleft f14 mr40">
                        <div class="border_light_bottom pb5 mb10 lato f20 b">Proximo partido</div>
                        <div>
                            <?php
                            if (empty($partidos)) {
                                echo "No hay partidos pautados.";
                            } else {
                                ?>
                                <?php foreach ($partidos as $partido): ?>               
<!--                                    <div class="fleft pt10">
                                        
                                    </div>-->
                                    <br clear="all" />
                                    <div class="wp100 pt5">
                                        <div class="fleft w450">
                                            <a href="/torneo/<?php echo $partido['idtorneo'] ?>" class="b black"><?php echo getTorneo($torneos_all,$partido['idtorneo']) ?></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/torneo/<?php echo $partido['idtorneo'] ?>/partido/<?php echo $partido['id'] ?>"><?php echo getEquipo($equipos,$partido['idequipo1']) ?> - <?php echo getEquipo($equipos,$partido['idequipo2']) ?></a>
                                        </div>
                                        <br/>
                                        <div class="w200 fleft">Fecha: <?php echo getFecha($partido['fecha']) ?></div>
                                    </div>    
                                <?php endforeach ?>
                            <?php } ?>
                                    <br clear="all" /><br clear="all" />
                            <div class="border_light_bottom_dotted pt20"></div>
                        </div>
                    </div>
                    <!--end prossime partite-->

                    <!--competizioni-->
                    <div class="w450 fleft f14 mt30">
                        <div class="border_light_bottom pb5 mb10 lato f20 b">Campeonatos</div>
                        <?php
                        if (empty($historial_torneos)) {
                            echo "No hay historial de torneos.";
                        } else {
                            ?>
                            <?php foreach ($historial_torneos as $historial_torneo): ?>               
                                <div class="fleft mb10" >
                                    <div class="w50 mr10 fleft"><a href="../../../tournament/view/id/38.html"><img class="w50" src="uploads/pages_2d5f74974a92c68008605371d23ef378.png" /></a></div>
                                    <div class="fleft">
                                        <div class="w200 mr10 fleft"><a href="../../../tournament/view/id/38.html">CoppaCalciotto</a></div>
                                        <div class="w70 mr10 fleft">2012</div>
                                        <div class="w100 fleft nowrap">Calcio a 8</div>
                                    </div>
                                </div>
                            <?php endforeach ?>
                        <?php } ?>
                    </div>
                    <!--end competizioni-->
                </div>

                <div class="clearfix"></div>

                <div class="mt20">
                    <!--video-->


                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</div> <!-- #wrapper -->

<div class="clearfix"></div>

<?

function getDelegado($delegado){
    if(!empty($delegado)){
        return $delegado['nombre_completo'];
    }else{
        return "No hay delegado";
    }
}

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

function getTorneo($torneos_all,$idtorneo){
    if (isset($torneos_all)) {
        foreach ($torneos_all as $torneo) {
            if ($torneo['id'] === $idtorneo)
                return $torneo['nombre'];
        }
    }else {
        return $idtorneo;
    }
}

function getFecha($fecha){
    if(!empty($fecha)){
        return $fecha;
    }else{
        return "No disponible";
    }
}

?>