
<div class="colmask threecol">
    <div class="colmid">
        <div class="colleft">
            <div class="col1">
                <div class="boxCategoria">
                    <table style="width: 100%">
                        <tr>
                            <td colspan="3" align="center">
                                <?php echo '<h2>' . strtoupper($rubro['descripcion']) . '</h2>'; ?>
                            </td>
                        </tr>
                        <?php foreach ($clientes as $cliente): ?>
                            <tr>
                                <td>
                                    <? echo $cliente['nombre']; ?>
                                </td>
                                <td>
                                </td>
                                <td align="right">
                                    <a href="/cliente/<? echo $cliente['idcliente']; ?>">Ver Microsite <img src="/images/Search_Icon.png" /></a>
                                </td>
                            </tr>
                        <?php endforeach ?>

                    </table>
                </div>
            </div>
            <div class="col2">
                <?php $this->load->view('home/acordion'); ?>
            </div>
            <div class="col3">
                <?php foreach ($banners_side as $banner): ?>
                    <img alt="<?php echo $banner['nombre'] ?>" src="<?php echo $banner['url'] ?>" width="150" height="150" />
                <?php endforeach ?>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function() {
        $( "#accordion" ).accordion({ autoHeight: false, active: 0 });
    });
</script>