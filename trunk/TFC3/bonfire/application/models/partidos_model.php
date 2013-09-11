<?php

class Partidos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function save_fixture($idtorneo, $fixturepartidos, $fixtureordenequipos, $data) {
        if ($fixturepartidos != '') {
            $equipos = explode(",", $fixtureordenequipos);
            $equipos_legth = $data['cantidad_equipos'];
            $rondas = $data['cantidad_fechas'];
            $cant_partidos_en_fecha = $data['cantidad_partidos'] / $data['cantidad_fechas'];

            $partidos = explode(",", $fixturepartidos);

            $numpartido = 1;
            $indice_fecha = 1;
            foreach ($partidos as $partido) {
                if (strpos($partido, 'Queda libre') !== false) {
                    
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

    public function actualizar_data_creacion_partido($partido, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja) {
        //Cargar tabla estadistica partido
        for ($i = 0; $i < count($jugador1id); $i++) {
            if ($jugador1goles[$i] != '' && intval($jugador1goles[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo1'], $jugador1id[$i], 1, $jugador1goles[$i]);
            }
            if ($jugador1tarjetaamarilla[$i] != '' && intval($jugador1tarjetaamarilla[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo1'], $jugador1id[$i], 2, $jugador1tarjetaamarilla[$i]);
            }
            if ($jugador1tarjetaroja[$i] != '' && intval($jugador1tarjetaroja[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo1'], $jugador1id[$i], 3, $jugador1tarjetaroja[$i]);
            }
        }
        for ($i = 0; $i < count($jugador2id); $i++) {
            if ($jugador2goles[$i] != '' && intval($jugador2goles[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo2'], $jugador2id[$i], 1, $jugador2goles[$i]);
            }
            if ($jugador2tarjetaamarilla[$i] != '' && intval($jugador2tarjetaamarilla[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo2'], $jugador2id[$i], 2, $jugador2tarjetaamarilla[$i]);
            }
            if ($jugador2tarjetaroja[$i] != '' && intval($jugador2tarjetaroja[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo2'], $jugador2id[$i], 3, $jugador2tarjetaroja[$i]);
            }
        }

        //Si el partido se marca como jugado se hace lo siguiente
        //Cargar tabla estadisticas jugador por torneo
        //Cargar tabla posiciones
        //Cargar tabla jugadores
        //Cargar tabla equipo

        if ($partido['jugado'] === 'true') {
            
        }
    }

    public function actualizar_data_actualizacion_partido($idpartido, $partido, $partido_old, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja) {
        $this->partidos_model->delete_estadisticas_partido($idpartido);

        for ($i = 0; $i < count($jugador1id); $i++) {
            if ($jugador1goles[$i] != '' && intval($jugador1goles[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo1'], $jugador1id[$i], 1, $jugador1goles[$i]);
            }
            if ($jugador1tarjetaamarilla[$i] != '' && intval($jugador1tarjetaamarilla[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo1'], $jugador1id[$i], 2, $jugador1tarjetaamarilla[$i]);
            }
            if ($jugador1tarjetaroja[$i] != '' && intval($jugador1tarjetaroja[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo1'], $jugador1id[$i], 3, $jugador1tarjetaroja[$i]);
            }
        }
        for ($i = 0; $i < count($jugador2id); $i++) {
            if ($jugador2goles[$i] != '' && intval($jugador2goles[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo2'], $jugador2id[$i], 1, $jugador2goles[$i]);
            }
            if ($jugador2tarjetaamarilla[$i] != '' && intval($jugador2tarjetaamarilla[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo2'], $jugador2id[$i], 2, $jugador2tarjetaamarilla[$i]);
            }
            if ($jugador2tarjetaroja[$i] != '' && intval($jugador2tarjetaroja[$i]) > 0) {
                $this->partidos_model->save_estadisticas_partido($id, $partido['idequipo2'], $jugador2id[$i], 3, $jugador2tarjetaroja[$i]);
            }
        }
    }

    public function actualizar_data_delete_partido($idpartido, $partido_old) {
        $this->partidos_model->delete_estadisticas_partido($pid);
    }

}