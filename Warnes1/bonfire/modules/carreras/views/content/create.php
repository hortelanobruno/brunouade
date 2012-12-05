
<?php if (validation_errors()) : ?>
<div class="alert alert-block alert-error fade in ">
  <a class="close" data-dismiss="alert">&times;</a>
  <h4 class="alert-heading">Please fix the following errors :</h4>
 <?php echo validation_errors(); ?>
</div>
<?php endif; ?>
<?php // Change the css classes to suit your needs
if( isset($carreras) ) {
    $carreras = (array)$carreras;
}
$id = isset($carreras['idcarrera']) ? $carreras['idcarrera'] : '';
?>
<div class="admin-box">
    <h3>Carreras</h3>
<?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('idcarrera_categoria') ? 'error' : ''; ?>">
            <?php echo form_label('Idcarrera Categoria', 'idcarrera_categoria', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="idcarrera_categoria" type="text" name="idcarrera_categoria" maxlength="11" value="<?php echo set_value('idcarrera_categoria', isset($carreras['idcarrera_categoria']) ? $carreras['idcarrera_categoria'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('idcarrera_categoria'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('numero_carrera') ? 'error' : ''; ?>">
            <?php echo form_label('Numero Carrera', 'numero_carrera', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="numero_carrera" type="text" name="numero_carrera" maxlength="11" value="<?php echo set_value('numero_carrera', isset($carreras['numero_carrera']) ? $carreras['numero_carrera'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('numero_carrera'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('fecha_carrera') ? 'error' : ''; ?>">
            <?php echo form_label('Fecha Carrera (2012-02-20)', 'fecha_carrera', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="fecha_carrera" type="text" name="fecha_carrera" maxlength="11" value="<?php echo set_value('fecha_carrera', isset($carreras['fecha_carrera']) ? $carreras['fecha_carrera'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('fecha_carrera'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('lugar') ? 'error' : ''; ?>">
            <?php echo form_label('Lugar', 'lugar', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="lugar" type="text" name="lugar" maxlength="200" value="<?php echo set_value('lugar', isset($carreras['lugar']) ? $carreras['lugar'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('lugar'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('circuito') ? 'error' : ''; ?>">
            <?php echo form_label('Circuito', 'circuito', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="circuito" type="text" name="circuito" maxlength="200" value="<?php echo set_value('circuito', isset($carreras['circuito']) ? $carreras['circuito'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('circuito'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('podio_anterior_1') ? 'error' : ''; ?>">
            <?php echo form_label('Podio Anterior 1', 'podio_anterior_1', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="podio_anterior_1" type="text" name="podio_anterior_1" maxlength="200" value="<?php echo set_value('podio_anterior_1', isset($carreras['podio_anterior_1']) ? $carreras['podio_anterior_1'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('podio_anterior_1'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('podio_anterior_2') ? 'error' : ''; ?>">
            <?php echo form_label('Podio Anterior 2', 'podio_anterior_2', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="podio_anterior_2" type="text" name="podio_anterior_2" maxlength="200" value="<?php echo set_value('podio_anterior_2', isset($carreras['podio_anterior_2']) ? $carreras['podio_anterior_2'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('podio_anterior_2'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('podio_anterior_3') ? 'error' : ''; ?>">
            <?php echo form_label('Podio Anterior 3', 'podio_anterior_3', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="podio_anterior_3" type="text" name="podio_anterior_3" maxlength="200" value="<?php echo set_value('podio_anterior_3', isset($carreras['podio_anterior_3']) ? $carreras['podio_anterior_3'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('podio_anterior_3'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('podio_actual_1') ? 'error' : ''; ?>">
            <?php echo form_label('Podio Actual 1', 'podio_actual_1', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="podio_actual_1" type="text" name="podio_actual_1" maxlength="200" value="<?php echo set_value('podio_actual_1', isset($carreras['podio_actual_1']) ? $carreras['podio_actual_1'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('podio_actual_1'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('podio_actual_2') ? 'error' : ''; ?>">
            <?php echo form_label('Podio Actual 2', 'podio_actual_2', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="podio_actual_2" type="text" name="podio_actual_2" maxlength="200" value="<?php echo set_value('podio_actual_2', isset($carreras['podio_actual_2']) ? $carreras['podio_actual_2'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('podio_actual_2'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('podio_actual_3') ? 'error' : ''; ?>">
            <?php echo form_label('Podio Actual 3', 'podio_actual_3', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="podio_actual_3" type="text" name="podio_actual_3" maxlength="200" value="<?php echo set_value('podio_actual_3', isset($carreras['podio_actual_3']) ? $carreras['podio_actual_3'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('podio_actual_3'); ?></span>
        </div>


        </div>
        <div class="control-group <?php echo form_error('anio') ? 'error' : ''; ?>">
            <?php echo form_label('Año', 'anio', array('class' => "control-label") ); ?>
            <div class='controls'>
        <input id="anio" type="text" name="anio" maxlength="11" value="<?php echo set_value('anio', isset($carreras['anio']) ? $carreras['anio'] : ''); ?>"  />
        <span class="help-inline"><?php echo form_error('anio'); ?></span>
        </div>


        </div>



        <div class="form-actions">
            <br/>
            <input type="submit" name="submit" class="btn btn-primary" value="Create Carreras" />
            or <?php echo anchor(SITE_AREA .'/content/carreras', lang('carreras_cancel'), 'class="btn btn-warning"'); ?>
            
        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
