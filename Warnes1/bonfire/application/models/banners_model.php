<?php

class Banners_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_banners_home_by_type($type = 2) {
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = " . $type . " and pa.description = 'home'");
        return $query->result_array();
    }

    public function get_banners_zonas_by_type($type = 2) {
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = " . $type . " and pa.description = 'zonas'");
        return $query->result_array();
    }

    public function get_banners_zona_by_type($idzona, $type = 2) {
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = " . $type . " and pa.description = 'zona' and bp.idelement = " . $idzona . "");
        return $query->result_array();
    }

    public function get_banners_rubros_by_type($type = 2) {
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = " . $type . " and pa.description = 'rubros'");
        return $query->result_array();
    }

    public function get_banners_rubro_by_type($idrubro, $type = 2) {
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = " . $type . " and pa.description = 'rubro' and bp.idelement = " . $idrubro . "");
        return $query->result_array();
    }

    public function get_banners_marcas_by_type($type = 2) {
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = " . $type . " and pa.description = 'marcas'");
        return $query->result_array();
    }

    public function get_banners_marca_by_type($idmarca, $type = 2) {
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = " . $type . " and pa.description = 'marca' and bp.idelement = " . $idmarca . "");
        return $query->result_array();
    }
    
    public function get_banners_by_cliente($idcliente){
        $query = $this->db->query("SELECT ba.* FROM mm_banners ba join mm_banner_page bp on ba.idbanner = bp.idbanner join mm_pages pa on bp.idpage=pa.idpage join mm_banners_types bt on bt.idbannertype = ba.idbannertype where ba.habilitado = true and bt.idbannertype = 2 and pa.description = 'cliente' and bp.idelement = " . $idcliente . "");
        return $query->result_array();
    }

}