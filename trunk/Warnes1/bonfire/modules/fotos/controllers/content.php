<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class content extends Admin_Controller {

    //--------------------------------------------------------------------


    public function __construct() {
        parent::__construct();

        $this->auth->restrict('Fotos.Content.View');
        $this->load->model('fotos_model', null, true);
        $this->lang->load('fotos');

        Template::set_block('sub_nav', 'content/_sub_nav');
    }

    //--------------------------------------------------------------------



    /*
      Method: index()

      Displays a list of form data.
     */
    public function index() {

        // Deleting anything?
        if ($action = $this->input->post('delete')) {
            if ($action == 'Delete') {
                $checked = $this->input->post('checked');

                if (is_array($checked) && count($checked)) {
                    $result = FALSE;
                    foreach ($checked as $pid) {
                        $result = $this->fotos_model->delete($pid);
                    }

                    if ($result) {
                        Template::set_message(count($checked) . ' ' . lang('fotos_delete_success'), 'success');
                    } else {
                        Template::set_message(lang('fotos_delete_failure') . $this->fotos_model->error, 'error');
                    }
                } else {
                    Template::set_message(lang('fotos_delete_error') . $this->fotos_model->error, 'error');
                }
            }
        }

        $records = $this->fotos_model->find_all();

        Template::set('records', $records);
        Template::set('toolbar_title', 'Manage Fotos');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: create()

      Creates a Fotos object.
     */
    public function create() {
        $this->auth->restrict('Fotos.Content.Create');

        if ($this->input->post('submit')) {
            if ($insert_id = $this->save_fotos()) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('fotos_act_create_record') . ': ' . $insert_id . ' : ' . $this->input->ip_address(), 'fotos');

                Template::set_message(lang('fotos_create_success'), 'success');
                Template::redirect(SITE_AREA . '/content/fotos');
            } else {
                Template::set_message(lang('fotos_create_failure') . $this->fotos_model->error, 'error');
            }
        }
        Assets::add_module_js('fotos', 'fotos.js');

        Template::set('toolbar_title', lang('fotos_create') . ' Fotos');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: edit()

      Allows editing of Fotos data.
     */
    public function edit() {
        $this->auth->restrict('Fotos.Content.Edit');

        $id = $this->uri->segment(5);

        if (empty($id)) {
            Template::set_message(lang('fotos_invalid_id'), 'error');
            redirect(SITE_AREA . '/content/fotos');
        }

        if ($this->input->post('submit')) {
            if ($this->save_fotos('update', $id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('fotos_act_edit_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'fotos');

                Template::set_message(lang('fotos_edit_success'), 'success');
            } else {
                Template::set_message(lang('fotos_edit_failure') . $this->fotos_model->error, 'error');
            }
        }

        Template::set('fotos', $this->fotos_model->find($id));
        Assets::add_module_js('fotos', 'fotos.js');

        Template::set('toolbar_title', lang('fotos_edit') . ' Fotos');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: delete()

      Allows deleting of Fotos data.
     */
    public function delete() {
        $this->auth->restrict('Fotos.Content.Delete');

        $id = $this->uri->segment(5);

        if (!empty($id)) {

            if ($this->fotos_model->delete($id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('fotos_act_delete_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'fotos');

                Template::set_message(lang('fotos_delete_success'), 'success');
            } else {
                Template::set_message(lang('fotos_delete_failure') . $this->fotos_model->error, 'error');
            }
        }

        redirect(SITE_AREA . '/content/fotos');
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    // !PRIVATE METHODS
    //--------------------------------------------------------------------

    /*
      Method: save_fotos()

      Does the actual validation and saving of form data.

      Parameters:
      $type	- Either "insert" or "update"
      $id		- The ID of the record to update. Not needed for inserts.

      Returns:
      An INT id for successful inserts. If updating, returns TRUE on success.
      Otherwise, returns FALSE.
     */
    private function save_fotos($type = 'insert', $id = 0) {
        if ($type == 'update') {
            $_POST['idfoto'] = $id;
        }


        $this->form_validation->set_rules('nombre', 'Nombre', 'required|max_length[500]');
        $this->form_validation->set_rules('titulo', 'Titulo', '');
//        $this->form_validation->set_rules('url', 'Url', 'required|max_length[100]');
        $this->form_validation->set_rules('habilitado', 'Habilitado', 'max_length[1]');
        $this->form_validation->set_rules('prioridad', 'Prioridad', 'required|max_length[11]');

        if ($this->form_validation->run() === FALSE) {
            return FALSE;
        }

        // make sure we only pass in the fields we want

        $data = array();
        $data['nombre'] = $this->input->post('nombre');
        $data['titulo'] = $this->input->post('titulo');
	
        $data['habilitado'] = $this->input->post('habilitado');
        $data['prioridad'] = $this->input->post('prioridad');

        if ($type == 'insert') {
            ///SAVE FILEEEE////////////////////////
            $config['upload_path'] = './images/fotos/';
            $config['allowed_types'] = 'gif|jpg|png';
            $config['max_size'] = '100';
            $config['max_width'] = '1024';
            $config['max_height'] = '768';

            $this->load->library('upload', $config);
            $this->upload->initialize($config);
            if (!$this->upload->do_upload()) {
                $error = array('error' => $this->upload->display_errors());
            } else {
                $result = array('upload_data' => $this->upload->data());
            }
            $data['url'] = '/images/fotos/' . $result['upload_data']['file_name'];
            ////////////////////////////

            $id = $this->fotos_model->insert($data);

            if (is_numeric($id)) {
                $return = $id;
            } else {
                $return = FALSE;
            }
        } else if ($type == 'update') {
            $data['url']        = $this->input->post('url');
            $return = $this->fotos_model->update($id, $data);
        }

        return $return;
    }

    //--------------------------------------------------------------------
}