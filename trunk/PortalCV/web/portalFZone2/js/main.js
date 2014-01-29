var isTouchDevice = function() {  return 'ontouchstart' in window || 'onmsgesturechange' in window; };
var isDesktop = window.screenX != 0 && !isTouchDevice() ? true : false;
var width = $(window).width();
var height = $(window).height(); 

function resizeWeb(){
    if(width >= 768){
        $('#img_titulo').attr('src', 'img/tituloBrowser.png');
        $('#img_titulo_premium').attr('src', 'img/tituloBrowserBlack.png');
        $('#img_titulo_login_fibertel').attr('src', 'img/frase_loginpremium_browser.png');
        $('#txt_home').attr('src', 'img/txt_home_b.png');
		$('#txt_homeP').attr('src', 'img/txt_homeP_b.png');
		$('#txt_homePS').attr('src', 'img/txt_homePS_b.png');
        $('#img_footer').attr('src', 'img/logo_fibertel.png');
    }else{
        $('#img_titulo').attr('src', 'img/titulo.png');
        $('#img_titulo_premium').attr('src', 'img/tituloBlack.png');
        $('#img_titulo_login_fibertel').attr('src', 'img/frase_loginpremium_phone.png');
        $('#txt_home').attr('src', 'img/txt_home_s.png');
		$('#txt_homeP').attr('src', 'img/txt_homeP_s.png');
		$('#txt_homePS').attr('src', 'img/txt_homePS_s.png');
        $('#img_footer').attr('src', 'img/logo_fibertel_phone.png');
    }
    
    // calcular el alto del contenido para poner el footer correctamente
    //alert($('body').height());
        var footer_y = $('.footer').offset().top;
        var footer_h = $('.footer').height();
           
    var height_content = $('body').height();
        
    if(height >= height_content){
        $('.footer').css('position', 'fixed');
        $('.footer').css('left', '0px');
        $('.footer').css('bottom', '0px');
    }else{
        $('.footer').css('position', 'inherit');
    }
        
    
}

$(window).resize(function() {
    width = $(this).width();
    height = $(this).height();
    resizeWeb();
});

$(document).ready(function(){
    resizeWeb();
    
    $('video').bind('ended',function(){
        //alert('video termino.');
        if(!isDesktop){
            window.location.href = urlRedireccion;
        }
    });
    
    $('#btn_next').click(function(){
        if($('#checkbox1').attr('checked') == false){
            alert('Debe aceptar los terminos y condiciones.');
            return false;
        }
    });
    
    if(isDesktop){
        $('video').removeAttr('controls');
    }
    
});

/*window.onload = function() {
    
    
  // Video
  var video = document.getElementById("video");

  // Buttons
  var playButton = document.getElementById("play-pause");
  //$(playButton).hide();
  var muteButton = document.getElementById("mute");
  var fullScreenButton = document.getElementById("full-screen");

  // Sliders
  var seekBar = document.getElementById("seek-bar");
  //$(seekBar).hide();
  var volumeBar = document.getElementById("volume-bar");

}*/