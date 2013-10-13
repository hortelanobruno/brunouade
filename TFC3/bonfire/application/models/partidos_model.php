<?php

class Partidos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_fixture($idtorneo) {
        $query = $this->db->query("SELECT * FROM tfc_partido WHERE idtorneo = " . $idtorneo);
        return $query->result_array();
    }

    public function save_fixture_copa($idtorneo, $equiposorden, $data) {
        $equipos = explode(",", $equiposorden);
        $numpartido = 1;
        for ($i = 0; $i < count($equipos); $i++) {
            $equipo1 = $equipos[$i];
            $i++;
            $equipo2 = $equipos[$i];
            $this->db->query("INSERT INTO tfc_partido (idtorneo,idfase,idequipo1,idequipo2,fecha_torneo) values (" . $idtorneo . "," . $numpartido . "," . $equipo1 . "," . $equipo2 . ",1);");
            $numpartido++;
        }
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

    public function save_estadistica_partido($idpartido, $idequipo, $idjugador, $action, $cantidad) {
        $this->db->query("INSERT INTO tfc_estadisticas_partido (idpartido,idequipo,idjugador,accion,cantidad) values (" . $idpartido . "," . $idequipo . "," . $idjugador . "," . $action . "," . $cantidad . ");");
    }

    public function save_estadisticas_partido($idpartido, $partido, $jugadorid, $jugadorgoles, $jugadortarjetaamarilla, $jugadortarjetaroja, $equiposelected, $equipogolesencontra, $equipoarquero) {
        if ($partido[$equiposelected] > 0) {
            for ($i = 0; $i < count($jugadorid); $i++) {
                if ($jugadorgoles[$i] != '' && intval($jugadorgoles[$i]) > 0) {
                    $this->save_estadistica_partido($idpartido, $partido[$equiposelected], $jugadorid[$i], 1, $jugadorgoles[$i]);
                } else {
                    $jugadorgoles[$i] = 0;
                }
                if ($jugadortarjetaamarilla[$i] != '' && intval($jugadortarjetaamarilla[$i]) > 0) {
                    $this->save_estadistica_partido($idpartido, $partido[$equiposelected], $jugadorid[$i], 2, $jugadortarjetaamarilla[$i]);
                } else {
                    $jugadortarjetaamarilla[$i] = 0;
                }
                if ($jugadortarjetaroja[$i] != '' && intval($jugadortarjetaroja[$i]) > 0) {
                    $this->save_estadistica_partido($idpartido, $partido[$equiposelected], $jugadorid[$i], 3, $jugadortarjetaroja[$i]);
                } else {
                    $jugadortarjetaroja[$i] = 0;
                }
            }
            //goles en contra
            if ($equipogolesencontra != '' && intval($equipogolesencontra) > 0) {
                $this->save_estadistica_partido($idpartido, $partido[$equiposelected], 0, 4, $equipogolesencontra);
            } else {
                $equipogolesencontra = 0;
            }

            //arquero, goles en contra
            if ($equipoarquero != '' && intval($equipoarquero) > 0) {
                if (strpos($equiposelected, '1') !== false) {
                    $this->save_estadistica_partido($idpartido, $partido[$equiposelected], intval($equipoarquero), 5, $partido['goles_equipo2']);
                } else {
                    $this->save_estadistica_partido($idpartido, $partido[$equiposelected], intval($equipoarquero), 5, $partido['goles_equipo1']);
                }
            }
        }
    }

    public function get_estadistica_goles_en_contra($idpartido, $idequipo) {
        $query = $this->db->query("SELECT cantidad FROM tfc_estadisticas_partido WHERE idpartido = " . $idpartido . " and idequipo = " . $idequipo . " and accion = 4");
        $row = $query->row_array();
        if (empty($row)) {
            return 0;
        } else {
            return $row['cantidad'];
        }
    }

    public function get_partido($idpartido) {
        $query = $this->db->query("SELECT * FROM tfc_partido WHERE id = " . $idpartido);
        return $query->row_array();
    }

    public function get_estadisticas_partido($idpartido) {
        $query = $this->db->query("SELECT * FROM tfc_estadisticas_partido WHERE idpartido = " . $idpartido);
        return $query->result_array();
    }

    public function actualizar_data_creacion_partido($idpartido, $partido, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja, $equipo1golesencontra, $equipo2golesencontra, $equipo1arquero, $equipo2arquero) {
        if ($partido['idequipo1'] > 0 && $partido['idequipo2'] > 0) {
            //Cargar tabla estadistica partido
            $this->save_estadisticas_partido($idpartido, $partido, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, 'idequipo1', $equipo1golesencontra, $equipo1arquero);
            $this->save_estadisticas_partido($idpartido, $partido, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja, 'idequipo2', $equipo2golesencontra, $equipo2arquero);

            //Computar partido
            $this->computar_partido($partido, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja, $equipo1arquero, $equipo2arquero);
        }
    }

    public function computar_partido($partido, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja, $equipo1arquero, $equipo2arquero) {
        //Si el partido se marca como jugado se hace lo siguiente
        if (intval($partido['jugado']) == 1) {
            //Cargar tabla estadisticas jugador por torneo
            for ($i = 0; $i < count($jugador1id); $i++) {
                if ($jugador1id[$i] == $equipo1arquero) {
                    $this->db->query("INSERT INTO tfc_estadisticas_jugador_por_torneo values (" . $partido['idtorneo'] . "," . $partido['idequipo1'] . "," . $jugador1id[$i] . "," . $this->parseStats($jugador1goles[$i]) . "," . $this->parseStats($jugador1tarjetaamarilla[$i]) . "," . $this->parseStats($jugador1tarjetaroja[$i]) . "," . $this->jugo($jugador1id[$i], $jugador1goles[$i], $jugador1tarjetaamarilla[$i], $jugador1tarjetaroja[$i], $equipo1arquero) . "," . $partido['goles_equipo2'] . ") ON DUPLICATE KEY UPDATE cantidad_goles=cantidad_goles+" . $this->parseStats($jugador1goles[$i]) . ",cantidad_tarjetas_amarillas=cantidad_tarjetas_amarillas+" . $this->parseStats($jugador1tarjetaamarilla[$i]) . ",cantidad_tarjetas_rojas=cantidad_tarjetas_rojas+" . $this->parseStats($jugador1tarjetaroja[$i]) . ",cantidad_partidos_jugados=cantidad_partidos_jugados+" . $this->jugo($jugador1id[$i], $jugador1goles[$i], $jugador1tarjetaamarilla[$i], $jugador1tarjetaroja[$i], $equipo1arquero) . ",cantidad_goles_en_contra=cantidad_goles_en_contra+" . $partido['goles_equipo2'] . ";");
                } else {
                    $this->db->query("INSERT INTO tfc_estadisticas_jugador_por_torneo values (" . $partido['idtorneo'] . "," . $partido['idequipo1'] . "," . $jugador1id[$i] . "," . $this->parseStats($jugador1goles[$i]) . "," . $this->parseStats($jugador1tarjetaamarilla[$i]) . "," . $this->parseStats($jugador1tarjetaroja[$i]) . "," . $this->jugo($jugador1id[$i], $jugador1goles[$i], $jugador1tarjetaamarilla[$i], $jugador1tarjetaroja[$i], $equipo1arquero) . ",0) ON DUPLICATE KEY UPDATE cantidad_goles=cantidad_goles+" . $this->parseStats($jugador1goles[$i]) . ",cantidad_tarjetas_amarillas=cantidad_tarjetas_amarillas+" . $this->parseStats($jugador1tarjetaamarilla[$i]) . ",cantidad_tarjetas_rojas=cantidad_tarjetas_rojas+" . $this->parseStats($jugador1tarjetaroja[$i]) . ",cantidad_partidos_jugados=cantidad_partidos_jugados+" . $this->jugo($jugador1id[$i], $jugador1goles[$i], $jugador1tarjetaamarilla[$i], $jugador1tarjetaroja[$i], $equipo1arquero) . ";");
                }
            }
            for ($i = 0; $i < count($jugador2id); $i++) {
                if ($jugador2id[$i] == $equipo2arquero) {
                    $this->db->query("INSERT INTO tfc_estadisticas_jugador_por_torneo values (" . $partido['idtorneo'] . "," . $partido['idequipo2'] . "," . $jugador2id[$i] . "," . $this->parseStats($jugador2goles[$i]) . "," . $this->parseStats($jugador2tarjetaamarilla[$i]) . "," . $this->parseStats($jugador2tarjetaroja[$i]) . "," . $this->jugo($jugador2id[$i], $jugador2goles[$i], $jugador2tarjetaamarilla[$i], $jugador2tarjetaroja[$i], $equipo2arquero) . "," . $partido['goles_equipo1'] . ") ON DUPLICATE KEY UPDATE cantidad_goles=cantidad_goles+" . $this->parseStats($jugador2goles[$i]) . ",cantidad_tarjetas_amarillas=cantidad_tarjetas_amarillas+" . $this->parseStats($jugador2tarjetaamarilla[$i]) . ",cantidad_tarjetas_rojas=cantidad_tarjetas_rojas+" . $this->parseStats($jugador2tarjetaroja[$i]) . ",cantidad_partidos_jugados=cantidad_partidos_jugados+" . $this->jugo($jugador2id[$i], $jugador2goles[$i], $jugador2tarjetaamarilla[$i], $jugador2tarjetaroja[$i], $equipo2arquero) . ",cantidad_goles_en_contra=cantidad_goles_en_contra+" . $partido['goles_equipo1'] . ";");
                } else {
                    $this->db->query("INSERT INTO tfc_estadisticas_jugador_por_torneo values (" . $partido['idtorneo'] . "," . $partido['idequipo2'] . "," . $jugador2id[$i] . "," . $this->parseStats($jugador2goles[$i]) . "," . $this->parseStats($jugador2tarjetaamarilla[$i]) . "," . $this->parseStats($jugador2tarjetaroja[$i]) . "," . $this->jugo($jugador2id[$i], $jugador2goles[$i], $jugador2tarjetaamarilla[$i], $jugador2tarjetaroja[$i], $equipo2arquero) . ",0) ON DUPLICATE KEY UPDATE cantidad_goles=cantidad_goles+" . $this->parseStats($jugador2goles[$i]) . ",cantidad_tarjetas_amarillas=cantidad_tarjetas_amarillas+" . $this->parseStats($jugador2tarjetaamarilla[$i]) . ",cantidad_tarjetas_rojas=cantidad_tarjetas_rojas+" . $this->parseStats($jugador2tarjetaroja[$i]) . ",cantidad_partidos_jugados=cantidad_partidos_jugados+" . $this->jugo($jugador2id[$i], $jugador2goles[$i], $jugador2tarjetaamarilla[$i], $jugador2tarjetaroja[$i], $equipo2arquero) . ";");
                }
            }
            //Cargar tabla posiciones
            if ($this->is_torneo_liga($partido['idtorneo'])) {
                $this->actualizar_tabla_posiciones($partido);
            }
            //Cargar tabla equipo
        }
    }

    private function is_torneo_liga($idtorneo) {
        $query = $this->db->query("SELECT categoria FROM tfc_torneo WHERE id = " . $idtorneo);
        $row = $query->row_array();
        if (empty($row)) {
            return false;
        } else {
            if ($row['categoria'] == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    private function parseStats($data) {
        if ($data == '') {
            return 0;
        }
        try {
            return intval($data);
        } catch (Exception $e) {
            return 0;
        }
    }

    private function jugo($jugadorid, $jugadorgoles, $jugadortarjetaamarilla, $jugadortarjetaroja, $equipoarquero) {
        if ($jugadorid == $equipoarquero) {
            return 1;
        }
        return 0;
    }

    public function descomputar_partido($idpartido, $partido_old) {
        //Si el partido se marca como jugado se hace lo siguiente
        if (intval($partido_old['jugado']) == 1) {
            $estadisticas = $this->get_estadisticas_partido($idpartido);
            //Descomputar estadistica jugador por torneo
            //Saco partidos jugados
            foreach ($estadisticas as $estadistica) {
                $this->descomputar_estadistica_jugador_torneo($partido_old['idtorneo'], $estadistica['idequipo'], $estadistica['idjugador'], $estadistica['accion'], $estadistica['cantidad']);
            }
            //Cargar tabla posiciones
            if ($this->is_torneo_liga($partido_old['idtorneo'])) {
                $this->desactualizar_tabla_posiciones($partido_old);
            }
        }
    }

    public function descomputar_estadistica_jugador_torneo($idtorneo, $idequipo, $idjugador, $accion, $cantidad) {
        if (intval($accion) == 1) {
            //goles
            $this->db->query("UPDATE tfc_estadisticas_jugador_por_torneo set cantidad_goles=cantidad_goles-" . $cantidad . " where idtorneo =" . $idtorneo . " and  idequipo=" . $idequipo . " and idjugador = " . $idjugador);
        } else if (intval($accion) == 2) {
            //amarillas
            $this->db->query("UPDATE tfc_estadisticas_jugador_por_torneo set cantidad_tarjetas_amarillas=cantidad_tarjetas_amarillas-" . $cantidad . " where idtorneo =" . $idtorneo . " and  idequipo=" . $idequipo . " and idjugador = " . $idjugador);
        } else if (intval($accion) == 3) {
            //rojas
            $this->db->query("UPDATE tfc_estadisticas_jugador_por_torneo set cantidad_tarjetas_rojas=cantidad_tarjetas_rojas-" . $cantidad . " where idtorneo =" . $idtorneo . " and  idequipo=" . $idequipo . " and idjugador = " . $idjugador);
        } else if (intval($accion) == 5) {
            //goles en contra de arqueros
            $this->db->query("UPDATE tfc_estadisticas_jugador_por_torneo set cantidad_partidos_jugados=cantidad_partidos_jugados-1,cantidad_goles_en_contra=cantidad_goles_en_contra-" . $cantidad . " where idtorneo =" . $idtorneo . " and  idequipo=" . $idequipo . " and idjugador = " . $idjugador);
        }
    }

    public function actualizar_tabla_posiciones($partido) {
        if (intval($partido['goles_equipo1']) == intval($partido['goles_equipo2'])) {
            //empate
            $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos+1,partidos_jugados=partidos_jugados+1,partidos_empatados=partidos_empatados+1,goles_a_favor=goles_a_favor+" . $partido['goles_equipo1'] . ",goles_en_contra=goles_en_contra+" . $partido['goles_equipo2'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo1']);
            $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos+1,partidos_jugados=partidos_jugados+1,partidos_empatados=partidos_empatados+1,goles_a_favor=goles_a_favor+" . $partido['goles_equipo2'] . ",goles_en_contra=goles_en_contra+" . $partido['goles_equipo1'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo2']);
        } else {
            if (intval($partido['goles_equipo1']) > intval($partido['goles_equipo2'])) {
                //gano equipo1
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos+3,partidos_jugados=partidos_jugados+1,partidos_ganados=partidos_ganados+1,goles_a_favor=goles_a_favor+" . $partido['goles_equipo1'] . ",goles_en_contra=goles_en_contra+" . $partido['goles_equipo2'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo1']);
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set partidos_jugados=partidos_jugados+1,partidos_perdidos=partidos_perdidos+1,goles_a_favor=goles_a_favor+" . $partido['goles_equipo2'] . ",goles_en_contra=goles_en_contra+" . $partido['goles_equipo1'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo2']);
            } else {
                //ganoe equipo2
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set partidos_jugados=partidos_jugados+1,partidos_perdidos=partidos_perdidos+1,goles_a_favor=goles_a_favor+" . $partido['goles_equipo1'] . ",goles_en_contra=goles_en_contra+" . $partido['goles_equipo2'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo1']);
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos+3,partidos_jugados=partidos_jugados+1,partidos_ganados=partidos_ganados+1,goles_a_favor=goles_a_favor+" . $partido['goles_equipo2'] . ",goles_en_contra=goles_en_contra+" . $partido['goles_equipo1'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo2']);
            }
        }
    }

    public function desactualizar_tabla_posiciones($partido) {
        if (intval($partido['goles_equipo1']) == intval($partido['goles_equipo2'])) {
            //empate
            $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos-1,partidos_jugados=partidos_jugados-1,partidos_empatados=partidos_empatados-1,goles_a_favor=goles_a_favor-" . $partido['goles_equipo1'] . ",goles_en_contra=goles_en_contra-" . $partido['goles_equipo2'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo1']);
            $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos-1,partidos_jugados=partidos_jugados-1,partidos_empatados=partidos_empatados-1,goles_a_favor=goles_a_favor-" . $partido['goles_equipo2'] . ",goles_en_contra=goles_en_contra-" . $partido['goles_equipo1'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo2']);
        } else {
            if (intval($partido['goles_equipo1']) > intval($partido['goles_equipo2'])) {
                //gano equipo1
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos-3,partidos_jugados=partidos_jugados-1,partidos_ganados=partidos_ganados-1,goles_a_favor=goles_a_favor-" . $partido['goles_equipo1'] . ",goles_en_contra=goles_en_contra-" . $partido['goles_equipo2'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo1']);
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set partidos_jugados=partidos_jugados-1,partidos_perdidos=partidos_perdidos-1,goles_a_favor=goles_a_favor-" . $partido['goles_equipo2'] . ",goles_en_contra=goles_en_contra-" . $partido['goles_equipo1'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo2']);
            } else {
                //ganoe equipo2
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set partidos_jugados=partidos_jugados-1,partidos_perdidos=partidos_perdidos-1,goles_a_favor=goles_a_favor-" . $partido['goles_equipo1'] . ",goles_en_contra=goles_en_contra-" . $partido['goles_equipo2'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo1']);
                $this->db->query("UPDATE tfc_torneo_tabla_posiciones set puntos=puntos-3,partidos_jugados=partidos_jugados-1,partidos_ganados=partidos_ganados-1,goles_a_favor=goles_a_favor-" . $partido['goles_equipo2'] . ",goles_en_contra=goles_en_contra-" . $partido['goles_equipo1'] . " where idtorneo =" . $partido['idtorneo'] . " and idfase=" . $partido['idfase'] . " and idequipo=" . $partido['idequipo2']);
            }
        }
    }

    public function actualizar_data_actualizacion_partido($idpartido, $partido, $partido_old, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja, $equipo1golesencontra, $equipo2golesencontra, $equipo1arquero, $equipo2arquero) {
        if ($partido_old['idequipo1'] > 0 && $partido_old['idequipo2'] > 0) {
            //Descomputar partido
            $this->descomputar_partido($idpartido, $partido_old);
            //Remover tabla estadistica partido
            $this->partidos_model->delete_estadisticas_partido($idpartido);
        }
        if ($partido['idequipo1'] > 0 && $partido['idequipo2'] > 0) {
            //Cargar tabla estadistica partido
            $this->save_estadisticas_partido($idpartido, $partido, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, 'idequipo1', $equipo1golesencontra, $equipo1arquero);
            $this->save_estadisticas_partido($idpartido, $partido, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja, 'idequipo2', $equipo2golesencontra, $equipo2arquero);
            //Computar partido
            $this->computar_partido($partido, $jugador1id, $jugador1goles, $jugador1tarjetaamarilla, $jugador1tarjetaroja, $jugador2id, $jugador2goles, $jugador2tarjetaamarilla, $jugador2tarjetaroja, $equipo1arquero, $equipo2arquero);
        }
    }

    public function actualizar_data_delete_partido($idpartido, $partido_old) {
        //Descomputar partido
        $this->descomputar_partido($idpartido, $partido_old);
        //Remover tabla estadistica partido
        $this->partidos_model->delete_estadisticas_partido($idpartido);
    }

}