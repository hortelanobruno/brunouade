<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Clientes_rubros_model extends BF_Model {

	protected $table		= "cliente_rubro";
	protected $key			= "idcliente_rubro";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
