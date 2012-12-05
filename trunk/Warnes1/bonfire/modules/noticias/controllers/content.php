<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Noticias.Content.View');
		$this->load->model('noticias_model', null, true);
		$this->lang->load('noticias');
		
			Assets::add_css('flick/jquery-ui-1.8.13.custom.css');
			Assets::add_js('jquery-ui-1.8.13.min.js');
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
						$result = $this->noticias_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('noticias_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('noticias_delete_failure') . $this->noticias_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('noticias_delete_error') . $this->noticias_model->error, 'error');
				}
			}
		}

		$records = $this->noticias_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Noticias');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Noticias object.
	*/
	public function create()
	{
		$this->auth->restrict('Noticias.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_noticias())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('noticias_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'noticias');

				Template::set_message(lang('noticias_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/noticias');
			}
			else
			{
				Template::set_message(lang('noticias_create_failure') . $this->noticias_model->error, 'error');
			}
		}
		Assets::add_module_js('noticias', 'noticias.js');

		Template::set('toolbar_title', lang('noticias_create') . ' Noticias');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Noticias data.
	*/
	public function edit()
	{
		$this->auth->restrict('Noticias.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('noticias_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/noticias');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_noticias('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('noticias_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'noticias');

				Template::set_message(lang('noticias_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('noticias_edit_failure') . $this->noticias_model->error, 'error');
			}
		}

		Template::set('noticias', $this->noticias_model->find($id));
		Assets::add_module_js('noticias', 'noticias.js');

		Template::set('toolbar_title', lang('noticias_edit') . ' Noticias');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Noticias data.
	*/
	public function delete()
	{
		$this->auth->restrict('Noticias.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->noticias_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('noticias_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'noticias');

				Template::set_message(lang('noticias_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('noticias_delete_failure') . $this->noticias_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/noticias');
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
	private function save_noticias($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idnoticia'] = $id;
		}

		
		$this->form_validation->set_rules('titulo','Titulo','required');
		$this->form_validation->set_rules('epigrafe','Epigrafe','');
		$this->form_validation->set_rules('descripcion','Descripcion','required');
		$this->form_validation->set_rules('fechanoticia','Fechanoticia','required');
		$this->form_validation->set_rules('habilitado','Habilitado','max_length[1]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['titulo']        = $this->input->post('titulo');
		$data['epigrafe']        = $this->input->post('epigrafe');
		$data['descripcion']        = $this->input->post('descripcion');
		$data['fechanoticia']        = $this->input->post('fechanoticia') ? $this->input->post('fechanoticia') : '0000-00-00';
		$data['habilitado']        = $this->input->post('habilitado');

		if ($type == 'insert')
		{
			$id = $this->noticias_model->insert($data);

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
			$return = $this->noticias_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}