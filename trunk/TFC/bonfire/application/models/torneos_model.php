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
        $query = $this->db->query("SELECT * FROM tfc_torneo where id=".$idtorneo);
        return $query->row_array();
    }

}