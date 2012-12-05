<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Banners_types_model extends BF_Model {

	protected $table		= "banners_types";
	protected $key			= "idbannertype";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
