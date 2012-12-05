<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

/**
 * Bonfire
 *
 * An open source project to allow developers get a jumpstart their development of CodeIgniter applications
 *
 * @package   Bonfire
 * @author    Bonfire Dev Team
 * @copyright Copyright (c) 2011 - 2012, Bonfire Dev Team
 * @license   http://guides.cibonfire.com/license.html
 * @link      http://cibonfire.com
 * @since     Version 1.0
 * @filesource
 */
// ------------------------------------------------------------------------

/**
 * Home controller
 *
 * The base controller which displays the homepage of the Bonfire site.
 *
 * @package    Bonfire
 * @subpackage Controllers
 * @category   Controllers
 * @author     Bonfire Dev Team
 * @link       http://guides.cibonfire.com/helpers/file_helpers.html
 *
 */
class Cliente extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('clientes_model');
        $this->load->model('banners_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index($idcliente) {
//		Template::render();

        $data['cliente'] = $this->clientes_model->get_cliente($idcliente);
        $data['rubros'] = $this->clientes_model->get_rubros_by_cliente($idcliente);
        $data['marcas'] = $this->clientes_model->get_marcas_by_cliente($idcliente);
        $data['zonas'] = $this->clientes_model->get_zonas_by_cliente($idcliente);
        $data['fotos'] = $this->banners_model->get_banners_by_cliente($idcliente);
        $this->load->view('home/template/top');
        $this->load->view('cliente/index', $data);
        $this->load->view('home/template/bottom');
    }


//end index()
    //--------------------------------------------------------------------
}

//end class