<div class="admin-box">
    <h3>Fotos</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Fotos.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Id</th>
                <th>Nombre</th>
                <th>Titulo</th>
                <th>Url</th>
                <th>Habilitado</th>
                <th>Prioridad</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Fotos.Content.Delete')) : ?>
                    <tr>
                        <td colspan="7">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php echo lang('fotos_delete_confirm'); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Fotos.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->idfoto ?>" /></td>
                        <?php endif; ?>

                        <td><?php echo $record->idfoto ?></td>
                        <?php if ($this->auth->has_permission('Fotos.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/fotos/edit/' . $record->idfoto, '<i class="icon-pencil">&nbsp;</i>' . $record->nombre) ?></td>
                        <?php else: ?>
                            <td><?php echo $record->nombre ?></td>
                        <?php endif; ?>

                        <td><?php echo $record->titulo ?></td>
                        <td><?php echo $record->url ?></td>
                        <td><?php echo $record->habilitado ?></td>
                        <td><?php echo $record->prioridad ?></td>
                    </tr>
                <?php endforeach; ?>
            <?php else: ?>
                <tr>
                    <td colspan="7">No records found that match your selection.</td>
                </tr>
            <?php endif; ?>
        </tbody>
    </table>
    <?php echo form_close(); ?>
</div>