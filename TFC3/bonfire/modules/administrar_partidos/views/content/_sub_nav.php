<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/administrar_partidos') ?>" id="list"><?php echo lang('administrar_partidos_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Administrar_Partidos.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/administrar_partidos/create') ?>" id="create_new"><?php echo lang('administrar_partidos_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>