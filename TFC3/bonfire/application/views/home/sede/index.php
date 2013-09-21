<div class="wrapper bgwhite">

    <div id="content">
        <div id="content" class="m25 pt20 pb20">
            <div id="pageView-title">
                <span class="b">Sedes</span>
            </div>
            <table border="0" class="wp100 mt40 m40">
                <tbody>
                    <tr>	
                        <?php foreach ($sedes as $sede): ?>
                            <td style="width: 25%;height:40px">
                                <strong><?php echo $sede['nombre'] ?></strong><br><?php echo $sede['direccion'] ?> - <?php echo $sede['localidad'] ?>				
                            </td>
                        <?php endforeach ?>
                    </tr>
                </tbody>
            </table>
            <div style="height:600px;" class="pt20 pb20">
                
                <div id="map-canvas">


                </div>
            </div>
        </div>
    </div>

    <div class="clearfix"></div>