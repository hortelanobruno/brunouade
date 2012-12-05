<?php

class Clientes_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }
    
    public function get_clientes_by_marca($idmarca) {
        $query = $this->db->query("SELECT cli.* FROM mm_clientes cli join mm_cliente_marca cr on cli.idcliente = cr.idcliente where cli.habilitado = true and cr.idmarca = " . $idmarca . " order by cli.nombre");
        return $query->result_array();
    }
    
    public function get_clientes_by_zona($idzona) {
        $query = $this->db->query("SELECT cli.* FROM mm_clientes cli join mm_cliente_zona cr on cli.idcliente = cr.idcliente where cli.habilitado = true and cr.idzona = " . $idzona . " order by cli.nombre");
        return $query->result_array();
    }

    public function get_clientes_by_rubro($idrubro) {
        $query = $this->db->query("SELECT cli.* FROM mm_clientes cli join mm_cliente_rubro cr on cli.idcliente = cr.idcliente where cli.habilitado = true and cr.idrubro = " . $idrubro . " order by cli.nombre");
        return $query->result_array();
    }

    public function get_cliente($idcliente) {
        $query = $this->db->query("SELECT cli.* FROM mm_clientes cli where cli.habilitado = true and cli.idcliente = " . $idcliente);
        foreach ($query->result_array() as $row) {
            return $row;
        }
    }
    
    public function get_rubros_by_cliente($idcliente){
        $query = $this->db->query("SELECT ru.descripcion FROM mm_rubros ru join mm_cliente_rubro cr on ru.idrubro = cr.idrubro where ru.habilitado = true and cr.idcliente = " . $idcliente . " order by ru.descripcion");
        return $query->result_array();
    }
    
    public function get_marcas_by_cliente($idcliente){
        $query = $this->db->query("SELECT ru.descripcion FROM mm_marcas ru join mm_cliente_marca cr on ru.idmarca = cr.idmarca where ru.habilitado = true and cr.idcliente = " . $idcliente . " order by ru.descripcion");
        return $query->result_array();
    }
    
    public function get_zonas_by_cliente($idcliente){
        $query = $this->db->query("SELECT ru.descripcion FROM mm_zonas ru join mm_cliente_zona cr on ru.idzona = cr.idzona where ru.habilitado = true and cr.idcliente = " . $idcliente . " order by ru.descripcion");
        return $query->result_array();
    }
    

}