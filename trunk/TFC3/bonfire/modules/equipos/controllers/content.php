<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Equipos.Content.View');
		$this->load->model('equipos_model', null, true);
		$this->lang->load('equipos');
		
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
		if ($this->input->post_key_exists('delete'))
		{
			$checked = $this->input->post('checked');

			if (is_array($checked) && count($checked))
			{
				$result = FALSE;
				foreach ($checked as $pid)
				{
					$result = $this->equipos_model->delete($pid);
				}

				if ($result)
				{
					Template::set_message(count($checked) .' '. lang('equipos_delete_success'), 'success');
				}
				else
				{
					Template::set_message(lang('equipos_delete_failure') . $this->equipos_model->error, 'error');
				}
			}
		}

		$records = $this->equipos_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Equipos');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Equipos object.
	*/
	public function create()
	{
		$this->auth->restrict('Equipos.Content.Create');

		if ($this->input->post_key_exists('save'))
		{
			if ($insert_id = $this->save_equipos())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('equipos_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'equipos');

				Template::set_message(lang('equipos_create_success'), 'success');
				redirect(SITE_AREA .'/content/equipos');
			}
			else
			{
				Template::set_message(lang('equipos_create_failure') . $this->equipos_model->error, 'error');
			}
		}
		Assets::add_module_js('equipos', 'equipos.js');

		Template::set('toolbar_title', lang('equipos_create') . ' Equipos');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Equipos data.
	*/
	public function edit()
	{
		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('equipos_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/equipos');
		}

		if ($this->input->post_key_exists('save'))
		{
			$this->auth->restrict('Equipos.Content.Edit');

			if ($this->save_equipos('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('equipos_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'equipos');

				Template::set_message(lang('equipos_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('equipos_edit_failure') . $this->equipos_model->error, 'error');
			}
		}
		else if ($this->input->post_key_exists('delete'))
		{
			$this->auth->restrict('Equipos.Content.Delete');

			if ($this->equipos_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('equipos_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'equipos');

				Template::set_message(lang('equipos_delete_success'), 'success');

				redirect(SITE_AREA .'/content/equipos');
			} else
			{
				Template::set_message(lang('equipos_delete_failure') . $this->equipos_model->error, 'error');
			}
		}
		Template::set('equipos', $this->equipos_model->find($id));
		Assets::add_module_js('equipos', 'equipos.js');

		Template::set('toolbar_title', lang('equipos_edit') . ' Equipos');
		Template::render();
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_equipos()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_equipos($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['id'] = $id;
		}

		
		$this->form_validation->set_rules('equipos_nombre','Nombre','max_length[200]');
		$this->form_validation->set_rules('equipos_logo_grande','Logo Grande','max_length[200]');
		$this->form_validation->set_rules('equipos_logo_chico','Logo Chico','max_length[200]');
		$this->form_validation->set_rules('equipos_pagina_web','Pagina Web','max_length[200]');
		$this->form_validation->set_rules('equipos_ciudad','Ciudad','max_length[200]');
		$this->form_validation->set_rules('equipos_foto','Foto','max_length[200]');
		$this->form_validation->set_rules('equipos_cantidad_partidos_perdidos','Cantidad Partidos Perdidos','max_length[11]');
		$this->form_validation->set_rules('equipos_cantidad_tarjetas_rojas','Cantidad Tarjetas Rojas','max_length[11]');
		$this->form_validation->set_rules('equipos_cantidad_tarjetas_amarillas','Cantidad Tarjetas Amarillas','max_length[11]');
		$this->form_validation->set_rules('equipos_cantidad_goles_en_contra','Cantidad Goles En Contra','max_length[11]');
		$this->form_validation->set_rules('equipos_cantidad_goles_a_favor','Cantidad Goles A Favor','max_length[11]');
		$this->form_validation->set_rules('equipos_cantidad_partidos_empatados','Cantidad Partidos Empatados','max_length[11]');
		$this->form_validation->set_rules('equipos_cantidad_partidos_ganados','Cantidad Partidos Ganados','max_length[11]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['nombre']        = $this->input->post('equipos_nombre');
		$data['logo_grande']        = $this->input->post('equipos_logo_grande');
		$data['logo_chico']        = $this->input->post('equipos_logo_chico');
		$data['pagina_web']        = $this->input->post('equipos_pagina_web');
		$data['ciudad']        = $this->input->post('equipos_ciudad');
		$data['foto']        = $this->input->post('equipos_foto');
		$data['cantidad_partidos_perdidos']        = intval($this->input->post('equipos_cantidad_partidos_perdidos'));
		$data['cantidad_tarjetas_rojas']        = intval($this->input->post('equipos_cantidad_tarjetas_rojas'));
		$data['cantidad_tarjetas_amarillas']        = intval($this->input->post('equipos_cantidad_tarjetas_amarillas'));
		$data['cantidad_goles_en_contra']        = intval($this->input->post('equipos_cantidad_goles_en_contra'));
		$data['cantidad_goles_a_favor']        = intval($this->input->post('equipos_cantidad_goles_a_favor'));
		$data['cantidad_partidos_empatados']        = intval($this->input->post('equipos_cantidad_partidos_empatados'));
		$data['cantidad_partidos_ganados']        = intval($this->input->post('equipos_cantidad_partidos_ganados'));

		if ($type == 'insert')
		{
			$id = $this->equipos_model->insert($data);

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
			$return = $this->equipos_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}