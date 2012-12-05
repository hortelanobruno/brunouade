<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class content extends Admin_Controller {

    //--------------------------------------------------------------------


    public function __construct() {
        parent::__construct();

        $this->auth->restrict('Banners.Content.View');
        $this->load->model('banners_model', null, true);
        $this->lang->load('banners');
        $this->load->library('upload');

        Assets::add_css('flick/jquery-ui-1.8.13.custom.css');
        Assets::add_js('jquery-ui-1.8.13.min.js');
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
                        $result = $this->banners_model->delete($pid);
                    }

                    if ($result) {
                        Template::set_message(count($checked) . ' ' . lang('banners_delete_success'), 'success');
                    } else {
                        Template::set_message(lang('banners_delete_failure') . $this->banners_model->error, 'error');
                    }
                } else {
                    Template::set_message(lang('banners_delete_error') . $this->banners_model->error, 'error');
                }
            }
        }

        $records = $this->banners_model->find_all();

        Template::set('records', $records);
        Template::set('toolbar_title', 'Manage Banners');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: create()

      Creates a Banners object.
     */
    public function create() {
        $this->auth->restrict('Banners.Content.Create');

        if ($this->input->post('submit')) {
            if ($insert_id = $this->save_banners()) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('banners_act_create_record') . ': ' . $insert_id . ' : ' . $this->input->ip_address(), 'banners');

                Template::set_message(lang('banners_create_success'), 'success');
                Template::redirect(SITE_AREA . '/content/banners');
            } else {
                Template::set_message(lang('banners_create_failure') . $this->banners_model->error, 'error');
            }
        }
        Assets::add_module_js('banners', 'banners.js');

        Template::set('toolbar_title', lang('banners_create') . ' Banners');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: edit()

      Allows editing of Banners data.
     */
    public function edit() {
        $this->auth->restrict('Banners.Content.Edit');

        $id = $this->uri->segment(5);

        if (empty($id)) {
            Template::set_message(lang('banners_invalid_id'), 'error');
            redirect(SITE_AREA . '/content/banners');
        }

        if ($this->input->post('submit')) {
            if ($this->save_banners('update', $id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('banners_act_edit_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'banners');

                Template::set_message(lang('banners_edit_success'), 'success');
            } else {
                Template::set_message(lang('banners_edit_failure') . $this->banners_model->error, 'error');
            }
        }

        Template::set('banners', $this->banners_model->find($id));
        Assets::add_module_js('banners', 'banners.js');

        Template::set('toolbar_title', lang('banners_edit') . ' Banners');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: delete()

      Allows deleting of Banners data.
     */
    public function delete() {
        $this->auth->restrict('Banners.Content.Delete');

        $id = $this->uri->segment(5);

        if (!empty($id)) {

            if ($this->banners_model->delete($id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('banners_act_delete_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'banners');

                Template::set_message(lang('banners_delete_success'), 'success');
            } else {
                Template::set_message(lang('banners_delete_failure') . $this->banners_model->error, 'error');
            }
        }

        redirect(SITE_AREA . '/content/banners');
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    // !PRIVATE METHODS
    //--------------------------------------------------------------------

    /*
      Method: save_banners()

      Does the actual validation and saving of form data.

      Parameters:
      $type	- Either "insert" or "update"
      $id		- The ID of the record to update. Not needed for inserts.

      Returns:
      An INT id for successful inserts. If updating, returns TRUE on success.
      Otherwise, returns FALSE.
     */
    private function save_banners($type = 'insert', $id = 0) {
        if ($type == 'update') {
            $_POST['idbanner'] = $id;
        }


        $this->form_validation->set_rules('nombre', 'Nombre', 'required|max_length[45]');
//        $this->form_validation->set_rules('url', 'Url', 'required|max_length[45]');
        $this->form_validation->set_rules('fechainicio', 'Fechainicio', 'required|max_length[45]');
        $this->form_validation->set_rules('fechafin', 'Fechafin', 'required|max_length[45]');
        $this->form_validation->set_rules('habilitado', 'Habilitado', 'max_length[1]');
        $this->form_validation->set_rules('idbannertype', 'Idbannertype', 'required|max_length[11]');
        $this->form_validation->set_rules('prioridad', 'Prioridad', 'required|max_length[11]');

        if ($this->form_validation->run() === FALSE) {
            return FALSE;
        }


        // make sure we only pass in the fields we want


        $data = array();
        $data['nombre'] = $this->input->post('nombre');
        
        $data['fechainicio'] = $this->input->post('fechainicio') ? $this->input->post('fechainicio') : '0000-00-00';
        $data['fechafin'] = $this->input->post('fechafin') ? $this->input->post('fechafin') : '0000-00-00';
        $data['habilitado'] = $this->input->post('habilitado');
        $data['idbannertype'] = $this->input->post('idbannertype');
        $data['prioridad'] = $this->input->post('prioridad');

        if ($type == 'insert') {
            ///SAVE FILEEEE////////////////////////
            $config['upload_path'] = './images/banners/';
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
            $data['url'] = '/images/banners/' . $result['upload_data']['file_name'];
            ////////////////////////////



            $id = $this->banners_model->insert($data);

            if (is_numeric($id)) {
                $return = $id;
            } else {
                $return = FALSE;
            }
        } else if ($type == 'update') {
            $data['url'] = $this->input->post('url');
            $return = $this->banners_model->update($id, $data);
        }

        return $return;
    }

    //--------------------------------------------------------------------
}