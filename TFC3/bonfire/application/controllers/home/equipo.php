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
 * Equipo controller
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
class Equipo extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('torneos_model');
        $this->load->model('equipos_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $data['torneos'] = $this->torneos_model->get_last_4_torneos();
        $this->load->view('home/partes/header', $data);
        $this->load->view('home/equipo/index', $data);
        $this->load->view('home/partes/footer', $data);
    }

//end index()

    public function view($idequipo) {
        $data['torneos'] = $this->torneos_model->get_last_4_torneos();
        $data['torneos_all'] = $this->torneos_model->get_all_torneos();
        $data['equipo'] = $this->equipos_model->get_equipo($idequipo);
        $data['delegado'] = $this->equipos_model->get_delegado_from_equipo($idequipo);
        $data['jugadores'] = $this->equipos_model->get_jugadores_from_equipo($idequipo);
        $data['partidos'] = $this->torneos_model->get_next_5_matchs($idequipo);
        $data['equipos'] = $this->equipos_model->get_all_equipos();
        $data['historial_torneos'] = $this->torneos_model->get_historial_torneos($idequipo);
        $this->load->view('home/partes/header', $data);
        $this->load->view('home/equipo/view', $data);
        $this->load->view('home/partes/footer', $data);
    }

    //--------------------------------------------------------------------
}

//end class