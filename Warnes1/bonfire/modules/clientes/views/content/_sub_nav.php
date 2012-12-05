<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes') ?>"><?php echo lang('clientes_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Clientes.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes/create') ?>" id="create_new"><?php echo lang('clientes_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>