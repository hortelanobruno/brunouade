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
class Noticia extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('noticias_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $data['noticias'] = $this->noticias_model->get_ultimas_noticias(8);
        $data['archivos'] = $this->noticias_model->get_fecha_para_archivos();
        $this->load->view('home/template/top', $data);
        $this->load->view('noticia/index', $data);
        $this->load->view('home/template/bottom', $data);
    }

    public function view($idnoticia) {
        $data['noticia'] = $this->noticias_model->get_noticia($idnoticia);
        $data['fotos'] = $this->noticias_model->get_fotos_by_noticia($idnoticia);
        $this->load->view('home/template/top', $data);
        $this->load->view('noticia/view', $data);
        $this->load->view('home/template/bottom', $data);
    }

    public function archivo($archivo) {
        $data['archivos'] = $this->noticias_model->get_fecha_para_archivos();
        $data['noticias'] = $this->noticias_model->get_noticias_by_archivo($archivo);
        $this->load->view('home/template/top', $data);
        $this->load->view('noticia/archive', $data);
        $this->load->view('home/template/bottom', $data);
    }

//end index()
    //--------------------------------------------------------------------
}

//end class