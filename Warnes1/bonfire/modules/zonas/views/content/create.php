
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($zonas) ) {
    $zonas = (array)$zonas;
}
$id = isset($zonas['idzona']) ? $zonas['idzona'] : '';
?>
<div class="admin-box">
    <h3>Zonas</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('descripcion') ? 'error' : ''; ?>">
            <?php echo form_label('Descripcion'. lang('bf_form_label_required'), 'descripcion', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="descripcion" type="text" name="descripcion" maxlength="45" value="<?php echo set_value('descripcion', isset($zonas['descripcion']) ? $zonas['descripcion'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('descripcion'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('habilitado') ? 'error' : ''; ?>">
            <?php echo form_label('Habilitado', 'habilitado', array('class' => "control-label") ); ?>
            <div class='controls'>
            <label class="checkbox" for="habilitado">
            <input type="checkbox" id="habilitado" name="habilitado" value="1" <?php echo (isset($zonas['habilitado']) && $zonas['habilitado'] == 1) ? 'checked="checked"' : set_checkbox('habilitado', 1); ?>>
            <span class="help-inline"><?php echo form_error('habilitado'); ?></span>
            </label>

        </div>

        </div>
        <div class="control-group <?php echo form_error('prioridad') ? 'error' : ''; ?>">
            <?php echo form_label('Prioridad'. lang('bf_form_label_required'), 'prioridad', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="prioridad" type="text" name="prioridad" maxlength="11" value="<?php echo set_value('prioridad', isset($zonas['prioridad']) ? $zonas['prioridad'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('prioridad'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Create Zonas" />
            or <?php echo anchor(SITE_AREA .'/content/zonas', lang('zonas_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
