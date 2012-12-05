<div class="admin-box">
    <h3>Banners</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Banners.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Id</th>
                <th>Nombre</th>
                <th>Url</th>
                <th>Fechainicio</th>
                <th>Fechafin</th>
                <th>Habilitado</th>
                <th>Idbannertype</th>
                <th>Prioridad</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Banners.Content.Delete')) : ?>
                    <tr>
                        <td colspan="9">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php echo lang('banners_delete_confirm'); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Banners.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->idbanner ?>" /></td>
                        <?php endif; ?>

                        <td><?php echo $record->idbanner ?></td>
                        <?php if ($this->auth->has_permission('Banners.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/banners/edit/' . $record->idbanner, '<i class="icon-pencil">&nbsp;</i>' . $record->nombre) ?></td>
                        <?php else: ?>
                            <td><?php echo $record->nombre ?></td>
                        <?php endif; ?>

                        <td><?php echo $record->url ?></td>
                        <td><?php echo $record->fechainicio ?></td>
                        <td><?php echo $record->fechafin ?></td>
                        <td><?php echo $record->habilitado ?></td>
                        <td><?php echo $record->idbannertype ?></td>
                        <td><?php echo $record->prioridad ?></td>
                    </tr>
                <?php endforeach; ?>
            <?php else: ?>
                <tr>
                    <td colspan="9">No records found that match your selection.</td>
                </tr>
            <?php endif; ?>
        </tbody>
    </table>
    <?php echo form_close(); ?>
</div>