<link rel="stylesheet" type="text/css" href="/assets/css/jquery-ui-1.8.13.custom.css" />
<!-- stile aggiunto per sfondo menu -->
<style type="text/css">
    .pager {
        text-align: center;
        padding: 6px 0;
    }

    .pager a {
        border: 1px solid #bcbcbc;
        padding: 0 3px;
        margin-right: 3px;
    }

    .pager a:hover {
        background: url(/assets/imgs/table/header_shadow.png) repeat-x scroll center top #E5EDF4;
    }

    .pager a.selected {
        background: #ccc;
        color: #fefefe;
    }

    .ui-widget-header {
        background:#333333;
    }

    span.sep {
        margin-right: 3px;
    }
</style>

<!-- scripts generici -->
<script>
    $(document).ready(function() {
        $("#tabs").tabs();
    });
</script>
<!-- scripts -->
<div id="tabs" class="mt10 pb10">
    <div class="ml20">
        <ul class="w200 fleft bggray">
            <li><a href="#tabs-0">Informacion general</a></li>
            <li><a href="#tabs-1">Noticias</a></li>
            <li><a href="#tabs-2">Reglamento</a></li>
            <li><a href="#tabs-3">Equipos</a></li>
            <li><a href="#tabs-4">Fixture</a></li>
            <li><a href="#tabs-5">Tabla de posiciones</a></li>
            <li><a href="#tabs-6">Goleadores</a></li>
            <li><a href="#tabs-7">Valla menos vencida</a></li>
            <li><a href="#tabs-8">Amonestaciones</a></li>
        </ul>
    </div>
    <div id="tabs-0" class="clearfix tournament-home">
        <fieldset class="fleft w700">
            <label>Informacion general</label>
            <table class="uiGrid mvm" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td class="vTop"><?php echo $torneo['informaciongeneral'] ?></td>
                    </tr>
                </tbody>
            </table>							</fieldset>

        <div class="clearfix"></div>
    </div>	
</div>