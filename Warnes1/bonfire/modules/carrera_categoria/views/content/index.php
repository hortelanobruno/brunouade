<div class="admin-box">
    <h3>Carrera Categoria</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Carrera_Categoria.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Id</th>
                <th>Nombre</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Carrera_Categoria.Content.Delete')) : ?>
                    <tr>
                        <td colspan="3">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php echo lang('carrera_categoria_delete_confirm'); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Carrera_Categoria.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->idcarrera_categoria ?>" /></td>
                        <?php endif; ?>

                        <td><?php echo $record->idcarrera_categoria ?></td>
                        <?php if ($this->auth->has_permission('Carrera_Categoria.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/carrera_categoria/edit/' . $record->idcarrera_categoria, '<i class="icon-pencil">&nbsp;</i>' . $record->nombre) ?></td>
                        <?php else: ?>
                            <td><?php echo $record->nombre ?></td>
                        <?php endif; ?>

                    </tr>
                <?php endforeach; ?>
            <?php else: ?>
                <tr>
                    <td colspan="3">No records found that match your selection.</td>
                </tr>
            <?php endif; ?>
        </tbody>
    </table>
    <?php echo form_close(); ?>
</div>