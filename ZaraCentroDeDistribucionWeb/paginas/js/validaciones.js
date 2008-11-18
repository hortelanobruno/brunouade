
function comprobarCantAPed(){
	
	var pedir = document.getElementsByName("cantMinAPedir");
	var fab = document.getElementsByName("cantAFab");
	var aux = 0;
	for(var i=0 ; i < pedir.length ; i++){
		var pedirValue = pedir[i].value;
		var fabValue = fab[i].value;
		if(pedirValue > fabValue){
			fab[i].value = "";
			aux++;
		}
	}
	if(aux == 0){
		document.getElementById('msj').style.visibility = 'hidden';
		document.form1.submit();
	}else{
		document.getElementById('msj').innerHTML = "Error en los numeros ingresados";
        document.getElementById('msj').style.visibility = 'visible';
	}
}