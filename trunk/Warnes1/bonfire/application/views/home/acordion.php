<div id="accordion">
    <h3><a href="#">Rubros</a></h3>
    <div>
        <table>
            <?php foreach ($rubros as $rubro): ?>
                <tr>
                    <td><a href="/rubro/<?php echo $rubro['idrubro'] ?>"><?php echo strtoupper($rubro['descripcion']) ?></a></td>
                </tr>
            <?php endforeach ?>
            <tr>
                <td><a href="/rubros" ><?php echo strtoupper("Todas los rubros") ?></a></td>
            </tr>
        </table>
    </div>
    <h3><a href="#">Marcas</a></h3>
    <div style="height: auto">
        <table>
            <?php foreach ($marcas as $marca): ?>
                <tr>
                    <td><a href="/marca/<?php echo $marca['idmarca'] ?>"><?php echo strtoupper($marca['descripcion']) ?></a></td>
                </tr>
            <?php endforeach ?>
            <tr>
                <td><a href="/marcas" ><?php echo strtoupper("Todas las marcas") ?></a></td>
            </tr>
        </table>
    </div>
    <h3><a href="#">Zonas</a></h3>
    <div>
        <table>
            <?php foreach ($zonas as $zona): ?>
                <tr>
                    <td><a href="/zona/<?php echo $zona['idzona'] ?>"><?php echo strtoupper($zona['descripcion']) ?></a></td>
                </tr>
            <?php endforeach ?>
            <tr>
                <td><a href="/zonas" ><?php echo strtoupper("Todas las zonas") ?></a></td>
            </tr>
        </table>
    </div>
</div>