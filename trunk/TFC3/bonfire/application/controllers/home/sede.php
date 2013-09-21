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
 * Sede controller
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
class Sede extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('torneos_model');
        $this->load->model('equipos_model');
        $this->load->model('sedes_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $data['torneos'] = $this->torneos_model->get_last_4_torneos();
        $data['sedes'] = $this->sedes_model->get_sedes();
        
        $this->load->view('home/partes/header', $data);
        $this->load->view('home/sede/index', $data);
        $this->load->view('home/partes/footer', $data);
    }

//end index()
    //--------------------------------------------------------------------
}

//end class