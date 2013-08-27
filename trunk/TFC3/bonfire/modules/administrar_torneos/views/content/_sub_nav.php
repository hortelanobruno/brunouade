<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/administrar_torneos') ?>" id="list"><?php echo lang('administrar_torneos_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Administrar_Torneos.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/administrar_torneos/create') ?>" id="create_new"><?php echo lang('administrar_torneos_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>