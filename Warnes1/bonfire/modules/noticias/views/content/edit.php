
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
$id = isset($noticias['idnoticia']) ? $noticias['idnoticia'] : '';
?>
<div class="admin-box">
    <h3>Noticias</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('titulo') ? 'error' : ''; ?>">
            <?php echo form_label('Titulo'. lang('bf_form_label_required'), 'titulo', array('class' => "control-label") ); ?>
            <div class='controls'>
            <?php echo form_textarea( array( 'name' => 'titulo', 'id' => 'titulo', 'rows' => '5', 'cols' => '80', 'value' => set_value('titulo', isset($noticias['titulo']) ? $noticias['titulo'] : '') ) )?>
            <span class="help-inline"><?php echo form_error('titulo'); ?></span>
        </div>

        </div>
        <div class="control-group <?php echo form_error('epigrafe') ? 'error' : ''; ?>">
            <?php echo form_label('Epigrafe', 'epigrafe', array('class' => "control-label") ); ?>
            <div class='controls'>
            <?php echo form_textarea( array( 'name' => 'epigrafe', 'id' => 'epigrafe', 'rows' => '5', 'cols' => '80', 'value' => set_value('epigrafe', isset($noticias['epigrafe']) ? $noticias['epigrafe'] : '') ) )?>
            <span class="help-inline"><?php echo form_error('epigrafe'); ?></span>
        </div>

        </div>
        <div class="control-group <?php echo form_error('descripcion') ? 'error' : ''; ?>">
            <?php echo form_label('Descripcion'. lang('bf_form_label_required'), 'descripcion', array('class' => "control-label") ); ?>
            <div class='controls'>
            <?php echo form_textarea( array( 'name' => 'descripcion', 'id' => 'descripcion', 'rows' => '5', 'cols' => '80', 'value' => set_value('descripcion', isset($noticias['descripcion']) ? $noticias['descripcion'] : '') ) )?>
            <span class="help-inline"><?php echo form_error('descripcion'); ?></span>
        </div>

        </div>
        <div class="control-group <?php echo form_error('fechanoticia') ? 'error' : ''; ?>">
            <?php echo form_label('Fechanoticia'. lang('bf_form_label_required'), 'fechanoticia', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="fechanoticia" type="text" name="fechanoticia"  value="<?php echo set_value('fechanoticia', isset($noticias['fechanoticia']) ? $noticias['fechanoticia'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('fechanoticia'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('habilitado') ? 'error' : ''; ?>">
            <?php echo form_label('Habilitado', 'habilitado', array('class' => "control-label") ); ?>
            <div class='controls'>
            <label class="checkbox" for="habilitado">
            <input type="checkbox" id="habilitado" name="habilitado" value="1" <?php echo (isset($noticias['habilitado']) && $noticias['habilitado'] == 1) ? 'checked="checked"' : set_checkbox('habilitado', 1); ?>>
            <span class="help-inline"><?php echo form_error('habilitado'); ?></span>
            </label>

        </div>

        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Noticias" />
            or <?php echo anchor(SITE_AREA .'/content/noticias', lang('noticias_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Noticias.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/noticias/delete/'. $id);?>" onclick="return confirm('<?php echo lang('noticias_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('noticias_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
