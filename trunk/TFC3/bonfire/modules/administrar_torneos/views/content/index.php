<div class="admin-box">
	<h3>Administrar Torneos</h3>
	<?php echo form_open($this->uri->uri_string()); ?>
		<table class="table table-striped">
			<thead>
				<tr>
					<?php if ($this->auth->has_permission('Administrar_Torneos.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
					<th class="column-check"><input class="check-all" type="checkbox" /></th>
					<?php endif;?>
					
					<th>Nombre</th>
					<th>Categoria</th>
					<th>Logo Chico</th>
					<th>Logo Grande</th>
					<!--<th>Cantidad Tarjetas Amarillas</th>-->
					<!--<th>Cantidad Fechas</th>-->
					<!--<th>Cantidad Partidos</th>-->
					<th>Cantidad Equipos</th>
					<!--<th>Cantidad Tarjetas Rojas</th>-->
					<!--<th>Cantidad Goles</th>-->
					<th>Cantidad Equipos Ascienden</th>
					<th>Cantidad Equipos Descienden</th>
					<th>Archivado</th>
					<!--<th>Informaciongeneral</th>-->
					<!--<th>Reglamento</th>-->
					<th>Ida Y Vuelta Grupo</th>
					<th>Ida Y Vuelta Llave</th>
					<th>Cantidad de equipos en el grupo</th>
					<th>Cantidad equipos que pasan a la llave</th>
					<!--<th>Cant Fases</th>-->
				</tr>
			</thead>
			<?php if (isset($records) && is_array($records) && count($records)) : ?>
			<tfoot>
				<?php if ($this->auth->has_permission('Administrar_Torneos.Content.Delete')) : ?>
				<tr>
					<td colspan="21">
						<?php echo lang('bf_with_selected') ?>
						<input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php e(js_escape(lang('administrar_torneos_delete_confirm'))); ?>')">
					</td>
				</tr>
				<?php endif;?>
			</tfoot>
			<?php endif; ?>
			<tbody>
			<?php if (isset($records) && is_array($records) && count($records)) : ?>
			<?php foreach ($records as $record) : ?>
				<tr>
					<?php if ($this->auth->has_permission('Administrar_Torneos.Content.Delete')) : ?>
					<td><input type="checkbox" name="checked[]" value="<?php echo $record->id ?>" /></td>
					<?php endif;?>
					
				<?php if ($this->auth->has_permission('Administrar_Torneos.Content.Edit')) : ?>
				<td><?php echo anchor(SITE_AREA .'/content/administrar_torneos/edit/'. $record->id, '<i class="icon-pencil">&nbsp;</i>' .  $record->nombre) ?></td>
				<?php else: ?>
				<td><?php e($record->nombre) ?></td>
				<?php endif; ?>
			
				<td><?php e($record->categoria) ?></td>
                                <td><img src="<?php e($record->logo_chico) ?>" /></td>
				<td><img src="<?php e($record->logo_grande) ?>" /></td>
				<!--<td><!?php e($record->cantidad_tarjetas_amarillas) ?></td>-->
				<!--<td><?php e($record->cantidad_fechas) ?></td>-->
				<!--<td><?php e($record->cantidad_partidos) ?></td>-->
				<td><?php e($record->cantidad_equipos) ?></td>
				<!--<td><!?php e($record->cantidad_tarjetas_rojas) ?></td>-->
				<!--<td><!?php e($record->cantidad_goles) ?></td>-->
				<td><?php e($record->cantidad_equipos_ascienden) ?></td>
				<td><?php e($record->cantidad_equipos_descienden) ?></td>
				<td><?php e($record->archivado) ?></td>
				<!--<td><!?php e($record->informaciongeneral) ?></td>-->
				<!--<td><!?php e($record->reglamento) ?></td>-->
				<td><?php e($record->ida_y_vuelta_grupo) ?></td>
				<td><?php e($record->ida_y_vuelta_llave) ?></td>
				<td><?php e($record->group_size) ?></td>
				<td><?php e($record->cant_pass_to_llave) ?></td>
				<!--<td><?php e($record->cant_fases) ?></td>-->
				</tr>
			<?php endforeach; ?>
			<?php else: ?>
				<tr>
					<td colspan="21">No records found that match your selection.</td>
				</tr>
			<?php endif; ?>
			</tbody>
		</table>
	<?php echo form_close(); ?>
</div>