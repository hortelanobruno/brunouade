<?php

class Noticias_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_last_10_news() {
        $query = $this->db->query("SELECT * FROM tfc_noticias order by fecha desc limit 10");
        return $query->result_array();
    }

}