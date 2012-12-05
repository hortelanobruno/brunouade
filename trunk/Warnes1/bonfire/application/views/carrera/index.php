<div class="colmask threecol">
    <div class="colmid">
        <div class="colleft">
            <div class="col7">
                <div id="contentRight">﻿
                    <!-- HTML File /modules/calendar/views/calendar.htm -->
                    <div id="calendar">
                        <div id="radiousTop"> 
<!--                            <img src="/images/title_calendario.jpg" id="title">-->

                            <!-- HTML File /vistas/include/tabs/tabs_white.htm -->
                            <div id="tabswhite">
<!--                                <input id="tabs_calendar" value="1" type="hidden">
                                <img id="imgCatSeleccionada_calendar" name="imgCatSeleccionada_calendar" src="/images/seccion_tc.jpg" border="0" class="titleSeccion">-->
                                <!--Calendario--> 
                                <!--MENU-->
                                <div class="menuTab">
                                    <label>Categoria</label>
                                    <select id="selanio" name="selanio" onchange="location = '/carreras/categoria/'+this.options[this.selectedIndex].value">
                                        <?php foreach ($categorias as $categoria): ?>
                                            <?php if ($categoria_sel == $categoria['idcarrera_categoria']) { ?>
                                                <option value="<?php echo $categoria['idcarrera_categoria'] ?>" selected=""><?php echo $categoria['nombre'] ?></option>
                                            <?php } else { ?>
                                                <option value="<?php echo $categoria['idcarrera_categoria'] ?>"><?php echo $categoria['nombre'] ?></option>
                                            <?php } ?>
                                        <?php endforeach ?>
                                    </select>
                                </div>
                                <!-- FIN MENU-->
                            </div>
                        </div>
                        <div id="radiousBottom">
                            <div id="filters">
                                <label>Año</label>
                                <select id="selanio" name="selanio" onchange="location = '/carreras/calendario/'+this.options[this.selectedIndex].value+'/'+<?php echo $categoria_sel ?>">
                                    <?php foreach ($calendario as $cal): ?>
                                        <?php if ($año_sel == $cal['año']) { ?>
                                            <option value="<?php echo $cal['año'] ?>" selected=""><?php echo $cal['año'] ?></option>
                                        <?php } else { ?>
                                            <option value="<?php echo $cal['año'] ?>" ><?php echo $cal['año'] ?></option>
                                        <?php } ?>

                                    <?php endforeach ?>
                                </select>
                            </div>
                            <!-- HTML File /modules/calendar/views/list.htm -->
                            <input type="hidden" id="idCategoria" name="idCategoria" value="1">
                            <!--Turismo Carretera-->
                            <div id="break"><h2>Calendario: <?php echo $año_sel ?></h2></div>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tableCalendar">
                                <tbody><tr class="bg-a">
                                        <td width="109" height="20" align="center">Nº</td>
                                        <td width="81">Fecha</td>
                                        <td width="115">Lugar</td>
                                        <td width="278">Circuito</td>
                                        <td width="375" align="left">Podio Anterior</td>
                                        <td width="334" align="left">Podio Actual</td>
                                        <td width="121" align="center"></td>
                                    </tr>
                                    <?php foreach ($carreras as $carrera): ?>
                                        <tr class="bg-b">
                                            <td align="center" class="numerostabla"><?php echo $carrera['numero_carrera'] ?></td>
                                            <td><strong><?php echo $carrera['fecha_carrera'] ?></strong></td>
                                            <td><?php echo $carrera['lugar'] ?></td>
                                            <td width="278"><?php echo $carrera['circuito'] ?></td>
                                            <td><ul style="	list-style:none;margin:0;padding:0;">

                                                    <li style="margin:0 5px;"><?php echo $carrera['podio_anterior_1'] ?></li>

                                                    <li style="margin:0 5px;"><?php echo $carrera['podio_anterior_2'] ?></li>

                                                    <li style="margin:0 5px;"><?php echo $carrera['podio_anterior_3'] ?></li>

                                                </ul></td>
                                            <td><ul style="	list-style:none;margin:0;padding:0;">

                                                    <li style="margin:0 5px;"><?php echo $carrera['podio_actual_1'] ?></li>

                                                    <li style="margin:0 5px;"><?php echo $carrera['podio_actual_2'] ?></li>

                                                    <li style="margin:0 5px;"><?php echo $carrera['podio_actual_3'] ?></li>

                                                </ul></td>
                                            <td>

                                            </td>
                                        </tr>
                                    <?php endforeach ?>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col8">
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
            </div>
            <div class="col9">
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
                <img alt="publicidad" src="/images/imageshome/muestraavisopublic.jpg" width="150" height="150" />
            </div>
        </div>
    </div>
</div>