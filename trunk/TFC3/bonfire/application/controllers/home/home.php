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
class Home extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('noticias_model');
        $this->load->model('torneos_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $data['torneos'] = $this->torneos_model->get_last_4_torneos();
        $data['news'] = $this->noticias_model->get_last_10_news();
        $data['proximos_partidos'] = $this->torneos_model->get_proximos_partidos(5);
        $this->load->view('home/partes/header',$data);
        $this->load->view('home/home/index',$data);
        $this->load->view('home/partes/footer',$data);
    }

//end index()
    //--------------------------------------------------------------------
}

//end class