
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
        <div class="control-group <?php echo form_error('idtorneo') ? 'error' : ''; ?>">
            <?php echo form_label('Idtorneo', 'idtorneo', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="idtorneo" type="text" name="idtorneo" maxlength="11" value="<?php echo set_value('idtorneo', isset($noticias['idtorneo']) ? $noticias['idtorneo'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('idtorneo'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('titulo') ? 'error' : ''; ?>">
            <?php echo form_label('Titulo', 'titulo', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="titulo" type="text" name="titulo" maxlength="200" value="<?php echo set_value('titulo', isset($noticias['titulo']) ? $noticias['titulo'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('titulo'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('contenido') ? 'error' : ''; ?>">
            <?php echo form_label('Contenido', 'contenido', array('class' => "control-label") ); ?>
            <div class="controls">
                <?php echo form_textarea( array( 'name' => 'contenido', 'id' => 'contenido', 'rows' => '5', 'cols' => '80', 'value' => set_value('contenido', isset($noticias['contenido']) ? $noticias['contenido'] : '') ) )?>
                <span class="help-inline"><?php echo form_error('contenido'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('fecha') ? 'error' : ''; ?>">
            <?php echo form_label('Fecha', 'fecha', array('class' => "control-label") ); ?>
            <div class="controls">

               <input id="fecha" type="text" name="fecha" maxlength="200" value="<?php echo set_value('fecha', isset($noticias['fecha']) ? $noticias['fecha'] : ''); ?>"  />
               <span class="help-inline"><?php echo form_error('fecha'); ?></span>
            </div>

        </div>


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Create Noticias" />
            or <?php echo anchor(SITE_AREA .'/content/noticias', lang('noticias_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
