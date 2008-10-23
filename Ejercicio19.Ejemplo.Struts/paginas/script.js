function llamarAjax(){
	//Funca para un solo elemento
	
	//var title = document.getElementById("title1");
	//alert(title.innerHTML);
	
	
	
	var title = document.getElementsByName("title");
	for(var i=0 ; i < title.length ; i++){
		alert(title[i].innerHTML);
	}
	
	
	
}