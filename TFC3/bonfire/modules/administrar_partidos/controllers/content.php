<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class content extends Admin_Controller {

    //--------------------------------------------------------------------


    public function __construct() {
        parent::__construct();

        $this->auth->restrict('Administrar_Partidos.Content.View');
        $this->load->model('administrar_partidos_model', null, true);
        $this->load->model('torneos_model', null, true);
        $this->load->model('partidos_model', null, true);
        $this->load->model('equipos_model', null, true);
        $this->lang->load('administrar_partidos');

        Assets::add_css('flick/jquery-ui-1.8.13.custom.css');
        Assets::add_js('jquery-ui-1.8.13.min.js');
        Assets::add_css('jquery-ui-timepicker.css');
        Assets::add_js('jquery-ui-timepicker-addon.js');
        Template::set_block('sub_nav', 'content/_sub_nav');
    }

    //--------------------------------------------------------------------



    /*
      Method: index()

      Displays a list of form data.
     */
    public function index() {

        $torneos = $this->torneos_model->get_all_torneos();
        // Deleting anything?
        if ($this->input->post_key_exists('delete')) {
            $checked = $this->input->post('checked');

            if (is_array($checked) && count($checked)) {
                $result = FALSE;
                foreach ($checked as $pid) {
                    $data_old = $this->partidos_model->get_partido($pid);
                    $result = $this->administrar_partidos_model->delete($pid);
                    $this->partidos_model->actualizar_data_delete_partido($pid, $data_old);
                }

                if ($result) {
                    Template::set_message(count($checked) . ' ' . lang('administrar_partidos_delete_success'), 'success');
                } else {
                    Template::set_message(lang('administrar_partidos_delete_failure') . $this->administrar_partidos_model->error, 'error');
                }
            }
        }

        $data = array();
        $data['torneo'] = $this->input->post('torneo');
        if ($data['torneo'] == null) {
            $data['torneo'] = $torneos[0]['id'];
        }
        $data['fecha'] = $this->input->post('fecha');
        //Filtro segun tipo        
        if ($data['torneo'] != null) {
            Template::set('torneoselected', $data['torneo']);
            $torneodata = $this->torneos_model->get_torneo($data['torneo']);
            Template::set('fechas', $torneodata['cantidad_fechas']);

            if ($data['fecha'] != null) {
                Template::set('fechaselected', $data['fecha']);
                $records = $this->administrar_partidos_model->find_all_by(array('idtorneo' => $data['torneo'], 'fecha_torneo' => $data['fecha']));
            } else {
                $records = $this->administrar_partidos_model->find_all_by(array('idtorneo' => $data['torneo'], 'fecha_torneo' => 1));
            }
        } else {
//            $records = $this->administrar_partidos_model->find_all();
        }

//        $records = $this->administrar_partidos_model->find_all();



        Template::set('records', $records);
        Template::set('torneos', $torneos);
        Template::set('equipos', $this->equipos_model->get_equipos_from_torneo($data['torneo']));
        Template::set('toolbar_title', 'Manage Administrar Partidos');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: create()

      Creates a Administrar Partidos object.
     */
    public function create() {
        $this->auth->restrict('Administrar_Partidos.Content.Create');
        $torneos = $this->torneos_model->get_all_torneos();

        if ($this->input->post_key_exists('save')) {
            if ($insert_id = $this->save_administrar_partidos()) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administrar_partidos_act_create_record') . ': ' . $insert_id . ' : ' . $this->input->ip_address(), 'administrar_partidos');

                Template::set_message(lang('administrar_partidos_create_success'), 'success');
                redirect(SITE_AREA . '/content/administrar_partidos');
            } else {
                Template::set_message(lang('administrar_partidos_create_failure') . $this->administrar_partidos_model->error, 'error');
            }
        } else {
            $data = array();

            if ($this->input->post('eventfrom') == 'change_torneo') {
                $data['torneoelegido'] = $this->input->post('administrar_partidos_idtorneo');
                $data['fechaelegido'] = $this->input->post('administrar_partidos_fecha_torneo');
                $administrar_partidos = array();

                $torneodata = $this->torneos_model->get_torneo($data['torneoelegido']);
                $administrar_partidos['fechas_torneo'] = $torneodata['cantidad_fechas'];
                Template::set('torneodata', $torneodata);
                Template::set('torneoselected', $data['torneoelegido']);
                Template::set('administrar_partidos', $administrar_partidos);
                Template::set('equipos', $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']));

                $arr = (array) $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']);
                $data['equipo1'] = $arr[0]['id'];
                Template::set('jugadores1', $this->equipos_model->get_jugadores_from_equipo($data['equipo1']));
                Template::set('equipo1selected', $data['equipo1']);

                $arr = (array) $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']);
                $data['equipo2'] = $arr[1]['id'];
                Template::set('jugadores2', $this->equipos_model->get_jugadores_from_equipo($data['equipo2']));
                Template::set('equipo2selected', $data['equipo2']);
            } else if ($this->input->post('eventfrom') == 'change_equipo') {
                $data['torneoelegido'] = $this->input->post('administrar_partidos_idtorneo');
                $data['fechaelegido'] = $this->input->post('administrar_partidos_fecha_torneo');
                $administrar_partidos = array();

                $torneodata = $this->torneos_model->get_torneo($data['torneoelegido']);
                $administrar_partidos['fechas_torneo'] = $torneodata['cantidad_fechas'];
                Template::set('torneodata', $torneodata);
                Template::set('torneoselected', $data['torneoelegido']);
                Template::set('administrar_partidos', $administrar_partidos);
                Template::set('equipos', $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']));

                $data['equipo1'] = $this->input->post('administrar_partidos_idequipo1');
                Template::set('jugadores1', $this->equipos_model->get_jugadores_from_equipo($data['equipo1']));
                Template::set('equipo1selected', $data['equipo1']);

                $data['equipo2'] = $this->input->post('administrar_partidos_idequipo2');
                Template::set('jugadores2', $this->equipos_model->get_jugadores_from_equipo($data['equipo2']));
                Template::set('equipo2selected', $data['equipo2']);
            } else {
                $data['torneoelegido'] = $this->input->post('administrar_partidos_idtorneo');
                if ($data['torneoelegido'] == NULL) {
                    $data['torneoelegido'] = $torneos[0]['id'];
                }
                $data['fechaelegido'] = $this->input->post('administrar_partidos_fecha_torneo');

                $administrar_partidos = array();


                //Filtro segun tipo        
                if ($data['torneoelegido'] != NULL) {
                    $torneodata = $this->torneos_model->get_torneo($data['torneoelegido']);
                    $administrar_partidos['fechas_torneo'] = $torneodata['cantidad_fechas'];

                    Template::set('torneodata', $torneodata);
                    Template::set('torneoselected', $data['torneoelegido']);
                    Template::set('administrar_partidos', $administrar_partidos);

                    Template::set('equipos', $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']));
                }

                $data['equipo1'] = $this->input->post('administrar_partidos_idequipo1');
                if ($data['equipo1'] == NULL) {
                    $arr = (array) $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']);
                    $data['equipo1'] = $arr[0]['id'];
                }
                $data['equipo2'] = $this->input->post('administrar_partidos_idequipo2');
                if ($data['equipo2'] == NULL) {
                    $arr = (array) $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']);
                    $data['equipo2'] = $arr[1]['id'];
                }

                if ($data['equipo1'] != NULL) {
                    Template::set('jugadores1', $this->equipos_model->get_jugadores_from_equipo($data['equipo1']));
                    Template::set('equipo1selected', $data['equipo1']);
                }

                if ($data['equipo2'] != NULL) {
                    Template::set('jugadores2', $this->equipos_model->get_jugadores_from_equipo($data['equipo2']));
                    Template::set('equipo2selected', $data['equipo2']);
                }
            }
        }

        Assets::add_module_js('administrar_partidos', 'administrar_partidos.js');

        Template::set('torneos', $torneos);
        Template::set('toolbar_title', lang('administrar_partidos_create') . ' Administrar Partidos');
        Template::render();
    }

    //--------------------------------------------------------------------



    /*
      Method: edit()

      Allows editing of Administrar Partidos data.
     */
    public function edit() {
        $id = $this->uri->segment(5);

        if (empty($id)) {
            Template::set_message(lang('administrar_partidos_invalid_id'), 'error');
            redirect(SITE_AREA . '/content/administrar_partidos');
        }

        $partido = $this->administrar_partidos_model->find($id);

        if ($this->input->post_key_exists('save')) {
            $this->auth->restrict('Administrar_Partidos.Content.Edit');

            if ($this->save_administrar_partidos('update', $id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administrar_partidos_act_edit_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'administrar_partidos');

                Template::set_message(lang('administrar_partidos_edit_success'), 'success');
            } else {
                Template::set_message(lang('administrar_partidos_edit_failure') . $this->administrar_partidos_model->error, 'error');
            }
        } else if ($this->input->post_key_exists('delete')) {
            $this->auth->restrict('Administrar_Partidos.Content.Delete');

            if ($this->administrar_partidos_model->delete($id)) {
                // Log the activity
                $this->activity_model->log_activity($this->current_user->id, lang('administrar_partidos_act_delete_record') . ': ' . $id . ' : ' . $this->input->ip_address(), 'administrar_partidos');

                Template::set_message(lang('administrar_partidos_delete_success'), 'success');

                redirect(SITE_AREA . '/content/administrar_partidos');
            } else {
                Template::set_message(lang('administrar_partidos_delete_failure') . $this->administrar_partidos_model->error, 'error');
            }
        }
        //Filtro segun tipo        
        $data = array();
        $data['torneoelegido'] = $partido->idtorneo;
        $data['fechaelegido'] = $partido->fecha_torneo;
        $data['equipo1'] = $partido->idequipo1;
        $data['equipo2'] = $partido->idequipo2;



        if ($data['torneoelegido'] != NULL) {
            $torneodata = $this->torneos_model->get_torneo($data['torneoelegido']);

            Template::set('torneodata', $torneodata);
            Template::set('torneoselected', $data['torneoelegido']);

            Template::set('equipos', $this->equipos_model->get_equipos_from_torneo($data['torneoelegido']));
        }

        if ($data['fechaelegido'] != NULL) {
            Template::set('fechaselected', $data['fechaelegido']);
        }

        if ($data['equipo1'] != NULL) {
            Template::set('jugadores1', $this->equipos_model->get_jugadores_from_equipo($data['equipo1']));
            Template::set('equipo1selected', $data['equipo1']);
            Template::set('equipo1golesencontra', $this->partidos_model->get_estadistica_goles_en_contra($id, $data['equipo1']));
        }

        if ($data['equipo2'] != NULL) {
            Template::set('jugadores2', $this->equipos_model->get_jugadores_from_equipo($data['equipo2']));
            Template::set('equipo2selected', $data['equipo2']);
            Template::set('equipo2golesencontra', $this->partidos_model->get_estadistica_goles_en_contra($id, $data['equipo2']));
        }

        Template::set('estadisticaspartido', $this->partidos_model->get_estadisticas_partido($id));
        //fin

        Template::set('administrar_partidos', $partido);
        Assets::add_module_js('administrar_partidos', 'administrar_partidos.js');

        Template::set('torneos', $this->torneos_model->get_all_torneos());
        Template::set('toolbar_title', lang('administrar_partidos_edit') . ' Administrar Partidos');
        Template::render();
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    // !PRIVATE METHODS
    //--------------------------------------------------------------------

    /*
      Method: save_administrar_partidos()

      Does the actual validation and saving of form data.

      Parameters:
      $type	- Either "insert" or "update"
      $id		- The ID of the record to update. Not needed for inserts.

      Returns:
      An INT id for successful inserts. If updating, returns TRUE on success.
      Otherwise, returns FALSE.
     */
    private function save_administrar_partidos($type = 'insert', $id = 0) {
        if ($type == 'update') {
            $_POST['id'] = $id;
        }


        $this->form_validation->set_rules('administrar_partidos_fecha', 'Fecha', 'max_length[200]');
        $this->form_validation->set_rules('administrar_partidos_idtorneo', 'Idtorneo', 'max_length[11]');
        $this->form_validation->set_rules('administrar_partidos_idfase', 'Idfase', 'max_length[11]');
        $this->form_validation->set_rules('administrar_partidos_idequipo1', 'Idequipo1', 'max_length[11]');
        $this->form_validation->set_rules('administrar_partidos_idequipo2', 'Idequipo2', 'max_length[11]');
        $this->form_validation->set_rules('administrar_partidos_goles_equipo1', 'Goles Equipo1', 'max_length[11]');
        $this->form_validation->set_rules('administrar_partidos_goles_equipo2', 'Goles Equipo2', 'max_length[11]');
        $this->form_validation->set_rules('administrar_partidos_fecha_torneo', 'Fecha Torneo', 'max_length[11]');
        $this->form_validation->set_rules('administrar_partidos_jugado', 'Jugado', 'max_length[1]');

        if ($this->form_validation->run() === FALSE) {
            return FALSE;
        }

        // make sure we only pass in the fields we want

        $data = array();
        $data['fecha'] = $this->input->post('administrar_partidos_fecha') ? $this->input->post('administrar_partidos_fecha') : '0000-00-00 00:00:00';
        $data['idtorneo'] = intval($this->input->post('administrar_partidos_idtorneo'));
//        $data['idfase'] = intval($this->input->post('administrar_partidos_idfase'));
        $data['idequipo1'] = intval($this->input->post('administrar_partidos_idequipo1'));
        $data['idequipo2'] = intval($this->input->post('administrar_partidos_idequipo2'));
        $data['goles_equipo1'] = intval($this->input->post('administrar_partidos_goles_equipo1'));
        $data['goles_equipo2'] = intval($this->input->post('administrar_partidos_goles_equipo2'));
        $data['fecha_torneo'] = intval($this->input->post('administrar_partidos_fecha_torneo'));
        $data['jugado'] = $this->input->post('administrar_partidos_jugado');
        $data['idfase'] = $this->torneos_model->get_fase_torneo($data);

        if ($type == 'insert') {
            //Creacion de partido
            $id = $this->administrar_partidos_model->insert($data);

            if (is_numeric($id)) {
                $return = $id;
            } else {
                $return = FALSE;
            }

            $this->partidos_model->actualizar_data_creacion_partido($id, $data, $_POST["jugador1id"], $_POST["jugador1goles"], $_POST["jugador1tarjetaamarilla"], $_POST["jugador1tarjetaroja"], $_POST["jugador2id"], $_POST["jugador2goles"], $_POST["jugador2tarjetaamarilla"], $_POST["jugador2tarjetaroja"], $_POST["equipo1golesencontra"], $_POST["equipo2golesencontra"]);
        } else if ($type == 'update') {
            $data_old = $this->partidos_model->get_partido($id);
            $return = $this->administrar_partidos_model->update($id, $data);

            $this->partidos_model->actualizar_data_actualizacion_partido($id, $data, $data_old, $_POST["jugador1id"], $_POST["jugador1goles"], $_POST["jugador1tarjetaamarilla"], $_POST["jugador1tarjetaroja"], $_POST["jugador2id"], $_POST["jugador2goles"], $_POST["jugador2tarjetaamarilla"], $_POST["jugador2tarjetaroja"], $_POST["equipo1golesencontra"], $_POST["equipo2golesencontra"]);
        }

        return $return;
    }

    //--------------------------------------------------------------------
}