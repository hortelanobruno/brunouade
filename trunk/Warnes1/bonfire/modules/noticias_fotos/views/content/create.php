
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($noticias_fotos) ) {
    $noticias_fotos = (array)$noticias_fotos;
}
$id = isset($noticias_fotos['idnoticias_fotos']) ? $noticias_fotos['idnoticias_fotos'] : '';
?>
<div class="admin-box">
    <h3>Noticias Fotos</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('idnoticias') ? 'error' : ''; ?>">
            <?php echo form_label('Idnoticias'. lang('bf_form_label_required'), 'idnoticias', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idnoticias" type="text" name="idnoticias" maxlength="11" value="<?php echo set_value('idnoticias', isset($noticias_fotos['idnoticias']) ? $noticias_fotos['idnoticias'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idnoticias'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('idfoto') ? 'error' : ''; ?>">
            <?php echo form_label('Idfoto'. lang('bf_form_label_required'), 'idfoto', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idfoto" type="text" name="idfoto" maxlength="11" value="<?php echo set_value('idfoto', isset($noticias_fotos['idfoto']) ? $noticias_fotos['idfoto'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idfoto'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Create Noticias Fotos" />
            or <?php echo anchor(SITE_AREA .'/content/noticias_fotos', lang('noticias_fotos_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
