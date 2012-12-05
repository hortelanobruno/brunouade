<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8">
            <title>Problema</title>
    </head>
    <body>
        <?php
        set_time_limit(0);
        $conexion = mysql_connect("localhost", "root", "");
        mysql_select_db("mundomotor", $conexion);
        mysql_query("SET NAMES 'utf8'");
        //Rubros
        mysql_query("delete from mm_clientes", $conexion);
        mysql_query("delete from mm_cliente_rubro", $conexion);

        $líneas = file('./clientes2');
        foreach ($líneas as $num_línea => $línea) {
            echo utf8_encode($línea) . "<br/>";
            $myArray = explode(',', $línea);
            $result = mysql_query("SELECT * FROM mm_clientes where nombre like '" . trim($myArray[0]) . "'");
            if (mysql_num_rows($result) == 0) {
                mysql_query("insert into mm_clientes(nombre,direccion,telefonodeltrabajo,habilitado) values ('" . trim($myArray[0]) . "','" . trim($myArray[1]) . "','" . trim($myArray[2]) . "',false)", $conexion);
                $idcliente = mysql_insert_id();
                if (strpos(trim($myArray[3]), '-') !== false) {
                    $rubros = explode('-', trim($myArray[3]));
                    foreach ($rubros as $num_rubro => $rubro) {
                        mysql_query("insert into mm_cliente_rubro(idcliente,idrubro) values (" . $idcliente . ",(select idrubro from mm_rubros where descripcion like '" . strtoupper(trim($rubro)) . "'))", $conexion);
                    }
                } else {
                    mysql_query("insert into mm_cliente_rubro(idcliente,idrubro) values (" . $idcliente . ",(select idrubro from mm_rubros where descripcion like '" . strtoupper(trim($myArray[3])) . "'))", $conexion);
                }
            }
        }
        mysql_close($conexion);
        echo "Loaded.";
        ?>
    </body>
</html>