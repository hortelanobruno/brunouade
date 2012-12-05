<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/rubros') ?>"><?php echo lang('rubros_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Rubros.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/rubros/create') ?>" id="create_new"><?php echo lang('rubros_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>