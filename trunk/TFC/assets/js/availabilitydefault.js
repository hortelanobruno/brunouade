function showModalBooking(data, time, price, fname, cid, duration, facname){
	$('#code').hide();
	$('#loading').show();
	$('#modal_booking').show();
	var t = time.split(':');
	$('#span_booking_details').html('<b>data:</b> ' + beautifyDate ( data ) + ' <b>ora:</b> ' + time + '<br /><b>campo:</b> ' + fname +'<br /><b>centro sportivo:</b> ' + facname);
	$('#data').val(data);
	$('#ora').val(t[0]);
	$('#min').val(t[1]);
	$('#cid').val(cid);
	//dijit.byId('da_pagare').set('value', price);
	//$('#da_pagare').val('€ ' + price);
	//dijit.byId('durata').attr('value', duration);
	

	dijit.byId('modal_dialog').show();


	$.get('/ajax/availability/generatecode', { field: cid, duration: duration, date: data, time_hour: t[0], time_min: t[1] }, function(data) {
		eval('var r = '+data+';');
		if(r.success == true){
			$('#loading').hide();
			$('#code').text('Questo è il tuo codice: ' + r.code + ' ');
			$('#code').fadeIn();
		}
	});
}

function showModalBookingSMS(data, time, price, fname, cid, duration, facname){
	$('#code').hide();
	$('#loading').hide();
	$('#modal_booking').show();
	var t = time.split(':');
	$('#span_booking_details').html('<b>data:</b> ' + beautifyDate ( data ) + ' <b>ora:</b> ' + time + '<br /><b>campo:</b> ' + fname +'<br /><b>centro sportivo:</b> ' + facname);
	$('#data').val(data);
	$('#ora').val(t[0]);
	$('#min').val(t[1]);
	$('#cid').val(cid);
	$('#duration').val(duration);
	//dijit.byId('da_pagare').set('value', price);
	//$('#da_pagare').val('€ ' + price);
	//dijit.byId('durata').attr('value', duration);
	

	dijit.byId('modal_dialog').show();

	/*
	$.get('/ajax/availability/generatecode', { field: cid, duration: duration, date: data, time_hour: t[0], time_min: t[1] }, function(data) {
		eval('var r = '+data+';');
		if(r.success == true){
			$('#loading').hide();
			$('#code').text('Questo è il tuo codice: ' + r.code + ' ');
			$('#code').fadeIn();
		}
	});
	*/ 
}

function confirmBooking(){
	var data = $('#data').val() + ' ' + $('#ora').val() + ':' +$('#min').val();
	var cid = $('#cid').val();
	var duration = $('#duration').val();
	var urltocall = '/default/availability/book/';
	var params = '';
	
	$('#confirmBtn').hide();
	$('#loading').show();
	
	$.get('/api/availability/book', { field_id: cid, duration: duration, date: data }, function(data) {
		eval('var r = '+data+';');
		if( r.success ){
			params = '?bi='+r.book_id;
			
			if(r.warning_string){
				params += '&ws=t';
			}
		}
		else {
			params += '?s=f&em='+r.message;
		}
		
		location.href = urltocall + params;
		
	});
	
	return false;
	
	
	
}
