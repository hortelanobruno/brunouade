<ul class="nav nav-pills">
	<li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
		<a href="<?php echo site_url(SITE_AREA .'/content/noticias_fotos') ?>"><?php echo lang('noticias_fotos_list'); ?></a>
	</li>
	<?php if ($this->auth->has_permission('Noticias_Fotos.Content.Create')) : ?>
	<li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
		<a href="<?php echo site_url(SITE_AREA .'/content/noticias_fotos/create') ?>" id="create_new"><?php echo lang('noticias_fotos_new'); ?></a>
	</li>
	<?php endif; ?>
</ul>