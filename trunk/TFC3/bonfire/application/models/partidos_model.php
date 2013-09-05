<?php

class Partidos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function save_fixture($idtorneo, $fixturepartidos, $fixtureordenequipos) {
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
                $this->db->query("INSERT INTO tfc_partido (idtorneo,idfase,idequipo1,idequipo2,fecha_torneo) values (" . $idtorneo . "," . $numpartido . "," . $equipos[0] . "," . $equipos[1] . "," . $numpartido . ");");
                $numpartido++;
                if ($numpartido == ($cant_partidos + 1)) {
                    $numpartido = 1;
                }
            }
        }
    }

    public function delete_partidos_de_torneo($idtorneo) {
//        $this->db->query("DELETE FROM tfc_estadisticas_partido where idpartido in (SELECT id FROM tfc_partido WHERE idtorneo=" . $idtorneo . ")");
        $this->db->query("DELETE FROM tfc_partido where idtorneo=" . $idtorneo);
    }

}