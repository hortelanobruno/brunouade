
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($noticias) ) {
    $noticias = (array)$noticias;
}
$id = isset($noticias['idnoticias']) ? $noticias['idnoticias'] : '';
?>
<div class="admin-box">
    <h3>Noticias</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('noticias_idtorneo') ? 'error' : ''; ?>">
            <?php echo form_label('Idtorneo', 'noticias_idtorneo', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="noticias_idtorneo" type="text" name="noticias_idtorneo" maxlength="11" value="<?php echo set_value('noticias_idtorneo', isset($noticias['idtorneo']) ? $noticias['idtorneo'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('noticias_idtorneo'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('noticias_titulo') ? 'error' : ''; ?>">
            <?php echo form_label('Titulo', 'noticias_titulo', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="noticias_titulo" type="text" name="noticias_titulo" maxlength="200" value="<?php echo set_value('noticias_titulo', isset($noticias['titulo']) ? $noticias['titulo'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('noticias_titulo'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('noticias_contenido') ? 'error' : ''; ?>">
            <?php echo form_label('Contenido', 'noticias_contenido', array('class' => "control-label") ); ?>
            <div class="controls">
                <?php echo form_textarea( array( 'name' => 'noticias_contenido', 'id' => 'noticias_contenido', 'rows' => '5', 'cols' => '80', 'value' => set_value('noticias_contenido', isset($noticias['contenido']) ? $noticias['contenido'] : '') ) )?>
                <span class="help-inline"><?php echo form_error('noticias_contenido'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('noticias_fecha') ? 'error' : ''; ?>">
            <?php echo form_label('Fecha', 'noticias_fecha', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="noticias_fecha" type="text" name="noticias_fecha" maxlength="200" value="<?php echo set_value('noticias_fecha', isset($noticias['fecha']) ? $noticias['fecha'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('noticias_fecha'); ?></span>
            </div>

        </div>


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Edit Noticias" />
            or <?php echo anchor(SITE_AREA .'/content/noticias', lang('noticias_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Noticias.Content.Delete')) : ?>

            or <button type="submit" name="delete" class="btn btn-danger" id="delete-me" onclick="return confirm('<?php e(js_escape(lang('noticias_delete_confirm'))); ?>')">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('noticias_delete_record'); ?>
            </button>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
