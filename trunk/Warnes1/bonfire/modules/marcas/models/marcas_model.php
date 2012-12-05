<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Marcas_model extends BF_Model {

	protected $table		= "marcas";
	protected $key			= "idmarca";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
