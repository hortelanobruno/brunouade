<?php

class Noticias_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_last_10_news() {
        $query = $this->db->query("SELECT * FROM tfc_noticias order by fecha desc limit 10");
        return $query->result_array();
    }

    public function get_noticia($idnoticia) {
        $query = $this->db->query("SELECT * FROM tfc_noticias where idnoticias = " . $idnoticia);
        return $query->row_array();
    }
    
    public function get_noticias_by_torneo($idtorneo) {
        $query = $this->db->query("SELECT * FROM tfc_noticias where idtorneo = " . $idtorneo);
        return $query->result_array();
    }

}