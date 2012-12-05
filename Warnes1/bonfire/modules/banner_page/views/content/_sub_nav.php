<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/banner_page') ?>"><?php echo lang('banner_page_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Banner_Page.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/banner_page/create') ?>" id="create_new"><?php echo lang('banner_page_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>