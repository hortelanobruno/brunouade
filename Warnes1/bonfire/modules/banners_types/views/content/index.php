<div class="admin-box">
    <h3>Banners Types</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Banners_Types.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Id</th>
                <th>Description</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Banners_Types.Content.Delete')) : ?>
                    <tr>
                        <td colspan="3">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php echo lang('banners_types_delete_confirm'); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Banners_Types.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->idbannertype ?>" /></td>
                        <?php endif; ?>

                        <td><?php echo $record->idbannertype ?></td>
                        <?php if ($this->auth->has_permission('Banners_Types.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/banners_types/edit/' . $record->idbannertype, '<i class="icon-pencil">&nbsp;</i>' . $record->description) ?></td>
                        <?php else: ?>
                            <td><?php echo $record->description ?></td>
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