
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($banner_page) ) {
    $banner_page = (array)$banner_page;
}
$id = isset($banner_page['idbanner_page']) ? $banner_page['idbanner_page'] : '';
?>
<div class="admin-box">
    <h3>Banner Page</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('idbanner') ? 'error' : ''; ?>">
            <?php echo form_label('Id banner'. lang('bf_form_label_required'), 'idbanner', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idbanner" type="text" name="idbanner" maxlength="11" value="<?php echo set_value('idbanner', isset($banner_page['idbanner']) ? $banner_page['idbanner'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idbanner'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('idpage') ? 'error' : ''; ?>">
            <?php echo form_label('Id page'. lang('bf_form_label_required'), 'idpage', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idpage" type="text" name="idpage" maxlength="11" value="<?php echo set_value('idpage', isset($banner_page['idpage']) ? $banner_page['idpage'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idpage'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('idelement') ? 'error' : ''; ?>">
            <?php echo form_label('Id element', 'idelement', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idelement" type="text" name="idelement" maxlength="11" value="<?php echo set_value('idelement', isset($banner_page['idelement']) ? $banner_page['idelement'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idelement'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Banner Page" />
            or <?php echo anchor(SITE_AREA .'/content/banner_page', lang('banner_page_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Banner_Page.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/banner_page/delete/'. $id);?>" onclick="return confirm('<?php echo lang('banner_page_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('banner_page_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
