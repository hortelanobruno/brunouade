<div class="wrapper bgwhite">
    <div id="content">
        <div id="pageView-title" class="f20 b">

        </div>

        <div class="mt20 pb20">
            <div id="pageView-title" class="f24 b wp90 tl mauto" style="border-bottom:1px solid #D7D7D7">
                Todos los torneos

            </div>
            <div id="pageView-title" class="f24 b wp90 tl mauto">
                <table class="mauto table-p10 w710 mb30 calendario">
                    <tbody>
                        <?php foreach ($torneos_all as $torneo): ?>
                            <tr><td colspan="3"><span class="b i"></span></td></tr>
                            <tr class="">
                                <td><a class="nowrap f14" href="/torneo/<?php echo $torneo['id'] ?>" style="color: black"><?php echo $torneo['nombre'] ?></a></td>
                                <td rowspan="4" class="nowrap wp10"></td>
                            </tr>
                            <tr id="tr_1470"><td colspan="4"><div id="iframe_1470"></div></td></tr>
                        <?php endforeach ?>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="mb20"></div>
    </div>
</div>