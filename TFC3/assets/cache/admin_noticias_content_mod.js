
					if( !('noticias_contenido' in CKEDITOR.instances)) {
						CKEDITOR.replace( 'noticias_contenido' );
					}
$('#noticias_fecha').datetimepicker({ dateFormat: 'yy-mm-dd', timeFormat: 'hh:mm:ss'});

