<ul class="nav nav-pills">
    <li <?php echo $this->uri->segment(4) == '' ? 'class="active"' : '' ?>>
        <a href="<?php echo site_url(SITE_AREA . '/content/jugadores') ?>" id="list"><?php echo lang('jugadores_list'); ?></a>
    </li>
    <?php if ($this->auth->has_permission('Jugadores.Content.Create')) : ?>
        <li <?php echo $this->uri->segment(4) == 'create' ? 'class="active"' : '' ?> >
            <a href="<?php echo site_url(SITE_AREA . '/content/jugadores/create') ?>" id="create_new"><?php echo lang('jugadores_new'); ?></a>
        </li>
    <?php endif; ?>
    <li>
        <a style="cursor: pointer" onclick="showImportJugadores()" id="import">Import</a>
    </li>
</ul>