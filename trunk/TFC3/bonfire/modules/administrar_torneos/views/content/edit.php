
<?php if (validation_errors()) : ?>
    <div class="alert alert-block alert-error fade in ">
        <a class="close" data-dismiss="alert">&times;</a>
        <h4 class="alert-heading">Please fix the following errors :</h4>
        <?php echo validation_errors(); ?>
    </div>
<?php endif; ?>
<?php
// Change the css classes to suit your needs
if (isset($administrar_torneos)) {
    $administrar_torneos = (array) $administrar_torneos;
}
$id = isset($administrar_torneos['id']) ? $administrar_torneos['id'] : '';
?>
<div class="admin-box">
    <h3>Administrar Torneos</h3>
    <?php echo form_open($this->uri->uri_string(), 'class="form-horizontal"'); ?>
    <fieldset>
        <div class="control-group <?php echo form_error('administrar_torneos_nombre') ? 'error' : ''; ?>">
            <?php echo form_label('Nombre', 'administrar_torneos_nombre', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_nombre" type="text" name="administrar_torneos_nombre" maxlength="200" value="<?php echo set_value('administrar_torneos_nombre', isset($administrar_torneos['nombre']) ? $administrar_torneos['nombre'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_nombre'); ?></span>
            </div>

        </div>        
        <div class="control-group <?php echo form_error('administrar_torneos_categoria') ? 'error' : ''; ?>">
            <?php echo form_label('Categoria', 'administrar_torneos_categoria', array('class' => "control-label")); ?>
            <div class="controls">

<!--                <input id="administrar_torneos_categoria" type="text" name="administrar_torneos_categoria" maxlength="11" value="<!?php echo set_value('administrar_torneos_categoria', isset($administrar_torneos['categoria']) ? $administrar_torneos['categoria'] : ''); ?>"  />-->

                <select id="administrar_torneos_categoria" name="administrar_torneos_categoria">
                    <option value="1">Grupo</option>
                    <option value="2">Llave</option>
                    <option value="3">Grupo y llave</option>
                </select>
                <?php
                echo "<script type='text/javascript'>";
                echo "document.getElementById('administrar_torneos_categoria').value=" . $administrar_torneos['categoria'] . ";";
                echo "</script>";
                ?>




                <span class="help-inline"><?php echo form_error('administrar_torneos_categoria'); ?></span>
            </div>

        </div>        
        <div class="control-group <?php echo form_error('administrar_torneos_logo_chico') ? 'error' : ''; ?>">
            <?php echo form_label('Logo Chico', 'administrar_torneos_logo_chico', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_logo_chico" type="text" name="administrar_torneos_logo_chico" maxlength="200" value="<?php echo set_value('administrar_torneos_logo_chico', isset($administrar_torneos['logo_chico']) ? $administrar_torneos['logo_chico'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_logo_chico'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_logo_grande') ? 'error' : ''; ?>">
            <?php echo form_label('Logo Grande', 'administrar_torneos_logo_grande', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_logo_grande" type="text" name="administrar_torneos_logo_grande" maxlength="200" value="<?php echo set_value('administrar_torneos_logo_grande', isset($administrar_torneos['logo_grande']) ? $administrar_torneos['logo_grande'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_logo_grande'); ?></span>
            </div>

        </div>        
        <!--        <div class="control-group <!?php echo form_error('administrar_torneos_cantidad_tarjetas_amarillas') ? 'error' : ''; ?>">
                    <!?php echo form_label('Cantidad Tarjetas Amarillas', 'administrar_torneos_cantidad_tarjetas_amarillas', array('class' => "control-label")); ?>
                    <div class="controls">
        
                        <input id="administrar_torneos_cantidad_tarjetas_amarillas" type="text" name="administrar_torneos_cantidad_tarjetas_amarillas" maxlength="11" value="<!?php echo set_value('administrar_torneos_cantidad_tarjetas_amarillas', isset($administrar_torneos['cantidad_tarjetas_amarillas']) ? $administrar_torneos['cantidad_tarjetas_amarillas'] : ''); ?>"  />
                        <span class="help-inline"><!?php echo form_error('administrar_torneos_cantidad_tarjetas_amarillas'); ?></span>
                    </div>
        
                </div>        -->
        <!--        <div class="control-group <!?php echo form_error('administrar_torneos_cantidad_fechas') ? 'error' : ''; ?>">
                    <!?php echo form_label('Cantidad Fechas', 'administrar_torneos_cantidad_fechas', array('class' => "control-label")); ?>
                    <div class="controls">
        
                        <input id="administrar_torneos_cantidad_fechas" type="text" name="administrar_torneos_cantidad_fechas" maxlength="11" value="<!?php echo set_value('administrar_torneos_cantidad_fechas', isset($administrar_torneos['cantidad_fechas']) ? $administrar_torneos['cantidad_fechas'] : ''); ?>"  />
                        <span class="help-inline"><!?php echo form_error('administrar_torneos_cantidad_fechas'); ?></span>
                    </div>
        
                </div>        -->
        <!--        <div class="control-group <!?php echo form_error('administrar_torneos_cantidad_partidos') ? 'error' : ''; ?>">
                    <!?php echo form_label('Cantidad Partidos', 'administrar_torneos_cantidad_partidos', array('class' => "control-label")); ?>
                    <div class="controls">
        
                        <input id="administrar_torneos_cantidad_partidos" type="text" name="administrar_torneos_cantidad_partidos" maxlength="11" value="<!?php echo set_value('administrar_torneos_cantidad_partidos', isset($administrar_torneos['cantidad_partidos']) ? $administrar_torneos['cantidad_partidos'] : ''); ?>"  />
                        <span class="help-inline"><!?php echo form_error('administrar_torneos_cantidad_partidos'); ?></span>
                    </div>
        
                </div>        -->
        <div class="control-group <?php echo form_error('administrar_torneos_cantidad_equipos') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Equipos', 'administrar_torneos_cantidad_equipos', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_cantidad_equipos" type="text" name="administrar_torneos_cantidad_equipos" maxlength="11" value="<?php echo set_value('administrar_torneos_cantidad_equipos', isset($administrar_torneos['cantidad_equipos']) ? $administrar_torneos['cantidad_equipos'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_cantidad_equipos'); ?></span>
            </div>

        </div>        
        <!--        <div class="control-group <!?php echo form_error('administrar_torneos_cantidad_tarjetas_rojas') ? 'error' : ''; ?>">
                    <!?php echo form_label('Cantidad Tarjetas Rojas', 'administrar_torneos_cantidad_tarjetas_rojas', array('class' => "control-label")); ?>
                    <div class="controls">
        
                        <input id="administrar_torneos_cantidad_tarjetas_rojas" type="text" name="administrar_torneos_cantidad_tarjetas_rojas" maxlength="11" value="<!?php echo set_value('administrar_torneos_cantidad_tarjetas_rojas', isset($administrar_torneos['cantidad_tarjetas_rojas']) ? $administrar_torneos['cantidad_tarjetas_rojas'] : ''); ?>"  />
                        <span class="help-inline"><!?php echo form_error('administrar_torneos_cantidad_tarjetas_rojas'); ?></span>
                    </div>
        
                </div>        -->
        <!--        <div class="control-group <!?php echo form_error('administrar_torneos_cantidad_goles') ? 'error' : ''; ?>">
                    <!?php echo form_label('Cantidad Goles', 'administrar_torneos_cantidad_goles', array('class' => "control-label")); ?>
                    <div class="controls">
        
                        <input id="administrar_torneos_cantidad_goles" type="text" name="administrar_torneos_cantidad_goles" maxlength="11" value="<!?php echo set_value('administrar_torneos_cantidad_goles', isset($administrar_torneos['cantidad_goles']) ? $administrar_torneos['cantidad_goles'] : ''); ?>"  />
                        <span class="help-inline"><!?php echo form_error('administrar_torneos_cantidad_goles'); ?></span>
                    </div>
        
                </div>        -->
        <div class="control-group <?php echo form_error('administrar_torneos_cantidad_equipos_ascienden') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Equipos Ascienden', 'administrar_torneos_cantidad_equipos_ascienden', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_cantidad_equipos_ascienden" type="text" name="administrar_torneos_cantidad_equipos_ascienden" maxlength="11" value="<?php echo set_value('administrar_torneos_cantidad_equipos_ascienden', isset($administrar_torneos['cantidad_equipos_ascienden']) ? $administrar_torneos['cantidad_equipos_ascienden'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_cantidad_equipos_ascienden'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_cantidad_equipos_descienden') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad Equipos Descienden', 'administrar_torneos_cantidad_equipos_descienden', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_cantidad_equipos_descienden" type="text" name="administrar_torneos_cantidad_equipos_descienden" maxlength="11" value="<?php echo set_value('administrar_torneos_cantidad_equipos_descienden', isset($administrar_torneos['cantidad_equipos_descienden']) ? $administrar_torneos['cantidad_equipos_descienden'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_cantidad_equipos_descienden'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_archivado') ? 'error' : ''; ?>">
            <?php echo form_label('Archivado', 'administrar_torneos_archivado', array('class' => "control-label")); ?>
            <div class="controls">

                <label class="checkbox" for="archivado">
                    <input type="checkbox" id="administrar_torneos_archivado" name="administrar_torneos_archivado" value="1" <?php echo (isset($administrar_torneos['archivado']) && $administrar_torneos['archivado'] == 1) ? 'checked="checked"' : set_checkbox('archivado', 1); ?>>
                    <span class="help-inline"><?php echo form_error('administrar_torneos_archivado'); ?></span>
                </label>

            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_informaciongeneral') ? 'error' : ''; ?>">
            <?php echo form_label('Informaciongeneral', 'administrar_torneos_informaciongeneral', array('class' => "control-label")); ?>
            <div class="controls">
                <?php echo form_textarea(array('name' => 'administrar_torneos_informaciongeneral', 'id' => 'administrar_torneos_informaciongeneral', 'rows' => '5', 'cols' => '80', 'value' => set_value('administrar_torneos_informaciongeneral', isset($administrar_torneos['informaciongeneral']) ? $administrar_torneos['informaciongeneral'] : ''))) ?>
                <span class="help-inline"><?php echo form_error('administrar_torneos_informaciongeneral'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_reglamento') ? 'error' : ''; ?>">
            <?php echo form_label('Reglamento', 'administrar_torneos_reglamento', array('class' => "control-label")); ?>
            <div class="controls">
                <?php echo form_textarea(array('name' => 'administrar_torneos_reglamento', 'id' => 'administrar_torneos_reglamento', 'rows' => '5', 'cols' => '80', 'value' => set_value('administrar_torneos_reglamento', isset($administrar_torneos['reglamento']) ? $administrar_torneos['reglamento'] : ''))) ?>
                <span class="help-inline"><?php echo form_error('administrar_torneos_reglamento'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_ida_y_vuelta_grupo') ? 'error' : ''; ?>">
            <?php echo form_label('Ida Y Vuelta Grupo', 'administrar_torneos_ida_y_vuelta_grupo', array('class' => "control-label")); ?>
            <div class="controls">

                <label class="checkbox" for="ida_y_vuelta_grupo">
                    <input type="checkbox" id="administrar_torneos_ida_y_vuelta_grupo" name="administrar_torneos_ida_y_vuelta_grupo" value="1" <?php echo (isset($administrar_torneos['ida_y_vuelta_grupo']) && $administrar_torneos['ida_y_vuelta_grupo'] == 1) ? 'checked="checked"' : set_checkbox('ida_y_vuelta_grupo', 1); ?>>
                    <span class="help-inline"><?php echo form_error('administrar_torneos_ida_y_vuelta_grupo'); ?></span>
                </label>

            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_ida_y_vuelta_llave') ? 'error' : ''; ?>">
            <?php echo form_label('Ida Y Vuelta Llave', 'administrar_torneos_ida_y_vuelta_llave', array('class' => "control-label")); ?>
            <div class="controls">

                <label class="checkbox" for="ida_y_vuelta_llave">
                    <input type="checkbox" id="administrar_torneos_ida_y_vuelta_llave" name="administrar_torneos_ida_y_vuelta_llave" value="1" <?php echo (isset($administrar_torneos['ida_y_vuelta_llave']) && $administrar_torneos['ida_y_vuelta_llave'] == 1) ? 'checked="checked"' : set_checkbox('ida_y_vuelta_llave', 1); ?>>
                    <span class="help-inline"><?php echo form_error('administrar_torneos_ida_y_vuelta_llave'); ?></span>
                </label>

            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_group_size') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad de equipos en el grupo', 'administrar_torneos_group_size', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_group_size" type="text" name="administrar_torneos_group_size" maxlength="11" value="<?php echo set_value('administrar_torneos_group_size', isset($administrar_torneos['group_size']) ? $administrar_torneos['group_size'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_group_size'); ?></span>
            </div>

        </div>        <div class="control-group <?php echo form_error('administrar_torneos_cant_pass_to_llave') ? 'error' : ''; ?>">
            <?php echo form_label('Cantidad equipos que pasan a la llave', 'administrar_torneos_cant_pass_to_llave', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_cant_pass_to_llave" type="text" name="administrar_torneos_cant_pass_to_llave" maxlength="11" value="<?php echo set_value('administrar_torneos_cant_pass_to_llave', isset($administrar_torneos['cant_pass_to_llave']) ? $administrar_torneos['cant_pass_to_llave'] : ''); ?>"  />
                <span class="help-inline"><?php echo form_error('administrar_torneos_cant_pass_to_llave'); ?></span>
            </div>

        </div>        
<!--        <div class="control-group <!?php echo form_error('administrar_torneos_cant_fases') ? 'error' : ''; ?>">
            <!?php echo form_label('Cant Fases', 'administrar_torneos_cant_fases', array('class' => "control-label")); ?>
            <div class="controls">

                <input id="administrar_torneos_cant_fases" type="text" name="administrar_torneos_cant_fases" maxlength="11" value="<!?php echo set_value('administrar_torneos_cant_fases', isset($administrar_torneos['cant_fases']) ? $administrar_torneos['cant_fases'] : ''); ?>"  />
                <span class="help-inline"><!?php echo form_error('administrar_torneos_cant_fases'); ?></span>
            </div>

        </div>-->


        <div class="form-actions">
            <br/>
            <input type="submit" name="save" class="btn btn-primary" value="Edit Administrar Torneos" />
            or <?php echo anchor(SITE_AREA . '/content/administrar_torneos', lang('administrar_torneos_cancel'), 'class="btn btn-warning"'); ?>


            <?php if ($this->auth->has_permission('Administrar_Torneos.Content.Delete')) : ?>

                or <button type="submit" name="delete" class="btn btn-danger" id="delete-me" onclick="return confirm('<?php e(js_escape(lang('administrar_torneos_delete_confirm'))); ?>')">
                    <i class="icon-trash icon-white">&nbsp;</i>&nbsp;<?php echo lang('administrar_torneos_delete_record'); ?>
                </button>

            <?php endif; ?>


        </div>
    </fieldset>
    <?php echo form_close(); ?>


</div>
