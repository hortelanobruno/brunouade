<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Carrera_Categoria.Content.View');
		$this->load->model('carrera_categoria_model', null, true);
		$this->lang->load('carrera_categoria');
		
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
						$result = $this->carrera_categoria_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('carrera_categoria_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('carrera_categoria_delete_failure') . $this->carrera_categoria_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('carrera_categoria_delete_error') . $this->carrera_categoria_model->error, 'error');
				}
			}
		}

		$records = $this->carrera_categoria_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Carrera Categoria');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Carrera Categoria object.
	*/
	public function create()
	{
		$this->auth->restrict('Carrera_Categoria.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_carrera_categoria())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('carrera_categoria_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'carrera_categoria');

				Template::set_message(lang('carrera_categoria_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/carrera_categoria');
			}
			else
			{
				Template::set_message(lang('carrera_categoria_create_failure') . $this->carrera_categoria_model->error, 'error');
			}
		}
		Assets::add_module_js('carrera_categoria', 'carrera_categoria.js');

		Template::set('toolbar_title', lang('carrera_categoria_create') . ' Carrera Categoria');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Carrera Categoria data.
	*/
	public function edit()
	{
		$this->auth->restrict('Carrera_Categoria.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('carrera_categoria_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/carrera_categoria');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_carrera_categoria('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('carrera_categoria_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'carrera_categoria');

				Template::set_message(lang('carrera_categoria_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('carrera_categoria_edit_failure') . $this->carrera_categoria_model->error, 'error');
			}
		}

		Template::set('carrera_categoria', $this->carrera_categoria_model->find($id));
		Assets::add_module_js('carrera_categoria', 'carrera_categoria.js');

		Template::set('toolbar_title', lang('carrera_categoria_edit') . ' Carrera Categoria');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Carrera Categoria data.
	*/
	public function delete()
	{
		$this->auth->restrict('Carrera_Categoria.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->carrera_categoria_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('carrera_categoria_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'carrera_categoria');

				Template::set_message(lang('carrera_categoria_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('carrera_categoria_delete_failure') . $this->carrera_categoria_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/carrera_categoria');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_carrera_categoria()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_carrera_categoria($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idcarrera_categoria'] = $id;
		}

		
		$this->form_validation->set_rules('nombre','Nombre','required|max_length[200]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['nombre']        = $this->input->post('nombre');

		if ($type == 'insert')
		{
			$id = $this->carrera_categoria_model->insert($data);

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
			$return = $this->carrera_categoria_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}