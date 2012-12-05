
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($clientes_marcas) ) {
    $clientes_marcas = (array)$clientes_marcas;
}
$id = isset($clientes_marcas['idcliente_marca']) ? $clientes_marcas['idcliente_marca'] : '';
?>
<div class="admin-box">
    <h3>Clientes Marcas</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('idcliente') ? 'error' : ''; ?>">
            <?php echo form_label('Idcliente'. lang('bf_form_label_required'), 'idcliente', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idcliente" type="text" name="idcliente" maxlength="11" value="<?php echo set_value('idcliente', isset($clientes_marcas['idcliente']) ? $clientes_marcas['idcliente'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idcliente'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('idmarca') ? 'error' : ''; ?>">
            <?php echo form_label('Idmarca'. lang('bf_form_label_required'), 'idmarca', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idmarca" type="text" name="idmarca" maxlength="11" value="<?php echo set_value('idmarca', isset($clientes_marcas['idmarca']) ? $clientes_marcas['idmarca'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idmarca'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Clientes Marcas" />
            or <?php echo anchor(SITE_AREA .'/content/clientes_marcas', lang('clientes_marcas_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Clientes_Marcas.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/clientes_marcas/delete/'. $id);?>" onclick="return confirm('<?php echo lang('clientes_marcas_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('clientes_marcas_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
