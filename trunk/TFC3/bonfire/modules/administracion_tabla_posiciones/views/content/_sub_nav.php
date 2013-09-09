<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/administracion_tabla_posiciones') ?>" id="list"><?php echo lang('administracion_tabla_posiciones_list'); ?></a>
	</li>
	<!--?php if ($this->auth->has_permission('Administracion_Tabla_Posiciones.Content.Create')) : ?>
	<!--<li <!?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >-->
		<!--<a href="<!?php echo site_url(SITE_AREA .'/content/administracion_tabla_posiciones/create') ?>" id="create_new"><!?php echo lang('administracion_tabla_posiciones_new'); ?></a>-->
	<!--</li>-->
	<!--?php endif; ?-->
</ul>