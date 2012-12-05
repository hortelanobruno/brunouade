<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes_zonas') ?>"><?php echo lang('clientes_zonas_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Clientes_Zonas.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes_zonas/create') ?>" id="create_new"><?php echo lang('clientes_zonas_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>