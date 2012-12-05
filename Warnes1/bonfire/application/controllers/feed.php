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
class Feed extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->helper('xml');
        $this->load->helper('text');
        $this->load->model('noticias_model');
    }

    public function index() {
        $data['feed_name'] = 'MundoMotorizado.com'; // your website  
        $data['encoding'] = 'utf-8'; // the encoding  
        $data['feed_url'] = 'http://www.MundoMotorizado.com/feed'; // the url to your feed  
        $data['page_description'] = 'What my site is about comes here'; // some description  
        $data['page_language'] = 'es-es'; // the language  
        $data['creator_email'] = 'info@mundomotorizado.com'; // your email  
        $data['posts'] = $this->noticias_model->get_ultimas_noticias(10);
        header("Content-Type: application/rss+xml"); // important!  
        $this->load->view('feed/index', $data);
    }

}

//end class

