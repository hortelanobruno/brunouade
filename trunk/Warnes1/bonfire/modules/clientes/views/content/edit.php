
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($clientes) ) {
    $clientes = (array)$clientes;
}
$id = isset($clientes['idcliente']) ? $clientes['idcliente'] : '';
?>
<div class="admin-box">
    <h3>Clientes</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('nombre') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre'. lang('bf_form_label_required'), 'nombre', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="nombre" type="text" name="nombre" maxlength="45" value="<?php echo set_value('nombre', isset($clientes['nombre']) ? $clientes['nombre'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('nombre'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('contacto') ? 'error' : ''; ?>">
            <?php echo form_label('Contacto', 'contacto', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="contacto" type="text" name="contacto" maxlength="45" value="<?php echo set_value('contacto', isset($clientes['contacto']) ? $clientes['contacto'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('contacto'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('telefonodeltrabajo') ? 'error' : ''; ?>">
            <?php echo form_label('Telefono del trabajo', 'telefonodeltrabajo', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="telefonodeltrabajo" type="text" name="telefonodeltrabajo" maxlength="45" value="<?php echo set_value('telefonodeltrabajo', isset($clientes['telefonodeltrabajo']) ? $clientes['telefonodeltrabajo'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('telefonodeltrabajo'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('telefonocelular') ? 'error' : ''; ?>">
            <?php echo form_label('Telefono celular', 'telefonocelular', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="telefonocelular" type="text" name="telefonocelular" maxlength="45" value="<?php echo set_value('telefonocelular', isset($clientes['telefonocelular']) ? $clientes['telefonocelular'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('telefonocelular'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('telefonoparticular') ? 'error' : ''; ?>">
            <?php echo form_label('Telefono particular', 'telefonoparticular', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="telefonoparticular" type="text" name="telefonoparticular" maxlength="45" value="<?php echo set_value('telefonoparticular', isset($clientes['telefonoparticular']) ? $clientes['telefonoparticular'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('telefonoparticular'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('numerodefax') ? 'error' : ''; ?>">
            <?php echo form_label('Numero de fax', 'numerodefax', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="numerodefax" type="text" name="numerodefax" maxlength="45" value="<?php echo set_value('numerodefax', isset($clientes['numerodefax']) ? $clientes['numerodefax'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('numerodefax'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('direccion') ? 'error' : ''; ?>">
            <?php echo form_label('Direccion', 'direccion', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="direccion" type="text" name="direccion" maxlength="45" value="<?php echo set_value('direccion', isset($clientes['direccion']) ? $clientes['direccion'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('direccion'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('ciudad') ? 'error' : ''; ?>">
            <?php echo form_label('Ciudad', 'ciudad', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="ciudad" type="text" name="ciudad" maxlength="45" value="<?php echo set_value('ciudad', isset($clientes['ciudad']) ? $clientes['ciudad'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('ciudad'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('provincia') ? 'error' : ''; ?>">
            <?php echo form_label('Provincia', 'provincia', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="provincia" type="text" name="provincia" maxlength="45" value="<?php echo set_value('provincia', isset($clientes['provincia']) ? $clientes['provincia'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('provincia'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('codigopostal') ? 'error' : ''; ?>">
            <?php echo form_label('Codigo postal', 'codigopostal', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="codigopostal" type="text" name="codigopostal" maxlength="45" value="<?php echo set_value('codigopostal', isset($clientes['codigopostal']) ? $clientes['codigopostal'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('codigopostal'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('paisregion') ? 'error' : ''; ?>">
            <?php echo form_label('Pais o region', 'paisregion', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="paisregion" type="text" name="paisregion" maxlength="45" value="<?php echo set_value('paisregion', isset($clientes['paisregion']) ? $clientes['paisregion'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('paisregion'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('mail') ? 'error' : ''; ?>">
            <?php echo form_label('Mail', 'mail', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="mail" type="text" name="mail" maxlength="45" value="<?php echo set_value('mail', isset($clientes['mail']) ? $clientes['mail'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('mail'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('web') ? 'error' : ''; ?>">
            <?php echo form_label('Web', 'web', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="web" type="text" name="web" maxlength="45" value="<?php echo set_value('web', isset($clientes['web']) ? $clientes['web'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('web'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('observaciones') ? 'error' : ''; ?>">
            <?php echo form_label('Observaciones', 'observaciones', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="observaciones" type="text" name="observaciones" maxlength="45" value="<?php echo set_value('observaciones', isset($clientes['observaciones']) ? $clientes['observaciones'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('observaciones'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('habilitado') ? 'error' : ''; ?>">
            <?php echo form_label('Habilitado', 'habilitado', array('class' => "control-label") ); ?>
            <div class='controls'>
            <label class="checkbox" for="habilitado">
            <input type="checkbox" id="habilitado" name="habilitado" value="1" <?php echo (isset($clientes['habilitado']) && $clientes['habilitado'] == 1) ? 'checked="checked"' : set_checkbox('habilitado', 1); ?>>
            <span class="help-inline"><?php echo form_error('habilitado'); ?></span>
            </label>

        </div>

        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Clientes" />
            or <?php echo anchor(SITE_AREA .'/content/clientes', lang('clientes_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Clientes.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/clientes/delete/'. $id);?>" onclick="return confirm('<?php echo lang('clientes_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('clientes_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
