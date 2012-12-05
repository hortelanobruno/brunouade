<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Zonas.Content.View');
		$this->load->model('zonas_model', null, true);
		$this->lang->load('zonas');
		
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
						$result = $this->zonas_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('zonas_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('zonas_delete_failure') . $this->zonas_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('zonas_delete_error') . $this->zonas_model->error, 'error');
				}
			}
		}

		$records = $this->zonas_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Zonas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Zonas object.
	*/
	public function create()
	{
		$this->auth->restrict('Zonas.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_zonas())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('zonas_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'zonas');

				Template::set_message(lang('zonas_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/zonas');
			}
			else
			{
				Template::set_message(lang('zonas_create_failure') . $this->zonas_model->error, 'error');
			}
		}
		Assets::add_module_js('zonas', 'zonas.js');

		Template::set('toolbar_title', lang('zonas_create') . ' Zonas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Zonas data.
	*/
	public function edit()
	{
		$this->auth->restrict('Zonas.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('zonas_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/zonas');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_zonas('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('zonas_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'zonas');

				Template::set_message(lang('zonas_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('zonas_edit_failure') . $this->zonas_model->error, 'error');
			}
		}

		Template::set('zonas', $this->zonas_model->find($id));
		Assets::add_module_js('zonas', 'zonas.js');

		Template::set('toolbar_title', lang('zonas_edit') . ' Zonas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Zonas data.
	*/
	public function delete()
	{
		$this->auth->restrict('Zonas.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->zonas_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('zonas_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'zonas');

				Template::set_message(lang('zonas_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('zonas_delete_failure') . $this->zonas_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/zonas');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_zonas()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_zonas($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idzona'] = $id;
		}

		
		$this->form_validation->set_rules('descripcion','Descripcion','required|max_length[45]');
		$this->form_validation->set_rules('habilitado','Habilitado','max_length[1]');
		$this->form_validation->set_rules('prioridad','Prioridad','required|max_length[11]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['descripcion']        = $this->input->post('descripcion');
		$data['habilitado']        = $this->input->post('habilitado');
		$data['prioridad']        = $this->input->post('prioridad');

		if ($type == 'insert')
		{
			$id = $this->zonas_model->insert($data);

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
			$return = $this->zonas_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}