
<?php if (validation_errors()) : ?>
    <div class="alert alert-block alert-error fade in ">
        <a class="close" data-dismiss="alert">&times;</a>
        <h4 class="alert-heading">Please fix the following errors :</h4>
        <?php echo validation_errors(); ?>
    </div>
<?php endif; ?>
<?php
// Change the css classes to suit your needs
if (isset($administrar_partidos)) {
    $administrar_partidos = (array) $administrar_partidos;
}
$id = isset($administrar_partidos['id']) ? $administrar_partidos['id'] : '';
?>
<div class="admin-box">
    <h3>Administrar Partidos</h3>
    <?php
    $attributes = array('id' => 'formcreatepart', 'name' => 'formcreatepart', 'class' => 'form-horizontal');
    echo form_open($this->uri->uri_string(), $attributes);
    ?>
    <fieldset>
        <table style="margin-top: 30px;">
            <tr>
                <td>
                    <div class="control-group <?php echo form_error('administrar_partidos_idtorneo') ? 'error' : ''; ?>">
                        <?php echo form_label('Torneo', 'administrar_partidos_idtorneo', array('class' => "control-label")); ?>
                        <div class="controls">
                            <!--<input id="administrar_partidos_idtorneo" type="text" name="administrar_partidos_idtorneo" maxlength="11" value="<!?php echo set_value('administrar_partidos_idtorneo', isset($administrar_partidos['idtorneo']) ? $administrar_partidos['idtorneo'] : ''); ?>"  />-->
                            <?php if (isset($torneos)) { ?>
                                <select id="administrar_partidos_idtorneo" name="administrar_partidos_idtorneo" onchange="return changeTorneo()">
                                    <?php foreach ($torneos as $torneo): ?>
                                        <?php if ($torneo['id'] === $torneoselected) : ?>
                                            <option value="<?php echo $torneo['id'] ?>" selected><?php echo $torneo['nombre'] ?></option>
                                        <?php else : ?>
                                            <option value="<?php echo $torneo['id'] ?>"><?php echo $torneo['nombre'] ?></option>
                                        <?php endif; ?>
                                    <?php endforeach ?>
                                </select>
                            <?php } ?>
                            <input type="hidden" id="refasync" name="refasync" value="no"/>
                            <span class="help-inline"><?php echo form_error('administrar_partidos_idtorneo'); ?></span>
                        </div>
                    </div> 
                </td>
                <td>

                    <!--                    <div class="control-group <!?php echo form_error('administrar_partidos_idfase') ? 'error' : ''; ?>">
                                            <!?php echo form_label('Fase/Ronda', 'administrar_partidos_idfase', array('class' => "control-label")); ?>
                                            <div class="controls">
                                                <input id="administrar_partidos_idfase" type="text" name="administrar_partidos_idfase" maxlength="11" value="<!?php echo set_value('administrar_partidos_idfase', isset($administrar_partidos['idfase']) ? $administrar_partidos['idfase'] : ''); ?>"  />
                                                <span class="help-inline"><!?php echo form_error('administrar_partidos_idfase'); ?></span>
                                            </div>
                                        </div>-->




                    <div class="control-group <?php echo form_error('administrar_partidos_fecha_torneo') ? 'error' : ''; ?>">
                        <?php echo form_label('Fecha', 'administrar_partidos_fecha_torneo', array('class' => "control-label")); ?>
                        <div class="controls">
                            <!--<input id="administrar_partidos_fecha_torneo" type="text" name="administrar_partidos_fecha_torneo" maxlength="11" value="<!?php echo set_value('administrar_partidos_fecha_torneo', isset($administrar_partidos['fecha_torneo']) ? $administrar_partidos['fecha_torneo'] : ''); ?>"  />-->

                            <?php if (isset($torneodata['cantidad_fechas'])) { ?>
                                <select id="administrar_partidos_fecha_torneo" name="administrar_partidos_fecha_torneo">
                                    <?php for ($i = 1; $i <= $torneodata['cantidad_fechas']; $i++): ?>
                                        <?php if ($i == $fechaselected) : ?>
                                            <option value="<?php echo $i ?>" selected><?php echo $i ?></option>
                                        <?php else : ?>
                                            <option value="<?php echo $i ?>"><?php echo $i ?></option>
                                        <?php endif; ?>
                                    <?php endfor ?>
                                </select>
                            <?php } ?>

                            <span class="help-inline"><?php echo form_error('fecha_torneo'); ?></span>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="control-group <?php echo form_error('administrar_partidos_fecha') ? 'error' : ''; ?>">
                        <?php echo form_label('Fecha', 'administrar_partidos_fecha', array('class' => "control-label")); ?>
                        <div class="controls">
                            <input id="administrar_partidos_fecha" type="text" name="administrar_partidos_fecha" maxlength="1" value="<?php echo set_value('administrar_partidos_fecha', isset($administrar_partidos['fecha']) ? $administrar_partidos['fecha'] : ''); ?>"  />
                            <span class="help-inline"><?php echo form_error('administrar_partidos_fecha'); ?></span>
                        </div>
                    </div> 
                </td>
            </tr>
            <tr>
                <td style="padding-right: 50px;">
                    <div class="control-group <?php echo form_error('administrar_partidos_idequipo1') ? 'error' : ''; ?>">
                        <?php echo form_label('Equipo 1', 'administrar_partidos_idequipo1', array('class' => "control-label")); ?>
                        <div class="controls">
                            <!--<input id="administrar_partidos_idequipo1" type="text" name="administrar_partidos_idequipo1" maxlength="11" value="<!?php echo set_value('administrar_partidos_idequipo1', isset($administrar_partidos['idequipo1']) ? $administrar_partidos['idequipo1'] : ''); ?>"  />-->
                            <?php if (isset($equipos)) { ?>
                                <select id="administrar_partidos_idequipo1" name="administrar_partidos_idequipo1" onchange="return changeTorneo()">
                                    <?php foreach ($equipos as $equipo): ?>
                                        <?php if ($equipo['id'] === $equipo1selected) : ?>
                                            <option value="<?php echo $equipo['id'] ?>" selected><?php echo $equipo['nombre'] ?></option>
                                        <?php else : ?>
                                            <option value="<?php echo $equipo['id'] ?>"><?php echo $equipo['nombre'] ?></option>
                                        <?php endif; ?>
                                    <?php endforeach ?>
                                </select>
                            <?php } ?>
                            <span class="help-inline"><?php echo form_error('administrar_partidos_idequipo1'); ?></span>
                        </div>
                    </div>  
                </td>
                <td>
                    <div class="control-group <?php echo form_error('administrar_partidos_idequipo2') ? 'error' : ''; ?>">
                        <?php echo form_label('Equipo 2', 'administrar_partidos_idequipo2', array('class' => "control-label")); ?>
                        <div class="controls">
                            <!--<input id="administrar_partidos_idequipo2" type="text" name="administrar_partidos_idequipo2" maxlength="11" value="<!?php echo set_value('administrar_partidos_idequipo2', isset($administrar_partidos['idequipo2']) ? $administrar_partidos['idequipo2'] : ''); ?>"  />-->
                            <?php if (isset($equipos)) { ?>
                                <select id="administrar_partidos_idequipo2" name="administrar_partidos_idequipo2" onchange="return changeTorneo()">
                                    <?php foreach ($equipos as $equipo): ?>
                                        <?php if ($equipo['id'] === $equipo2selected) : ?>
                                            <option value="<?php echo $equipo['id'] ?>" selected><?php echo $equipo['nombre'] ?></option>
                                        <?php else : ?>
                                            <option value="<?php echo $equipo['id'] ?>"><?php echo $equipo['nombre'] ?></option>
                                        <?php endif; ?>
                                    <?php endforeach ?>
                                </select>
                            <?php } ?>
                            <span class="help-inline"><?php echo form_error('administrar_partidos_idequipo2'); ?></span>
                        </div>
                    </div>   
                </td>
            </tr>
            <tr>
                <td style="padding-right: 50px;">
                    <div class="control-group <?php echo form_error('administrar_partidos_goles_equipo1') ? 'error' : ''; ?>">
                        <?php echo form_label('Goles Equipo 1', 'administrar_partidos_goles_equipo1', array('class' => "control-label")); ?>
                        <div class="controls">
                            <input id="administrar_partidos_goles_equipo1" type="text" name="administrar_partidos_goles_equipo1" maxlength="11" value="<?php echo set_value('administrar_partidos_goles_equipo1', isset($administrar_partidos['goles_equipo1']) ? $administrar_partidos['goles_equipo1'] : '0'); ?>"  />
                            <span class="help-inline"><?php echo form_error('administrar_partidos_goles_equipo1'); ?></span>
                        </div>
                    </div>      
                </td>
                <td>
                    <div class="control-group <?php echo form_error('administrar_partidos_goles_equipo2') ? 'error' : ''; ?>">
                        <?php echo form_label('Goles Equipo 2', 'administrar_partidos_goles_equipo2', array('class' => "control-label")); ?>
                        <div class="controls">
                            <input id="administrar_partidos_goles_equipo2" type="text" name="administrar_partidos_goles_equipo2" maxlength="11" value="<?php echo set_value('administrar_partidos_goles_equipo2', isset($administrar_partidos['goles_equipo2']) ? $administrar_partidos['goles_equipo2'] : '0'); ?>"  />
                            <span class="help-inline"><?php echo form_error('administrar_partidos_goles_equipo2'); ?></span>
                        </div>
                    </div>    
                </td>
            </tr>
            <tr>
                <td style="padding-right: 50px;">
                    <div class="control-group" style="padding-left: 110px;">
                        <table>
                            <thead>
                                <tr>
                                    <td>Juagador</td>
                                    <td>Goles</td>
                                    <td>Amarillas</td>
                                    <td>Rojas</td>
                                </tr>
                            </thead>
                            <tbody>
                                <?php if (isset($jugadores1)) { ?>
                                    <?php foreach ($jugadores1 as $jugador): ?>
                                        <tr>
                                            <td style="width: 150px">
                                                <?php echo $jugador['nombre_completo'] ?>
                                                <input type="hidden" id="jugador1id" name="jugador1id[]" style="width: 30px" value="<?php echo $jugador['id'] ?>" />
                                            </td>
                                            <td style="width: 60px">
                                                <input type="text" id="jugador1goles" name="jugador1goles[]" style="width: 30px" oninput="calculateGoles('jugador1goles','administrar_partidos_goles_equipo1','equipo1golesencontra')" value="<?php echo getEstadistica($estadisticaspartido, $jugador['id'], 1) ?>" />
                                            </td>
                                            <td style="width: 60px">
                                                <input type="text" id="jugador1tarjetaamarilla" name="jugador1tarjetaamarilla[]" style="width: 30px" value="<?php echo getEstadistica($estadisticaspartido, $jugador['id'], 2) ?>" />
                                            </td>
                                            <td style="width: 60px">
                                                <input type="text" id="jugador1tarjetaroja" name="jugador1tarjetaroja[]" style="width: 30px" value="<?php echo getEstadistica($estadisticaspartido, $jugador['id'], 3) ?>" />
                                            </td>
                                        </tr>
                                    <?php endforeach ?>
                                <?php } ?>
                            </tbody>
                        </table>
                    </div>
                </td>
                <td>
                    <div class="control-group" style="padding-left: 110px;">
                        <table>
                            <thead>
                                <tr>
                                    <td>Juagador</td>
                                    <td>Goles</td>
                                    <td>Amarillas</td>
                                    <td>Rojas</td>
                                </tr>
                            </thead>
                            <tbody>
                                <?php if (isset($jugadores2)) { ?>
                                    <?php foreach ($jugadores2 as $jugador): ?>
                                        <tr>
                                            <td style="width: 150px">
                                                <?php echo $jugador['nombre_completo'] ?>
                                                <input type="hidden" id="jugador2id" name="jugador2id[]" style="width: 30px" value="<?php echo $jugador['id'] ?>" />
                                            </td>
                                            <td style="width: 60px">
                                                <input type="text" id="jugador2goles" name="jugador2goles[]" style="width: 30px" oninput="calculateGoles('jugador2goles','administrar_partidos_goles_equipo2','equipo2golesencontra')" value="<?php echo getEstadistica($estadisticaspartido, $jugador['id'], 1) ?>" />
                                            </td>
                                            <td style="width: 60px">
                                                <input type="text" id="jugador2tarjetaamarilla" name="jugador2tarjetaamarilla[]" style="width: 30px" value="<?php echo getEstadistica($estadisticaspartido, $jugador['id'], 2) ?>" />
                                            </td>
                                            <td style="width: 60px">
                                                <input type="text" id="jugador2tarjetaroja" name="jugador2tarjetaroja[]" style="width: 30px" value="<?php echo getEstadistica($estadisticaspartido, $jugador['id'], 3) ?>" />
                                            </td>
                                        </tr>
                                    <?php endforeach ?>
                                <?php } ?>
                            </tbody>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td style="padding-right: 50px;">
                    <div class="control-group">
                        <label class="control-label">Goles en contra</label>
                        <div class="controls">
                            <input type="text" id="equipo1golesencontra" name="equipo1golesencontra" style="width: 30px" oninput="calculateGoles('jugador1goles','administrar_partidos_goles_equipo1','equipo1golesencontra')" value="<?php echo $equipo1golesencontra ?>" />
                        </div>
                    </div> 
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">Goles en contra</label>
                        <div class="controls">
                            <input type="text" id="equipo2golesencontra" name="equipo2golesencontra" style="width: 30px" oninput="calculateGoles('jugador2goles','administrar_partidos_goles_equipo2','equipo2golesencontra')" value="<?php echo $equipo2golesencontra ?>" />
                        </div>
                    </div> 
                </td>
            </tr>
        </table>
        <div class="control-group <?php echo form_error('administrar_partidos_jugado') ? 'error' : ''; ?>">
            <?php echo form_label('Terminado', 'administrar_partidos_jugado', array('class' => "control-label")); ?>
            <div class="controls">
                <label class="checkbox" for="jugado">
                    <input type="checkbox" id="administrar_partidos_jugado" name="administrar_partidos_jugado" value="1" <?php echo (isset($administrar_partidos['jugado']) && $administrar_partidos['jugado'] == 1) ? 'checked="checked"' : set_checkbox('jugado', 1); ?>>
                    <span class="help-inline"><?php echo form_error('administrar_partidos_jugado'); ?></span>
                </label>

            </div>
        </div>


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Edit Administrar Partidos" />
            or <?php echo anchor(SITE_AREA . '/content/administrar_partidos', lang('administrar_partidos_cancel'), 'class="btn btn-warning"'); ?>


            <?php if ($this->auth->has_permission('Administrar_Partidos.Content.Delete')) : ?>

                or <button type="submit" name="delete" class="btn btn-danger" id="delete-me" onclick="return confirm('<?php e(js_escape(lang('administrar_partidos_delete_confirm'))); ?>')">
                    <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('administrar_partidos_delete_record'); ?>
                </button>

            <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
<?php

function getEstadistica($estadisticaspartido, $idjugador, $accion) {
    foreach ($estadisticaspartido as $est) {
        if ($est['idjugador'] == $idjugador && $est['accion'] == $accion) {
            return $est['cantidad'];
        }
    }
    return "";
}
?>