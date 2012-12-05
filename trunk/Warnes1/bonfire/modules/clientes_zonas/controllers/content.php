<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Clientes_Zonas.Content.View');
		$this->load->model('clientes_zonas_model', null, true);
		$this->lang->load('clientes_zonas');
		
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
						$result = $this->clientes_zonas_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('clientes_zonas_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('clientes_zonas_delete_failure') . $this->clientes_zonas_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('clientes_zonas_delete_error') . $this->clientes_zonas_model->error, 'error');
				}
			}
		}

		$records = $this->clientes_zonas_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Clientes Zonas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Clientes Zonas object.
	*/
	public function create()
	{
		$this->auth->restrict('Clientes_Zonas.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_clientes_zonas())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('clientes_zonas_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'clientes_zonas');

				Template::set_message(lang('clientes_zonas_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/clientes_zonas');
			}
			else
			{
				Template::set_message(lang('clientes_zonas_create_failure') . $this->clientes_zonas_model->error, 'error');
			}
		}
		Assets::add_module_js('clientes_zonas', 'clientes_zonas.js');

		Template::set('toolbar_title', lang('clientes_zonas_create') . ' Clientes Zonas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Clientes Zonas data.
	*/
	public function edit()
	{
		$this->auth->restrict('Clientes_Zonas.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('clientes_zonas_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/clientes_zonas');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_clientes_zonas('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('clientes_zonas_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'clientes_zonas');

				Template::set_message(lang('clientes_zonas_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('clientes_zonas_edit_failure') . $this->clientes_zonas_model->error, 'error');
			}
		}

		Template::set('clientes_zonas', $this->clientes_zonas_model->find($id));
		Assets::add_module_js('clientes_zonas', 'clientes_zonas.js');

		Template::set('toolbar_title', lang('clientes_zonas_edit') . ' Clientes Zonas');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Clientes Zonas data.
	*/
	public function delete()
	{
		$this->auth->restrict('Clientes_Zonas.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->clientes_zonas_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('clientes_zonas_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'clientes_zonas');

				Template::set_message(lang('clientes_zonas_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('clientes_zonas_delete_failure') . $this->clientes_zonas_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/clientes_zonas');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_clientes_zonas()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_clientes_zonas($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idcliente_zona'] = $id;
		}

		
		$this->form_validation->set_rules('idcliente','Idcliente','required|max_length[11]');
		$this->form_validation->set_rules('idzona','Idzona','required|max_length[11]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['idcliente']        = $this->input->post('idcliente');
		$data['idzona']        = $this->input->post('idzona');

		if ($type == 'insert')
		{
			$id = $this->clientes_zonas_model->insert($data);

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
			$return = $this->clientes_zonas_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}