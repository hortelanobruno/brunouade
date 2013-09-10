<?php

class Partidos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function save_fixture($idtorneo, $fixturepartidos, $fixtureordenequipos, $data) {
        if ($fixturepartidos != '') {
            $equipos = explode(",", $fixtureordenequipos);
//            $equipos_legth = count($equipos) / 2;
//            if ($equipos_legth % 2 == 0) {
//                $rondas = $equipos_legth - 1;
//                $cant_partidos_en_fecha = $equipos_legth / 2;
//            } else {
//                $rondas = $equipos_legth;
//                $cant_partidos_en_fecha = ($equipos_legth - 1) / 2;
//            }
            $equipos_legth = $data['cantidad_equipos'];
            $rondas = $data['cantidad_fechas'];
            $cant_partidos_en_fecha = $data['cantidad_partidos'] / $data['cantidad_fechas'];
            $cant_fechas = $data['cantidad_fechas'];

            $partidos = explode(",", $fixturepartidos);

            $numpartido = 1;
            $indice_fecha = 1;
            echo "aca: rondas: " . $rondas . ' cant_partidos: ' . $cant_partidos_en_fecha . " equipos: " . $equipos_legth;
            foreach ($partidos as $partido) {
                echo "entro..\n";
                echo "partido: " . $partido . "\n";
                if (strpos($partido, 'Queda libre') !== false) {
                    echo "nono\n";
                } else {
                    $equipos = explode(" vs ", $partido);
                    $this->db->query("INSERT INTO tfc_partido (idtorneo,idfase,idequipo1,idequipo2,fecha_torneo) values (" . $idtorneo . "," . $numpartido . "," . $equipos[0] . "," . $equipos[1] . "," . $indice_fecha . ");");
                    $numpartido++;
                    if ($numpartido == ($cant_partidos_en_fecha + 1)) {
                        $numpartido = 1;
                        $indice_fecha++;
                    }
                }
            }
        }
    }

    public function delete_partidos_de_torneo($idtorneo) {
        $query = $this->db->query("SELECT id FROM tfc_partido WHERE idtorneo=" . $idtorneo);
        $results = $query->result_array();
        foreach ($results as $result) {
            $this->db->query("DELETE FROM tfc_estadisticas_partido where idpartido=" . $result['id']);
        }

        $this->db->query("DELETE FROM tfc_partido where idtorneo=" . $idtorneo);
    }

    public function delete_estadisticas_partido($idpartido) {
        $this->db->query("DELETE FROM tfc_estadisticas_partido where idpartido =" . $idpartido);
    }

    public function save_estadisticas_partido($idpartido, $idequipo, $idjugador, $action, $cantidad) {
        $this->db->query("INSERT INTO tfc_estadisticas_partido (idpartido,idequipo,idjugador,accion,cantidad) values (" . $idpartido . "," . $idequipo . "," . $idjugador . "," . $action . "," . $cantidad . ");");
    }

    public function get_estadisticas_partido($idpartido) {
        $query = $this->db->query("SELECT * FROM tfc_estadisticas_partido WHERE idpartido = " . $idpartido);
        return $query->result_array();
    }

}