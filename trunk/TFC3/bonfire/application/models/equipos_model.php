<?php

class Equipos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_equipos_from_torneo($idtorneo) {
        $query = $this->db->query("SELECT eq.*,et.baja FROM tfc_dev.tfc_equipo eq join tfc_dev.tfc_equipos_torneo et on eq.id=et.idequipo where et.idtorneo=" . $idtorneo);
        return $query->result_array();
    }

    public function get_all_equipos() {
        $query = $this->db->query("SELECT * FROM tfc_dev.tfc_equipo");
        return $query->result_array();
    }

    public function get_equipo($idequipo) {
        $query = $this->db->query("SELECT eq.* FROM tfc_dev.tfc_equipo eq where eq.id=" . $idequipo);
        return $query->row_array();
    }

    public function get_jugadores_from_equipo($idequipo) {
        $query = $this->db->query("SELECT * FROM tfc_dev.tfc_jugador where idequipo=" . $idequipo);
        return $query->result_array();
    }

    public function get_delegado_from_equipo($idequipo) {
        $query = $this->db->query("SELECT * FROM tfc_dev.tfc_jugador where delegado=true and idequipo=" . $idequipo);
        return $query->row_array();
    }

}