<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Clientes_marcas_model extends BF_Model {

	protected $table		= "cliente_marca";
	protected $key			= "idcliente_marca";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
