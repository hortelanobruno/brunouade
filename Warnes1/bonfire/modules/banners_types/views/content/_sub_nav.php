<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/banners_types') ?>"><?php echo lang('banners_types_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Banners_Types.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/banners_types/create') ?>" id="create_new"><?php echo lang('banners_types_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>