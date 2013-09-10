<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class content extends Admin_Controller {

    //--------------------------------------------------------------------


    public function __construct() {
        parent::__construct();

        $this->auth->restrict('Administracion_Tabla_Posiciones.Content.View');
        $this->load->model('administracion_tabla_posiciones_model', null, true);
        $this->load->model('torneos_model', null, true);
        $this->load->model('equipos_model', null, true);
        $this->lang->load('administracion_tabla_posiciones');

        Template::set_block('sub_nav', 'content/_sub_nav');
    }

    //--------------------------------------------------------------------



    /*
      Method: index()

      Displays a list of form data.
     */
    public function index() {

        $torneos = $this->torneos_model->get_all_torneos();

        // Deleting anything?
        if ($this->input->post_key_exists('delete')) {
            $checked = $this->input->post('checked');

            if (is_array($checked) && count($checked)) {
                $result = FALSE;
                foreach ($checked as $pid) {
                    $result = $this->administracion_tabla_posiciones_model->delete($pid);
                }

                if ($result) {
                    Template::set_message(count($checked) . ' ' . lang('administracion_tabla_posiciones_delete_success'), 'success');
                } else {
                    Template::set_message(lang('administracion_tabla_posiciones_delete_failure') . $this->administracion_tabla_posiciones_model->error, 'error');
                }
            }
        }

        $data = array();
        $data['torneo'] = $this->input->post('torneo');
        if ($data['torneo'] == null) {
            $data['torneo'] = $torneos[0]['id'];
        }
        $equipos = $this->equipos_model->get_equipos_from_torneo($data['torneo']);
        Template::set('equipos', $equipos);
        $data['ronda'] = $this->input->post('ronda');
        if ($data['torneo'] != null) {
            Template::set('torneoselected', $data['torneo']);
            $torneodata = $this->torneos_model->get_torneo($data['torneo']);
            Template::set('rondas', $torneodata['cant_fases']);

            if ($data['ronda'] != null) {
                Template::set('rondaselected', $data['ronda']);
//                $records = $this->administracion_tabla_posiciones_model->find_all_by(array('idtorneo' => $data['torneo'], 'idfase' => $data['ronda']));
                $records = $this->administracion_tabla_posiciones_model->select('idtorneo, idfase, idequipo, puntos, partidos_jugados, partidos_ganados, partidos_empatados, partidos_perdidos, goles_a_favor, goles_en_contra, (goles_a_favor - goles_en_contra) diff')->order_by(array(
                            'puntos' => 'desc',
                            'diff' => 'desc',
                            'goles_a_favor' => 'desc'
                        ))->find_all_by(array('idtorneo' => $data['torneo'], 'idfase' => $data['ronda']));
            } else {
                $records = $this->administracion_tabla_posiciones_model->select('idtorneo, idfase, idequipo, puntos, partidos_jugados, partidos_ganados, partidos_empatados, partidos_perdidos, goles_a_favor, goles_en_contra, (goles_a_favor - goles_en_contra) diff')->order_by(array(
                            'puntos' => 'desc',
                            'diff' => 'desc',
                            'goles_a_favor' => 'desc'
                        ))->find_all_by('idtorneo', $data['torneo']);
//                $records = $this->torneos_model->get_tabla_from_torneo($data['torneo']);
            }
        } else {
            $records = $this->administracion_tabla_posiciones_model->find_all();
        }



        Template::set('records', $records);
        Template::set('torneos', $torneos);
        Template::set('toolbar_title', 'Manage Administrar Tabla Posiciones');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: create()

      Creates a Administracion Tabla Posiciones object.
     */
    public function create() {
        $this->auth->restrict('Administracion_Tabla_Posiciones.Content.Create');

        if ($this->input->post_key_exists('save')) {
            if ($insert_id = $this->save_administracion_tabla_posiciones()) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administracion_tabla_posiciones_act_create_record') . ': ' . $insert_id . ' : ' . $this->input->ip_address(), 'administracion_tabla_posiciones');

                Template::set_message(lang('administracion_tabla_posiciones_create_success'), 'success');
                redirect(SITE_AREA . '/content/administracion_tabla_posiciones');
            } else {
                Template::set_message(lang('administracion_tabla_posiciones_create_failure') . $this->administracion_tabla_posiciones_model->error, 'error');
            }
        }
        Assets::add_module_js('administracion_tabla_posiciones', 'administracion_tabla_posiciones.js');

        Template::set('toolbar_title', lang('administracion_tabla_posiciones_create') . ' Administracion Tabla Posiciones');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: edit()

      Allows editing of Administracion Tabla Posiciones data.
     */
    public function edit() {
        $id = $this->uri->segment(5);
        $idfase = $this->uri->segment(6);
        $idequipo = $this->uri->segment(7);
        $equipo = $this->equipos_model->get_equipo($idequipo);
        Template::set('equipo', $equipo);

        if (empty($id)) {
            Template::set_message(lang('administracion_tabla_posiciones_invalid_id'), 'error');
            redirect(SITE_AREA . '/content/administracion_tabla_posiciones');
        }

        if ($this->input->post_key_exists('save')) {
            $this->auth->restrict('Administracion_Tabla_Posiciones.Content.Edit');

            if ($this->save_administracion_tabla_posiciones('update', $id, $idfase, $idequipo)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administracion_tabla_posiciones_act_edit_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'administracion_tabla_posiciones');

                Template::set_message(lang('administracion_tabla_posiciones_edit_success'), 'success');
            } else {
                Template::set_message(lang('administracion_tabla_posiciones_edit_failure') . $this->administracion_tabla_posiciones_model->error, 'error');
            }
        } else if ($this->input->post_key_exists('delete')) {
            $this->auth->restrict('Administracion_Tabla_Posiciones.Content.Delete');
//            if ($this->administracion_tabla_posiciones_model->delete_with_complete_key($id)) {
            if ($this->torneos_model->delete_from_tabla_posiciones($id, $idfase, $idequipo)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administracion_tabla_posiciones_act_delete_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'administracion_tabla_posiciones');

                Template::set_message(lang('administracion_tabla_posiciones_delete_success'), 'success');

                redirect(SITE_AREA . '/content/administracion_tabla_posiciones');
            } else {
                Template::set_message(lang('administracion_tabla_posiciones_delete_failure') . $this->administracion_tabla_posiciones_model->error, 'error');
            }
        }
        Template::set('administracion_tabla_posiciones', $this->administracion_tabla_posiciones_model->find_by(array('idtorneo' => $id, 'idfase' => $idfase, 'idequipo' => $idequipo)));
        Assets::add_module_js('administracion_tabla_posiciones', 'administracion_tabla_posiciones.js');

        Template::set('toolbar_title', lang('administracion_tabla_posiciones_edit') . ' Administracion Tabla Posiciones');
        Template::render();
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    // !PRIVATE METHODS
    //--------------------------------------------------------------------

    /*
      Method: save_administracion_tabla_posiciones()

      Does the actual validation and saving of form data.

      Parameters:
      $type	- Either "insert" or "update"
      $id		- The ID of the record to update. Not needed for inserts.

      Returns:
      An INT id for successful inserts. If updating, returns TRUE on success.
      Otherwise, returns FALSE.
     */
    private function save_administracion_tabla_posiciones($type = 'insert', $id = 0, $idfase, $idequipo) {
        if ($type == 'update') {
            $_POST['idtorneo'] = $id;
        }


        $this->form_validation->set_rules('administracion_tabla_posiciones_idfase', 'Idfase', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_idequipo', 'Idequipo', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_puntos', 'Puntos', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_partidos_jugados', 'Partidos Jugados', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_partidos_ganados', 'Partidos Ganados', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_partidos_empatados', 'Partidos Empatados', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_partidos_perdidos', 'Partidos Perdidos', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_goles_a_favor', 'Goles A Favor', 'max_length[11]');
        $this->form_validation->set_rules('administracion_tabla_posiciones_goles_en_contra', 'Goles En Contra', 'max_length[11]');

        if ($this->form_validation->run() === FALSE) {
            return FALSE;
        }

        // make sure we only pass in the fields we want

        $data = array();
        $data['idfase'] = $this->input->post('administracion_tabla_posiciones_idfase');
        $data['idequipo'] = $this->input->post('administracion_tabla_posiciones_idequipo');
        $data['puntos'] = $this->input->post('administracion_tabla_posiciones_puntos');
        $data['partidos_jugados'] = $this->input->post('administracion_tabla_posiciones_partidos_jugados');
        $data['partidos_ganados'] = $this->input->post('administracion_tabla_posiciones_partidos_ganados');
        $data['partidos_empatados'] = $this->input->post('administracion_tabla_posiciones_partidos_empatados');
        $data['partidos_perdidos'] = $this->input->post('administracion_tabla_posiciones_partidos_perdidos');
        $data['goles_a_favor'] = $this->input->post('administracion_tabla_posiciones_goles_a_favor');
        $data['goles_en_contra'] = $this->input->post('administracion_tabla_posiciones_goles_en_contra');

        if ($type == 'insert') {
            $id = $this->administracion_tabla_posiciones_model->insert($data);

            if (is_numeric($id)) {
                $return = $id;
            } else {
                $return = FALSE;
            }
        } else if ($type == 'update') {
//            $return = $this->administracion_tabla_posiciones_model->update($id, $data);
            $this->torneos_model->update_tabla_posiciones($id, $idfase, $idequipo, $data);
            return true;
        }

        return $return;
    }

    //--------------------------------------------------------------------
}