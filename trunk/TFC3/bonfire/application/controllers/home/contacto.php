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
 * Contacto controller
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
class Contacto extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('torneos_model');
        $this->load->model('equipos_model');
        $this->load->library('emailer/emailer');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $data['torneos'] = $this->torneos_model->get_last_4_torneos();
        $this->load->view('home/partes/header', $data);
        $this->load->view('home/contacto/index', $data);
        $this->load->view('home/partes/footer', $data);
    }
    
    public function sendmail() {
        foreach ($this->input->post() as $name => $value) {
            $data[$name] = $value;
        }
        $data = array(
            'to' => 'info@tfcdelsur.com.ar', // either string or array
            'subject' => 'Mensaje de ' . $data['nombre'], // string
            'message' => '<div>Nombre: ' . $data['nombre'] . '</div><br/><div>Email: ' . $data['email'] . '</div><br/><div>Telefono: ' . $data['telefono'] . '</div><br/><div>Equipo: ' . $data['equipo'].'</div><br/><div>Consulta: ' . $data['consulta'].'</div><br/><br/>', // string
            'alt_message' => ''       // optional (text alt to html email)
        );
        $this->emailer->send($data);
        $data['enviado'] = 'Enviado!';
        $data['torneos'] = $this->torneos_model->get_last_4_torneos();
        $this->load->view('home/partes/header', $data);
        $this->load->view('home/contacto/index', $data);
        $this->load->view('home/partes/footer', $data);
    }

//end index()
    //--------------------------------------------------------------------
}

//end class