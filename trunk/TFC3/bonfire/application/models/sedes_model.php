<?php

class Sedes_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_sedes() {
        $query = $this->db->query("SELECT * FROM tfc_sede");
        return $query->result_array();
    }

    public function get_sede($idsede) {
        $query = $this->db->query("SELECT * FROM tfc_sede where idsede = " . $idsede);
        return $query->row_array();
    }

}