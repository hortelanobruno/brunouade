
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($equipos) ) {
    $equipos = (array)$equipos;
}
$id = isset($equipos['id']) ? $equipos['id'] : '';
?>
<div class="admin-box">
    <h3>Equipos</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('equipos_nombre') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre', 'equipos_nombre', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_nombre" type="text" name="equipos_nombre" maxlength="200" value="<?php echo set_value('equipos_nombre', isset($equipos['nombre']) ? $equipos['nombre'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_nombre'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_logo_grande') ? 'error' : ''; ?>">
            <?php echo form_label('Logo Grande', 'equipos_logo_grande', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_logo_grande" type="text" name="equipos_logo_grande" maxlength="200" value="<?php echo set_value('equipos_logo_grande', isset($equipos['logo_grande']) ? $equipos['logo_grande'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_logo_grande'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_logo_chico') ? 'error' : ''; ?>">
            <?php echo form_label('Logo Chico', 'equipos_logo_chico', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_logo_chico" type="text" name="equipos_logo_chico" maxlength="200" value="<?php echo set_value('equipos_logo_chico', isset($equipos['logo_chico']) ? $equipos['logo_chico'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_logo_chico'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_pagina_web') ? 'error' : ''; ?>">
            <?php echo form_label('Pagina Web', 'equipos_pagina_web', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_pagina_web" type="text" name="equipos_pagina_web" maxlength="200" value="<?php echo set_value('equipos_pagina_web', isset($equipos['pagina_web']) ? $equipos['pagina_web'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_pagina_web'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_ciudad') ? 'error' : ''; ?>">
            <?php echo form_label('Ciudad', 'equipos_ciudad', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_ciudad" type="text" name="equipos_ciudad" maxlength="200" value="<?php echo set_value('equipos_ciudad', isset($equipos['ciudad']) ? $equipos['ciudad'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_ciudad'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_foto') ? 'error' : ''; ?>">
            <?php echo form_label('Foto', 'equipos_foto', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_foto" type="text" name="equipos_foto" maxlength="200" value="<?php echo set_value('equipos_foto', isset($equipos['foto']) ? $equipos['foto'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_foto'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_cantidad_partidos_perdidos') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Partidos Perdidos', 'equipos_cantidad_partidos_perdidos', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_cantidad_partidos_perdidos" type="text" name="equipos_cantidad_partidos_perdidos" maxlength="11" value="<?php echo set_value('equipos_cantidad_partidos_perdidos', isset($equipos['cantidad_partidos_perdidos']) ? $equipos['cantidad_partidos_perdidos'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_cantidad_partidos_perdidos'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_cantidad_tarjetas_rojas') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Tarjetas Rojas', 'equipos_cantidad_tarjetas_rojas', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_cantidad_tarjetas_rojas" type="text" name="equipos_cantidad_tarjetas_rojas" maxlength="11" value="<?php echo set_value('equipos_cantidad_tarjetas_rojas', isset($equipos['cantidad_tarjetas_rojas']) ? $equipos['cantidad_tarjetas_rojas'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_cantidad_tarjetas_rojas'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_cantidad_tarjetas_amarillas') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Tarjetas Amarillas', 'equipos_cantidad_tarjetas_amarillas', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_cantidad_tarjetas_amarillas" type="text" name="equipos_cantidad_tarjetas_amarillas" maxlength="11" value="<?php echo set_value('equipos_cantidad_tarjetas_amarillas', isset($equipos['cantidad_tarjetas_amarillas']) ? $equipos['cantidad_tarjetas_amarillas'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_cantidad_tarjetas_amarillas'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_cantidad_goles_en_contra') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Goles En Contra', 'equipos_cantidad_goles_en_contra', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_cantidad_goles_en_contra" type="text" name="equipos_cantidad_goles_en_contra" maxlength="11" value="<?php echo set_value('equipos_cantidad_goles_en_contra', isset($equipos['cantidad_goles_en_contra']) ? $equipos['cantidad_goles_en_contra'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_cantidad_goles_en_contra'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_cantidad_goles_a_favor') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Goles A Favor', 'equipos_cantidad_goles_a_favor', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_cantidad_goles_a_favor" type="text" name="equipos_cantidad_goles_a_favor" maxlength="11" value="<?php echo set_value('equipos_cantidad_goles_a_favor', isset($equipos['cantidad_goles_a_favor']) ? $equipos['cantidad_goles_a_favor'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_cantidad_goles_a_favor'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_cantidad_partidos_empatados') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Partidos Empatados', 'equipos_cantidad_partidos_empatados', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_cantidad_partidos_empatados" type="text" name="equipos_cantidad_partidos_empatados" maxlength="11" value="<?php echo set_value('equipos_cantidad_partidos_empatados', isset($equipos['cantidad_partidos_empatados']) ? $equipos['cantidad_partidos_empatados'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_cantidad_partidos_empatados'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('equipos_cantidad_partidos_ganados') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Partidos Ganados', 'equipos_cantidad_partidos_ganados', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="equipos_cantidad_partidos_ganados" type="text" name="equipos_cantidad_partidos_ganados" maxlength="11" value="<?php echo set_value('equipos_cantidad_partidos_ganados', isset($equipos['cantidad_partidos_ganados']) ? $equipos['cantidad_partidos_ganados'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('equipos_cantidad_partidos_ganados'); ?></span>
            </div>

        </div>


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Edit Equipos" />
            or <?php echo anchor(SITE_AREA .'/content/equipos', lang('equipos_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Equipos.Content.Delete')) : ?>

            or <button type="submit" name="delete" class="btn btn-danger" id="delete-me" onclick="return confirm('<?php e(js_escape(lang('equipos_delete_confirm'))); ?>')">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('equipos_delete_record'); ?>
            </button>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
