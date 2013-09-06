<div class="admin-box">
    <div> Torneo
        <?php
        $attributes = array('id' => 'formtorneo', 'name' => 'formtorneo');
        echo form_open($this->uri->uri_string(), $attributes);
        ?>
        <select id="torneo" name="torneo" onchange="return changeTorneoAdmPartidos()">
            <?php foreach ($torneos as $torneo): ?>
                <?php if ($torneo['id'] === $torneoselected) : ?>
                    <option value="<?php echo $torneo['id'] ?>" selected><?php echo $torneo['nombre'] ?></option>
                <?php else : ?>
                    <option value="<?php echo $torneo['id'] ?>"><?php echo $torneo['nombre'] ?></option>
                <?php endif; ?>
            <?php endforeach ?>
        </select>
        Ronda
        <?php if (isset($rondas)) { ?>
            <select id="ronda" name="ronda" onchange="return changeTorneoAdmPartidos()">
                <?php for ($i = 1; $i <= $rondas; $i++): ?>
                    <?php if ($i == $rondaselected) : ?>
                        <option value="<?php echo $i ?>" selected><?php echo $i ?></option>
                    <?php else : ?>
                        <option value="<?php echo $i ?>"><?php echo $i ?></option>
                    <?php endif; ?>
                <?php endfor ?>
            </select>
        <?php } ?>
        <?php echo form_close(); ?>
    </div>
    <h3>Administrar Partidos</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Administrar_Partidos.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <th>Fecha</th>
                <th>Torneo</th>
                <th>Fase/Ronda</th>
                <th>Equipo 1</th>
                <th>Equipo 2</th>
                <th>Goles Equipo 1</th>
                <th>Goles Equipo 2</th>
                <!--<th>Fecha Torneo</th>-->
                <th>Terminado</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Administrar_Partidos.Content.Delete')) : ?>
                    <tr>
                        <td colspan="10">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php e(js_escape(lang('administrar_partidos_delete_confirm'))); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Administrar_Partidos.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->id ?>" /></td>
                        <?php endif; ?>

                        <?php if ($this->auth->has_permission('Administrar_Partidos.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/administrar_partidos/edit/' . $record->id, '<i class="icon-pencil">&nbsp;</i>' . $record->fecha) ?></td>
                        <?php else: ?>
                            <td><?php e($record->fecha) ?></td>
                        <?php endif; ?>

                        <td><?php e($record->idtorneo) ?></td>
                        <td><?php e($record->idfase) ?></td>
                        <td><?php e($record->idequipo1) ?></td>
                        <td><?php e($record->idequipo2) ?></td>
                        <td><?php e($record->goles_equipo1) ?></td>
                        <td><?php e($record->goles_equipo2) ?></td>
                        <!--<td><?php e($record->fecha_torneo) ?></td>-->
                        <td><?php e($record->jugado) ?></td>
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