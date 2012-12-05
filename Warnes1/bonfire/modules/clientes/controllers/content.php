<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Clientes.Content.View');
		$this->load->model('clientes_model', null, true);
		$this->lang->load('clientes');
		
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
						$result = $this->clientes_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('clientes_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('clientes_delete_failure') . $this->clientes_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('clientes_delete_error') . $this->clientes_model->error, 'error');
				}
			}
		}

		$records = $this->clientes_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Clientes');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Clientes object.
	*/
	public function create()
	{
		$this->auth->restrict('Clientes.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_clientes())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('clientes_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'clientes');

				Template::set_message(lang('clientes_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/clientes');
			}
			else
			{
				Template::set_message(lang('clientes_create_failure') . $this->clientes_model->error, 'error');
			}
		}
		Assets::add_module_js('clientes', 'clientes.js');

		Template::set('toolbar_title', lang('clientes_create') . ' Clientes');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Clientes data.
	*/
	public function edit()
	{
		$this->auth->restrict('Clientes.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('clientes_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/clientes');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_clientes('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('clientes_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'clientes');

				Template::set_message(lang('clientes_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('clientes_edit_failure') . $this->clientes_model->error, 'error');
			}
		}

		Template::set('clientes', $this->clientes_model->find($id));
		Assets::add_module_js('clientes', 'clientes.js');

		Template::set('toolbar_title', lang('clientes_edit') . ' Clientes');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Clientes data.
	*/
	public function delete()
	{
		$this->auth->restrict('Clientes.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->clientes_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('clientes_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'clientes');

				Template::set_message(lang('clientes_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('clientes_delete_failure') . $this->clientes_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/clientes');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_clientes()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_clientes($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idcliente'] = $id;
		}

		
		$this->form_validation->set_rules('nombre','Nombre','required|max_length[45]');
		$this->form_validation->set_rules('contacto','Contacto','max_length[45]');
		$this->form_validation->set_rules('telefonodeltrabajo','Telefono del trabajo','max_length[45]');
		$this->form_validation->set_rules('telefonocelular','Telefono celular','max_length[45]');
		$this->form_validation->set_rules('telefonoparticular','Telefono particular','max_length[45]');
		$this->form_validation->set_rules('numerodefax','Numero de fax','max_length[45]');
		$this->form_validation->set_rules('direccion','Direccion','max_length[45]');
		$this->form_validation->set_rules('ciudad','Ciudad','max_length[45]');
		$this->form_validation->set_rules('provincia','Provincia','max_length[45]');
		$this->form_validation->set_rules('codigopostal','Codigo postal','max_length[45]');
		$this->form_validation->set_rules('paisregion','Pais o region','max_length[45]');
		$this->form_validation->set_rules('mail','Mail','max_length[45]');
		$this->form_validation->set_rules('web','Web','max_length[45]');
		$this->form_validation->set_rules('observaciones','Observaciones','max_length[45]');
		$this->form_validation->set_rules('habilitado','Habilitado','max_length[1]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['nombre']        = $this->input->post('nombre');
		$data['contacto']        = $this->input->post('contacto');
		$data['telefonodeltrabajo']        = $this->input->post('telefonodeltrabajo');
		$data['telefonocelular']        = $this->input->post('telefonocelular');
		$data['telefonoparticular']        = $this->input->post('telefonoparticular');
		$data['numerodefax']        = $this->input->post('numerodefax');
		$data['direccion']        = $this->input->post('direccion');
		$data['ciudad']        = $this->input->post('ciudad');
		$data['provincia']        = $this->input->post('provincia');
		$data['codigopostal']        = $this->input->post('codigopostal');
		$data['paisregion']        = $this->input->post('paisregion');
		$data['mail']        = $this->input->post('mail');
		$data['web']        = $this->input->post('web');
		$data['observaciones']        = $this->input->post('observaciones');
		$data['habilitado']        = $this->input->post('habilitado');

		if ($type == 'insert')
		{
			$id = $this->clientes_model->insert($data);

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
			$return = $this->clientes_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}