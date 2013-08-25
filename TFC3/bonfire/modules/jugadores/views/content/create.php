
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($jugadores) ) {
    $jugadores = (array)$jugadores;
}
$id = isset($jugadores['id']) ? $jugadores['id'] : '';
?>
<div class="admin-box">
    <h3>Jugadores</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('jugadores_nombre_completo') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre Completo', 'jugadores_nombre_completo', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="jugadores_nombre_completo" type="text" name="jugadores_nombre_completo" maxlength="200" value="<?php echo set_value('jugadores_nombre_completo', isset($jugadores['nombre_completo']) ? $jugadores['nombre_completo'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('jugadores_nombre_completo'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_nombre') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre', 'jugadores_nombre', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="jugadores_nombre" type="text" name="jugadores_nombre" maxlength="200" value="<?php echo set_value('jugadores_nombre', isset($jugadores['nombre']) ? $jugadores['nombre'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('jugadores_nombre'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_apellido') ? 'error' : ''; ?>">
            <?php echo form_label('Apellido', 'jugadores_apellido', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="jugadores_apellido" type="text" name="jugadores_apellido" maxlength="200" value="<?php echo set_value('jugadores_apellido', isset($jugadores['apellido']) ? $jugadores['apellido'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('jugadores_apellido'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_delegado') ? 'error' : ''; ?>">
            <?php echo form_label('Delegado', 'jugadores_delegado', array('class' => "control-label") ); ?>
            <div class="controls">

                <label class="checkbox" for="delegado">
                    <input type="checkbox" id="jugadores_delegado" name="jugadores_delegado" value="1" <?php echo (isset($jugadores['delegado']) && $jugadores['delegado'] == 1) ? 'checked="checked"' : set_checkbox('delegado', 1); ?>>
                    <span class="help-inline"><?php echo form_error('jugadores_delegado'); ?></span>
                </label>

            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_idequipo') ? 'error' : ''; ?>">
            <?php echo form_label('Equipo', 'jugadores_idequipo', array('class' => "control-label") ); ?>
            <div class="controls">

              <!-- <input id="jugadores_idequipo" type="text" name="jugadores_idequipo" maxlength="11" value="<!?php echo set_value('jugadores_idequipo', isset($jugadores['idequipo']) ? $jugadores['idequipo'] : ''); ?>"  /> -->
                
                <?php if (isset($equipos)) { ?>
                <select id="jugadores_idequipo" name="jugadores_idequipo">
                    <?php foreach ($equipos as $equipo): ?>
                        <option value="<?php echo $equipo['id'] ?>"><?php echo $equipo['nombre'] ?></option>
                    <?php endforeach ?>
                </select>
                <?php } ?>
                
               <span class="help-inline"><?php echo form_error('jugadores_idequipo'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_cantidad_tarjetas_amarillas') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Tarjetas Amarillas', 'jugadores_cantidad_tarjetas_amarillas', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="jugadores_cantidad_tarjetas_amarillas" type="text" name="jugadores_cantidad_tarjetas_amarillas" maxlength="11" value="<?php echo set_value('jugadores_cantidad_tarjetas_amarillas', isset($jugadores['cantidad_tarjetas_amarillas']) ? $jugadores['cantidad_tarjetas_amarillas'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('jugadores_cantidad_tarjetas_amarillas'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_cantidad_partidos_jugados') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Partidos Jugados', 'jugadores_cantidad_partidos_jugados', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="jugadores_cantidad_partidos_jugados" type="text" name="jugadores_cantidad_partidos_jugados" maxlength="11" value="<?php echo set_value('jugadores_cantidad_partidos_jugados', isset($jugadores['cantidad_partidos_jugados']) ? $jugadores['cantidad_partidos_jugados'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('jugadores_cantidad_partidos_jugados'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_cantidad_tarjetas_rojas') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Tarjetas Rojas', 'jugadores_cantidad_tarjetas_rojas', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="jugadores_cantidad_tarjetas_rojas" type="text" name="jugadores_cantidad_tarjetas_rojas" maxlength="11" value="<?php echo set_value('jugadores_cantidad_tarjetas_rojas', isset($jugadores['cantidad_tarjetas_rojas']) ? $jugadores['cantidad_tarjetas_rojas'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('jugadores_cantidad_tarjetas_rojas'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('jugadores_cantidad_goles') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Goles', 'jugadores_cantidad_goles', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="jugadores_cantidad_goles" type="text" name="jugadores_cantidad_goles" maxlength="11" value="<?php echo set_value('jugadores_cantidad_goles', isset($jugadores['cantidad_goles']) ? $jugadores['cantidad_goles'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('jugadores_cantidad_goles'); ?></span>
            </div>

        </div>


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Create Jugadores" />
            or <?php echo anchor(SITE_AREA .'/content/jugadores', lang('jugadores_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
