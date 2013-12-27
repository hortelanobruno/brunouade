function initBrunoli() {
	$.ajax({
		url : "brunoli.ajax",
		context : document.body
	}).done(function(datos) {
		$("#nombre").text(datos.nombre);
	});

}