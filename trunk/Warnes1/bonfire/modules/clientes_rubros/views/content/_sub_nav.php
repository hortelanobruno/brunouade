<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes_rubros') ?>"><?php echo lang('clientes_rubros_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Clientes_Rubros.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/clientes_rubros/create') ?>" id="create_new"><?php echo lang('clientes_rubros_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>