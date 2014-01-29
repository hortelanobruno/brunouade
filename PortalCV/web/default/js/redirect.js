    function arranque() {

        createCookie('urlOrigen', document.referrer, 1)
        setTimeout(function () {
            window.scrollTo(0, window.innerHeight)
        }, 100);
        if (Modernizr.canvas) {} else {
            var element = document.createElement('link');
            element.href = 'css/symbian.css';
            element.rel = 'stylesheet';
            element.type = 'text/css';

            document.body.appendChild(element);

            if (document.getElementById) {
                document.getElementById('portrait')
                document.getElementById('portrait').style.visibility = 'hidden';
            }
        }

        if ((screen.width == 320) && (screen.height == 240)) {
            var src = "fondoinicio_01LR.jpg";
            show_image(src, 300, 223, "Google Logo", "#Map320L");
        } else if ((screen.width == 360) && (screen.height == 640)) {
            var src = "vert_fondoinicio01S.jpg";
            show_image(src, 340, 443, "Google Logo", "#Map360P");
        } else {
			self.location="home.html";
        }
    }


    function show_image(src, width, height, alt, map) {
        var img = document.createElement("img");
        img.src = src;
        img.width = width;
        img.height = height;
        img.alt = alt;
        img.setAttribute("usemap", map);
        document.body.appendChild(img);
    }

    function terminos(URL) {
        (function ($) {
            $.alerts.okButton = 'Acepto';
            $.alerts.cancelButton = 'No Acepto';
            var cookie = readCookie('aceptoTerminos')
            if (cookie) {
                location.href = URL;
            } else jConfirm('Para acceder a este servicio deberás aceptar los <a href="http://fibertel.com.ar/ContentPopup.aspx?id=1190" title="Términos y Condiciones" target="_blank"> Términos y Condiciones</a> ', 'Fibertel Zone', function (r) {
                if (r == true) {
                    createCookie('aceptoTerminos', 'Accept', 30)
                    location.href = URL;
                } else {
                    return false;
                }
            });
        })(jQuery);
    };