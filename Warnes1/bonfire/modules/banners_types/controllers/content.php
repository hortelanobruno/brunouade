<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Banners_Types.Content.View');
		$this->load->model('banners_types_model', null, true);
		$this->lang->load('banners_types');
		
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
						$result = $this->banners_types_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('banners_types_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('banners_types_delete_failure') . $this->banners_types_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('banners_types_delete_error') . $this->banners_types_model->error, 'error');
				}
			}
		}

		$records = $this->banners_types_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Banners Types');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Banners Types object.
	*/
	public function create()
	{
		$this->auth->restrict('Banners_Types.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_banners_types())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('banners_types_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'banners_types');

				Template::set_message(lang('banners_types_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/banners_types');
			}
			else
			{
				Template::set_message(lang('banners_types_create_failure') . $this->banners_types_model->error, 'error');
			}
		}
		Assets::add_module_js('banners_types', 'banners_types.js');

		Template::set('toolbar_title', lang('banners_types_create') . ' Banners Types');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Banners Types data.
	*/
	public function edit()
	{
		$this->auth->restrict('Banners_Types.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('banners_types_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/banners_types');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_banners_types('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('banners_types_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'banners_types');

				Template::set_message(lang('banners_types_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('banners_types_edit_failure') . $this->banners_types_model->error, 'error');
			}
		}

		Template::set('banners_types', $this->banners_types_model->find($id));
		Assets::add_module_js('banners_types', 'banners_types.js');

		Template::set('toolbar_title', lang('banners_types_edit') . ' Banners Types');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Banners Types data.
	*/
	public function delete()
	{
		$this->auth->restrict('Banners_Types.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->banners_types_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('banners_types_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'banners_types');

				Template::set_message(lang('banners_types_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('banners_types_delete_failure') . $this->banners_types_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/banners_types');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_banners_types()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_banners_types($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idbannertype'] = $id;
		}

		
		$this->form_validation->set_rules('description','Description','required|max_length[45]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['description']        = $this->input->post('description');

		if ($type == 'insert')
		{
			$id = $this->banners_types_model->insert($data);

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
			$return = $this->banners_types_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}