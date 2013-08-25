<div class="admin-box">
	<h3>Equipos</h3>
	<?php echo form_open($this->uri->uri_string()); ?>
		<table class="table table-striped">
			<thead>
				<tr>
					<?php if ($this->auth->has_permission('Equipos.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
					<th class="column-check"><input class="check-all" type="checkbox" /></th>
					<?php endif;?>
					
					<th>Nombre</th>
					<th>Logo Grande</th>
					<th>Logo Chico</th>
					<th>Pagina Web</th>
					<th>Ciudad</th>
					<th>Foto</th>
					<th>Cantidad Partidos Perdidos</th>
					<th>Cantidad Tarjetas Rojas</th>
					<th>Cantidad Tarjetas Amarillas</th>
					<th>Cantidad Goles En Contra</th>
					<th>Cantidad Goles A Favor</th>
					<th>Cantidad Partidos Empatados</th>
					<th>Cantidad Partidos Ganados</th>
				</tr>
			</thead>
			<?php if (isset($records) && is_array($records) && count($records)) : ?>
			<tfoot>
				<?php if ($this->auth->has_permission('Equipos.Content.Delete')) : ?>
				<tr>
					<td colspan="14">
						<?php echo lang('bf_with_selected') ?>
						<input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php e(js_escape(lang('equipos_delete_confirm'))); ?>')">
					</td>
				</tr>
				<?php endif;?>
			</tfoot>
			<?php endif; ?>
			<tbody>
			<?php if (isset($records) && is_array($records) && count($records)) : ?>
			<?php foreach ($records as $record) : ?>
				<tr>
					<?php if ($this->auth->has_permission('Equipos.Content.Delete')) : ?>
					<td><input type="checkbox" name="checked[]" value="<?php echo $record->id ?>" /></td>
					<?php endif;?>
					
				<?php if ($this->auth->has_permission('Equipos.Content.Edit')) : ?>
				<td><?php echo anchor(SITE_AREA .'/content/equipos/edit/'. $record->id, '<i class="icon-pencil">&nbsp;</i>' .  $record->nombre) ?></td>
				<?php else: ?>
				<td><?php e($record->nombre) ?></td>
				<?php endif; ?>
			
				<td><?php e($record->logo_grande) ?></td>
				<td><?php e($record->logo_chico) ?></td>
				<td><?php e($record->pagina_web) ?></td>
				<td><?php e($record->ciudad) ?></td>
				<td><?php e($record->foto) ?></td>
				<td><?php e($record->cantidad_partidos_perdidos) ?></td>
				<td><?php e($record->cantidad_tarjetas_rojas) ?></td>
				<td><?php e($record->cantidad_tarjetas_amarillas) ?></td>
				<td><?php e($record->cantidad_goles_en_contra) ?></td>
				<td><?php e($record->cantidad_goles_a_favor) ?></td>
				<td><?php e($record->cantidad_partidos_empatados) ?></td>
				<td><?php e($record->cantidad_partidos_ganados) ?></td>
				</tr>
			<?php endforeach; ?>
			<?php else: ?>
				<tr>
					<td colspan="14">No records found that match your selection.</td>
				</tr>
			<?php endif; ?>
			</tbody>
		</table>
	<?php echo form_close(); ?>
</div>