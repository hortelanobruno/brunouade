<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class content extends Admin_Controller {

    //--------------------------------------------------------------------


    public function __construct() {
        parent::__construct();

        $this->auth->restrict('Noticias.Content.View');
        $this->load->model('noticias_model', null, true);
        $this->load->model('torneos_model', null, true);
        $this->lang->load('noticias');

        Assets::add_js(Template::theme_url('js/editors/ckeditor/ckeditor.js'));
        Assets::add_css('flick/jquery-ui-1.8.13.custom.css');
        Assets::add_js('jquery-ui-1.8.13.min.js');
        Assets::add_css('jquery-ui-timepicker.css');
        Assets::add_js('jquery-ui-timepicker-addon.js');
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
                    $result = $this->noticias_model->delete($pid);
                }

                if ($result) {
                    Template::set_message(count($checked) . ' ' . lang('noticias_delete_success'), 'success');
                } else {
                    Template::set_message(lang('noticias_delete_failure') . $this->noticias_model->error, 'error');
                }
            }
        }

        $records = $this->noticias_model->find_all();

        Template::set('torneos', $this->torneos_model->get_all_torneos());
        Template::set('records', $records);
        Template::set('toolbar_title', 'Manage Noticias');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: create()

      Creates a Noticias object.
     */
    public function create() {
        $this->auth->restrict('Noticias.Content.Create');

        if ($this->input->post_key_exists('save')) {
            if ($insert_id = $this->save_noticias()) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('noticias_act_create_record') . ': ' . $insert_id . ' : ' . $this->input->ip_address(), 'noticias');

                Template::set_message(lang('noticias_create_success'), 'success');
                redirect(SITE_AREA . '/content/noticias');
            } else {
                Template::set_message(lang('noticias_create_failure') . $this->noticias_model->error, 'error');
            }
        }
        Assets::add_module_js('noticias', 'noticias.js');

        Template::set('torneos', $this->torneos_model->get_all_torneos());
        Template::set('toolbar_title', lang('noticias_create') . ' Noticias');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: edit()

      Allows editing of Noticias data.
     */
    public function edit() {
        $id = $this->uri->segment(5);

        if (empty($id)) {
            Template::set_message(lang('noticias_invalid_id'), 'error');
            redirect(SITE_AREA . '/content/noticias');
        }

        if ($this->input->post_key_exists('save')) {
            $this->auth->restrict('Noticias.Content.Edit');

            if ($this->save_noticias('update', $id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('noticias_act_edit_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'noticias');

                Template::set_message(lang('noticias_edit_success'), 'success');
            } else {
                Template::set_message(lang('noticias_edit_failure') . $this->noticias_model->error, 'error');
            }
        } else if ($this->input->post_key_exists('delete')) {
            $this->auth->restrict('Noticias.Content.Delete');

            if ($this->noticias_model->delete($id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('noticias_act_delete_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'noticias');

                Template::set_message(lang('noticias_delete_success'), 'success');

                redirect(SITE_AREA . '/content/noticias');
            } else {
                Template::set_message(lang('noticias_delete_failure') . $this->noticias_model->error, 'error');
            }
        }
        Template::set('noticias', $this->noticias_model->find($id));
        Template::set('torneos', $this->torneos_model->get_all_torneos());
        Assets::add_module_js('noticias', 'noticias.js');

        Template::set('toolbar_title', lang('noticias_edit') . ' Noticias');
        Template::render();
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    // !PRIVATE METHODS
    //--------------------------------------------------------------------

    /*
      Method: save_noticias()

      Does the actual validation and saving of form data.

      Parameters:
      $type	- Either "insert" or "update"
      $id		- The ID of the record to update. Not needed for inserts.

      Returns:
      An INT id for successful inserts. If updating, returns TRUE on success.
      Otherwise, returns FALSE.
     */
    private function save_noticias($type = 'insert', $id = 0) {
        if ($type == 'update') {
            $_POST['idnoticias'] = $id;
        }


        $this->form_validation->set_rules('noticias_idtorneo', 'Idtorneo', 'max_length[11]');
        $this->form_validation->set_rules('noticias_titulo', 'Titulo', 'max_length[200]');
        $this->form_validation->set_rules('noticias_subtitulo', 'Subtitulo', 'max_length[500]');
        $this->form_validation->set_rules('noticias_contenido', 'Contenido', '');
        $this->form_validation->set_rules('noticias_fecha', 'Fecha', 'max_length[200]');
        $this->form_validation->set_rules('noticias_foto_portada', 'Foto Portada', 'max_length[200]');

        if ($this->form_validation->run() === FALSE) {
            return FALSE;
        }

        // make sure we only pass in the fields we want

        $data = array();
        $data['idtorneo'] = $this->input->post('noticias_idtorneo');
        $data['titulo'] = $this->input->post('noticias_titulo');
        $data['subtitulo'] = $this->input->post('noticias_subtitulo');
        $data['contenido'] = $this->input->post('noticias_contenido');
        $data['fecha'] = $this->input->post('noticias_fecha') ? $this->input->post('noticias_fecha') : '0000-00-00 00:00:00';
        $data['foto_portada'] = $this->input->post('noticias_foto_portada');

        if ($type == 'insert') {
            $id = $this->noticias_model->insert($data);

            if (is_numeric($id)) {
                $return = $id;
            } else {
                $return = FALSE;
            }
        } else if ($type == 'update') {
            $return = $this->noticias_model->update($id, $data);
        }

        return $return;
    }

    //--------------------------------------------------------------------
}