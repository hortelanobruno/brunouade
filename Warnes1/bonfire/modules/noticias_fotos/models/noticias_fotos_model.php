<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Noticias_fotos_model extends BF_Model {

	protected $table		= "noticias_fotos";
	protected $key			= "idnoticias_fotos";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
