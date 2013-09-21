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
 * Noticia controller
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
class Noticia extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('noticias_model');
        $this->load->model('torneos_model');
        $this->load->model('equipos_model');
        $this->load->model('partidos_model');
        $this->load->model('sedes_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $this->load->view('home/partes/header');
        $this->load->view('home/noticia/index');
        $this->load->view('home/partes/footer');
    }

//end index()

    public function view($idnoticia) {
        //Cargo noticia
        $data['torneos'] = $this->torneos_model->get_last_4_torneos();
        $noticia = $this->noticias_model->get_noticia($idnoticia);
        //Cargo la info del torneo para mostrar la noticia en el torneo
        $idtorneo = $noticia['idtorneo'];
        $data['noticia'] = $noticia;

        $data['torneo'] = $this->torneos_model->get_torneo($idtorneo);
        $data['equipos'] = $this->equipos_model->get_equipos_from_torneo($idtorneo);
        $data['tablaPosiciones'] = $this->torneos_model->get_tabla_from_torneo($idtorneo);
        $data['goleadores'] = $this->torneos_model->get_goleadores_from_torneo($idtorneo);
        $data['vallaMenosVencida'] = $this->torneos_model->get_vallaMenosVencida_from_torneo($idtorneo);
        $data['tarjetas'] = $this->torneos_model->get_tarjetas_from_torneo($idtorneo);
        $data['fixture'] = $this->partidos_model->get_fixture($idtorneo);
        $data['sedes'] = $this->sedes_model->get_sedes();
        $data['fechaelegida'] = 1;


        $this->load->view('home/partes/header', $data);
        $this->load->view('home/torneo/view', $data);
        $this->load->view('home/partes/footer', $data);
    }

//end index()
    //--------------------------------------------------------------------
}

//end class