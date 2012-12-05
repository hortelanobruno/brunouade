<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/marcas') ?>"><?php echo lang('marcas_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Marcas.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/marcas/create') ?>" id="create_new"><?php echo lang('marcas_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>