<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Pages.Content.View');
		$this->load->model('pages_model', null, true);
		$this->lang->load('pages');
		
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
						$result = $this->pages_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('pages_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('pages_delete_failure') . $this->pages_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('pages_delete_error') . $this->pages_model->error, 'error');
				}
			}
		}

		$records = $this->pages_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Pages');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Pages object.
	*/
	public function create()
	{
		$this->auth->restrict('Pages.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_pages())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('pages_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'pages');

				Template::set_message(lang('pages_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/pages');
			}
			else
			{
				Template::set_message(lang('pages_create_failure') . $this->pages_model->error, 'error');
			}
		}
		Assets::add_module_js('pages', 'pages.js');

		Template::set('toolbar_title', lang('pages_create') . ' Pages');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Pages data.
	*/
	public function edit()
	{
		$this->auth->restrict('Pages.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('pages_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/pages');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_pages('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('pages_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'pages');

				Template::set_message(lang('pages_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('pages_edit_failure') . $this->pages_model->error, 'error');
			}
		}

		Template::set('pages', $this->pages_model->find($id));
		Assets::add_module_js('pages', 'pages.js');

		Template::set('toolbar_title', lang('pages_edit') . ' Pages');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Pages data.
	*/
	public function delete()
	{
		$this->auth->restrict('Pages.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->pages_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('pages_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'pages');

				Template::set_message(lang('pages_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('pages_delete_failure') . $this->pages_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/pages');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_pages()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_pages($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idpage'] = $id;
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
			$id = $this->pages_model->insert($data);

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
			$return = $this->pages_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}