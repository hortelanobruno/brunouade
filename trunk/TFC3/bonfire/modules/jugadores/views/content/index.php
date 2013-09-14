<div class="admin-box">
    <div> Torneo
        <?php
        $attributes = array('id' => 'formtorneo', 'name' => 'formtorneo');
        echo form_open($this->uri->uri_string(), $attributes);
        ?>
        <select id="equipo" name="equipo" onchange="return changeTorneoAdmPartidos()">
            <?php foreach ($equipos as $equipo): ?>
                <?php if ($equipo['id'] === $equiposelected) : ?>
                    <option value="<?php echo $equipo['id'] ?>" selected><?php echo $equipo['nombre'] ?></option>
                <?php else : ?>
                    <option value="<?php echo $equipo['id'] ?>"><?php echo $equipo['nombre'] ?></option>
                <?php endif; ?>
            <?php endforeach ?>
        </select>
    </div>
    <h3>Jugadores</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Jugadores.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Nombre Completo</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Delegado</th>
                <th>Equipo</th>
                <th>Cantidad Tarjetas Amarillas</th>
                <th>Cantidad Partidos Jugados</th>
                <th>Cantidad Tarjetas Rojas</th>
                <th>Cantidad Goles</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Jugadores.Content.Delete')) : ?>
                    <tr>
                        <td colspan="10">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php e(js_escape(lang('jugadores_delete_confirm'))); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Jugadores.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->id ?>" /></td>
                        <?php endif; ?>

                        <?php if ($this->auth->has_permission('Jugadores.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/jugadores/edit/' . $record->id, '<i class="icon-pencil">&nbsp;</i>' . $record->nombre_completo) ?></td>
                        <?php else: ?>
                            <td><?php e($record->nombre_completo) ?></td>
                        <?php endif; ?>

                        <td><?php e($record->nombre) ?></td>
                        <td><?php e($record->apellido) ?></td>
                        <td><?php e($record->delegado) ?></td>
                        <td><?php e(getEquipo($equipos, $record->idequipo)) ?></td>
                        <td><?php e($record->cantidad_tarjetas_amarillas) ?></td>
                        <td><?php e($record->cantidad_partidos_jugados) ?></td>
                        <td><?php e($record->cantidad_tarjetas_rojas) ?></td>
                        <td><?php e($record->cantidad_goles) ?></td>
                    </tr>
                <?php endforeach; ?>
            <?php else: ?>
                <tr>
                    <td colspan="10">No records found that match your selection.</td>
                </tr>
            <?php endif; ?>
        </tbody>
    </table>
    <?php echo form_close(); ?>
</div>

<?php

function getEquipo($equipo, $idequipo) {
    if (isset($equipo)) {
        foreach ($equipo as $equipo) {
            if ($equipo['id'] === $idequipo)
                return $equipo['nombre'];
        }
    }else {
        return $idequipo;
    }
}
?>