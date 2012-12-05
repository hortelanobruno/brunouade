<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/fotos') ?>"><?php echo lang('fotos_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Fotos.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/fotos/create') ?>" id="create_new"><?php echo lang('fotos_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>