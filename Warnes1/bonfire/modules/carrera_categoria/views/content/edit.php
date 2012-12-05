
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($carrera_categoria) ) {
    $carrera_categoria = (array)$carrera_categoria;
}
$id = isset($carrera_categoria['idcarrera_categoria']) ? $carrera_categoria['idcarrera_categoria'] : '';
?>
<div class="admin-box">
    <h3>Carrera Categoria</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('nombre') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre'. lang('bf_form_label_required'), 'nombre', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="nombre" type="text" name="nombre" maxlength="200" value="<?php echo set_value('nombre', isset($carrera_categoria['nombre']) ? $carrera_categoria['nombre'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('nombre'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Carrera Categoria" />
            or <?php echo anchor(SITE_AREA .'/content/carrera_categoria', lang('carrera_categoria_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Carrera_Categoria.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/carrera_categoria/delete/'. $id);?>" onclick="return confirm('<?php echo lang('carrera_categoria_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('carrera_categoria_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
