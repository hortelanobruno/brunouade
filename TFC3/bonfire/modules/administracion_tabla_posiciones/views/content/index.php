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
    <h3>Administracion Tabla Posiciones</h3>
    <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
                <?php if ($this->auth->has_permission('Administracion_Tabla_Posiciones.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
                <?php endif; ?>

                <!--<th>Idfase</th>-->
                <th>Idequipo</th>
                <th>Puntos</th>
                <th>Partidos Jugados</th>
                <th>Partidos Ganados</th>
                <th>Partidos Empatados</th>
                <th>Partidos Perdidos</th>
                <th>Goles A Favor</th>
                <th>Goles En Contra</th>
            </tr>
        </thead>
        <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
                <?php if ($this->auth->has_permission('Administracion_Tabla_Posiciones.Content.Delete')) : ?>
                    <tr>
                        <td colspan="10">
                            <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php e(js_escape(lang('administracion_tabla_posiciones_delete_confirm'))); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
        <?php endif; ?>
        <tbody>
            <?php if (isset($records) && is_array($records) && count($records)) : ?>
                <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Administracion_Tabla_Posiciones.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->idtorneo ?>" /></td>
                        <?php endif; ?>

                        <!--?php if ($this->auth->has_permission('Administracion_Tabla_Posiciones.Content.Edit')) : ?>
                            <td><!?php echo anchor(SITE_AREA . '/content/administracion_tabla_posiciones/edit/' . $record->idtorneo, '<i class="icon-pencil">&nbsp;</i>' . $record->idfase) ?></td>
                        <!--?php else: ?>
                            <td><!?php e($record->idfase) ?></td>
                        <!--?php endif; ?-->

                        <?php if ($this->auth->has_permission('Administracion_Tabla_Posiciones.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/administracion_tabla_posiciones/edit/' . $record->idtorneo.'/'.$record->idfase.'/'.$record->idequipo, '<i class="icon-pencil">&nbsp;</i>' . getEquipo($equipos,$record->idequipo)) ?></td>
                        <?php else: ?>
                            <td><?php e(getEquipo($equipos,$record->idequipo)) ?></td>
                        <?php endif; ?>
                            
                        <td><?php e($record->puntos) ?></td>
                        <td><?php e($record->partidos_jugados) ?></td>
                        <td><?php e($record->partidos_ganados) ?></td>
                        <td><?php e($record->partidos_empatados) ?></td>
                        <td><?php e($record->partidos_perdidos) ?></td>
                        <td><?php e($record->goles_a_favor) ?></td>
                        <td><?php e($record->goles_en_contra) ?></td>
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

function getEquipo($equipo,$idequipo) {
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