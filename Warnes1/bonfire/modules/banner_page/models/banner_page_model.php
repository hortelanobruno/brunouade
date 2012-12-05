<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Banner_page_model extends BF_Model {

	protected $table		= "banner_page";
	protected $key			= "idbanner_page";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
