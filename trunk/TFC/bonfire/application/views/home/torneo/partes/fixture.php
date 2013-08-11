<style type="text/css">
    .nav_fixture {
        background: none repeat scroll 0 0 #999999;
        height: auto;
        line-height: 20px;
        overflow: auto;
        width: auto;
        border-bottom: 1px solid #FFFFFF;
    }
    .nav_fixture li {
        float: left;
        margin: 0px 0px 0px 10px;
        color: #333333;
        text-decoration: none;
        font-family: 'Conv_DISPAT02',Sans-Serif;
        font-size: 15px;
    }

    .nav_fixture li .active {
        color: #FFFFFF;
    }

    .nav_fixture li a {
        color: #cdcdcd;
        text-decoration: none;
        font-family: 'Conv_DISPAT02',Sans-Serif;
        font-size: 15px;
    }

    .table {
        background: #FFFFFF;
        border-left: 1px solid #d7d7d7;
        border-right: 1px solid #d7d7d7;
    }

    .marginBottom20 {
        margin-bottom: 20px!important;
    }

    .table ._01 {
        width: 100%;
        border-bottom: 1px solid #D7D7D7 !important;
    }

    .table ._01 .first {
        background-color: #cccccc !important;
        height: 20px;
        line-height: 20px;
        cursor: pointer;
    }

    .table ._01 tr {
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #DFDFDF !important;
    }

    .table ._01 .first .par {
        background-image: none;
        padding: 0 0 0 135px;
        cursor: auto;
    }

    .table ._01 .first .fec, .table ._01 .first .fic, .table ._01 .first .est, .table ._01 .first .jue {
        text-align: center;
        padding: 0px 0px 0px 0px;
        cursor: auto;
    }

    .table ._01 .first .fic, .table ._01 .fic, .table ._01 .first .est, .table ._01 .est, .table ._01 .first .jue, .table ._01 .jue, .table ._01 .first .par, .table ._01 .par, .table ._01 .first .pos, .table ._01 tr .pos {
        border-right: 1px solid #DFDFDF;
        border-left: none;
    }

    .table ._01 .first .par, .table ._01 .first .fec, .table ._01 .first .fic, .table ._01 .first .est, .table ._01 .first .jue {
        background-image: none;
    }

    .table ._01 .first .par, .table ._01 .first .fec, .table ._01 .first .fic, .table ._01 .first .est, .table ._01 .first .jue, .table ._01 .first .jug {
        font-family: 'Conv_DISPAT02',Sans-Serif;
        color: #FFF;
        font-size: 13px;
        text-transform: uppercase;
        text-align: left;
        padding: 0px 0px 0px 20px;
    }

    .table ._01 tr .jue, .table ._01 tr .est {
        color: #666666;
        font-size: 10px;
        line-height: 10px;
        padding: 0px 5px;
    }

    .table ._01 .first th {
        background: url("/assets/imgs/SpriteVer_Estadisticas.png") no-repeat right -2200px #cccccc;
    }

    .table ._01 .first .fic, .table ._01 .fic, .table ._01 .first .est, .table ._01 .est, .table ._01 .first .jue, .table ._01 .jue, .table ._01 .first .par, .table ._01 .par, .table ._01 .first .pos, .table ._01 tr .pos {
        border-right: 1px solid #DFDFDF;
        border-left: none;
    }

    .w_50 {
        width: 50px;
    }

    .table ._01 tr {
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #DFDFDF !important;
    }

    .table ._01 tr .esc {
        border-left: none;
        border-right: none;
        line-height: 15px;
    }

    .table ._01 tr td {
        text-align: center;
        border-right: 1px solid #DFDFDF;
        border-bottom: 1px solid #D7D7D7 !important;
        vertical-align: middle;
        color: #666666;
    }

    .w_60 {
        width: 60px;
    }

    .table ._01 tr .loc, .table ._01 tr .vis {
        border-left: medium none;
        border-right: medium none;
        color: #666666;
        font-size: 11px;
        line-height: 15px;
        text-transform: uppercase;
    }

    .w_85 {
        width: 85px;
    }
    .table ._01 tr .res {
        color: #ff6600;
        font-size: 16px;
        font-weight: bold;
        border-left: medium none;
        border-right: medium none;
        line-height: 16px;
    }
    .w_40 {
        width: 40px;
    }
    .table ._01 tr .bar {
        background: url("/assets/imgs/SpriteVer_Estadisticas.png") scroll 30px -420px transparent;
        border-left: medium none;
        border-right: medium none;
    }
    .w_10 {
        width: 10px;
    }
    .table ._01 tr .ext {
        border-right: 1px solid #DFDFDF;
    }
    .w_80 {
        width: 80px;
    }
    .table ._01 tr .jue, .table ._01 tr .est {
        color: #666666;
        font-size: 10px;
        line-height: 10px;
        padding: 0px 5px;
    }
    .table ._01 tr .dark {
        background: url("/assets/imgs/SpriteVer_Estadisticas.png") repeat-x scroll 0 -370px transparent;
    }
    .table ._01 tr .fec {
        color: #ff6600;
        font-size: 11px;
        font-weight: bold;
        line-height: 15px;
        padding: 0px 5px;
        width: 100px\9;
    }
</style>

<?php if ($fixture != null) { ?>
    <div id="_fixture" class="tabsitem" style="display: block;">
        <ul id="tab" class="tabsnav nav_fixture">
            <li>Fechas</li>

            <li><a href="#_1" class="getFechasBTN BTN_40381 active" rel="40381"> 1</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40382" rel="40382"> 2</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40383" rel="40383"> 3</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40384" rel="40384"> 4</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40385" rel="40385"> 5</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40386" rel="40386"> 6</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40387" rel="40387"> 7</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40388" rel="40388"> 8</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40389" rel="40389"> 9</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40390" rel="40390"> 10</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40391" rel="40391"> 11</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40392" rel="40392"> 12</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40393" rel="40393"> 13</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40394" rel="40394"> 14</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40395" rel="40395"> 15</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40396" rel="40396"> 16</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40397" rel="40397"> 17</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40398" rel="40398"> 18</a></li>

            <li><a href="#_1" class="getFechasBTN BTN_40399" rel="40399"> 19</a></li>

        </ul>
        <div id="_1" class="tabsitem">
            <div class="table resultFecha marginBottom20">


                <table class="_01" id=""> 
                    <thead>
                        <tr class="first">
                            <th class="par w_460" colspan="8">PARTIDO</th>
                            <th class="est w_50">SEDE</th>
                            <th class="fec w_60" colspan="2">FECHA</th>
                        </tr>
                    </thead>
                    <tbody>



                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/godoy-cruz/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/132.png" title="Godoy Cruz">-->
                                </a>



                            </td>
                            <td class="loc w_85">Godoy Cruz</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Argentinos</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/argentinos/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/2.png" title="Argentinos">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>

                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>02-08-2013</p>
                                <p>18:00</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/arsenal/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/3.png" title="Arsenal">-->
                                </a>



                            </td>
                            <td class="loc w_85">Arsenal</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Estudiantes</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/estudiantes/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/7.png" title="Estudiantes">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>

                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>02-08-2013</p>
                                <p>20:15</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/all-boys/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/668.png" title="All Boys">-->
                                </a>



                            </td>
                            <td class="loc w_85">All Boys</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Atl. Rafaela</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/atletico-rafaela/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/99.png" title="Atl. Rafaela">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>03-08-2013</p>
                                <p>16:10</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/tigre/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/136.png" title="Tigre">-->
                                </a>



                            </td>
                            <td class="loc w_85">Tigre</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Vélez Sarsfield</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/velez/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/20.png" title="Vélez Sarsfield">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>03-08-2013</p>
                                <p>18:10</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/colon/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/6.png" title="Colón">-->
                                </a>



                            </td>
                            <td class="loc w_85">Colón</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Racing</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/racing/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/16.png" title="Racing">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>03-08-2013</p>
                                <p>20:15</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/san-lorenzo/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/19.png" title="San Lorenzo">-->
                                </a>



                            </td>
                            <td class="loc w_85">San Lorenzo</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Olimpo</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/olimpo/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/14.png" title="Olimpo">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>04-08-2013</p>
                                <p>15:30</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/lanus/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/12.png" title="Lanús">-->
                                </a>



                            </td>
                            <td class="loc w_85">Lanús</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Belgrano</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/belgrano/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/124.png" title="Belgrano">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>04-08-2013</p>
                                <p>16:00</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/gimnasia-y-esgrima/" target="_blank">
                                    <!--<img src="/gimnasia-y-esgrima/Gimnasia-LP_OLEIMA20130718_0080_25.jpg" title="Gimnasia de LP">-->
                                </a>



                            </td>
                            <td class="loc w_85">Gimnasia</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">River</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/river-plate/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/17.png" title="River">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>04-08-2013</p>
                                <p>18:10</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/rosario-central/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/18.png" title="Rosario Central">-->
                                </a>



                            </td>
                            <td class="loc w_85">Rosario Central</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Quilmes</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/quilmes/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/15.png" title="Quilmes">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>

                            <td class="fec w_70 dark">
                                <p>04-08-2013</p>
                                <p>21:30</p>
                            </td>
                        </tr>




                        <tr>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/boca-juniors/" target="_blank">
                                    <!--<img src="/static/OLEOle/images/escudos/escudos_g/png/5.png" title="Boca">-->
                                </a>



                            </td>
                            <td class="loc w_85">Boca</td>
                            <td class="res w_40">- <span class="pen_loc"></span> </td>
                            <td class="bar w_10"></td>
                            <td class="res w_40">- <span class="pen_vis"> </span> </td>
                            <td class="vis w_85">Newell`s</td>
                            <td class="esc w_60">



                                <a href="http://www.ole.com.ar/newells-old-boys/" target="_blank">
                                    <!--<img src="/newells-old-boys/Estadisticas_OLEIMA20130607_0081_26.png" title="Estadisticas">-->
                                </a>



                            </td>
                            <td class="ext w_80">
                            </td>
                            <td class="est w_50">

                                Sazo FC

                            </td>
                            <td class="fec w_70 dark">
                                <p>13-08-2013</p>
                                <p>21:30</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<?php }else{ ?>
    No hay informacion de los partidos
<?php } ?>





