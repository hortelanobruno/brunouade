<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/banners') ?>"><?php echo lang('banners_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Banners.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/banners/create') ?>" id="create_new"><?php echo lang('banners_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>