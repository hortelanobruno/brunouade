<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class content extends Admin_Controller {

	//--------------------------------------------------------------------


	public function __construct()
	{
		parent::__construct();

		$this->auth->restrict('Banner_Page.Content.View');
		$this->load->model('banner_page_model', null, true);
		$this->lang->load('banner_page');
		
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
						$result = $this->banner_page_model->delete($pid);
					}

					if ($result)
					{
						Template::set_message(count($checked) .' '. lang('banner_page_delete_success'), 'success');
					}
					else
					{
						Template::set_message(lang('banner_page_delete_failure') . $this->banner_page_model->error, 'error');
					}
				}
				else
				{
					Template::set_message(lang('banner_page_delete_error') . $this->banner_page_model->error, 'error');
				}
			}
		}

		$records = $this->banner_page_model->find_all();

		Template::set('records', $records);
		Template::set('toolbar_title', 'Manage Banner Page');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: create()

		Creates a Banner Page object.
	*/
	public function create()
	{
		$this->auth->restrict('Banner_Page.Content.Create');

		if ($this->input->post('submit'))
		{
			if ($insert_id = $this->save_banner_page())
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('banner_page_act_create_record').': ' . $insert_id . ' : ' . $this->input->ip_address(), 'banner_page');

				Template::set_message(lang('banner_page_create_success'), 'success');
				Template::redirect(SITE_AREA .'/content/banner_page');
			}
			else
			{
				Template::set_message(lang('banner_page_create_failure') . $this->banner_page_model->error, 'error');
			}
		}
		Assets::add_module_js('banner_page', 'banner_page.js');

		Template::set('toolbar_title', lang('banner_page_create') . ' Banner Page');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: edit()

		Allows editing of Banner Page data.
	*/
	public function edit()
	{
		$this->auth->restrict('Banner_Page.Content.Edit');

		$id = $this->uri->segment(5);

		if (empty($id))
		{
			Template::set_message(lang('banner_page_invalid_id'), 'error');
			redirect(SITE_AREA .'/content/banner_page');
		}

		if ($this->input->post('submit'))
		{
			if ($this->save_banner_page('update', $id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('banner_page_act_edit_record').': ' . $id . ' : ' . $this->input->ip_address(), 'banner_page');

				Template::set_message(lang('banner_page_edit_success'), 'success');
			}
			else
			{
				Template::set_message(lang('banner_page_edit_failure') . $this->banner_page_model->error, 'error');
			}
		}

		Template::set('banner_page', $this->banner_page_model->find($id));
		Assets::add_module_js('banner_page', 'banner_page.js');

		Template::set('toolbar_title', lang('banner_page_edit') . ' Banner Page');
		Template::render();
	}

	//--------------------------------------------------------------------



	/*
		Method: delete()

		Allows deleting of Banner Page data.
	*/
	public function delete()
	{
		$this->auth->restrict('Banner_Page.Content.Delete');

		$id = $this->uri->segment(5);

		if (!empty($id))
		{

			if ($this->banner_page_model->delete($id))
			{
				// Log the activity
				$this->activity_model->log_activity($this->current_user->id, lang('banner_page_act_delete_record').': ' . $id . ' : ' . $this->input->ip_address(), 'banner_page');

				Template::set_message(lang('banner_page_delete_success'), 'success');
			} else
			{
				Template::set_message(lang('banner_page_delete_failure') . $this->banner_page_model->error, 'error');
			}
		}

		redirect(SITE_AREA .'/content/banner_page');
	}

	//--------------------------------------------------------------------


	//--------------------------------------------------------------------
	// !PRIVATE METHODS
	//--------------------------------------------------------------------

	/*
		Method: save_banner_page()

		Does the actual validation and saving of form data.

		Parameters:
			$type	- Either "insert" or "update"
			$id		- The ID of the record to update. Not needed for inserts.

		Returns:
			An INT id for successful inserts. If updating, returns TRUE on success.
			Otherwise, returns FALSE.
	*/
	private function save_banner_page($type='insert', $id=0)
	{
		if ($type == 'update') {
			$_POST['idbanner_page'] = $id;
		}

		
		$this->form_validation->set_rules('idbanner','Id banner','required|max_length[11]');
		$this->form_validation->set_rules('idpage','Id page','required|max_length[11]');
		$this->form_validation->set_rules('idelement','Id element','max_length[11]');

		if ($this->form_validation->run() === FALSE)
		{
			return FALSE;
		}

		// make sure we only pass in the fields we want
		
		$data = array();
		$data['idbanner']        = $this->input->post('idbanner');
		$data['idpage']        = $this->input->post('idpage');
		$data['idelement']        = $this->input->post('idelement');

		if ($type == 'insert')
		{
			$id = $this->banner_page_model->insert($data);

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
			$return = $this->banner_page_model->update($id, $data);
		}

		return $return;
	}

	//--------------------------------------------------------------------



}