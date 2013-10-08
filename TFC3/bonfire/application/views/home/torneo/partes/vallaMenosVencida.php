<table id="tblScores" class="w700 pseudoDojo mauto table-p5">
    <thead>
        <tr>
            <th style="width: 5px"></th>
            <th>Valla menos vencida</th>
            <th class="w30 tc">GC</th>
            <th class="w30 tc">PJ</th>
            <th class="w30 tc">PROM</th>
            <th class="w30 tc">P/EQ</th>
            <th class="w30 tc">%</th>
        </tr>
    </thead>
    <tbody>
        <?php 
        $i=0;
        foreach ($vallaMenosVencida as $valla): ?>
            <tr>
                <td class="tr"><div class="index b"><?php echo ++$i ?>°</div></td>
                <td class="name"><a href=""><img class="vm" src="/assets/imgs/user/defaultUser.png" height="25"> <?php echo $valla['nombre_completo'] ?></a> <a href="/equipo/<?php echo $valla['idequipo'] ?>"><span class="gray"><?php echo $valla['nombre'] ?></span></a></td>
                <td class="score"><?php echo $valla['cantidad_goles_en_contra'] ?></td>
                <td class="score"><?php echo $valla['cantidad_partidos_jugados'] ?></td>
                <td class="score"><?php echo calcularPromedio($valla['cantidad_goles_en_contra'], $valla['cantidad_partidos_jugados']) ?></td>
                <td class="score"><?php echo calcularPartidosPorEquipo($valla['cantidad_partidos_jugados'], $valla['idequipo'], $tablaPosiciones) ?></td>
                <td class="score"><?php echo calcularPorcentaje($valla['cantidad_partidos_jugados'], $valla['idequipo'], $tablaPosiciones) ?></td>
            </tr>
        <?php endforeach ?>


<!--        <tr>
    <td class="tr"><div class="index b">15°</div></td>
    <td class="name"><a href="/default/user/index/id/3208"><img class="vm" src="/assets/imgs/user/defaultUser.png" height="25"> NANDO MANGO</a> <a href="/default/team/index/id/276"><span class="gray">(Real San Pietro)</span></a></td>
    <td class="score">27</td>
    <td class="score">12</td>
    <td class="score">2.25</td>
    <td class="score">17</td>
    <td class="score">70.59</td>
</tr>-->
    </tbody>
</table>
<!--<div class="pager mt10" id="statsPager">
    <a class="page_0 selected" href="javascript:getStatsForSerie(0)">1</a>
    <a class="page_1" href="javascript:getStatsForSerie(1)">2</a>
</div>-->

<?php

function calcularPromedio($cantidad_goles_en_contra, $cantidad_partidos_jugados) {
    return number_format($cantidad_goles_en_contra / $cantidad_partidos_jugados, 2, '.', ',');
}

function calcularPartidosPorEquipo($cantidad_partidos_jugados, $idequipo, $tablaPosiciones) {
    foreach ($tablaPosiciones as $posicion) {
        if ($posicion['idequipo'] == $idequipo) {
            return $posicion['partidos_jugados'];
        }
    }
    return $cantidad_partidos_jugados;
}

function calcularPorcentaje($cantidad_partidos_jugados, $idequipo, $tablaPosiciones) {
    foreach ($tablaPosiciones as $posicion) {
        if ($posicion['idequipo'] == $idequipo) {
            return number_format($cantidad_partidos_jugados / $posicion['partidos_jugados'], 2, '.', ',');
        }
    }
    return 1;
}
?>