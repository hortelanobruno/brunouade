
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
        <div class="control-group <?php echo form_error('idfase') ? 'error' : ''; ?>">
            <?php echo form_label('Idfase', 'idfase', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="idfase" type="text" name="idfase" maxlength="11" value="<?php echo set_value('idfase', isset($administracion_tabla_posiciones['idfase']) ? $administracion_tabla_posiciones['idfase'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('idfase'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('idequipo') ? 'error' : ''; ?>">
            <?php echo form_label('Idequipo', 'idequipo', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="idequipo" type="text" name="idequipo" maxlength="11" value="<?php echo set_value('idequipo', isset($administracion_tabla_posiciones['idequipo']) ? $administracion_tabla_posiciones['idequipo'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('idequipo'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('puntos') ? 'error' : ''; ?>">
            <?php echo form_label('Puntos', 'puntos', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="puntos" type="text" name="puntos" maxlength="11" value="<?php echo set_value('puntos', isset($administracion_tabla_posiciones['puntos']) ? $administracion_tabla_posiciones['puntos'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('puntos'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('partidos_jugados') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Jugados', 'partidos_jugados', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="partidos_jugados" type="text" name="partidos_jugados" maxlength="11" value="<?php echo set_value('partidos_jugados', isset($administracion_tabla_posiciones['partidos_jugados']) ? $administracion_tabla_posiciones['partidos_jugados'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('partidos_jugados'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('partidos_ganados') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Ganados', 'partidos_ganados', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="partidos_ganados" type="text" name="partidos_ganados" maxlength="11" value="<?php echo set_value('partidos_ganados', isset($administracion_tabla_posiciones['partidos_ganados']) ? $administracion_tabla_posiciones['partidos_ganados'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('partidos_ganados'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('partidos_empatados') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Empatados', 'partidos_empatados', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="partidos_empatados" type="text" name="partidos_empatados" maxlength="11" value="<?php echo set_value('partidos_empatados', isset($administracion_tabla_posiciones['partidos_empatados']) ? $administracion_tabla_posiciones['partidos_empatados'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('partidos_empatados'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('partidos_perdidos') ? 'error' : ''; ?>">
            <?php echo form_label('Partidos Perdidos', 'partidos_perdidos', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="partidos_perdidos" type="text" name="partidos_perdidos" maxlength="11" value="<?php echo set_value('partidos_perdidos', isset($administracion_tabla_posiciones['partidos_perdidos']) ? $administracion_tabla_posiciones['partidos_perdidos'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('partidos_perdidos'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('goles_a_favor') ? 'error' : ''; ?>">
            <?php echo form_label('Goles A Favor', 'goles_a_favor', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="goles_a_favor" type="text" name="goles_a_favor" maxlength="11" value="<?php echo set_value('goles_a_favor', isset($administracion_tabla_posiciones['goles_a_favor']) ? $administracion_tabla_posiciones['goles_a_favor'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('goles_a_favor'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('goles_en_contra') ? 'error' : ''; ?>">
            <?php echo form_label('Goles En Contra', 'goles_en_contra', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="goles_en_contra" type="text" name="goles_en_contra" maxlength="11" value="<?php echo set_value('goles_en_contra', isset($administracion_tabla_posiciones['goles_en_contra']) ? $administracion_tabla_posiciones['goles_en_contra'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('goles_en_contra'); ?></span>
            </div>

        </div>


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Create Administracion Tabla Posiciones" />
            or <?php echo anchor(SITE_AREA . '/content/administracion_tabla_posiciones', lang('administracion_tabla_posiciones_cancel'), 'class="btn btn-warning"'); ?>

        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
