<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
/*
| -------------------------------------------------------------------------
| URI ROUTING
| -------------------------------------------------------------------------
| This file lets you re-map URI requests to specific controller functions.
|
| Typically there is a one-to-one relationship between a URL string
| and its corresponding controller class/method. The segments in a
| URL normally follow this pattern:
|
|	example.com/class/method/id/
|
| In some instances, however, you may want to remap this relationship
| so that a different class/function is called than the one
| corresponding to the URL.
|
| Please see the user guide for complete details:
|
|	http://codeigniter.com/user_guide/general/routing.html
|
| -------------------------------------------------------------------------
| RESERVED ROUTES
| -------------------------------------------------------------------------
|
| There area two reserved routes:
|
|	$route['default_controller'] = 'welcome';
|
| This route indicates which controller class should be loaded if the
| URI contains no data. In the above example, the "welcome" class
| would be loaded.
|
|	$route['404_override'] = 'errors/page_missing';
|
| This route will tell the Router what URI segments to use if those provided
| in the URL cannot be matched to a valid route.
|
*/

$route['default_controller'] = "home";
$route['rubro/(:any)'] = 'rubro/view/$1';
$route['rubros'] = 'rubro';
$route['marca/(:any)'] = 'marca/view/$1';
$route['marcas'] = 'marca';
$route['zona/(:any)'] = 'zona/view/$1';
$route['zonas'] = 'zona';
$route['cliente/(:any)'] = 'cliente/index/$1';
$route['noticias/archivo/(:any)'] = 'noticia/archivo/$1';
$route['noticias/(:any)'] = 'noticia/view/$1';
$route['noticias'] = 'noticia';
$route['carreras/categoria/(:any)'] = 'carrera/categoria/$1';
$route['carreras/calendario/(:any),(:any)'] = 'carrera/calendario/$1,$2';
$route['carreras'] = 'carrera';
$route['404_override'] = '';


// Authorization
$route['login']					= 'users/login';
$route['register']				= 'users/register';
$route['logout']				= 'users/logout';
$route['forgot_password']		= 'users/forgot_password';
$route['reset_password/(:any)/(:any)']	= "users/reset_password/$1/$2";
$route['activate/(:any)/(:any)']					= 'users/activate/$1/$2';

// Contexts
$route[SITE_AREA .'/([a-z_]+)/(:any)/(:any)/(:any)/(:any)/(:any)']		= "$2/$1/$3/$4/$5/$6";
$route[SITE_AREA .'/([a-z_]+)/(:any)/(:any)/(:any)/(:any)']		= "$2/$1/$3/$4/$5";
$route[SITE_AREA .'/([a-z_]+)/(:any)/(:any)/(:any)']		= "$2/$1/$3/$4";
$route[SITE_AREA .'/([a-z_]+)/(:any)/(:any)'] 		= "$2/$1/$3";
$route[SITE_AREA .'/([a-z_]+)/(:any)']				= "$2/$1/index";
$route[SITE_AREA .'/content']				= "admin/content/index";
$route[SITE_AREA .'/reports']				= "admin/reports/index";
$route[SITE_AREA .'/developer']				= "admin/developer/index";
$route[SITE_AREA .'/settings']				= "settings/index";

$route[SITE_AREA]	= SITE_AREA .'/home';

// Activation
$route['activate']		        = 'users/activate';
$route['resend_activation']		= 'users/resend_activation';

/* End of file routes.php */
/* Location: ./application/config/routes.php */