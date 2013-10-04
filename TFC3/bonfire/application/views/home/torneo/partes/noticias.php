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
        <?php if (isset($noticias)) { ?>
            <?php foreach ($noticias as $noti): ?>
                <div class="news-item">
                    <div class="data"><?php echo $noti['fecha'] ?></div>
                    <div class="titolo"><a href="/noticia/<?php echo $noti['idnoticias'] ?>"><p><strong><em><?php echo $noti['titulo'] ?></em></strong></p></a></div>
                </div>
            <?php endforeach ?>
        <?php } ?>
    <?php } ?>


</fieldset>
<div class="clearfix"></div>