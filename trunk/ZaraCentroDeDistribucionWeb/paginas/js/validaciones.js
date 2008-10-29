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
	alert("Entro");
	var value = document.getElementByName("select1"); 
	var aux = document.getElementById("capo");
	alert("Nuevo valor"+aux);
	var text = document.form1.select1.options[document.form1.select1.selectedIndex].text;
    var url = "cargarsoldis?id=" + value; 
    alert(value);
    alert("Text "+text);
    alert("SelectedIndex "+document.form1.select1.selectedIndex);
    
    // Invoke initRequest(url) to create XMLHttpRequest object
    //initRequest(url);

    // The "processRequest" function is set as a callback function.
    // (Please note that, in JavaScript, functions are first-class objects: they
    // can be passed around as objects.  This is different from the way
    // methods are treated in Java programming language.)
   
    //req.onreadystatechange = fillSolDisRequest;
   // req.open("GET", url, true); 
    
    //req.send(null);
}

function fillSolDisRequest(){
	if (req.readyState == 4) {
        if (req.status == 200) {

          // Extract "true" or "false" from the returned data from the server.
          // The req.responseXML should contain either <valid>true</valid> or <valid>false</valid>
          var message = req.responseXML.getElementsByTagName("valid")[0].childNodes[0].nodeValue;
  
          // Call "setMessageUsingDOM(message)" function to display
          // "Valid User Id" or "Invalid User Id" message.
          
  
          // If the user entered value is not valid, do not allow the user to
          // click submit button.
          var submitBtn = document.getElementById("submit_btn");
          if (message == "false") {
              submitBtn.disabled = true;
          } else {
              submitBtn.disabled = false;
          }
        }
      }
}