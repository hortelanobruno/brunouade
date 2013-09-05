<?php

function save_fixture($idtorneo, $fixturepartidos, $fixtureordenequipos) {
    $equipos = explode(",", $fixtureordenequipos);
    $equipos_legth = count($equipos) / 2;
    if ($equipos_legth % 2 == 0) {
        $rondas = $equipos_legth - 1;
        $cant_partidos = $equipos_legth / 2;
    } else {
        $rondas = $equipos_legth;
        $cant_partidos = ($equipos_legth - 1) / 2;
    }


    $partidos = explode(",", $fixturepartidos);

    $numpartido = 1;
    echo "aca: rondas: " . $rondas . ' cant_partidos: ' . $cant_partidos . " equipos: " . $equipos_legth;
    foreach ($partidos as $partido) {
        echo "entro..\n";
        echo "partido: " . $partido . "\n";
        if (strpos($partido, 'Queda libre') !== false) {
            echo "nono\n";
        } else {
            $equipos = explode(" vs ", $partido);
            echo "INSERT INTO tfc_partido (idtorneo,idfase,idequipo1,idequipo2,fecha_torneo) values (" . $idtorneo . "," . $numpartido . "," . $equipos[0] . "," . $equipos[1] . "," . $numpartido . ");";
            $numpartido++;
            if ($numpartido == ($cant_partidos + 1)) {
                $numpartido = 1;
            }
        }
    }
}

echo save_fixture('1', 'Queda libre 7,5 vs 6,3 vs 4,Queda libre 4,6 vs 3,7 vs 5,Queda libre 5,3 vs 7,4 vs 6,Queda libre 6,7 vs 4,5 vs 3,Queda libre 3,4 vs 5,6 vs 7', '7,Los Palos de VC,5,Sarandi FC,3,Ritmo y Sustancias,4,Los Pibes del Mate,6,Piedra que late');
?>
