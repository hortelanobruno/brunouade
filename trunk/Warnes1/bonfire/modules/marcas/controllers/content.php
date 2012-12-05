<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Marcas.Content.View');
		$this->load->model('marcas_model', null, true);
		$this->lang->load('marcas');
		
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
						$result = $this->marcas_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('marcas_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('marcas_delete_failure') . $this->marcas_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('marcas_delete_error') . $this->marcas_model->error, 'error');
				}
			}
		}

		$records = $this->marcas_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Marcas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Marcas object.
	*/
	public function create()
	{
		$this->auth->restrict('Marcas.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_marcas())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('marcas_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'marcas');

				Template::set_message(lang('marcas_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/marcas');
			}
			else
			{
				Template::set_message(lang('marcas_create_failure') . $this->marcas_model->error, 'error');
			}
		}
		Assets::add_module_js('marcas', 'marcas.js');

		Template::set('toolbar_title', lang('marcas_create') . ' Marcas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Marcas data.
	*/
	public function edit()
	{
		$this->auth->restrict('Marcas.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('marcas_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/marcas');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_marcas('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('marcas_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'marcas');

				Template::set_message(lang('marcas_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('marcas_edit_failure') . $this->marcas_model->error, 'error');
			}
		}

		Template::set('marcas', $this->marcas_model->find($id));
		Assets::add_module_js('marcas', 'marcas.js');

		Template::set('toolbar_title', lang('marcas_edit') . ' Marcas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Marcas data.
	*/
	public function delete()
	{
		$this->auth->restrict('Marcas.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->marcas_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('marcas_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'marcas');

				Template::set_message(lang('marcas_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('marcas_delete_failure') . $this->marcas_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/marcas');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_marcas()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_marcas($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idmarca'] = $id;
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
			$id = $this->marcas_model->insert($data);

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
			$return = $this->marcas_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}