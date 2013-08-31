/* Nofication Close Buttons */
$('.notification a.close').click(function(e) {
    e.preventDefault();

    $(this).parent('.notification').fadeOut();
});

/*
 Check All Feature
 */
$(".check-all").click(function() {
    $("table input[type=checkbox]").attr('checked', $(this).is(':checked'));
});

/*
 Dropdowns
 */
$('.dropdown-toggle').dropdown();

/*
 Set focus on the first form field
 */
$(":input:visible:first").focus();

/*
 Responsive Navigation
 */
$('.collapse').collapse();

/*
 Prevent elements classed with "no-link" from linking
 */
//$(".no-link").click(function(e){ e.preventDefault();	});
function changeTiposTorneos() {
    document.getElementById("formtiposdetorneos").submit();
}


function sortearFixture() {
    //Obtengo equipos elegidos
    var e = document.getElementById("equipoelegidos");
    var options = e && e.options;
    var opt;
    var result = [];
    for (var i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];

        if (opt.selected) {
            result.push(opt.value);
        }
    }
    alert(result);

    //Obtengo categoria: 1 grupo 2 llave 3 grupo y llave
    var e = document.getElementById("administrar_torneos_categoria");
    var categoria = e.options[e.selectedIndex].value;
    alert(categoria);
    //Sorteo fixture


    var teams = result.length;
    var ghost = false;
    if (teams % 2 === 1) {
        teams++;
        ghost = true;
    }
    alert(teams);
    alert(ghost);
}