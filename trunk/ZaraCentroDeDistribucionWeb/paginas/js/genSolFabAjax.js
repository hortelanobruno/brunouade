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


function cargarArticulosAFabricar(){
	
	var title = document.getElementsByName("title");
	for(var i=0 ; i < title.length ; i++){
		alert(title[i].innerHTML);
	}
	
	
	
	
	
	
	if (!target) target = document.getElementById("userid");
    var url = "cargarArtFab?id=" + escape(target.value); 

    // Invoke initRequest(url) to create XMLHttpRequest object
    initRequest(url);

    // The "processRequest" function is set as a callback function.
    // (Please note that, in JavaScript, functions are first-class objects: they
    // can be passed around as objects.  This is different from the way
    // methods are treated in Java programming language.)
    req.onreadystatechange = cargarArticulosAFabricarRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	
}

function cargarArticulosAFabricarRequest() {
    if (req.readyState == 4) {
      if (req.status == 200) {

        // Extract "true" or "false" from the returned data from the server.
        // The req.responseXML should contain either <valid>true</valid> or <valid>false</valid>
        var message = req.responseXML.getElementsByTagName("valid")[0].childNodes[0].nodeValue;

        // Call "setMessageUsingDOM(message)" function to display
        // "Valid User Id" or "Invalid User Id" message.
        setMessageUsingDOM(message);

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

//This function is not used for now. You will use this later.
//
function setMessageUsingInline(message) {
    mdiv = document.getElementById("userIdMessage");
    if (message == "false") {
        mdiv.innerHTML = "<div style=\"color:red\">Invalid User Id</div>";
    } else {
        mdiv.innerHTML = "<div style=\"color:green\">Valid User Id</div>";
    }  
}

// (5) Function in which message indicating the validity of the data gets displayed
// through the "userIdMessage" <div> element.
//
function setMessageUsingDOM(message) {
    var userMessageElement = document.getElementById("userIdMessage");
    var messageText;
    if (message == "false") {
        userMessageElement.style.color = "red";
        messageText = "Invalid User Id";
    } else {
        userMessageElement.style.color = "green";
        messageText = "Valid User Id";
    }
    var messageBody = document.createTextNode(messageText);
    // if the messageBody element has been created simple replace it otherwise
    // append the new element
    if (userMessageElement.childNodes[0]) {
        userMessageElement.replaceChild(messageBody, userMessageElement.childNodes[0]);
    } else {
        userMessageElement.appendChild(messageBody);
    }
}

function disableSubmitBtn() {
    var submitBtn = document.getElementById("submit_btn");
    submitBtn.disabled = true;
}