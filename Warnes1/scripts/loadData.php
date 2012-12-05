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
        //Rubros
        mysql_query("delete from mm_rubros", $conexion);
        
        $líneas = file('./rubros');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_rubros(descripcion,habilitado,prioridad) values ('".$myArray[0]."',true,".$myArray[1].")", $conexion);
        }
        
        //Marcas
        mysql_query("delete from mm_marcas", $conexion);
        
        $líneas = file('./marcas');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_marcas(descripcion,habilitado,prioridad) values ('".$myArray[0]."',true,".$myArray[1].")", $conexion);
        }
        
        //Zonas
        mysql_query("delete from mm_zonas", $conexion);
        
        $líneas = file('./zonas');
        foreach ($líneas as $num_línea => $línea) {
            $myArray = explode(',', $línea);
            mysql_query("insert into mm_zonas(descripcion,habilitado,prioridad) values ('".$myArray[0]."',true,".$myArray[1].")", $conexion);
        }

        
        mysql_close($conexion);
        echo "Loaded.";
        ?>
    </body>
</html>