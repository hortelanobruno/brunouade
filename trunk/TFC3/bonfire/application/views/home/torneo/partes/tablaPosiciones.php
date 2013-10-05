<div id="ajax-ranking">
    <table class="pseudoDojo mauto table-p10 w710">
        <tbody>
            <tr><th class="w300"></th><th class="w50 tc">PTS</th><th class="w50 tc">PJ</th><th class="w50 tc">PG</th><th class="w50 tc">PE</th><th class="w50 tc">PP</th><th class="w50 tc">GF</th><th class="w50 tc">GC</th><th class="w50 tc">Diff</th></tr>

            <?php foreach ($tablaPosiciones as $posicion): ?>

                <tr><td>
                        <a href="/equipo/<?php echo $posicion['idequipo'] ?>"><img width="30" class="mr5" src="/assets/imgs/default/team/defaultTeamLogo.png"><?php echo $posicion['nombre'] ?></a>
                    </td>
                    <td><?php echo $posicion['puntos'] ?></td>
                    <td><?php echo $posicion['partidos_jugados'] ?></td>
                    <td><?php echo $posicion['partidos_ganados'] ?></td>
                    <td><?php echo $posicion['partidos_empatados'] ?></td>
                    <td><?php echo $posicion['partidos_perdidos'] ?></td>
                    <td><?php echo $posicion['goles_a_favor'] ?></td>
                    <td><?php echo $posicion['goles_en_contra'] ?></td>
                    <td><?php echo $posicion['diff'] ?></td>
                </tr>
            <?php endforeach ?>
<!--            <tr><td><a href="/default/team/index/id/43"><img width="30" class="mr5" src="/assets/uploads/logo_22400862950730ef653a4eedf7437bda.png">Oro Massiccio</a></td><td>9</td><td>3</td><td>3</td><td>0</td><td>0</td><td>14</td><td>8</td><td>6</td></tr>
            <tr><td><a href="/default/team/index/id/286"><img width="30" class="mr5" src="/assets/uploads/logo_c3e177e3bdd479f844db92abd5215da5.png">Chi ama non dimentica</a></td><td>6</td><td>3</td><td>2</td><td>0</td><td>1</td><td>10</td><td>9</td><td>1</td></tr>
            <tr><td><a href="/default/team/index/id/195"><img width="30" class="mr5" src="/assets/uploads/logo_1b1b6e99857f24a9ec3db40109727464.png">Monster Beats Naples</a></td><td>3</td><td>3</td><td>1</td><td>0</td><td>2</td><td>11</td><td>8</td><td>3</td></tr>
            <tr><td><a href="/default/team/index/id/279"><img width="30" class="mr5" src="/assets/uploads/logo_9edf0270a8fac5411ec10e3c6d64bbe8.png">Cantera</a></td><td>0</td><td>3</td><td>0</td><td>0</td><td>3</td><td>4</td><td>14</td><td>-10</td></tr>-->
        </tbody>
    </table>
</div>



