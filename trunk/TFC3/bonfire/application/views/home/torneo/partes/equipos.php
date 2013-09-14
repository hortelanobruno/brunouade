<table class="mauto table-p10 w710 mb30 calendario">
    <caption class="f24 b mb20">Equipos</caption>
    <tbody>
        <?php foreach ($equipos as $equipo): ?>
            <tr><td colspan="3"><span class="b i"></span></td></tr>
            <tr class="">
                <td><a class="nowrap f14" href="/equipo/<?php echo $equipo['id'] ?>"><?php echo $equipo['nombre'] ?></a></td>
                <td rowspan="4" class="nowrap wp10"></td>
            </tr>
            <tr id="tr_1470"><td colspan="4"><div id="iframe_1470"></div></td></tr>
                <?php endforeach ?>
    </tbody>
</table>