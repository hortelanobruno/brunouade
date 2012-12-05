<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Clientes_zonas_model extends BF_Model {

	protected $table		= "cliente_zona";
	protected $key			= "idcliente_zona";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
