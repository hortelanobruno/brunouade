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
class Contacto extends Front_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->helper('email');
        $this->load->library('email');
    }

    /**
     * Displays the homepage of the Bonfire app
     *
     * @return void
     */
    public function index() {
        $this->load->view('home/template/top');
        $this->load->view('contacto/index');
        $this->load->view('home/template/bottom');
    }

    public function send() {
        echo $this->input->post('mail');
        if (valid_email($this->input->post('mail'))) {
            echo 'email is valid';
//            mail($this->input->post('mail'), 'Mi título', 'asdfffffffffffffmensaje');

            $config['useragent'] = 'test'; // The "user agent".
            $config['protocol'] = 'smtp'; // mail, sendmail, or smtp    The mail sending protocol.
            $config['mailpath'] = ''; // The server path to Sendmail.
            $config['smtp_host'] = 'smtp.gmail.com'; // SMTP Server Address.
            $config['smtp_user'] = 'hortelanobruno@gmail.com'; // SMTP Username.
            $config['smtp_pass'] = '3932210540167'; // SMTP Password.
            $config['smtp_port'] = '465'; // SMTP Port.
            $config['smtp_timeout'] = '5'; // SMTP Timeout (in seconds).
            $config['wordwrap'] = TRUE; // TRUE or FALSE (boolean)    Enable word-wrap.
            $config['wrapchars'] = 76; // Character count to wrap at.
            $config['mailtype'] = 'html'; // text or html Type of mail. If you send HTML email you must send it as a complete web page. Make sure you don't have any relative links or relative image paths otherwise they will not work.
            $config['charset'] = 'utf-8'; // Character set (utf-8, iso-8859-1, etc.).
            $config['validate'] = FALSE; // TRUE or FALSE (boolean)    Whether to validate the email address.
            $config['priority'] = 3; // 1, 2, 3, 4, 5    Email Priority. 1 = highest. 5 = lowest. 3 = normal.
            $config['crlf'] = '\r\n'; // "\r\n" or "\n" or "\r" Newline character. (Use "\r\n" to comply with RFC 822).
            $config['newline'] = '\r\n'; // "\r\n" or "\n" or "\r"    Newline character. (Use "\r\n" to comply with RFC 822).
            $config['bcc_batch_mode'] = FALSE; // TRUE or FALSE (boolean)    Enable BCC Batch Mode.
            $config['bcc_batch_size'] = 200; // Number of emails in each BCC batch.';


            $this->email->initialize($config);

            $this->email->from('your@example.com', 'Your Name');
            $this->email->to($this->input->post('mail'));
//$this->email->cc('another@another-example.com'); 
//$this->email->bcc('them@their-example.com'); 

            $this->email->subject('Email Test');
            $this->email->message('Testing the emaissssssssss222222222l class.');

            $this->email->send();
            $this->load->view('contacto/valid');
        } else {
            echo 'email is not valid';
            $this->load->view('contacto/invalid');
        }
    }

//end index()
    //--------------------------------------------------------------------
}

//end class