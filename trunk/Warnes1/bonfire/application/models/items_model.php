<?php

class Items_model extends CI_Model {

    const ACORDION_RUBROS_SIZE = 'ACORDION_RUBROS_SIZE';
    const ACORDION_MARCAS_SIZE = 'ACORDION_MARCAS_SIZE';
    const ACORDION_ZONAS_SIZE = 'ACORDION_ZONAS_SIZE';
    
    public function __construct() {
        $this->load->database();
    }

    public function get_rubros() {
        $query = $this->db->query("SELECT * FROM mm_rubros where habilitado = true order by descripcion");
        return $query->result_array();
    }
    
    public function get_marcas() {
        $query = $this->db->query("SELECT * FROM mm_marcas where habilitado = true order by descripcion");
        return $query->result_array();
    }
    
    public function get_zonas() {
        $query = $this->db->query("SELECT * FROM mm_zonas where habilitado = true order by descripcion");
        return $query->result_array();
    }
    
    public function get_rubros_by_prioridad($prioridad) {
        $query = $this->db->query("SELECT * FROM mm_rubros where prioridad = ".$prioridad." where habilitado = true order by descripcion");
        return $query->result_array();
    }
    
    public function get_marcas_by_prioridad($prioridad) {
        $query = $this->db->query("SELECT * FROM mm_marcas where prioridad = ".$prioridad." where habilitado = true order by descripcion");
        return $query->result_array();
    }
    
    public function get_zonas_by_prioridad($prioridad) {
        $query = $this->db->query("SELECT * FROM mm_zonas where prioridad = ".$prioridad." where habilitado = true order by descripcion");
        return $query->result_array();
    }
    
    public function get_rubros_for_acordion() {
        $query = $this->db->query("SELECT value FROM mm_portal_config where property_name = '".self::ACORDION_RUBROS_SIZE."'");
        $value = $query->row_array();
        $query = $this->db->query("SELECT * FROM (SELECT * FROM mm_rubros where habilitado = true order by prioridad desc limit ".$value['value'].") RERE ORDER BY RERE.descripcion");
        return $query->result_array();
    }
    
    public function get_marcas_for_acordion() {
        $query = $this->db->query("SELECT value FROM mm_portal_config where property_name = '".self::ACORDION_MARCAS_SIZE."'");
        $value = $query->row_array();
        $query = $this->db->query("SELECT * FROM (SELECT * FROM mm_marcas where habilitado = true order by prioridad desc limit ".$value['value'].") RERE ORDER BY RERE.descripcion");
        return $query->result_array();
    }
    
    public function get_zonas_for_acordion() {
        $query = $this->db->query("SELECT value FROM mm_portal_config where property_name = '".self::ACORDION_ZONAS_SIZE."'");
        $value = $query->row_array();
        $query = $this->db->query("SELECT * FROM (SELECT * FROM mm_zonas where habilitado = true order by prioridad desc limit ".$value['value'].") RERE ORDER BY RERE.descripcion");
        return $query->result_array();
    }
    
    public function get_rubro($idrubro) {
        $query = $this->db->query("SELECT * FROM mm_rubros where idrubro = ".$idrubro." and habilitado = true");
        return $query->row_array();
    }
    
    public function get_marca($idmarca) {
        $query = $this->db->query("SELECT * FROM mm_marcas where idmarca = ".$idmarca." and habilitado = true");
        return $query->row_array();
    }
    
    public function get_zona($idzona) {
        $query = $this->db->query("SELECT * FROM mm_zonas where idzona = ".$idzona." and habilitado = true");
        return $query->row_array();
    }

}