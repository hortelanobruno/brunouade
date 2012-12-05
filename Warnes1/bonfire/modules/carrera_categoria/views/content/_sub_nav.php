<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/carrera_categoria') ?>"><?php echo lang('carrera_categoria_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Carrera_Categoria.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/carrera_categoria/create') ?>" id="create_new"><?php echo lang('carrera_categoria_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>