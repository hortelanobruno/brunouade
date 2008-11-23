
function comprobarCantAPed(){
	
	var pedir = document.getElementsByName("cantMinAPedir");
	var fab = document.getElementsByName("cantAFab");
	var aux = 0;
	var aux2 = 0;
	for(var i=0 ; i < pedir.length ; i++){
		var pedirValue = pedir[i].value;
		if(pedirValue == ""){
			aux++;
		}else{
			pedirValue = parseInt(pedir[i].value);
		}
		var fabValue = fab[i].value;
		if(fabValue == ""){
			aux++;
		}else{
			fabValue = parseInt(fab[i].value);
		}
		if(fabValue != 0 ){
			if(pedirValue > fabValue){
				fab[i].value = "";
				aux++;
			}else{
				aux2++;
			}
		}
	}
	if((aux == 0)&&(aux2 >0)){
		document.getElementById('msj').style.visibility = 'hidden';
		document.form1.submit();
	}else{
		document.getElementById('msj').innerHTML = "Error en los numeros ingresados";
        document.getElementById('msj').style.visibility = 'visible';
	}
}

function validarEnvio(){
	var stock = document.getElementsByName("stock");
	var aenv = document.getElementsByName("cantidadaenviar");
	var env = document.getElementsByName("cantidadenviada");
	var ped = document.getElementsByName("cantidadpedida");
	var aux = 0;
	var aux2 = 0;
	for(var i=0 ; i < stock.length ; i++){
		var stockValue = parseInt(stock[i].value);
		var aenvValue = aenv[i].value;
		if(aenvValue == ""){
			aux++;
		}else{
			aenvValue = parseInt(aenv[i].value);
		}
		var envValue = parseInt(env[i].value);
		var pedValue = parseInt(ped[i].value);
		if(aenvValue != 0){
			if(pedValue < (envValue+aenvValue)){
				aenv[i].value = "";
				aux++;
			}else if(aenvValue > stockValue){
				aenv[i].value = "";
				aux++;
			}else{
				aux2++;
			}
		}
	}
	if((aux == 0)&&(aux2 >0)){
		document.getElementById('msj').style.visibility = 'hidden';
		document.form1.submit();
	}else{
		document.getElementById('msj').innerHTML = "Error en los numeros ingresados";
        document.getElementById('msj').style.visibility = 'visible';
	}
}