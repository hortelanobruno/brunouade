<?php

class Equipos_model extends CI_Model {

    public function __construct() {
        $this->load->database();
    }

    public function get_equipos_from_torneo($idtorneo) {
        $query = $this->db->query("SELECT eq.*,et.baja FROM tfc_equipo eq join tfc_equipos_torneo et on eq.id=et.idequipo where et.idtorneo=" . $idtorneo);
        return $query->result_array();
    }

    public function get_all_equipos() {
        $query = $this->db->query("SELECT * FROM tfc_equipo");
        return $query->result_array();
    }

    public function get_equipo($idequipo) {
        $query = $this->db->query("SELECT eq.* FROM tfc_equipo eq where eq.id=" . $idequipo);
        return $query->row_array();
    }
    
    public function get_equipo_by_name($name) {
        $query = $this->db->query("SELECT eq.* FROM tfc_equipo eq where eq.nombre='" . $name."'");
        return $query->row_array();
    }
    
    public function get_jugador_by_nombre_completo($nombre_completo) {
        $query = $this->db->query("SELECT * FROM tfc_jugador where nombre_completo='" . $nombre_completo."'");
        return $query->row_array();
    }

    public function get_jugadores_from_equipo($idequipo) {
        $query = $this->db->query("SELECT * FROM tfc_jugador where idequipo=" . $idequipo." order by nombre_completo asc");
        return $query->result_array();
    }

    public function get_delegado_from_equipo($idequipo) {
        $query = $this->db->query("SELECT * FROM tfc_jugador where delegado=1 and idequipo=" . $idequipo);
        return $query->row_array();
    }

    public function agregar_equipos_a_torneo($idtorneo, $equipos) {
        foreach ($equipos as $equipo) {
            $this->db->query("INSERT INTO tfc_equipos_torneo values(" . $idtorneo . "," . $equipo . ",false);");
        }
    }

    public function agregar_equipo_a_torneo($idtorneo, $equipo) {
        $this->db->query("INSERT INTO tfc_equipos_torneo values(" . $idtorneo . "," . $equipo . ",false);");
    }

    public function delete_equipos_de_torneo($idtorneo) {
        $this->db->query("DELETE FROM tfc_equipos_torneo where idtorneo=" . $idtorneo);
    }

    public function actualizar_equipos_en_torneo($idtorneo, $equipos) {
        $this->delete_equipos_de_torneo($idtorneo);
        foreach ($equipos as $equipo) {
            $this->agregar_equipo_a_torneo($idtorneo, $equipo);
        }
    }
    
    private function create_jugador_from_import($nombre_completo,$nombre,$apellido,$equipoid){
        $this->db->query("INSERT INTO tfc_jugador (nombre_completo,nombre,apellido,idequipo) values('".$nombre_completo."','".$nombre."','".$apellido."',".$equipoid.");");
    }
    
    private function update_jugador_from_import($jugadorid,$equipoid){
        $this->db->query("UPDATE tfc_jugador set idequipo=".$equipoid." where id=".$jugadorid);
    }
    
    public function importJugadores($fileContent){
        $datos = explode(",", $fileContent);
        
        
        $row = 0;
        $cantjugnew=0;
        $cantjugupda=0;
        $result='';
        for ($i=0 ; $i < count($datos) ; $i++){
            $row++;
            $nombre_completo = trim($datos[$i++]);
            $nombre = trim($datos[$i++]);
            $apellido = trim($datos[$i++]);
            $equipo = trim($datos[$i]);
            
            $equipodb = $this->get_equipo_by_name($equipo);
            
            if(isset($equipodb)&&count($equipodb)>0){
                $jugadordb = $this->get_jugador_by_nombre_completo($nombre_completo);
                if(isset($jugadordb)&&count($jugadordb)>0){
                    //Existe, le cambio el equipo
                    
                    if ($jugadordb['idequipo'] != $equipodb['id']){
                        $result = $result. 'Update jugador: '.$nombre_completo.'. Fila: '.$row.'.';
                        $cantjugupda++;
                        $this->update_jugador_from_import($jugadordb['id'],$equipodb['id']);
                    }
                }else{
                    //No existe, lo creo
                    $result = $result. 'No existe jugador: '.$nombre_completo.'. Fila: '.$row.'.';
                    $cantjugnew++;
                    $this->create_jugador_from_import($nombre_completo,$nombre,$apellido,$equipodb['id']);
                }
            }else{
                $result = $result.'Invalid equipo: '.$equipo.'. Fila: '.$row.'.';
            }
            
//            $result = $result. $nombre_completo.' '.$nombre.' '.$apellido.' '.$equipo.' ';
        }
            
        $result = $result.'Jugadores Nuevos: '.$cantjugnew.'. Jugadores actualizados: '.$cantjugupda.'. Total: '.$row.'.';
                
        return $result;
    }

}