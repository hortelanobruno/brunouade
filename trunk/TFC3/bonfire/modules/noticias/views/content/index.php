<div class="admin-box">
    <h3>Noticias</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Noticias.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Torneo</th>
                <th>Titulo</th>
                <th>Subtitulo</th>
                <th>Contenido</th>
                <th>Fecha</th>
                <th>Foto Portada</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Noticias.Content.Delete')) : ?>
                    <tr>
                        <td colspan="5">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php e(js_escape(lang('noticias_delete_confirm'))); ?>')">
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
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->idnoticias ?>" /></td>
                        <?php endif; ?>

                        <?php if ($this->auth->has_permission('Noticias.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/noticias/edit/' . $record->idnoticias, '<i class="icon-pencil">&nbsp;</i>' . getTorneo($torneos,$record->idtorneo)) ?></td>
                        <?php else: ?>
                            <td><?php e(getTorneo($torneos,$record->idtorneo)) ?></td>
                        <?php endif; ?>

                        <td><?php e($record->titulo) ?></td>
                        <td><?php e($record->subtitulo) ?></td>
                        <td><?php //e($record->contenido)   ?></td>
                        <td><?php e($record->fecha) ?></td>
                        <td><?php e($record->foto_portada) ?></td>
                    </tr>
                <?php endforeach; ?>
            <?php else: ?>
                <tr>
                    <td colspan="5">No records found that match your selection.</td>
                </tr>
            <?php endif; ?>
        </tbody>
    </table>
    <?php echo form_close(); ?>
</div>

<?php

function getTorneo($torneos,$idtorneo) {
    if (isset($torneos)) {
        foreach ($torneos as $torneo) {
            if ($torneo['id'] === $idtorneo)
                return $torneo['nombre'];
        }
        return "None";
    }else {
        return $idtorneo;
    }
}
?>