<fieldset class="fleft w700">
    <?php if (isset($noticia)) { ?>
        <!--        <label>Noticia</label>
                <table class="uiGrid mvm" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <td class="vTop"><!?php echo $noticia['contenido'] ?></td>
                        </tr>
                    </tbody>
                </table>-->
        <div class="news-item">
            <div class="data"><?php echo $noticia['fecha'] ?></div>
            <div class="titolo"><p><strong><em><?php echo $noticia['titulo'] ?></em></strong></p></div>
            <div class="descrizione"><p><?php echo $noticia['contenido'] ?></p></div>
        </div>
    <?php } else { ?>
        <label>Noticias</label>
    <?php } ?>


</fieldset>
<div class="clearfix"></div>