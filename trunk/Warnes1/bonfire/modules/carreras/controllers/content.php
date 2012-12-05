<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Carreras.Content.View');
		$this->load->model('carreras_model', null, true);
		$this->lang->load('carreras');
		
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
						$result = $this->carreras_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('carreras_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('carreras_delete_failure') . $this->carreras_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('carreras_delete_error') . $this->carreras_model->error, 'error');
				}
			}
		}

		$records = $this->carreras_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Carreras');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Carreras object.
	*/
	public function create()
	{
		$this->auth->restrict('Carreras.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_carreras())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('carreras_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'carreras');

				Template::set_message(lang('carreras_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/carreras');
			}
			else
			{
				Template::set_message(lang('carreras_create_failure') . $this->carreras_model->error, 'error');
			}
		}
		Assets::add_module_js('carreras', 'carreras.js');

		Template::set('toolbar_title', lang('carreras_create') . ' Carreras');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Carreras data.
	*/
	public function edit()
	{
		$this->auth->restrict('Carreras.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('carreras_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/carreras');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_carreras('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('carreras_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'carreras');

				Template::set_message(lang('carreras_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('carreras_edit_failure') . $this->carreras_model->error, 'error');
			}
		}

		Template::set('carreras', $this->carreras_model->find($id));
		Assets::add_module_js('carreras', 'carreras.js');

		Template::set('toolbar_title', lang('carreras_edit') . ' Carreras');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Carreras data.
	*/
	public function delete()
	{
		$this->auth->restrict('Carreras.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->carreras_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('carreras_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'carreras');

				Template::set_message(lang('carreras_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('carreras_delete_failure') . $this->carreras_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/carreras');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_carreras()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_carreras($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idcarrera'] = $id;
		}

		
		$this->form_validation->set_rules('idcarrera_categoria','Idcarrera Categoria','max_length[11]');
		$this->form_validation->set_rules('numero_carrera','Numero Carrera','max_length[11]');
		$this->form_validation->set_rules('fecha_carrera','Fecha Carrera','max_length[11]');
		$this->form_validation->set_rules('lugar','Lugar','max_length[200]');
		$this->form_validation->set_rules('circuito','Circuito','max_length[200]');
		$this->form_validation->set_rules('podio_anterior_1','Podio Anterior 1','max_length[200]');
		$this->form_validation->set_rules('podio_anterior_2','Podio Anterior 2','max_length[200]');
		$this->form_validation->set_rules('podio_anterior_3','Podio Anterior 3','max_length[200]');
		$this->form_validation->set_rules('podio_actual_1','Podio Actual 1','max_length[200]');
		$this->form_validation->set_rules('podio_actual_2','Podio Actual 2','max_length[200]');
		$this->form_validation->set_rules('podio_actual_3','Podio Actual 3','max_length[200]');
		$this->form_validation->set_rules('anio','Anio','max_length[11]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['idcarrera_categoria']        = $this->input->post('idcarrera_categoria');
		$data['numero_carrera']        = $this->input->post('numero_carrera');
		$data['fecha_carrera']        = $this->input->post('fecha_carrera') ? $this->input->post('fecha_carrera') : '0000-00-00';
		$data['lugar']        = $this->input->post('lugar');
		$data['circuito']        = $this->input->post('circuito');
		$data['podio_anterior_1']        = $this->input->post('podio_anterior_1');
		$data['podio_anterior_2']        = $this->input->post('podio_anterior_2');
		$data['podio_anterior_3']        = $this->input->post('podio_anterior_3');
		$data['podio_actual_1']        = $this->input->post('podio_actual_1');
		$data['podio_actual_2']        = $this->input->post('podio_actual_2');
		$data['podio_actual_3']        = $this->input->post('podio_actual_3');
		$data['anio']        = $this->input->post('anio');

		if ($type == 'insert')
		{
			$id = $this->carreras_model->insert($data);

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
			$return = $this->carreras_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}