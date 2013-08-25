<div class="wrapper bgwhite">

    <div id="content">

        <div id="pageView-title" class="f20 b">

        </div>

        <div class="mt20 pb20">
            <div id="pageView-title" class="f24 b wp90 tl mauto" style="border-bottom:1px solid #D7D7D7">
                <?php echo $torneo['nombre'] ?>	</div>

            <div class="wp100">
                <div class="wp100 f24 b tc mauto">

                    <img style="max-height:150px;max-width:45%;" class="mb5" src="<?php echo $torneo['logo_grande'] ?>" />
                </div>

            </div>



            <div class="clearfix"></div>


            <?php $this->load->view('home/torneo/menutorneo'); ?>
        </div>
        <div class="clearfix"></div>
        <div class="mb20"></div>
    </div>
</div>
</div> <!-- #wrapper -->