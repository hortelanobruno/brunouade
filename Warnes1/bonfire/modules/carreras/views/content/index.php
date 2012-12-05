<div class="admin-box">
	<h3>Carreras</h3>
	<?php echo form_open($this->uri->uri_string()); ?>
		<table class="table table-striped">
			<thead>
				<tr>
					<?php if ($this->auth->has_permission('Carreras.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
					<th class="column-check"><input class="check-all" type="checkbox" /></th>
					<?php endif;?>
					
					<th>Idcarrera Categoria</th>
					<th>Numero Carrera</th>
					<th>Fecha Carrera</th>
					<th>Lugar</th>
					<th>Circuito</th>
					<th>Podio Anterior 1</th>
					<th>Podio Anterior 2</th>
					<th>Podio Anterior 3</th>
					<th>Podio Actual 1</th>
					<th>Podio Actual 2</th>
					<th>Podio Actual 3</th>
					<th>Año</th>
				</tr>
			</thead>
			<?php if (isset($records) && is_array($records) && count($records)) : ?>
			<tfoot>
				<?php if ($this->auth->has_permission('Carreras.Content.Delete')) : ?>
				<tr>
					<td colspan="13">
						<?php echo lang('bf_with_selected') ?>
						<input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php echo lang('carreras_delete_confirm'); ?>')">
					</td>
				</tr>
				<?php endif;?>
			</tfoot>
			<?php endif; ?>
			<tbody>
			<?php if (isset($records) && is_array($records) && count($records)) : ?>
			<?php foreach ($records as $record) : ?>
				<tr>
					<?php if ($this->auth->has_permission('Carreras.Content.Delete')) : ?>
					<td><input type="checkbox" name="checked[]" value="<?php echo $record->idcarrera ?>" /></td>
					<?php endif;?>
					
				<?php if ($this->auth->has_permission('Carreras.Content.Edit')) : ?>
				<td><?php echo anchor(SITE_AREA .'/content/carreras/edit/'. $record->idcarrera, '<i class="icon-pencil">&nbsp;</i>' .  $record->idcarrera_categoria) ?></td>
				<?php else: ?>
				<td><?php echo $record->idcarrera_categoria ?></td>
				<?php endif; ?>
			
				<td><?php echo $record->numero_carrera?></td>
				<td><?php echo $record->fecha_carrera?></td>
				<td><?php echo $record->lugar?></td>
				<td><?php echo $record->circuito?></td>
				<td><?php echo $record->podio_anterior_1?></td>
				<td><?php echo $record->podio_anterior_2?></td>
				<td><?php echo $record->podio_anterior_3?></td>
				<td><?php echo $record->podio_actual_1?></td>
				<td><?php echo $record->podio_actual_2?></td>
				<td><?php echo $record->podio_actual_3?></td>
				<td><?php echo $record->anio?></td>
				</tr>
			<?php endforeach; ?>
			<?php else: ?>
				<tr>
					<td colspan="13">No records found that match your selection.</td>
				</tr>
			<?php endif; ?>
			</tbody>
		</table>
	<?php echo form_close(); ?>
</div>