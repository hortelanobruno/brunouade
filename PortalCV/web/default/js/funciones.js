var imgHome = "img/";

function createCookie(name, value, days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    } else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}

function createCookie2(name, value, min) {
    if (min) {
        var date = new Date();
        date.setTime(date.getTime() + (min * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    } else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function eraseCookie(name) {
    createCookie(name, "", - 1);
}

function terminos(URL) {
    (function ($) {
        $.alerts.okButton = 'Acepto';
        $.alerts.cancelButton = 'No Acepto';
        var cookie = readCookie('aceptoTerminos')
        if (cookie) {
            location.href = URL;
        } else jConfirm('Para acceder a este servicio deber\u00e1s aceptar los <a href="/condiciones" title="T\u00e9rminos y Condiciones" target="_blank"> T\u00e9rminos y Condiciones</a> ', 'Fibertel Zone', function (r) {
            if (r == true) {
                createCookie('aceptoTerminos', 'Accept', 30)
                location.href = URL;
            } else {
                return false;
            }
        });
    })(jQuery);
};

function show_image(src, ancho, alto, alt, map, divname) {
    // Descripción: Crea una imagen dentro de un div
    // 	Los parametros a pasar son:
    // 		src: origen de la imagen
    // 		width: ancho de la imagen
    // 		height: alto de la imagen
    // 		alt: nombre alternativo de la imagen
    // 		map: nombre del mapa para la imagen
    // 		divname: nombre del div a utilizar
    // Creo el link de la imagen
    var img = document.createElement("img");
    img.src = src;
    img.width = ancho;
    img.height = alto;
    img.alt = alt;
    img.border = 0;
    img.setAttribute("useMap", map, 0);
    // Creo el div
    var div = document.createElement("div");
    div.id = divname;
    div.width = ancho;
    div.height = alto;
    //    div.style.width = ancho;
    //    div.style.height = alto;
    div.setAttribute('class', divname);
    div.align = 'center';
    //estilo = "position:relative;"
    //div.setAttribute("style", estilo);
    // Intertamos la imagen en el div
    div.appendChild(img)
    // Intertamos el div en el body
    document.body.appendChild(div);
}

function create_mail(divname, textBoxName, a1, a2, a3, a4, formName, method, action) {
    if (document.getElementById(divname)) {
        /*	
	
    //creo el textbox
    var input = document.createElement("input");
    input.type = "email";
    input.id = textBoxName;
    input.setAttribute("name", textBoxName);
	if (divname=='landscape'){
    estilo = "position:relative; left:52%; bottom:48.2%; width:" + Math.round(document.getElementById(divname)
        .firstChild.width * a1) + "px; border-color:transparent; position:absolute; z-index:1; float:left; height:" + Math.round(document.getElementById(divname)
        .firstChild.height * a2) + "px; background-color:transparent; border:auto; font-size:" + Math.round(document.getElementById(divname).firstChild.height * a2) + "px;" ;
	}else if (divname=='portrait'){
		estilo = "position:relative; left:55.5%; bottom:45%; width:26%; height:3.071%; border-color:transparent; position:absolute; z-index:1; float:left;  background-color:transparent; border:auto;font-size:" + Math.round(document.getElementById(divname).firstChild.height * 0.019) + "px;"
	}
    input.setAttribute("style", estilo);*/
        //creo el form
        var form = document.createElement("form");
        form.type = "form";
        form.id = formName;
        form.setAttribute("name", formName);
        form.setAttribute("method", method);
        form.setAttribute("action", action);
        //    form.appendChild(input);
        /*    // Creo el div
    var div = document.createElement("div");
    div.id = 'emailDiv';
    div.setAttribute("name", textBoxName);
    //estilo = "position:absolute;left: 50%; bottom:50%; width:20%; z-index:1";
	 estilo = "position:relative;left:" + Math.round(document.getElementById(divname)
            .firstChild.width * a3) + "px; bottom:" + Math.round(document.getElementById(divname)
            .firstChild.height * a4) + "px; width:" + Math.round(document.getElementById(divname)
            .firstChild.width * a1) + "px; z-index:1";
    div.setAttribute('style', estilo);
    div.appendChild(form);

    document.getElementById(divname)
        .appendChild(form);
}
*/
        /*if (((screen.width == 320) && (screen.height == 240))) {
            //creo el textbox
            var input = document.createElement("input");
            input.type = "email";
            input.id = textBoxName;
            input.setAttribute("name", textBoxName);
            //estilo = "position:relative; float:left; font-size: 6.5px; border-color:transparent; background-color:transparent;bottom:133.5px;left:49px; width:83px; height:8px; ";
            estilo = "position:relative; left:-125px; top:143px; width:240px; border-color:transparent; position:absolute; z-index:1; float:left; height:30px; background-color:transparent; border:auto; font-size:18px;";
            input.setAttribute("style", estilo);
            //creo el div
            var div = document.createElement("div");
            div.id = 'emailDiv';
            div.setAttribute("name", textBoxName);
            estilo = "left:50px; top:-116.8px; width:82px; z-index:1;";
            div.setAttribute('style', estilo);
        } else if (((screen.width == 360) && (screen.height == 640))) {
            //creo el textbox
            var input = document.createElement("input");
            input.type = "email";
            input.id = textBoxName;
            input.setAttribute("name", textBoxName);
            estilo = "position:relative; float:left; font-size:10px; border-color:transparent; background-color:transparent;bottom:231.5px;left:63px; width:120px; height:16px; ";
            input.setAttribute("style", estilo);
            //creo el div
            var div = document.createElement("div");
            div.id = 'emailDiv';
            div.setAttribute("name", textBoxName);
            estilo = "left:50px; top:-116.8px; width:82px; z-index:1;";
            div.setAttribute('style', estilo);
        } else {*/
        //creo el textbox
        var input = document.createElement("input");
        if (Modernizr.canvas) {
            input.type = "email";
        }
        input.id = textBoxName;
        if (divname == 'landscape') {
				
            if (((screen.width == 320) && (screen.height == 240))) {				
                estilo = "position:relative; left:-18px; top:-100px; width:140px; height:10px; border-color:transparent; position:absolute; z-index:1; float:left; height:25px; background-color:transparent; border:transparent; font-size:12px; font-style:italic; color:#999;";

            }else{

                if (!Modernizr.canvas){
                    estilo = "position:relative; left:-375px; top:102px; width:175px; height:25px; border-color:transparent; position:absolute; z-index:1; float:left; height:30px; background-color:transparent; border:auto; font-size:15px; font-style:italic; color:#999; ";
                }
                else {
                    estilo = "position:relative; left:-375px; top:95px; width:175px; height:25px; border-color:transparent; position:absolute; z-index:1; float:left; height:30px; background-color:transparent; border:auto; font-size:15px; font-style:italic; color:#999; ";
                }
            }
        } else if (divname == 'portrait') {
            if (((screen.width == 240) && (screen.height == 320))) {
                estilo = "position:absolute; float:left; left:-15px; bottom:127px; width:130px; height:20px; border-color:transparent; position:absolute; z-index:1; float:left;  background-color:transparent; border:2px;font-size:15px; font-style:italic; color:#999;"			
            }else{
                estilo = "position:absolute; float:left; left:-265px; bottom:231px; width:230px; height:40px; border-color:transparent; position:absolute; z-index:1; float:left; background-color:transparent; border:2px;font-size:22px; font-style:italic; color:#999;"
            }
        }
        input.setAttribute("style", estilo);
        input.value="Ingres\u00e1 tu mail aqu\u00ed";
        input.setAttribute("onclick", 'borraTexto("'+textBoxName+'","'+estilo+'")');
        input.setAttribute("name", 'usremail');

        // Creo el div
        var div = document.createElement("div");
        div.id = 'emailDiv';
        div.setAttribute("name", textBoxName);
        if ((screen.width == 320) && (screen.height == 240) || ((screen.width == 240) && (screen.height == 320))) {				
            estilo = "position:relative;left:0px; bottom:0px; width:100px; z-index:1";
        }else{
            estilo = "position:relative;left:200px; bottom:500px; width:100px; z-index:1";
        }
        div.setAttribute('style', estilo);
        // }
        form.appendChild(input);
        div.appendChild(form);
        document.getElementById(divname)
        .appendChild(div);
    }
}

function borraTexto(txt, estilo){
    document.getElementById(txt).value="";
    estilo2=estilo+ "font-style:normal; color:#000;"
    document.getElementById(txt).setAttribute("style", estilo2);
}

function create_relative_area(divname, a1, a2, a3, a4, urlA, title) {
    // Crea un mapa comprendido por dos áreas en forma dinámica en función de cordenadas relativas del tamaño de una imagen.
    // Los parametros a pasar son:
    // 		divname: Nombre del div donde crear el mapa
    // 		a1,a2,a3,a4: coordenadas relativas del área A
    // 		urlA: funcion a donde que se ejecutará al hacer click en el area A
    if (document.getElementById(divname)) {
        // Creo el area A
        var area = document.createElement("area");
        area.setAttribute("shape", "rect");
        coords = Math.round(document.getElementById(divname)
            .firstChild.width * a1) + "," + Math.round(document.getElementById(divname)
            .firstChild.height * a2) + "," + Math.round(document.getElementById(divname)
            .firstChild.width * a3) + "," + Math.round(document.getElementById(divname)
            .firstChild.height * a4);
        area.setAttribute("coords", coords);
        area.setAttribute("href", "javascript:void(0);");
        area.setAttribute("onclick", urlA);

        // Creo el mapa

        mapname = document.getElementById(divname).firstChild.getAttribute("usemap").replace("#","");
		
        if (document.getElementById(mapname)) {
            map = document.getElementById(mapname);
        } else {
            map = document.createElement("map");
            map.setAttribute("id", mapname);
            map.setAttribute("name", mapname);
            document.getElementById(divname).appendChild(map);
        }
		
        map.appendChild(area);
    }
}

function create_relative_map_help(divname, a1, a2, a3, a4, texto) {
    if (document.getElementById(divname)) {
        var areaH = document.createElement("area");
        areaH.setAttribute("shape", "rect");
        coordsH = Math.round(document.getElementById(divname)
            .firstChild.width * a1) + "," + Math.round(document.getElementById(divname)
            .firstChild.height * a2) + "," + Math.round(document.getElementById(divname)
            .firstChild.width * a3) + "," + Math.round(document.getElementById(divname)
            .firstChild.height * a4);
        areaH.setAttribute("coords", coordsH);
        areaH.setAttribute("href", "javascript:void(0);");
        areaH.setAttribute("title", texto);
		
        mapname = document.getElementById(divname).firstChild.getAttribute("usemap").replace("#","");
        map = document.getElementById(mapname);
        map.appendChild(areaH);
	
    }
}

function load_css() {
    // Verifico si soporto html5 (en particular el atributo @media)
    if (Modernizr.canvas) {
        // Cargo hoja de estilo porttrait
        var element = document.createElement('link');
        element.rel = 'stylesheet';
        element.setAttribute('media', "all and (orientation:portrait)");
        element.href = 'css/portrait.css';
        element.type = 'text/css';
        document.body.insertBefore(element, document.body.firstChild);
        // Cargo hoja de estilo landscape
        var element = document.createElement('link');
        element.rel = 'stylesheet';
        element.setAttribute('media', "all and (orientation:landscape)");
        element.href = 'css/landscape.css';
        element.type = 'text/css';
        document.body.insertBefore(element, document.body.firstChild);
    } else {
        // Cargo hoja de estilo simplificada
        var element = document.createElement('link');
        element.href = 'css/symbian.css';
        element.rel = 'stylesheet';
        element.type = 'text/css';
        document.body.insertBefore(element, document.body.firstChild);
    }
}

function create_page(tipo) {
    // Cargo la hoja de estilo
    load_css();
    //Elijo la imagen a utilizar en función de la resolución
    if ((screen.width == 320) && (screen.height == 240) || ((screen.width == 480) && (screen.height == 360))) {
        var src = imgHome + tipo + ".LL.jpg";
        show_image(src, 300, 223, "FibertelZone Logo", "#Map320L", "landscape"); 
    } else if (((screen.width == 240) && (screen.height == 320))) {
        var src = imgHome + tipo + ".LP.jpg";
        show_image(src, 240, 313, "FibertelZone Logo", "#Map240LP", "portrait");
    } /*else if (((screen.width == 320) && (screen.height == 480)))  {
        var src = imgHome + tipo + ".ML.jpg";
        show_image(src, 480, 357, "FibertelZone Logo", "#MapHDL", "landscape");
        if (Modernizr.canvas) {
            var src = imgHome + tipo + ".MP.jpg";
            show_image(src, 320, 417, "FibertelZone Logo", "#MapHDP", "portrait");
        }
               }*/else  {
        var src = imgHome + tipo + ".HDL.jpg";
        show_image(src, 873, 650, "FibertelZone Logo", "#MapHDL", "landscape");
        if (Modernizr.canvas) {
            var src = imgHome + tipo + ".HDP.jpg";
            show_image(src, 900, 1172, "FibertelZone Logo", "#MapHDP", "portrait");
        }
    }
}

function go(URL) {
    self.location = URL;
}

function terminosMovil(URL, URLnoCookie) {
    var cookie = readCookie('aceptoTerminos')
    if (cookie) {
        location.href = URL;
    } else {
        location.href = URLnoCookie;
    }
}

function aceptoTerminosMovil(URL) {
    createCookie('aceptoTerminos', 'Accept', 30)
    location.href = URL;
}

function register(formName, textBoxName) {
    if (document.getElementById(textBoxName)
        .value != '' && validateEmail(document.getElementById(textBoxName)
            .value)) {
        document.getElementById(formName).submit();
    } else {
        alert('Direccion de correo invalida.');
    }
}

function validateEmail(email) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if (!emailReg.test(email)) {
        return false;
    } else {
        return true;
    }
}