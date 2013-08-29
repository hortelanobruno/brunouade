<?php

class Torneos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_last_4_torneos() {
        $query = $this->db->query("SELECT * FROM tfc_torneo where archivado = false limit 4");
        return $query->result_array();
    }

    public function get_all_torneos() {
        $query = $this->db->query("SELECT * FROM tfc_torneo");
        return $query->result_array();
    }

    public function get_torneo($idtorneo) {
        $query = $this->db->query("SELECT * FROM tfc_torneo where id=" . $idtorneo);
        return $query->row_array();
    }

    public function get_tabla_from_torneo($idtorneo) {
        //SELECT tp.*,(tp.goles_a_favor - tp.goles_en_contra) diff,eq.nombre FROM tfc_dev.tfc_torneo_tabla_posiciones tp join tfc_dev.tfc_equipo eq on tp.idequipo=eq.id where idtorneo=1 order by tp.puntos desc,diff desc, tp.goles_a_favor desc;
        $query = $this->db->query("SELECT tp.*,(tp.goles_a_favor - tp.goles_en_contra) diff,eq.nombre FROM tfc_torneo_tabla_posiciones tp join tfc_equipo eq on tp.idequipo=eq.id where idtorneo=" . $idtorneo . " order by tp.puntos desc,diff desc, tp.goles_a_favor desc");
        return $query->result_array();
    }

    public function get_goleadores_from_torneo($idtorneo) {
        $query = $this->db->query("SELECT et.idequipo,et.idjugador,et.cantidad_goles,eq.nombre,ju.nombre_completo 
            FROM tfc_estadisticas_jugador_por_torneo et 
            join tfc_equipo eq on et.idequipo=eq.id 
            join tfc_jugador ju on et.idjugador=ju.id 
            where et.idtorneo=1 order by et.cantidad_goles desc;");
        return $query->result_array();
    }

    public function get_vallaMenosVencida_from_torneo($idtorneo) {
        $query = $this->db->query("SELECT et.idequipo,et.idjugador,et.cantidad_partidos_jugados,et.cantidad_goles_en_contra,eq.nombre,ju.nombre_completo 
            FROM tfc_estadisticas_jugador_por_torneo et 
            join tfc_equipo eq on et.idequipo=eq.id 
            join tfc_jugador ju on et.idjugador=ju.id 
            where et.idtorneo=1 and et.cantidad_goles_en_contra>0 order by et.cantidad_goles_en_contra desc, et.cantidad_partidos_jugados desc;");
        return $query->result_array();
    }

    public function get_tarjetas_from_torneo($idtorneo) {
        $query = $this->db->query("SELECT et.idequipo,et.idjugador,et.cantidad_tarjetas_amarillas,et.cantidad_tarjetas_rojas,eq.nombre,ju.nombre_completo 
            FROM tfc_dev.tfc_estadisticas_jugador_por_torneo et 
            join tfc_dev.tfc_equipo eq on et.idequipo=eq.id 
            join tfc_dev.tfc_jugador ju on et.idjugador=ju.id 
            where et.idtorneo=1 and (et.cantidad_tarjetas_amarillas>0 or et.cantidad_tarjetas_rojas>0)order by et.cantidad_tarjetas_rojas desc, et.cantidad_tarjetas_amarillas desc;");
        return $query->result_array();
    }

    public function get_next_5_matchs($idequipo) {
        $query = $this->db->query("SELECT * FROM tfc_dev.tfc_partido WHERE (idequipo1=" . $idequipo . " or idequipo2=" . $idequipo . ") and jugado=false order by fecha asc limit 5");
        return $query->result_array();
    }

    public function get_historial_torneos($idequipo) {
//        $query = $this->db->query("SELECT * FROM tfc_dev.tfc_partido WHERE (idequipo1=" . $idequipo . " or idequipo2=" . $idequipo . ") and jugado=false order by fecha asc limit 5");
//        return $query->result_array();
    }

    public function get_reglamento_from_torneo($idtorneo) {
        $query = $this->db->query("SELECT * FROM tfc_dev.tfc_torneo_reglamento WHERE idtorneo=" . $idtorneo);
        return $query->row_array();
    }

}