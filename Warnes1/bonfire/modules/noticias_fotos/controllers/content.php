<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Noticias_Fotos.Content.View');
		$this->load->model('noticias_fotos_model', null, true);
		$this->lang->load('noticias_fotos');
		
		Template::set_block('sub_nav', 'content/_sub_nav');
	}

	//--------------------------------------------------------------------



	/*
		Method: index()

		Displays a list of form data.
	*/
	public function index()
	{

		// Deleting anything?
		if ($action = $this->input->post('delete'))
		{
			if ($action == 'Delete')
			{
				$checked = $this->input->post('checked');

				if (is_array($checked) && count($checked))
				{
					$result = FALSE;
					foreach ($checked as $pid)
					{
						$result = $this->noticias_fotos_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('noticias_fotos_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('noticias_fotos_delete_failure') . $this->noticias_fotos_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('noticias_fotos_delete_error') . $this->noticias_fotos_model->error, 'error');
				}
			}
		}

		$records = $this->noticias_fotos_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Noticias Fotos');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Noticias Fotos object.
	*/
	public function create()
	{
		$this->auth->restrict('Noticias_Fotos.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_noticias_fotos())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('noticias_fotos_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'noticias_fotos');

				Template::set_message(lang('noticias_fotos_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/noticias_fotos');
			}
			else
			{
				Template::set_message(lang('noticias_fotos_create_failure') . $this->noticias_fotos_model->error, 'error');
			}
		}
		Assets::add_module_js('noticias_fotos', 'noticias_fotos.js');

		Template::set('toolbar_title', lang('noticias_fotos_create') . ' Noticias Fotos');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Noticias Fotos data.
	*/
	public function edit()
	{
		$this->auth->restrict('Noticias_Fotos.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('noticias_fotos_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/noticias_fotos');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_noticias_fotos('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('noticias_fotos_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'noticias_fotos');

				Template::set_message(lang('noticias_fotos_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('noticias_fotos_edit_failure') . $this->noticias_fotos_model->error, 'error');
			}
		}

		Template::set('noticias_fotos', $this->noticias_fotos_model->find($id));
		Assets::add_module_js('noticias_fotos', 'noticias_fotos.js');

		Template::set('toolbar_title', lang('noticias_fotos_edit') . ' Noticias Fotos');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Noticias Fotos data.
	*/
	public function delete()
	{
		$this->auth->restrict('Noticias_Fotos.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->noticias_fotos_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('noticias_fotos_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'noticias_fotos');

				Template::set_message(lang('noticias_fotos_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('noticias_fotos_delete_failure') . $this->noticias_fotos_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/noticias_fotos');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_noticias_fotos()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_noticias_fotos($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idnoticias_fotos'] = $id;
		}

		
		$this->form_validation->set_rules('idnoticias','Idnoticias','required|max_length[11]');
		$this->form_validation->set_rules('idfoto','Idfoto','required|max_length[11]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['idnoticias']        = $this->input->post('idnoticias');
		$data['idfoto']        = $this->input->post('idfoto');

		if ($type == 'insert')
		{
			$id = $this->noticias_fotos_model->insert($data);

			if (is_numeric($id))
			{
				$return = $id;
			} else
			{
				$return = FALSE;
			}
		}
		else if ($type == 'update')
		{
			$return = $this->noticias_fotos_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}