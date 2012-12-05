<?php

class Noticias_model extends CI_Model {

    const constant = 'valor constante';
    
    public function __construct() {
        $this->load->database();
    }

    public function get_ultimas_noticias($cant = 8) {
        $query = $this->db->query("select tabl.* from (select noti.idnoticia,noti.titulo,noti.epigrafe,fo.url from mm_noticias noti join mm_noticias_fotos nofo on noti.idnoticia = nofo.idnoticias join mm_fotos fo on nofo.idfoto = fo.idfoto where noti.habilitado = true order by fo.prioridad desc) as tabl group by tabl.idnoticia limit ".$cant);
        return $query->result_array();
    }

    public function get_noticia($idnoticia) {
        //select noti.idnoticia,noti.titulo,noti.epigrafe,noti.descripcion,noti.fechanoticia from mm_noticias noti where noti.idnoticia = 1
        $query = $this->db->query("select noti.idnoticia,noti.titulo,noti.epigrafe,noti.descripcion,DATE_FORMAT(noti.fechanoticia, '%d-%m-%Y') as fechanoticia from mm_noticias noti where noti.habilitado = true and noti.idnoticia = ".$idnoticia);
        foreach ($query->result_array() as $row) {
            return $row;
        }
    }
    
    public function get_fotos_by_noticia($idnoticia) {
        //select fo.url,fo.titulo from mm_noticias_fotos nofo join mm_fotos fo on nofo.idfoto = fo.idfoto where nofo.idnoticias = 1 order by fo.prioridad desc
        $query = $this->db->query("select fo.url,fo.titulo from mm_noticias_fotos nofo join mm_fotos fo on nofo.idfoto = fo.idfoto where fo.habilitado = true and nofo.idnoticias = ".$idnoticia." order by fo.prioridad desc");
        return $query->result_array();
    }
    
    public function get_fecha_para_archivos(){
        //SELECT MONTHNAME(fechanoticia) as mes,YEAR(fechanoticia) as anio from mm_noticias group by mes,anio;
        $query = $this->db->query("SELECT ELT(DATE_FORMAT(fechanoticia,'%m'),'Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre') as mes,YEAR(fechanoticia) as anio from mm_noticias group by mes,anio");
        return $query->result_array();
    }
    
    public function get_noticias_by_archivo($archivo,$cant = 8){
        //select tabl.* from (select noti.idnoticia,noti.titulo,noti.epigrafe,fo.url from mm_noticias noti join mm_noticias_fotos nofo on noti.idnoticia = nofo.idnoticias join mm_fotos fo on nofo.idfoto = fo.idfoto where monthname(noti.fechanoticia) = 'January' and year(noti.fechanoticia) = 2011 order by fo.prioridad desc) as tabl group by tabl.idnoticia limit 10
        $myArray = explode('-', $archivo);
        $mes = $myArray[0];
        $anio = $myArray[1];
        $query = $this->db->query("select tabl.* from (select noti.idnoticia,noti.titulo,noti.epigrafe,fo.url from mm_noticias noti join mm_noticias_fotos nofo on noti.idnoticia = nofo.idnoticias join mm_fotos fo on nofo.idfoto = fo.idfoto where noti.habilitado = true and ELT(DATE_FORMAT(fechanoticia,'%m'),'Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre') = '".$mes."' and year(noti.fechanoticia) = ".$anio." order by fo.prioridad desc) as tabl group by tabl.idnoticia limit ".$cant);
        return $query->result_array();
    }

}