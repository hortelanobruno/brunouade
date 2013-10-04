<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class content extends Admin_Controller {

    //--------------------------------------------------------------------


    public function __construct() {
        parent::__construct();

        $this->auth->restrict('Jugadores.Content.View');
        $this->load->model('jugadores_model', null, true);
        $this->load->model('equipos_model', null, true);
        $this->lang->load('jugadores');

        Template::set_block('sub_nav', 'content/_sub_nav');
    }

    //--------------------------------------------------------------------



    /*
      Method: index()

      Displays a list of form data.
     */
    public function index() {

        $equipos = $this->equipos_model->get_all_equipos();

        // Deleting anything?
        if ($this->input->post_key_exists('delete')) {
            $checked = $this->input->post('checked');

            if (is_array($checked) && count($checked)) {
                $result = FALSE;
                foreach ($checked as $pid) {
                    $result = $this->jugadores_model->delete($pid);
                }

                if ($result) {
                    Template::set_message(count($checked) . ' ' . lang('jugadores_delete_success'), 'success');
                } else {
                    Template::set_message(lang('jugadores_delete_failure') . $this->jugadores_model->error, 'error');
                }
            }
        }

//        $records = $this->jugadores_model->find_all();
        $data = array();
        $data['equipo'] = $this->input->post('equipo');
        if ($data['equipo'] == null) {
            $data['equipo'] = $equipos[0]['id'];
        }

        if ($data['equipo'] != null) {
            Template::set('equiposelected', $data['equipo']);

            $records = $this->jugadores_model->find_all_by(array('idequipo' => $data['equipo']));
        }

        $data['importjugadores'] = $this->input->post('importcontent');
        if ($data['importjugadores'] != null) {
            Template::set('resultimport', $this->equipos_model->importJugadores($data['importjugadores']));
        }

        Template::set('records', $records);
        Template::set('equipos', $equipos);
        Template::set('toolbar_title', 'Manage Jugadores');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: create()

      Creates a Jugadores object.
     */
    public function create() {
        $this->auth->restrict('Jugadores.Content.Create');

        if ($this->input->post_key_exists('save')) {
            if ($insert_id = $this->save_jugadores()) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('jugadores_act_create_record') . ': ' . $insert_id . ' : ' . $this->input->ip_address(), 'jugadores');

                Template::set_message(lang('jugadores_create_success'), 'success');
                redirect(SITE_AREA . '/content/jugadores');
            } else {
                Template::set_message(lang('jugadores_create_failure') . $this->jugadores_model->error, 'error');
            }
        }
        Assets::add_module_js('jugadores', 'jugadores.js');

        Template::set('equipos', $this->equipos_model->get_all_equipos());
        Template::set('toolbar_title', lang('jugadores_create') . ' Jugadores');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: edit()

      Allows editing of Jugadores data.
     */
    public function edit() {
        $id = $this->uri->segment(5);

        if (empty($id)) {
            Template::set_message(lang('jugadores_invalid_id'), 'error');
            redirect(SITE_AREA . '/content/jugadores');
        }

        if ($this->input->post_key_exists('save')) {
            $this->auth->restrict('Jugadores.Content.Edit');

            if ($this->save_jugadores('update', $id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('jugadores_act_edit_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'jugadores');

                Template::set_message(lang('jugadores_edit_success'), 'success');
            } else {
                Template::set_message(lang('jugadores_edit_failure') . $this->jugadores_model->error, 'error');
            }
        } else if ($this->input->post_key_exists('delete')) {
            $this->auth->restrict('Jugadores.Content.Delete');

            if ($this->jugadores_model->delete($id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('jugadores_act_delete_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'jugadores');

                Template::set_message(lang('jugadores_delete_success'), 'success');

                redirect(SITE_AREA . '/content/jugadores');
            } else {
                Template::set_message(lang('jugadores_delete_failure') . $this->jugadores_model->error, 'error');
            }
        }
        Template::set('jugadores', $this->jugadores_model->find($id));
        Assets::add_module_js('jugadores', 'jugadores.js');
        Template::set('equipos', $this->equipos_model->get_all_equipos());
        Template::set('toolbar_title', lang('jugadores_edit') . ' Jugadores');
        Template::render();
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    // !PRIVATE METHODS
    //--------------------------------------------------------------------

    /*
      Method: save_jugadores()

      Does the actual validation and saving of form data.

      Parameters:
      $type	- Either "insert" or "update"
      $id		- The ID of the record to update. Not needed for inserts.

      Returns:
      An INT id for successful inserts. If updating, returns TRUE on success.
      Otherwise, returns FALSE.
     */
    private function save_jugadores($type = 'insert', $id = 0) {
        if ($type == 'update') {
            $_POST['id'] = $id;
        }


        $this->form_validation->set_rules('jugadores_nombre_completo', 'Nombre Completo', 'max_length[200]');
        $this->form_validation->set_rules('jugadores_nombre', 'Nombre', 'max_length[200]');
        $this->form_validation->set_rules('jugadores_apellido', 'Apellido', 'max_length[200]');
        $this->form_validation->set_rules('jugadores_delegado', 'Delegado', 'max_length[1]');
        $this->form_validation->set_rules('jugadores_idequipo', 'Idequipo', 'max_length[11]');
        $this->form_validation->set_rules('jugadores_cantidad_tarjetas_amarillas', 'Cantidad Tarjetas Amarillas', 'max_length[11]');
        $this->form_validation->set_rules('jugadores_cantidad_partidos_jugados', 'Cantidad Partidos Jugados', 'max_length[11]');
        $this->form_validation->set_rules('jugadores_cantidad_tarjetas_rojas', 'Cantidad Tarjetas Rojas', 'max_length[11]');
        $this->form_validation->set_rules('jugadores_cantidad_goles', 'Cantidad Goles', 'max_length[11]');

        if ($this->form_validation->run() === FALSE) {
            return FALSE;
        }

        // make sure we only pass in the fields we want

        $data = array();
        $data['nombre_completo'] = $this->input->post('jugadores_nombre_completo');
        $data['nombre'] = $this->input->post('jugadores_nombre');
        $data['apellido'] = $this->input->post('jugadores_apellido');
        $data['delegado'] = $this->input->post('jugadores_delegado');
        $data['idequipo'] = intval($this->input->post('jugadores_idequipo'));
        $data['cantidad_tarjetas_amarillas'] = intval($this->input->post('jugadores_cantidad_tarjetas_amarillas'));
        $data['cantidad_partidos_jugados'] = intval($this->input->post('jugadores_cantidad_partidos_jugados'));
        $data['cantidad_tarjetas_rojas'] = intval($this->input->post('jugadores_cantidad_tarjetas_rojas'));
        $data['cantidad_goles'] = intval($this->input->post('jugadores_cantidad_goles'));

        if ($type == 'insert') {
            $id = $this->jugadores_model->insert($data);

            if (is_numeric($id)) {
                $return = $id;
            } else {
                $return = FALSE;
            }
        } else if ($type == 'update') {
            $return = $this->jugadores_model->update($id, $data);
        }

        return $return;
    }

    //--------------------------------------------------------------------

}