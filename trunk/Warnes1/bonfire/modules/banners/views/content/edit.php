
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($banners) ) {
    $banners = (array)$banners;
}
$id = isset($banners['idbanner']) ? $banners['idbanner'] : '';
?>
<div class="admin-box">
    <h3>Banners</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('nombre') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre'. lang('bf_form_label_required'), 'nombre', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="nombre" type="text" name="nombre" maxlength="45" value="<?php echo set_value('nombre', isset($banners['nombre']) ? $banners['nombre'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('nombre'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('url') ? 'error' : ''; ?>">
            <?php echo form_label('Url'. lang('bf_form_label_required'), 'url', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="url" type="text" name="url" maxlength="45" value="<?php echo set_value('url', isset($banners['url']) ? $banners['url'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('url'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('fechainicio') ? 'error' : ''; ?>">
            <?php echo form_label('Fechainicio'. lang('bf_form_label_required'), 'fechainicio', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="fechainicio" type="text" name="fechainicio" maxlength="45" value="<?php echo set_value('fechainicio', isset($banners['fechainicio']) ? $banners['fechainicio'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('fechainicio'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('fechafin') ? 'error' : ''; ?>">
            <?php echo form_label('Fechafin'. lang('bf_form_label_required'), 'fechafin', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="fechafin" type="text" name="fechafin" maxlength="45" value="<?php echo set_value('fechafin', isset($banners['fechafin']) ? $banners['fechafin'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('fechafin'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('habilitado') ? 'error' : ''; ?>">
            <?php echo form_label('Habilitado', 'habilitado', array('class' => "control-label") ); ?>
            <div class='controls'>
            <label class="checkbox" for="habilitado">
            <input type="checkbox" id="habilitado" name="habilitado" value="1" <?php echo (isset($banners['habilitado']) && $banners['habilitado'] == 1) ? 'checked="checked"' : set_checkbox('habilitado', 1); ?>>
            <span class="help-inline"><?php echo form_error('habilitado'); ?></span>
            </label>

        </div>

        </div>
        <div class="control-group <?php echo form_error('idbannertype') ? 'error' : ''; ?>">
            <?php echo form_label('Idbannertype'. lang('bf_form_label_required'), 'idbannertype', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idbannertype" type="text" name="idbannertype" maxlength="11" value="<?php echo set_value('idbannertype', isset($banners['idbannertype']) ? $banners['idbannertype'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idbannertype'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('prioridad') ? 'error' : ''; ?>">
            <?php echo form_label('Prioridad'. lang('bf_form_label_required'), 'prioridad', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="prioridad" type="text" name="prioridad" maxlength="11" value="<?php echo set_value('prioridad', isset($banners['prioridad']) ? $banners['prioridad'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('prioridad'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Edit Banners" />
            or <?php echo anchor(SITE_AREA .'/content/banners', lang('banners_cancel'), 'class="btn btn-warning"'); ?>
            

    <?php if ($this->auth->has_permission('Banners.Content.Delete')) : ?>

            or <a class="btn btn-danger" id="delete-me" href="<?php echo site_url(SITE_AREA .'/content/banners/delete/'. $id);?>" onclick="return confirm('<?php echo lang('banners_delete_confirm'); ?>')" name="delete-me">
            <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('banners_delete_record'); ?>
            </a>

    <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
