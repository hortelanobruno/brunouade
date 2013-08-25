<table id="tblScores" class="w700 pseudoDojo mauto table-p5">
    <thead>
        <tr>
            <th style="width: 5px"></th>
            <th>Amonestaciones</th>
            <th class="w90 tc">Rojas</th>
            <th class="w90 tc">Amarillas</th>
        </tr>
    </thead>
    <tbody>
        
        <?php foreach ($tarjetas as $tarjeta): ?>
            <tr>
                <td class="tr"><div class="index b">1°</div></td>
                <td class="name"><a href=""><img class="vm" src="/assets/imgs/user/defaultUser.png" height="25"> <?php echo $tarjeta['nombre_completo'] ?></a> <a href="/equipo/<?php echo $tarjeta['idequipo'] ?>"><span class="gray"><?php echo $tarjeta['nombre'] ?></span></a></td>
                <td class="score"><?php echo $tarjeta['cantidad_tarjetas_rojas'] ?></td>
                <td class="score"><?php echo $tarjeta['cantidad_tarjetas_amarillas'] ?></td>
            </tr>
        <?php endforeach ?>
<!--        <tr>
            <td class="tr"><div class="index b">15°</div></td>
            <td class="name"><a href="/default/user/index/id/3208"><img class="vm" src="/assets/imgs/user/defaultUser.png" height="25"> NANDO MANGO</a> <a href="/default/team/index/id/276"><span class="gray">(Real San Pietro)</span></a></td>
            <td class="score">3</td>
            <td class="score">2</td>
        </tr>-->
    </tbody>
</table>
<!--<div class="pager mt10" id="statsPager">
    <a class="page_0 selected" href="javascript:getStatsForSerie(0)">1</a>
    <a class="page_1" href="javascript:getStatsForSerie(1)">2</a>
</div>-->