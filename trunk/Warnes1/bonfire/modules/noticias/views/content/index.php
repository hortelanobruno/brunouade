<div class="admin-box">
    <h3>Noticias</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Noticias.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Id</th>
                <th>Titulo</th>
                <th>Epigrafe</th>
                <th>Descripcion</th>
                <th>Fechanoticia</th>
                <th>Habilitado</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Noticias.Content.Delete')) : ?>
                    <tr>
                        <td colspan="7">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php echo lang('noticias_delete_confirm'); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Noticias.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->idnoticia ?>" /></td>
                        <?php endif; ?>

                        <td><?php echo $record->idnoticia ?></td>
                        <?php if ($this->auth->has_permission('Noticias.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/noticias/edit/' . $record->idnoticia, '<i class="icon-pencil">&nbsp;</i>' . $record->titulo) ?></td>
                        <?php else: ?>
                            <td><?php echo $record->titulo ?></td>
                        <?php endif; ?>

                        <td><?php echo $record->epigrafe ?></td>
                        <td><?php echo $record->descripcion ?></td>
                        <td><?php echo $record->fechanoticia ?></td>
                        <td><?php echo $record->habilitado ?></td>
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