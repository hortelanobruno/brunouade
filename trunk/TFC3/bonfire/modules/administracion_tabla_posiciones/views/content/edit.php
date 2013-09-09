
<?php if (validation_errors()) : ?>
    <div class="alert alert-block alert-error fade in ">
        <a class="close" data-dismiss="alert">&times;</a>
        <h4 class="alert-heading">Please fix the following errors :</h4>
        <?php echo validation_errors(); ?>
    </div>
<?php endif; ?>
<?php
// Change the css classes to suit your needs
if (isset($administracion_tabla_posiciones)) {
    $administracion_tabla_posiciones = (array) $administracion_tabla_posiciones;
}
$id = isset($administracion_tabla_posiciones['idtorneo']) ? $administracion_tabla_posiciones['idtorneo'] : '';
?>
<div class="admin-box">
    <h3>Administracion Tabla Posiciones</h3>
    <?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <!--        <div class="control-group <!?php echo form_error('administracion_tabla_posiciones_idfase') ? 'error' : ''; ?>">
                    <!?php echo form_label('Idfase', 'administracion_tabla_posiciones_idfase', array('class' => "control-label")); ?>
                    <div class="controls">
                        <input id="administracion_tabla_posiciones_idfase" type="text" name="administracion_tabla_posiciones_idfase" maxlength="11" value="<!?php echo set_value('administracion_tabla_posiciones_idfase', isset($administracion_tabla_posiciones['idfase']) ? $administracion_tabla_posiciones['idfase'] : ''); ?>"  />
                        <span class="help-inline"><!?php echo form_error('administracion_tabla_posiciones_idfase'); ?></span>
                    </div>
                </div>        -->
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_idequipo') ? 'error' : ''; ?>">
            <?php echo form_label('Idequipo', 'administracion_tabla_posiciones_idequipo', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_idequipo" type="hidden" name="administracion_tabla_posiciones_idequipo" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_idequipo', isset($administracion_tabla_posiciones['idequipo']) ? $administracion_tabla_posiciones['idequipo'] : ''); ?>"  />
                <?php echo $equipo['nombre'] ?>
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_idequipo'); ?></span>
            </div>
        </div>        
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_puntos') ? 'error' : ''; ?>">
            <?php echo form_label('Puntos', 'administracion_tabla_posiciones_puntos', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_puntos" type="text" name="administracion_tabla_posiciones_puntos" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_puntos', isset($administracion_tabla_posiciones['puntos']) ? $administracion_tabla_posiciones['puntos'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_puntos'); ?></span>
            </div>
        </div>        
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_partidos_jugados') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Jugados', 'administracion_tabla_posiciones_partidos_jugados', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_partidos_jugados" type="text" name="administracion_tabla_posiciones_partidos_jugados" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_partidos_jugados', isset($administracion_tabla_posiciones['partidos_jugados']) ? $administracion_tabla_posiciones['partidos_jugados'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_partidos_jugados'); ?></span>
            </div>
        </div>        
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_partidos_ganados') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Ganados', 'administracion_tabla_posiciones_partidos_ganados', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_partidos_ganados" type="text" name="administracion_tabla_posiciones_partidos_ganados" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_partidos_ganados', isset($administracion_tabla_posiciones['partidos_ganados']) ? $administracion_tabla_posiciones['partidos_ganados'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_partidos_ganados'); ?></span>
            </div>
        </div>        
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_partidos_empatados') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Empatados', 'administracion_tabla_posiciones_partidos_empatados', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_partidos_empatados" type="text" name="administracion_tabla_posiciones_partidos_empatados" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_partidos_empatados', isset($administracion_tabla_posiciones['partidos_empatados']) ? $administracion_tabla_posiciones['partidos_empatados'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_partidos_empatados'); ?></span>
            </div>
        </div>        
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_partidos_perdidos') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Perdidos', 'administracion_tabla_posiciones_partidos_perdidos', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_partidos_perdidos" type="text" name="administracion_tabla_posiciones_partidos_perdidos" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_partidos_perdidos', isset($administracion_tabla_posiciones['partidos_perdidos']) ? $administracion_tabla_posiciones['partidos_perdidos'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_partidos_perdidos'); ?></span>
            </div>
        </div>        
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_goles_a_favor') ? 'error' : ''; ?>">
            <?php echo form_label('Goles A Favor', 'administracion_tabla_posiciones_goles_a_favor', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_goles_a_favor" type="text" name="administracion_tabla_posiciones_goles_a_favor" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_goles_a_favor', isset($administracion_tabla_posiciones['goles_a_favor']) ? $administracion_tabla_posiciones['goles_a_favor'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_goles_a_favor'); ?></span>
            </div>
        </div>        
        <div class="control-group <?php echo form_error('administracion_tabla_posiciones_goles_en_contra') ? 'error' : ''; ?>">
            <?php echo form_label('Goles En Contra', 'administracion_tabla_posiciones_goles_en_contra', array('class' => "control-label")); ?>
            <div class="controls">
                <input id="administracion_tabla_posiciones_goles_en_contra" type="text" name="administracion_tabla_posiciones_goles_en_contra" maxlength="11" value="<?php echo set_value('administracion_tabla_posiciones_goles_en_contra', isset($administracion_tabla_posiciones['goles_en_contra']) ? $administracion_tabla_posiciones['goles_en_contra'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administracion_tabla_posiciones_goles_en_contra'); ?></span>
            </div>
        </div>


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Edit Administracion Tabla Posiciones" />
            or <?php echo anchor(SITE_AREA . '/content/administracion_tabla_posiciones', lang('administracion_tabla_posiciones_cancel'), 'class="btn btn-warning"'); ?>


            <?php if ($this->auth->has_permission('Administracion_Tabla_Posiciones.Content.Delete')) : ?>

                or <button type="submit" name="delete" class="btn btn-danger" id="delete-me" onclick="return confirm('<?php e(js_escape(lang('administracion_tabla_posiciones_delete_confirm'))); ?>')">
                    <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('administracion_tabla_posiciones_delete_record'); ?>
                </button>

            <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
