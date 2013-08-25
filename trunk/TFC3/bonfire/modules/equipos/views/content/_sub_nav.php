<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/equipos') ?>" id="list"><?php echo lang('equipos_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Equipos.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/equipos/create') ?>" id="create_new"><?php echo lang('equipos_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>