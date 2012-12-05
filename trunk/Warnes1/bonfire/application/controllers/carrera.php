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
class Carrera extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('carreras_model');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $data['categorias'] = $this->carreras_model->get_all_categorias();
        $idcategoriadefault = $data['categorias'][0]['idcarrera_categoria'];
        $data['calendario'] = $this->carreras_model->get_calendario_by_categoria($idcategoriadefault);
        $data['categoria_sel'] = $idcategoriadefault;
        if (!empty($data['calendario'])) {
            $añodefault = $data['calendario'][0]['año'];
            $data['año_sel'] = $añodefault;
            $data['carreras'] = $this->carreras_model->get_carreras_by_categoria_and_año($idcategoriadefault,$añodefault);
        }else{
            $data['carreras'] = array();
            $data['año_sel'] = '';
        }
        $this->load->view('home/template/top', $data);
        $this->load->view('carrera/index', $data);
        $this->load->view('home/template/bottom', $data);
    }
    
    public function categoria($idcategoria) {
        $data['categorias'] = $this->carreras_model->get_all_categorias();
        $data['calendario'] = $this->carreras_model->get_calendario_by_categoria($idcategoria);
        if (!empty($data['calendario'])) {
            $añodefault = $data['calendario'][0]['año'];
            $data['año_sel'] = $añodefault;
            $data['carreras'] = $this->carreras_model->get_carreras_by_categoria_and_año($idcategoria,$añodefault);
        }else{
            $data['carreras'] = array();
            $data['año_sel'] = '';
        }
        $data['categoria_sel'] = $idcategoria;
        $this->load->view('home/template/top', $data);
        $this->load->view('carrera/index', $data);
        $this->load->view('home/template/bottom', $data);
    }
    
    public function calendario($año,$idcategoria) {
        $data['categorias'] = $this->carreras_model->get_all_categorias();
        $data['calendario'] = $this->carreras_model->get_calendario_by_categoria($idcategoria);
        $data['carreras'] = $this->carreras_model->get_carreras_by_categoria_and_año($idcategoria,$año);
        $data['categoria_sel'] = $idcategoria;
        $data['año_sel'] = $año;
        $this->load->view('home/template/top', $data);
        $this->load->view('carrera/index', $data);
        $this->load->view('home/template/bottom', $data);
    }

//end index()
    //--------------------------------------------------------------------
}

//end class