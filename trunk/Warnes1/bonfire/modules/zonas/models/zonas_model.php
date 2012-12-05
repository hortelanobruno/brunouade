<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Zonas_model extends BF_Model {

	protected $table		= "zonas";
	protected $key			= "idzona";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
