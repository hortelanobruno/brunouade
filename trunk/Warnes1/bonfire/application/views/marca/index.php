
<div class="colmask threecol">
    <div class="colmid">
        <div class="colleft">
            <div class="col1">
                <div class="boxAllCategory">
                    <div class="marcas">
                        <?php
                        $normalizeChars = array(
                            'Á' => 'A', 'À' => 'A', 'Â' => 'A', 'Ã' => 'A', 'Å' => 'A', 'Ä' => 'A', 'Æ' => 'AE', 'Ç' => 'C',
                            'É' => 'E', 'È' => 'E', 'Ê' => 'E', 'Ë' => 'E', 'Í' => 'I', 'Ì' => 'I', 'Î' => 'I', 'Ï' => 'I', 'Ð' => 'Eth',
                            'Ñ' => 'N', 'Ó' => 'O', 'Ò' => 'O', 'Ô' => 'O', 'Õ' => 'O', 'Ö' => 'O', 'Ø' => 'O',
                            'Ú' => 'U', 'Ù' => 'U', 'Û' => 'U', 'Ü' => 'U', 'Ý' => 'Y',
                            'á' => 'a', 'à' => 'a', 'â' => 'a', 'ã' => 'a', 'å' => 'a', 'ä' => 'a', 'æ' => 'ae', 'ç' => 'c',
                            'é' => 'e', 'è' => 'e', 'ê' => 'e', 'ë' => 'e', 'í' => 'i', 'ì' => 'i', 'î' => 'i', 'ï' => 'i', 'ð' => 'eth',
                            'ñ' => 'n', 'ó' => 'o', 'ò' => 'o', 'ô' => 'o', 'õ' => 'o', 'ö' => 'o', 'ø' => 'o',
                            'ú' => 'u', 'ù' => 'u', 'û' => 'u', 'ü' => 'u', 'ý' => 'y',
                            'ß' => 'sz', 'þ' => 'thorn', 'ÿ' => 'y'
                        );
                        $index = '';
                        $cantpercolumn = count($allmarcas);
                        $cantpercolumn2 = count($allmarcas);
                        foreach ($allmarcas as $marca):
                            $a = substr(strtr($marca['descripcion'], $normalizeChars), 0, 1);
                            if ($index <> $a) {
                                $cantpercolumn = $cantpercolumn + 3;
                                $index = $a;
                            }
                        endforeach;
                        $cantpercolumn = round($cantpercolumn / 3);
                        $index = '';
                        ?>
                        <div class="body">
                            <div class="header"><h1>Listado de marcas</h1></div>
                            <div class="main">
                                <div class="content-1">
                                    <div class="border2">
                                        <?php
                                        $i = 0;
                                        $j = 0;
                                        while ($i < $cantpercolumn && $j < $cantpercolumn2) {
                                            $a = substr(strtr($allmarcas[$j]['descripcion'], $normalizeChars), 0, 1);
                                            if ($index <> $a) {
                                                echo "<h3>" . strtoupper($a) . "</h3>";
                                                $index = $a;
                                                $i = $i + 3;
                                            }
                                            ?>
                                            <a href="/marca/<?php echo $allmarcas[$j]['idmarca'] ?>"><?php echo strtoupper($allmarcas[$j]['descripcion']) ?></a><br/>
                                            <?php
                                            $i++;
                                            $j++;
                                        }
                                        ?>
                                    </div>
                                </div>
                                <div class="content-2">
                                    <div class="content-2-1">
                                        <div class="border2">
                                            <?php
                                            $i = 0;
                                            while ($i < $cantpercolumn && $j < $cantpercolumn2) {
                                                $a = substr(strtr($allmarcas[$j]['descripcion'], $normalizeChars), 0, 1);
                                                if ($index <> $a) {
                                                    echo "<h3>" . strtoupper($a) . "</h3>";
                                                    $index = $a;
                                                    $i = $i + 3;
                                                }
                                                ?>
                                                <a href="/marca/<?php echo $allmarcas[$j]['idmarca'] ?>"><?php echo strtoupper($allmarcas[$j]['descripcion']) ?></a><br/>
                                                <?php
                                                $i++;
                                                $j++;
                                            }
                                            ?>
                                        </div>
                                    </div>
                                    <div class="content-2-2">
                                        <div class="border2">
                                            <?php
                                            $i = 0;
                                            while ($i < $cantpercolumn && $j < $cantpercolumn2) {
                                                $a = substr(strtr($allmarcas[$j]['descripcion'], $normalizeChars), 0, 1);
                                                if ($index <> $a) {
                                                    echo "<h3>" . strtoupper($a) . "</h3>";
                                                    $index = $a;
                                                    $i = $i + 3;
                                                }
                                                ?>
                                                <a href="/marca/<?php echo $allmarcas[$j]['idmarca'] ?>"><?php echo strtoupper($allmarcas[$j]['descripcion']) ?></a><br/>
                                                <?php
                                                $i++;
                                                $j++;
                                            }
                                            ?>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="footer"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col2">
                <?php $this->load->view('home/acordion'); ?>
            </div>
            <div class="col3">
<!--                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />-->
                <?php foreach ($banners_side as $banner): ?>
                    <img alt="<?php echo $banner['nombre'] ?>" src="<?php echo $banner['url'] ?>" width="150" height="150" />
                <?php endforeach ?>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function() {
        $( "#accordion" ).accordion({ autoHeight: false, active: 1 });
    });
</script>