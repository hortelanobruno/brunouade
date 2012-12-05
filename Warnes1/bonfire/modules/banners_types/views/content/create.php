
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($banners_types) ) {
    $banners_types = (array)$banners_types;
}
$id = isset($banners_types['idbannertype']) ? $banners_types['idbannertype'] : '';
?>
<div class="admin-box">
    <h3>Banners Types</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('description') ? 'error' : ''; ?>">
            <?php echo form_label('Description'. lang('bf_form_label_required'), 'description', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="description" type="text" name="description" maxlength="45" value="<?php echo set_value('description', isset($banners_types['description']) ? $banners_types['description'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('description'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Create Banners Types" />
            or <?php echo anchor(SITE_AREA .'/content/banners_types', lang('banners_types_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
