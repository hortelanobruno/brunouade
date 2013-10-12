/*
 * generic use functions
 * */

function validateForm(formId) {
    var form = dijit.byId(formId);
    return form.validate();
}

function objLength(obj) {
    var len = 0;
    for (var i in obj)
        len++;

    return len;
}

function pad(d) {
    if (parseInt(d + '') < 10)
        return '0' + d;

    return d;
}

//data una data (yyyy-mm-dd)  la ritorna in formato dd/mm/yyyy
function beautifyDate(d) {
    var newd = d.split('-');
    return newd[2] + '/' + newd[1] + '/' + newd[0];
}

// Trasforma un oggetto Date() d nella stringa YYYY-mm-dd
function dateToISO(d) {
    var dd = d.getDate();
    var mm = d.getMonth() + 1;
    var yy = d.getFullYear();

    return yy + "-" + (mm < 10 ? "0" : "") + mm + "-" + (dd < 10 ? "0" : "") + dd;
}

// Trasforma una stringa s con formato YYYY-mm-dd in una data
function ISOToDate(s) {
    return new Date(s);
}

// Trasforma una data nel formato dd/mm/YYYY
function ISOToString(s) {
    return beautifyDate(s);
}

// Cambia una data dal formato gg/mm/YYYY al formato YYYY-mm-dd
function stringToISO(s) {
    return dateToISO(stringToDate(s));
}

// Trasforma una data con formato dd/mm/YYYY in data
function stringToDate(s) {
    var v = s.split("/");
    var s = v[2] + "-" + v[1] + "-" + v[0];
    return new Date(s);
}

// Formatta una data in dd/mm/YYYY
function dateToString(d) {
    return ISOToString(dateToISO(d));
}


function seleccionarFecha(element, fecha) {
    $('.fecha_activa').removeClass('fecha_activa');
    element.addClass('fecha_activa');
    $('.tabla_fecha').hide();
    $('.fecha_' + fecha).show();
}

function submitContacto(){
    document.getElementById("enviar_contacto").submit();
}