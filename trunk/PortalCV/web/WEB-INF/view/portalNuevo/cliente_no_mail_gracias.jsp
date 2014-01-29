<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <script src="js/modernizr.min.js"></script>
        <script src='js/jquery.min.js' type='text/javascript'></script>
        <script src="js/jquery.ui.js" type="text/javascript"></script>
        <script src="js/jquery.alerts.js" type="text/javascript"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <title>::: Fibertel ZONE :::</title>
    </head>
    <body>
    
    <script>
			create_page('default/mail');
			if ((screen.width == 320) && (screen.height == 240) || ((screen.width == 480) && (screen.height == 360))) {
			}else{
			
			create_relative_area('portrait', 0.236, 0.573, 0.397, 0.650,"go('http://gmail.com')", 'Gmail');
			create_relative_area('portrait', 0.419, 0.573, 0.582, 0.650,"go('http://outlook.com')",'Hotmail');
			create_relative_area('portrait', 0.604, 0.573, 0.764, 0.650,"go('http://yahoo.com.ar/mail')",'Yahoo');
			create_relative_area('portrait', 0.328, 0.677, 0.490, 0.753,"go('http://wm2.fibertel.com.ar')",'Fibertel Mail');
			create_relative_area('portrait', 0.510, 0.677, 0.673, 0.753,"go('http://google.com.ar')",'Otros');
			
			create_relative_area('landscape', 0.166, 0.593, 0.283, 0.689,"go('http://gmail.com')", 'Gmail');
			create_relative_area('landscape', 0.302, 0.593, 0.419, 0.689,"go('http://outlook.com')",'Hotmail');
			create_relative_area('landscape', 0.440, 0.593, 0.558, 0.689,"go('http://yahoo.com.ar/mail')",'Yahoo');
			create_relative_area('landscape', 0.578, 0.593, 0.695, 0.689,"go('http://wm2.fibertel.com.ar')",'Fibertel Mail');
			create_relative_area('landscape', 0.716, 0.593, 0.833, 0.689,"go('http://google.com.ar')",'Otros');
			}
			setTimeout(function () { window.scrollTo(0, window.innerHeight) }, 100);
       </script>
    
    </body>
</html>