<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class content extends Admin_Controller {

    //--------------------------------------------------------------------


    public function __construct() {
        parent::__construct();

        $this->auth->restrict('Administrar_Torneos.Content.View');
        $this->load->model('administrar_torneos_model', null, true);
        $this->load->model('equipos_model', null, true);
        $this->load->model('partidos_model', null, true);
        $this->lang->load('administrar_torneos');

        Assets::add_js(Template::theme_url('js/editors/ckeditor/ckeditor.js'));
//        Assets::add_js(Template::theme_url('js/jquery-ui-1.8.13.min.js'));
//        Assets::add_js(Template::theme_url('js/jquery-1.7.2.js'));
        Template::set_block('sub_nav', 'content/_sub_nav');
    }

    //--------------------------------------------------------------------



    /*
      Method: index()

      Displays a list of form data.
     */
    public function index() {

        // Deleting anything?
        if ($this->input->post_key_exists('delete')) {
            $checked = $this->input->post('checked');

            if (is_array($checked) && count($checked)) {
                $result = FALSE;
                foreach ($checked as $pid) {
                    $result = $this->administrar_torneos_model->delete($pid);
                    $this->equipos_model->delete_equipos_de_torneo($pid);
                    $this->partidos_model->delete_partidos_de_torneo($pid);
                }

                if ($result) {
                    Template::set_message(count($checked) . ' ' . lang('administrar_torneos_delete_success'), 'success');
                } else {
                    Template::set_message(lang('administrar_torneos_delete_failure') . $this->administrar_torneos_model->error, 'error');
                }
            }
        }

        $data = array();
        $data['tiposdetorneos'] = $this->input->post('tiposdetorneos');
        //Filtro segun tipo        
        if ($data['tiposdetorneos'] == 'Abiertos') {
            $records = $this->administrar_torneos_model->find_all_by('archivado', false);
            Template::set('tipostorneosselected', $data['tiposdetorneos']);
        } else if ($data['tiposdetorneos'] == 'Cerrados') {
            $records = $this->administrar_torneos_model->find_all_by('archivado', true);
            Template::set('tipostorneosselected', $data['tiposdetorneos']);
        } else if ($data['tiposdetorneos'] == 'Todos') {
            $records = $this->administrar_torneos_model->find_all();
            Template::set('tipostorneosselected', $data['tiposdetorneos']);
        } else {
            $records = $this->administrar_torneos_model->find_all_by('archivado', false);
        }
        //FIN

        Template::set('records', $records);
        Template::set('toolbar_title', 'Manage ' . $data['tiposdetorneos'] . ' Administrar Torneos');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: create()

      Creates a Administrar Torneos object.
     */
    public function create() {
        $this->auth->restrict('Administrar_Torneos.Content.Create');

        if ($this->input->post_key_exists('save')) {
            if ($insert_id = $this->save_administrar_torneos()) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administrar_torneos_act_create_record') . ': ' . $insert_id . ' : ' . $this->input->ip_address(), 'administrar_torneos');

                Template::set_message(lang('administrar_torneos_create_success'), 'success');
                redirect(SITE_AREA . '/content/administrar_torneos');
            } else {
                Template::set_message(lang('administrar_torneos_create_failure') . $this->administrar_torneos_model->error, 'error');
            }
        }
        Assets::add_module_js('administrar_torneos', 'administrar_torneos.js');

        Template::set('equipos', $this->equipos_model->get_all_equipos());
        Template::set('toolbar_title', lang('administrar_torneos_create') . ' Administrar Torneos');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: edit()

      Allows editing of Administrar Torneos data.
     */
    public function edit() {
        $id = $this->uri->segment(5);

        if (empty($id)) {
            Template::set_message(lang('administrar_torneos_invalid_id'), 'error');
            redirect(SITE_AREA . '/content/administrar_torneos');
        }

        if ($this->input->post_key_exists('save')) {
            $this->auth->restrict('Administrar_Torneos.Content.Edit');

            if ($this->save_administrar_torneos('update', $id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administrar_torneos_act_edit_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'administrar_torneos');

                Template::set_message(lang('administrar_torneos_edit_success'), 'success');
            } else {
                Template::set_message(lang('administrar_torneos_edit_failure') . $this->administrar_torneos_model->error, 'error');
            }
        } else if ($this->input->post_key_exists('delete')) {
            $this->auth->restrict('Administrar_Torneos.Content.Delete');

            if ($this->administrar_torneos_model->delete($id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administrar_torneos_act_delete_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'administrar_torneos');

                Template::set_message(lang('administrar_torneos_delete_success'), 'success');

                redirect(SITE_AREA . '/content/administrar_torneos');
            } else {
                Template::set_message(lang('administrar_torneos_delete_failure') . $this->administrar_torneos_model->error, 'error');
            }
        }
        Template::set('administrar_torneos', $this->administrar_torneos_model->find($id));
        Assets::add_module_js('administrar_torneos', 'administrar_torneos.js');

        Template::set('equipos', $this->equipos_model->get_all_equipos());
        Template::set('equiposseleccionados', $this->equipos_model->get_equipos_from_torneo($id));
        Template::set('toolbar_title', lang('administrar_torneos_edit') . ' Administrar Torneos');
        Template::render();
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    // !PRIVATE METHODS
    //--------------------------------------------------------------------

    /*
      Method: save_administrar_torneos()

      Does the actual validation and saving of form data.

      Parameters:
      $type	- Either "insert" or "update"
      $id		- The ID of the record to update. Not needed for inserts.

      Returns:
      An INT id for successful inserts. If updating, returns TRUE on success.
      Otherwise, returns FALSE.
     */
    private function save_administrar_torneos($type = 'insert', $id = 0) {
        if ($type == 'update') {
            $_POST['id'] = $id;
        }


        $this->form_validation->set_rules('administrar_torneos_nombre', 'Nombre', 'required|max_length[200]');
        $this->form_validation->set_rules('administrar_torneos_categoria', 'Categoria', 'required|max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_logo_chico', 'Logo Chico', 'max_length[200]');
        $this->form_validation->set_rules('administrar_torneos_logo_grande', 'Logo Grande', 'max_length[200]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_tarjetas_amarillas', 'Cantidad Tarjetas Amarillas', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_fechas', 'Cantidad Fechas', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_partidos', 'Cantidad Partidos', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_equipos', 'Cantidad Equipos', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_tarjetas_rojas', 'Cantidad Tarjetas Rojas', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_goles', 'Cantidad Goles', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_equipos_ascienden', 'Cantidad Equipos Ascienden', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cantidad_equipos_descienden', 'Cantidad Equipos Descienden', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_archivado', 'Archivado', 'max_length[1]');
        $this->form_validation->set_rules('administrar_torneos_informaciongeneral', 'Informaciongeneral', '');
        $this->form_validation->set_rules('administrar_torneos_reglamento', 'Reglamento', '');
        $this->form_validation->set_rules('administrar_torneos_ida_y_vuelta_grupo', 'Ida Y Vuelta Grupo', 'max_length[1]');
        $this->form_validation->set_rules('administrar_torneos_ida_y_vuelta_llave', 'Ida Y Vuelta Llave', 'max_length[1]');
        $this->form_validation->set_rules('administrar_torneos_group_size', 'Group Size', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cant_pass_to_llave', 'Cant Pass To Llave', 'max_length[11]');
        $this->form_validation->set_rules('administrar_torneos_cant_fases', 'Cant Fases', 'max_length[11]');

        if ($this->form_validation->run() === FALSE) {
            return FALSE;
        }

        // make sure we only pass in the fields we want

        $data = array();

        $data['nombre'] = $this->input->post('administrar_torneos_nombre');
        $fixturepartidos = $this->input->post('fixtureresult');
        $fixtureordenequipos = $this->input->post('fixtureordenequipos');

        $data['categoria'] = intval($this->input->post('administrar_torneos_categoria'));
        $data['logo_chico'] = $this->input->post('administrar_torneos_logo_chico');
        $data['logo_grande'] = $this->input->post('administrar_torneos_logo_grande');
        $data['cantidad_tarjetas_amarillas'] = intval($this->input->post('administrar_torneos_cantidad_tarjetas_amarillas'));
        $data['cantidad_fechas'] = intval($this->input->post('administrar_torneos_cantidad_fechas'));
        $data['cantidad_partidos'] = intval($this->input->post('administrar_torneos_cantidad_partidos'));
        $data['cantidad_equipos'] = intval($this->input->post('administrar_torneos_cantidad_equipos'));
        $data['cantidad_tarjetas_rojas'] = intval($this->input->post('administrar_torneos_cantidad_tarjetas_rojas'));
        $data['cantidad_goles'] = intval($this->input->post('administrar_torneos_cantidad_goles'));
        $data['cantidad_equipos_ascienden'] = intval($this->input->post('administrar_torneos_cantidad_equipos_ascienden'));
        $data['cantidad_equipos_descienden'] = intval($this->input->post('administrar_torneos_cantidad_equipos_descienden'));
        $data['archivado'] = $this->input->post('administrar_torneos_archivado');
        $data['informaciongeneral'] = $this->input->post('administrar_torneos_informaciongeneral');
        $data['reglamento'] = $this->input->post('administrar_torneos_reglamento');
        $data['ida_y_vuelta_grupo'] = $this->input->post('administrar_torneos_ida_y_vuelta_grupo');
        $data['ida_y_vuelta_llave'] = $this->input->post('administrar_torneos_ida_y_vuelta_llave');
        $data['group_size'] = intval($this->input->post('administrar_torneos_group_size'));
        $data['cant_pass_to_llave'] = intval($this->input->post('administrar_torneos_cant_pass_to_llave'));
        $data['cant_fases'] = intval($this->input->post('administrar_torneos_cant_fases'));

        if ($type == 'insert') {
            $id = $this->administrar_torneos_model->insert($data);

            if (is_numeric($id)) {
                $return = $id;
            } else {
                $return = FALSE;
            }

            foreach ($_POST["equipoelegidos"] as $equipo) {
                $this->equipos_model->agregar_equipo_a_torneo($id, $equipo);
            }
            $this->partidos_model->save_fixture($id,$fixturepartidos, $fixtureordenequipos);
        } else if ($type == 'update') {
            $return = $this->administrar_torneos_model->update($id, $data);
            $this->equipos_model->actualizar_equipos_en_torneo($id, $_POST["equipoelegidos"]);
        }

        return $return;
    }

    //--------------------------------------------------------------------
}