
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($fotos) ) {
    $fotos = (array)$fotos;
}
$id = isset($fotos['idfoto']) ? $fotos['idfoto'] : '';
?>
<div class="admin-box">
    <h3>Fotos</h3>
<?php echo form_open_multipart($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('nombre') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre'. lang('bf_form_label_required'), 'nombre', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="nombre" type="text" name="nombre" maxlength="500" value="<?php echo set_value('nombre', isset($fotos['nombre']) ? $fotos['nombre'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('nombre'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('titulo') ? 'error' : ''; ?>">
            <?php echo form_label('Titulo', 'titulo', array('class' => "control-label") ); ?>
            <div class='controls'>
            <?php echo form_textarea( array( 'name' => 'titulo', 'id' => 'titulo', 'rows' => '5', 'cols' => '80', 'value' => set_value('titulo', isset($fotos['titulo']) ? $fotos['titulo'] : '') ) )?>
            <span class="help-inline"><?php echo form_error('titulo'); ?></span>
        </div>

        </div>
<!--        <div class="control-group <?php echo form_error('url') ? 'error' : ''; ?>">
            <?php echo form_label('Url'. lang('bf_form_label_required'), 'url', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="url" type="text" name="url" maxlength="100" value="<?php echo set_value('url', isset($fotos['url']) ? $fotos['url'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('url'); ?></span>
        </div>


        </div>-->
        <div class="control-group <?php echo form_error('url') ? 'error' : ''; ?>">
            <?php echo form_label('Url'. lang('bf_form_label_required'), 'url', array('class' => "control-label") ); ?>
            <div class='controls'>
                <input id="userfile" type="file" name="userfile" size="20" />
        <span class="help-inline"><?php echo form_error('url'); ?></span>
        </div>


        </div>
        
        
        <div class="control-group <?php echo form_error('habilitado') ? 'error' : ''; ?>">
            <?php echo form_label('Habilitado', 'habilitado', array('class' => "control-label") ); ?>
            <div class='controls'>
            <label class="checkbox" for="habilitado">
            <input type="checkbox" id="habilitado" name="habilitado" value="1" <?php echo (isset($fotos['habilitado']) && $fotos['habilitado'] == 1) ? 'checked="checked"' : set_checkbox('habilitado', 1); ?>>
            <span class="help-inline"><?php echo form_error('habilitado'); ?></span>
            </label>

        </div>

        </div>
        <div class="control-group <?php echo form_error('prioridad') ? 'error' : ''; ?>">
            <?php echo form_label('Prioridad'. lang('bf_form_label_required'), 'prioridad', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="prioridad" type="text" name="prioridad" maxlength="11" value="<?php echo set_value('prioridad', isset($fotos['prioridad']) ? $fotos['prioridad'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('prioridad'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Create Fotos" />
            or <?php echo anchor(SITE_AREA .'/content/fotos', lang('fotos_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
