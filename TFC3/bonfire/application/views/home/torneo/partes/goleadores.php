<table id="tblScores" class="w700 pseudoDojo mauto table-p5">
    <thead>
        <tr>
            <th style="width: 5px"></th>
            <th>Goleadores</th>
            <th class="w180 tc">Estadistica</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($goleadores as $goleador): ?>
            <tr>
                <td class="tr"><div class="index b">1°</div></td>
                <td class="name"><a href=""><img class="vm" src="/assets/imgs/user/defaultUser.png" height="25"> <?php echo $goleador['nombre_completo'] ?></a> <a href="/equipo/<?php echo $goleador['idequipo'] ?>"><span class="gray"><?php echo $goleador['nombre'] ?></span></a></td>
                <td class="score">Goles: <?php echo $goleador['cantidad_goles'] ?></td>
            </tr>
        <?php endforeach ?>


<!--        <tr>
    <td class="tr"><div class="index b">15°</div></td>
    <td class="name"><a href="/default/user/index/id/3208"><img class="vm" src="/assets/imgs/user/defaultUser.png" height="25"> NANDO MANGO</a> <a href="/default/team/index/id/276"><span class="gray">(Real San Pietro)</span></a></td>
    <td class="score">Goles: 1</td>
</tr>-->
    </tbody>
</table>
<!--<div class="pager mt10" id="statsPager">
    <a class="page_0 selected" href="javascript:getStatsForSerie(0)">1</a>
    <a class="page_1" href="javascript:getStatsForSerie(1)">2</a>
</div>-->