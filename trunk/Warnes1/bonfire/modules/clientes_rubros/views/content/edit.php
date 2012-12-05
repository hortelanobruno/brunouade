
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($clientes_rubros) ) {
    $clientes_rubros = (array)$clientes_rubros;
}
$id = isset($clientes_rubros['idcliente_rubro']) ? $clientes_rubros['idcliente_rubro'] : '';
?>
<div class="admin-box">
    <h3>Clientes Rubros</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('idcliente') ? 'error' : ''; ?>">
            <?php echo form_label('Idcliente'. lang('bf_form_label_required'), 'idcliente', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idcliente" type="text" name="idcliente" maxlength="11" value="<?php echo set_value('idcliente', isset($clientes_rubros['idcliente']) ? $clientes_rubros['idcliente'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idcliente'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('idrubro') ? 'error' : ''; ?>">
            <?php echo form_label('Idrubro'. lang('bf_form_label_required'), 'idrubro', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idrubro" type="text" name="idrubro" maxlength="11" value="<?php echo set_value('idrubro', isset($clientes_rubros['idrubro']) ? $clientes_rubros['idrubro'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idrubro'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Clientes Rubros" />
            or <?php echo anchor(SITE_AREA .'/content/clientes_rubros', lang('clientes_rubros_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Clientes_Rubros.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/clientes_rubros/delete/'. $id);?>" onclick="return confirm('<?php echo lang('clientes_rubros_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('clientes_rubros_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
