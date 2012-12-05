<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/zonas') ?>"><?php echo lang('zonas_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Zonas.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/zonas/create') ?>" id="create_new"><?php echo lang('zonas_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>