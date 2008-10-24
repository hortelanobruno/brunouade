function comprobarCantAPed(){
	alert("ComprobandoTextField");
	var pedir = document.getElementsByName("cantMinAPedir");
	var fab = document.getElementsByName("cantAFab");
	for(var i=0 ; i < pedir.length ; i++){
		var pedirValue = pedir[i].value;
		var fabValue = fab[i].value;
		if(pedirValue > fabValue){
			fab[i].value = "";
		}
		
	}
}
