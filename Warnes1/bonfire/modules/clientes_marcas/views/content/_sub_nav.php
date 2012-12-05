<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes_marcas') ?>"><?php echo lang('clientes_marcas_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Clientes_Marcas.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes_marcas/create') ?>" id="create_new"><?php echo lang('clientes_marcas_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>