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
class Marca extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('items_model');
        $this->load->model('clientes_model');
        $this->load->model('banners_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
//		Template::render();
        $data['rubros'] = $this->items_model->get_rubros_for_acordion();
        $data['marcas'] = $this->items_model->get_marcas_for_acordion();
        $data['zonas'] = $this->items_model->get_zonas_for_acordion();
        $data['allmarcas'] = $this->items_model->get_marcas();
        $data['banners_side'] = $this->banners_model->get_banners_marcas_by_type(2);
        $this->load->view('home/template/top', $data);
        $this->load->view('marca/index', $data);
        $this->load->view('home/template/bottom', $data);
    }

    public function view($idmarca) {
        $data['rubros'] = $this->items_model->get_rubros_for_acordion();
        $data['marcas'] = $this->items_model->get_marcas_for_acordion();
        $data['zonas'] = $this->items_model->get_zonas_for_acordion();
        $data['marca'] = $this->items_model->get_marca($idmarca);
        $data['clientes'] = $this->clientes_model->get_clientes_by_marca($idmarca);
        $data['banners_side'] = $this->banners_model->get_banners_marca_by_type($idmarca,2);

        if (empty($data['marca'])) {
            show_404();
        }

        $data['title'] = $data['marca']['descripcion'];

        $this->load->view('home/template/top', $data);
        $this->load->view('marca/view', $data);
        $this->load->view('home/template/bottom', $data);
    }

//end index()
    //--------------------------------------------------------------------
}

//end class