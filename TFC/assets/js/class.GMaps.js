var geocoded = new Array();
var maps = new Array();
var markers = new Array();
var infos = new Array();
var bounds = new Array();

function setGeocodeMapCenter(map, address)
{
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({'address':address}, callback_geocodeMarker);
	function callback_geocodeMarker(response, status) {
		if (status == google.maps.GeocoderStatus.OK && response[0])
			map.panTo(response[0].geometry.location);
	}
}

function setGeocodeMarker(map, mapId, address, infoContent, infoOpened, markerid, callback, icon)
{
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({'address':address}, callback_geocodeMarker);		
	function callback_geocodeMarker(response, status) {
		if (status == google.maps.GeocoderStatus.OK && response[0]) {	
			console.log(response[0].geometry.location);
			geocoded[mapId].push(response[0].geometry.location);										
			var marker = new google.maps.Marker({
				position: response[0].geometry.location,
				map: map,
				icon: icon
			});
			bounds[mapId].extend(response[0].geometry.location);
			if(markerid != '')
				markers[mapId][markerid] = marker;
			else
				markers[mapId].push(marker);

			if(infoContent)
			{
				var infowindow = new google.maps.InfoWindow({ content: infoContent});
					google.maps.event.addListener(marker, 'click', function() {
					infowindow.open(map, marker);
				});
				if(markerid != '')
					infos[mapId][markerid] = infowindow;
				else
					infos[mapId].push(infowindow);
				if(infoOpened)	
					infowindow.open(map, marker);
			}
			map.fitBounds(bounds[mapId]);
			$('#lat').val(response[0].geometry.location.lat());
			$('#lon').val(response[0].geometry.location.lng());
			if(callback)
				eval(callback+'('+response[0].geometry.location.lat()+','+response[0].geometry.location.lng()+')');
		}
		if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
			alert('Indirizzo non trovato');
		}
	}
}

var centerfunccalled = new Array();
function panMapOnGeocodedMarker(map, mapId)
{
	return;
	if(!isset(centerfunccalled[mapId]) || centerfunccalled[mapId] == 0)
		return;
	centerfunccalled[mapId] +=1;
	if(centerfunccalled[mapId]>=geocoded[mapId].length)
	{
		var totlat = 0;
		var totlon = 0;
		for(var i in geocoded[mapId])
		{
			totlat += geocoded[mapId][i].lat();
			totlon += geocoded[mapId][i].lng();
		}
		totlat = totlat/geocoded[mapId].length;
		totlon = totlon/geocoded[mapId].length;
		map.panTo(new google.maps.LatLng(totlat,totlon));
	}
}

function clearAllMarkers(mapId)
{
	for(i in markers[mapId])
		markers[mapId][i].setMap(null);
}

function setClusterer(map, mapId)
{
	var markerCluster = new MarkerClusterer(map, markers[mapId]);
}
