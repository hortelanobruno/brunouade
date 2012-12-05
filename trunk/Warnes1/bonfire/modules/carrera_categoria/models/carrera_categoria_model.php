<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Carrera_categoria_model extends BF_Model {

	protected $table		= "carrera_categoria";
	protected $key			= "idcarrera_categoria";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
