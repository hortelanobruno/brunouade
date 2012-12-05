<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title>Problema</title>
    </head>
    <body>
        <?php
        $conexion = mysql_connect("localhost", "root", "");
        mysql_select_db("mundomotor", $conexion);
        mysql_query("SET NAMES 'utf8'");
        //TC
        echo "Loading... TC<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'TC')", $conexion);

        $líneas = file('./tc');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
//            $as = explode('.', trim($myArray[1]));
//            $bb = $as[2]."-".$as[1]."-".$as[0];
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,podio_anterior_1,podio_anterior_2,podio_anterior_3,podio_actual_1,podio_actual_2,podio_actual_3,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'TC')," . trim($myArray[0]) . ",'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[3]) . "','" . trim($myArray[4]) . "','" . trim($myArray[5]) . "','" . trim($myArray[6]) . "','" . trim($myArray[7]) . "','" . trim($myArray[8]) . "','" . trim($myArray[8]) . "',2012)", $conexion);
        }
        echo "TC Loaded.<br/>";
        //TCM
        echo "Loading... TCM<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'TCM')", $conexion);

        $líneas = file('./tcm');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
//            $as = explode('.', trim($myArray[1]));
//            $bb = "20".$as[2]."-".$as[1]."-".$as[0];
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,podio_anterior_1,podio_anterior_2,podio_anterior_3,podio_actual_1,podio_actual_2,podio_actual_3,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'TCM')," . trim($myArray[0]) . ",'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[3]) . "','" . trim($myArray[4]) . "','" . trim($myArray[5]) . "','" . trim($myArray[6]) . "','" . trim($myArray[7]) . "','" . trim($myArray[8]) . "','" . trim($myArray[8]) . "',2012)", $conexion);
        }
        echo "TCP Loaded.<br/>";
        //TCP
        echo "Loading... TCP<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'TCP')", $conexion);

        $líneas = file('./tcp');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
//            $as = explode('.', trim($myArray[1]));
//            $bb = "20".$as[2]."-".$as[1]."-".$as[0];
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,podio_anterior_1,podio_anterior_2,podio_anterior_3,podio_actual_1,podio_actual_2,podio_actual_3,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'TCP')," . trim($myArray[0]) . ",'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[3]) . "','" . trim($myArray[4]) . "','" . trim($myArray[5]) . "','" . trim($myArray[6]) . "','" . trim($myArray[7]) . "','" . trim($myArray[8]) . "','" . trim($myArray[8]) . "',2012)", $conexion);
        }
        echo "TCPM Loaded.<br/>";
        //TCPM
        echo "Loading... TCPM<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'TCPM')", $conexion);

        $líneas = file('./tcpm');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
//            $as = explode('.', trim($myArray[1]));
//            $bb = "20".$as[2]."-".$as[1]."-".$as[0];
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,podio_anterior_1,podio_anterior_2,podio_anterior_3,podio_actual_1,podio_actual_2,podio_actual_3,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'TCPM')," . trim($myArray[0]) . ",'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[3]) . "','" . trim($myArray[4]) . "','" . trim($myArray[5]) . "','" . trim($myArray[6]) . "','" . trim($myArray[7]) . "','" . trim($myArray[8]) . "','" . trim($myArray[8]) . "',2012)", $conexion);
        }
        echo "TCPM Loaded.<br/>";
        //DTM
        echo "Loading... DTM<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'DTM')", $conexion);

        $líneas = file('./DTM');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'DTM'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . ", " . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "DTM Loaded.<br/>";
        //F1
        echo "Loading... F1<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia formula 1 world championship')", $conexion);

        $líneas = file('./F1');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia formula 1 world championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "F1 Loaded.<br/>";
        //abarth500rallytrophy
        echo "Loading... abarth500rallytrophy<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Abarth 500 rally trophy')", $conexion);

        $líneas = file('./abarth500rallytrophy');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Abarth 500 rally trophy'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "abarth500rallytrophy Loaded.<br/>";
        //abarth500rallytrophy
        echo "Loading... autoGP<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Auto GP')", $conexion);

        $líneas = file('./autoGP');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Auto GP'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "autoGP Loaded.<br/>";
        //bossGP
        echo "Loading... bossGP<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Boss GP')", $conexion);

        $líneas = file('./bossGP');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Boss GP'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "bossGP Loaded.<br/>";

        //europeanlemansseries
        echo "Loading... europeanlemansseries<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'European Le Mans Series')", $conexion);

        $líneas = file('./europeanlemansseries');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'European Le Mans Series'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "europeanlemansseries Loaded.<br/>";

        //ferrarichallengetrofeopirelliasiapacific
        echo "Loading... ferrarichallengetrofeopirelliasiapacific<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Ferrari Challenge trofeo  Pirelli-Asia Pacific')", $conexion);

        $líneas = file('./ferrarichallengetrofeopirelliasiapacific');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Ferrari Challenge trofeo  Pirelli-Asia Pacific'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . ", " . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "ferrarichallengetrofeopirelliasiapacific Loaded.<br/>";
        
        //ferrarichallengetrofeopirelliasiapacific
        echo "Loading... ferrarichallengetrofeopirellieurope<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Ferrari challenge trofeo Pirelli-Europe')", $conexion);

        $líneas = file('./ferrarichallengetrofeopirellieurope');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Ferrari challenge trofeo Pirelli-Europe'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . ", " . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "ferrarichallengetrofeopirellieurope Loaded.<br/>";

        //fiaGT1worldchampionship
        echo "Loading... fiaGT1worldchampionship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia GT1 world championship')", $conexion);

        $líneas = file('./fiaGT1worldchampionship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia GT1 world championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaGT1worldchampionship Loaded.<br/>";
        
        //fiaGT3europeanchampionship
        echo "Loading... fiaGT3europeanchampionship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia GT3 European championship')", $conexion);

        $líneas = file('./fiaGT3europeanchampionship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia GT3 European championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaGT3europeanchampionship Loaded.<br/>";
        
        //fiaalternativeenergiescup
        echo "Loading... fiaalternativeenergiescup<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia Alternative energies cup')", $conexion);

        $líneas = file('./fiaalternativeenergiescup');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia Alternative energies cup'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaalternativeenergiescup Loaded.<br/>";
        
        //fiaformula2championship
        echo "Loading... fiaformula2championship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia formula 2 championship')", $conexion);

        $líneas = file('./fiaformula2championship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia formula 2 championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaformula2championship Loaded.<br/>";
        
        //fiaproductioncarworldrallychampionship
        echo "Loading... fiaproductioncarworldrallychampionship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia production car world rally championship(pwrc)')", $conexion);

        $líneas = file('./fiaproductioncarworldrallychampionship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia production car world rally championship(pwrc)'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaproductioncarworldrallychampionship Loaded.<br/>";
        
        //fiaworldendurancechampionship
        echo "Loading... fiaworldendurancechampionship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia world endurance championship')", $conexion);

        $líneas = file('./fiaworldendurancechampionship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia world endurance championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaworldendurancechampionship Loaded.<br/>";
        
        //fiaworldrallychampionship
        echo "Loading... fiaworldrallychampionship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia world Rally championship')", $conexion);

        $líneas = file('./fiaworldrallychampionship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia world Rally championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaworldrallychampionship Loaded.<br/>";
        
        //fiaworldtouringcarchampionship
        echo "Loading... fiaworldtouringcarchampionship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia world touring car championship')", $conexion);

        $líneas = file('./fiaworldtouringcarchampionship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Fia world touring car championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "fiaworldtouringcarchampionship Loaded.<br/>";
        
        //internationalV8supercarschampionship
        echo "Loading... internationalV8supercarschampionship<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'International v8 supercars championship')", $conexion);

        $líneas = file('./internationalV8supercarschampionship');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'International v8 supercars championship'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "internationalV8supercarschampionship Loaded.<br/>";
        
        //lamborghiniblancpainsupertrofeo
        echo "Loading... lamborghiniblancpainsupertrofeo<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Lamborghini blancpain super trofeo')", $conexion);

        $líneas = file('./lamborghiniblancpainsupertrofeo');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Lamborghini blancpain super trofeo'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "lamborghiniblancpainsupertrofeo Loaded.<br/>";
        
        //lamborghiniblancpainsupertrofeoasia
        echo "Loading... lamborghiniblancpainsupertrofeoasia<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Lamborghini Blancpain super trofeo Asia')", $conexion);

        $líneas = file('./lamborghiniblancpainsupertrofeoasia');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Lamborghini Blancpain super trofeo Asia'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "lamborghiniblancpainsupertrofeoasia Loaded.<br/>";
        
        //maseratitrofeogranturismoMC
        echo "Loading... maseratitrofeogranturismoMC<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Maserati Trofeo  granturismo MC')", $conexion);

        $líneas = file('./maseratitrofeogranturismoMC');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Maserati Trofeo  granturismo MC'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "maseratitrofeogranturismoMC Loaded.<br/>";
        
        //porschecarreracupasi
        echo "Loading... porschecarreracupasi<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Porsche carrera cup Asia')", $conexion);

        $líneas = file('./porschecarreracupasi');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Porsche carrera cup Asia'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "porschecarreracupasi Loaded.<br/>";
        
        //porschecarreracupdeutschland
        echo "Loading... porschecarreracupdeutschland<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Porsche carrera cup deutschland')", $conexion);

        $líneas = file('./porschecarreracupdeutschland');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Porsche carrera cup deutschland'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "porschecarreracupdeutschland Loaded.<br/>";
        
        //porschemobil1supercup
        echo "Loading... porschemobil1supercup<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Porsche mobil 1 supercup')", $conexion);

        $líneas = file('./porschemobil1supercup');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Porsche mobil 1 supercup'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "porschemobil1supercup Loaded.<br/>";
        
        //tc2000
        echo "Loading... tc2000<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'TC2000')", $conexion);

        $líneas = file('./tc2000');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'TC2000'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "tc2000 Loaded.<br/>";
        
        //toprace FALTA ACA
        echo "Loading... toprace<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Top Race')", $conexion);

        $líneas = file('./toprace');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Top Race'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[2]) . "','" . trim($myArray[1]) . "',2012)", $conexion);
        }
        echo "toprace Loaded.<br/>";
        
        //trofeoabarth500europe
        echo "Loading... trofeoabarth500europe<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Trofeo Abarth 500 Europe')", $conexion);

        $líneas = file('./trofeoabarth500europe');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Trofeo Abarth 500 Europe'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "trofeoabarth500europe Loaded.<br/>";
        
        //turismonacionalclase2 FALTA ACA
        echo "Loading... turismonacionalclase2<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Turismo Nacional Clase 2')", $conexion);

        $líneas = file('./turismonacionalclase2');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            if(count($myArray) > 3){
                mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,podio_actual_1,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Turismo Nacional Clase 2'),".$num_línea."+1,'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[2]) . "','" . trim($myArray[3]) . "',2012)", $conexion);
            }else{
                mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Turismo Nacional Clase 2'),".$num_línea."+1,'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
            }
        }
        echo "turismonacionalclase2 Loaded.<br/>";
        
        //turismonacionalclase3 FALTA ACA
        echo "Loading... turismonacionalclase3<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Turismo Nacional clase 3')", $conexion);

        $líneas = file('./turismonacionalclase3');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            if(count($myArray) > 3){
                mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,podio_actual_1,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Turismo Nacional clase 3'),".$num_línea."+1,'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[2]) . "','" . trim($myArray[3]) . "',2012)", $conexion);
            }else{
                mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Turismo Nacional clase 3'),".$num_línea."+1,'" . trim($myArray[1]) . "','" . trim($myArray[2]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
            }
        }
        echo "turismonacionalclase3 Loaded.<br/>";
        
        //volkswagensciroccoRcup
        echo "Loading... volkswagensciroccoRcup<br/>";
        mysql_query("delete from mm_carreras where idcarrera_categoria = (select idcarrera_categoria from mm_carrera_categoria where nombre = 'Volkswagen scirocco  R-Cup')", $conexion);

        $líneas = file('./volkswagensciroccoRcup');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_carreras (idcarrera_categoria,numero_carrera,fecha_carrera,lugar,circuito,anio) values ((select idcarrera_categoria from mm_carrera_categoria where nombre = 'Volkswagen scirocco  R-Cup'),".$num_línea."+1,'" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',2012)", $conexion);
        }
        echo "volkswagensciroccoRcup Loaded.<br/>";


        mysql_close($conexion);
        echo "All Loaded.";
        ?>
    </body>
</html>