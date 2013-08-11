<?php

class Equipos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_equipos_from_torneo($idtorneo) {
        $query = $this->db->query("SELECT eq.*,et.baja FROM tfc_dev.tfc_equipo eq join tfc_dev.tfc_equipos_torneo et on eq.id=et.idequipo where et.idtorneo=".$idtorneo);
        return $query->result_array();
    }
    

}