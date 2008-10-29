var req;
var target;
var isIE;

function initRequest(url) {
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function comprobarCantAPed(){
	
	var pedir = document.getElementsByName("cantMinAPedir");
	var fab = document.getElementsByName("cantAFab");
	for(var i=0 ; i < pedir.length ; i++){
		var pedirValue = pedir[i].value;
		var fabValue = fab[i].value;
		alert("ComprobandoTextField: "+pedirValue+" "+fabValue);
		if(pedirValue > fabValue){
			fab[i].value = "";
		}
	}
}

function fillSolDis(){
	var value = this.form1.idsoldis.value 
    var url = "cargarsoldis?codigo=" + value; 
    initRequest(url);
    req.onreadystatechange = fillSolDisRequest;
    req.open("POST", url, true); 
    req.send(null);
}

function fillSolDisRequest(){
	if (req.readyState == 4) {
        if (req.status == 200) {
        	var estado = req.responseXML.getElementsByTagName("estado")[0].childNodes[0].nodeValue;
        	if(estado == 'lleno'){
        		var div = document.getElementById("tablaDatos");
        		var hidden = "";
        		var datos = "<table width='100%' border='1' cellpadding='1' cellspacing='0' bordercolor='#4D6FAC'>"
                datos += "<tr><td align='center'>Codigo</td><td align='center'>Descripcion</td><td align='center'>Cantidad Pedida</td><td align='center'>Cantidad Reservada</td><td align='center'>Stock</td><td align='center'>Cantidad Enviada</td><td align='center'>Cantidad a Enviar</td></tr>";
        		var articulo = req.responseXML.getElementsByTagName("articulo");
        		for(i=0 ; i < articulo.length ; i++){
        			var nodes = articulo[i].childNodes;
        			var codigo = nodes[0].childNodes[0].nodeValue;
        			var descripcion = nodes[1].childNodes[0].nodeValue;
        			var cantidadpedida = nodes[2].childNodes[0].nodeValue;
        			var cantidadreservada = nodes[3].childNodes[0].nodeValue;
        			var stock = nodes[4].childNodes[0].nodeValue;
        			var cantidadenviada = nodes[5].childNodes[0].nodeValue;
        			var cantidadaenviar = 0;
        			datos += "<tr><td align='center'>"+codigo+"</td><td align='center'>"+descripcion+"</td><td align='center'>"+cantidadpedida+"</td><td align='center'>"+cantidadreservada+"</td><td align='center'>"+stock+"</td><td>"+cantidadenviada+"</td><td align='center'><input id='"+i+"' name='cantidadaenviar' type='text' value='"+cantidadaenviar+"' /></td></tr>";
        			hidden += "<input type='hidden' name='codigo' value='"+codigo+"' /><input type='hidden' name='descripcion' value='"+descripcion+"' /><input type='hidden' name='cantidadpedida' value='"+cantidadpedida+"' /><input type='hidden' name='cantidadreservada' value='"+cantidadreservada+"' /><input type='hidden' name='stock' value='"+stock+"' /><input type='hidden' name='cantidadenviada' value='"+cantidadenviada+"' />";
        		}
        		datos += "</table>";
                div.innerHTML = datos+hidden;
        	}
        }
      }
}