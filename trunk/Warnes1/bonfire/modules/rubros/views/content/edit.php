
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($rubros) ) {
    $rubros = (array)$rubros;
}
$id = isset($rubros['idrubro']) ? $rubros['idrubro'] : '';
?>
<div class="admin-box">
    <h3>Rubros</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('descripcion') ? 'error' : ''; ?>">
            <?php echo form_label('Descripcion'. lang('bf_form_label_required'), 'descripcion', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="descripcion" type="text" name="descripcion" maxlength="45" value="<?php echo set_value('descripcion', isset($rubros['descripcion']) ? $rubros['descripcion'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('descripcion'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('habilitado') ? 'error' : ''; ?>">
            <?php echo form_label('Habilitado', 'habilitado', array('class' => "control-label") ); ?>
            <div class='controls'>
            <label class="checkbox" for="habilitado">
            <input type="checkbox" id="habilitado" name="habilitado" value="1" <?php echo (isset($rubros['habilitado']) && $rubros['habilitado'] == 1) ? 'checked="checked"' : set_checkbox('habilitado', 1); ?>>
            <span class="help-inline"><?php echo form_error('habilitado'); ?></span>
            </label>

        </div>

        </div>
        <div class="control-group <?php echo form_error('prioridad') ? 'error' : ''; ?>">
            <?php echo form_label('Prioridad'. lang('bf_form_label_required'), 'prioridad', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="prioridad" type="text" name="prioridad" maxlength="11" value="<?php echo set_value('prioridad', isset($rubros['prioridad']) ? $rubros['prioridad'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('prioridad'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Rubros" />
            or <?php echo anchor(SITE_AREA .'/content/rubros', lang('rubros_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Rubros.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/rubros/delete/'. $id);?>" onclick="return confirm('<?php echo lang('rubros_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('rubros_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
