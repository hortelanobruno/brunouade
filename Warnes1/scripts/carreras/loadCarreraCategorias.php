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
        mysql_query("delete from mm_carrera_categoria", $conexion);
        
        $líneas = file('./carrera_categorias');
        foreach ($líneas as $num_línea => $línea) {
            $línea = trim($línea);
            mysql_query("insert into mm_carrera_categoria (nombre) values ('".$línea."')", $conexion);
        }
        
        
        
        mysql_close($conexion);
        echo "Loaded.";
        ?>
    </body>
</html>