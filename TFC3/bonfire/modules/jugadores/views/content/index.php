<div id="importarJugadoresBox" style="display: none">
    <h3>Importar Jugadores</h3>
    <div>
        <?php
        $attributes = array('id' => 'formimport', 'name' => 'formimport');
        echo form_open($this->uri->uri_string(), $attributes);
        ?>
        <input type="hidden" id="importcontent" name="importcontent" value="222" />
        <?php echo form_close(); ?>
        <!--<input type="file" id="files" name="file" />-->
        <input type="file" id="files" name="files" />
        <script>
            /*
             CSV Reader
             Author:  Tudor Nita | cgrats.com
             Version: 0.2
             
             Brief:  Singleton pattern
             Brief:  Reads-in a comma separated file.
             Brief:  Does not care what the data will be used for. 
             Brief:  Stores every line ( and all the line's members ) in the dataArray as a single entry
             Brief:  Automatically casts numerical values
             
             Get:    if(csvReader.ready) x = fileData[y][z];
             
             Bugs:   Does not work in CHROME
             Bugs:   Not completely csv compatible as it's built for my own specific needs and not the official specs
             
             csv format should look like:
             # comment line - disregarded by the parser
             value,value,value,value\n
             OR
             value;value;value;value\n
             
             */
            csvReader = new function() {

                var me = this;              // pointer to ourselves
                me.ready = false;           // are we done with reading the file ?
                me.debug = true;            // are we in debug mode ?
                me.fileData = new Array();  // line data array

                /* read file - event callback from the file input box */
                me.readFileEvent = function(event) {

                    me.fileData.length = 0; // make sure the array is clear
                    me.ready = false;       // set ready to false

                    // the files, reader and string handler
                    var files = event.target.files;
                    var reader = new FileReader();
                    var str;

                    // parse the file - reader is async
                    reader.onload = function(theFile) {


                        str = theFile.target.result;            // load file values
                        var lines = str.split(/[\r\n|\n]+/);    // split data by line

                        // for every line, remove formatting characters
                        for (i = 0; i < lines.length; i++) {

                            lines[i] = lines[i].replace(/(\r\n|\n|\r|)/gm, "").split(/[,;]+/);          // remove formatting and split by comma OR semi colon
                            lines[i] = lines[i].filter(function(x) {
                                if (x != "")
                                    return true;
                            });     // filter out null members

                            //if it isn't a comment line
                            if (lines[i][0][0] != "#") {

                                // cast all members to correct type 
                                for (x = 0; x < lines[i].length; x++) {
                                    // try float
                                    var result = parseFloat(lines[i][x]);

                                    // check if cast ok and set value
                                    if (!isNaN(result))
                                        lines[i][x] = result;
                                }
                                // push line 
                                me.fileData.push(lines[i]);
                            }
                        }
                        // done, set ready
                        me.ready = true;
                        // if in debug, dump data to console
                        me.consoleDump();
                    }
                    reader.onerror = function() {
                        console.log('Error reading file');
                    }       // error message    
                    reader.readAsText(files[0],'ISO-8859-1')                                             // start reading the text, async
                }

                /* dump the file data to the console */
                me.consoleDump = function() {

                    if (!me.ready || !me.debug)
                        return null;
                    var end = me.fileData.length;
//                    for (i = 0; i < end; i++)
//                        console.log(me.fileData[i]);
                    
                    $('#importcontent').val(me.fileData.toString());
                    document.getElementById("formimport").submit();
                }
            }

            // set up an event for the file <input> element
            document.getElementById('files').addEventListener('change', csvReader.readFileEvent, false);
        </script>
    </div>
</div>
        <?php if (isset($resultimport)) : ?>
    <div>Resultado: <?php echo $resultimport ?></div>
        <?php endif; ?>
<br/>
<div class="admin-box">
    <div> Torneo
            <?php
            $attributes = array('id' => 'formtorneo', 'name' => 'formtorneo');
            echo form_open($this->uri->uri_string(), $attributes);
            ?>
        <select id="equipo" name="equipo" onchange="return changeTorneoAdmPartidos()">
            <?php foreach ($equipos as $equipo): ?>
                <?php if ($equipo['id'] === $equiposelected) : ?>
                    <option value="<?php echo $equipo['id'] ?>" selected><?php echo $equipo['nombre'] ?></option>
    <?php else : ?>
                    <option value="<?php echo $equipo['id'] ?>"><?php echo $equipo['nombre'] ?></option>
        <?php endif; ?>
<?php endforeach ?>
        </select>
    </div>
    <h3>Jugadores</h3>
                <?php echo form_open($this->uri->uri_string()); ?>
    <table class="table table-striped">
        <thead>
            <tr>
<?php if ($this->auth->has_permission('Jugadores.Content.Delete') && isset($records) && is_array($records) && count($records)) : ?>
                    <th class="column-check"><input class="check-all" type="checkbox" /></th>
<?php endif; ?>

                <th>Nombre Completo</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Delegado</th>
                <th>Equipo</th>
                <th>Cantidad Tarjetas Amarillas</th>
                <th>Cantidad Partidos Jugados</th>
                <th>Cantidad Tarjetas Rojas</th>
                <th>Cantidad Goles</th>
            </tr>
        </thead>
                    <?php if (isset($records) && is_array($records) && count($records)) : ?>
            <tfoot>
    <?php if ($this->auth->has_permission('Jugadores.Content.Delete')) : ?>
                    <tr>
                        <td colspan="10">
                <?php echo lang('bf_with_selected') ?>
                            <input type="submit" name="delete" id="delete-me" class="btn btn-danger" value="<?php echo lang('bf_action_delete') ?>" onclick="return confirm('<?php e(js_escape(lang('jugadores_delete_confirm'))); ?>')">
                        </td>
                    </tr>
                <?php endif; ?>
            </tfoot>
                <?php endif; ?>
        <tbody>
                <?php if (isset($records) && is_array($records) && count($records)) : ?>
                    <?php foreach ($records as $record) : ?>
                    <tr>
                        <?php if ($this->auth->has_permission('Jugadores.Content.Delete')) : ?>
                            <td><input type="checkbox" name="checked[]" value="<?php echo $record->id ?>" /></td>
                        <?php endif; ?>

        <?php if ($this->auth->has_permission('Jugadores.Content.Edit')) : ?>
                            <td><?php echo anchor(SITE_AREA . '/content/jugadores/edit/' . $record->id, '<i class="icon-pencil">&nbsp;</i>' . $record->nombre_completo) ?></td>
        <?php else: ?>
                            <td><?php e($record->nombre_completo) ?></td>
        <?php endif; ?>

                        <td><?php e($record->nombre) ?></td>
                        <td><?php e($record->apellido) ?></td>
                        <td><?php e($record->delegado) ?></td>
                        <td><?php e(getEquipo($equipos, $record->idequipo)) ?></td>
                        <td><?php e($record->cantidad_tarjetas_amarillas) ?></td>
                        <td><?php e($record->cantidad_partidos_jugados) ?></td>
                        <td><?php e($record->cantidad_tarjetas_rojas) ?></td>
                        <td><?php e($record->cantidad_goles) ?></td>
                    </tr>
                <?php endforeach; ?>
<?php else: ?>
                <tr>
                    <td colspan="10">No records found that match your selection.</td>
                </tr>
<?php endif; ?>
        </tbody>
    </table>
<?php echo form_close(); ?>
</div>

<?php

function getEquipo($equipo, $idequipo) {
    if (isset($equipo)) {
        foreach ($equipo as $equipo) {
            if ($equipo['id'] === $idequipo)
                return $equipo['nombre'];
        }
    }else {
        return $idequipo;
    }
}
?>