<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Administracion_tabla_posiciones_model extends BF_Model {

	protected $table		= "torneo_tabla_posiciones";
	protected $key			= "idtorneo";
	protected $soft_deletes	= false;
	protected $date_format	= "datetime";
	protected $set_created	= false;
	protected $set_modified = false;
}
