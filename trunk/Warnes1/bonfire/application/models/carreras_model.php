<?php

class Carreras_model extends CI_Model {

    const constant = 'valor constante';
    
    public function __construct() {
        $this->load->database();
    }

    public function get_all_categorias() {
        $query = $this->db->query("select idcarrera_categoria, nombre from mm_carrera_categoria");
        return $query->result_array();
    }

    public function get_calendario_by_categoria($idcategoria) {
        //select noti.idnoticia,noti.titulo,noti.epigrafe,noti.descripcion,noti.fechanoticia from mm_noticias noti where noti.idnoticia = 1
        $query = $this->db->query("select distinct(anio) as año from mm_carreras where idcarrera_categoria =".$idcategoria." order by anio desc");
        return $query->result_array();
    }
    
    public function get_carreras_by_categoria_and_año($idcategoria,$año) {
        $query = $this->db->query("select * from mm_carreras where anio = ".$año." and idcarrera_categoria = ".$idcategoria." order by numero_carrera asc");
        return $query->result_array();
    }

}