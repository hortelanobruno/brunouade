
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($clientes_zonas) ) {
    $clientes_zonas = (array)$clientes_zonas;
}
$id = isset($clientes_zonas['idcliente_zona']) ? $clientes_zonas['idcliente_zona'] : '';
?>
<div class="admin-box">
    <h3>Clientes Zonas</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('idcliente') ? 'error' : ''; ?>">
            <?php echo form_label('Idcliente'. lang('bf_form_label_required'), 'idcliente', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idcliente" type="text" name="idcliente" maxlength="11" value="<?php echo set_value('idcliente', isset($clientes_zonas['idcliente']) ? $clientes_zonas['idcliente'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idcliente'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('idzona') ? 'error' : ''; ?>">
            <?php echo form_label('Idzona'. lang('bf_form_label_required'), 'idzona', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idzona" type="text" name="idzona" maxlength="11" value="<?php echo set_value('idzona', isset($clientes_zonas['idzona']) ? $clientes_zonas['idzona'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idzona'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Create Clientes Zonas" />
            or <?php echo anchor(SITE_AREA .'/content/clientes_zonas', lang('clientes_zonas_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
