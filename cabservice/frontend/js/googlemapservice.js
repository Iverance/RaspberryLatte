angular.module('myApp.googleMapService', [])
    .factory('GoogleMapService', function($rootScope, $timeout, $http) {


var directionsDisplay;
var directionsService;
var cache;
var locations;
var markers;
var routes; 
var routeObjects = [];
var latLngs = [];

 var marker = null;
  var stepDisplay;
  var markerArray = [];
  var position;
  var polyline = null;
  var poly2 = null;
  var speed = 0.000005, wait = 1;
  var infowindow = null;
  
    var myPano;   
    var panoClient;
    var nextPanoId;
  var timerHandle = null;
  
  
  var car ={
  url:"caricon.png",
  //size: new google.maps.Size(20,32)
  //origin: new google.maps.Point(),
  anchor: new google.maps.Point(35,15)
  };
  //var car = new google.maps.MarkerImage("caricon.png");

        //The service our factory will return
        var googleMapService = {},

            //The last marker that was placed on the map
            //by the user
            lastMarker,

            //Array of the locations obtained from api call
            locations = [],

            //The current selected marker
            currentSelectedMarker;

        /***************************
         * Refresh the locations
         **************************/
        googleMapService.refreshLocations = function() {

			//we clear the array holding the api response 
			locations = [];

            //Ajax call
			$http.get('api/locations').success(function(response) {
				locations = responseToLocations(response);

                //We initialize the map
				initialize();

                //We remove last marker placed on the map
				if (lastMarker) lastMarker.setMap(null);
			}).error(function() {}); 
		};

        /***************************
         * Returns the position of the marker placed
         * on the map
         **************************/
        googleMapService.getLocation = function() {
            return {
                longitude: lastMarker.getPosition().lng(),
                latitude: lastMarker.getPosition().lat()
            };
        };

        /***************************
         * Returns true if marker was placed on the map
         **************************/
        googleMapService.isMarkerSet = function() {
            if (lastMarker === undefined) return false;
            else return true;
        };

        /***************************
         * Returns the current selected marker
         **************************/
        googleMapService.getSelectedLocation = function() {
            return currentSelectedMarker;
        };

        /***************************
         * Delete the last marker set on the map
         **************************/
        googleMapService.clearMarker = function() {
            if (lastMarker)
                lastMarker.setMap(null);
        };

        /***************************
         * The Location object
         **************************/
        function Location(latlon, message, username, id) {
            this.latlon = latlon;
            this.message = message;
            this.username = username;
            this.id = id;
        }

        /***************************
         * Convert the json response to
         * an array of Location objects
         **************************/
        function responseToLocations(response) {

            //We push into our locations array
            for (var i = 0, l = response.length; i < l; i++) {

                var r = response[i];

                //The message we'll put in the infowindow
                var contentString = '<div class="info-box"><h5>' +
                    r.username +
                    ' said:</h5><p>' +
                    r.message +
                    '</p><br/></div>';

                //add to the locations
                locations.push(new Location(
                    new google.maps.LatLng(r.latitude, r.longitude),
                    new google.maps.InfoWindow({
                        content: contentString,
                        maxWidth: 320
                    }),
                    r.username,
                    r._id
                ));
            }
            return locations;
        }

        /***************************
         * Set a marker on the map
         **************************/
        function setMarker(position, map) {
            var marker = new google.maps.Marker({
                position: position,
                animation: google.maps.Animation.BOUNCE,
                map: map,
                icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
            });
            googleMapService.clearMarker();
            lastMarker = marker;
            map.panTo(position);
        }

        /***************************
         * Get the bounds from an array
         * of locations
         **************************/
        function getBounds(locations) {
            var latlngbounds = new google.maps.LatLngBounds();
            locations.forEach(function(n) {
                latlngbounds.extend(n.latlon);
            });
            return latlngbounds;
        }

        /***************************
         *  Remove from the map all the markers from
         *  an array of markers
         **************************/
        function clearMarkers(markers) {
            markers.forEach(function(m) {
                m.setMap(null);
            });
            return [];
        }


//Add routes to the map
googleMapService.addRoutes = function() {

	for(var index=0; index<routes.length-1;index++){
	
    var start = new google.maps.LatLng(routes[index].position.lat(), routes[index].position.lng())
	var end = new google.maps.LatLng(routes[index+1].position.lat(), routes[index+1].position.lng())
	var request = {
      origin:start,
      destination:end,
      travelMode: google.maps.TravelMode.DRIVING
};
	directionsService = new google.maps.DirectionsService();

	directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
		directionsDisplay = new google.maps.DirectionsRenderer();
		routeObjects.push(directionsDisplay);
		directionsDisplay.setDirections(response);
		directionsDisplay.setMap(cache.map);
    }
});
}
}

// Sets the map on all markers in the array.
function setAllMap(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

googleMapService.clearMarkers = function()  {
	
	for(var index=0; index<routeObjects.length;index++){
		routeObjects[index].setMap(null);
	}
}
//***********************************


function createMarker(latlng, label, html) {
// alert("createMarker("+latlng+","+label+","+html+","+color+")");
    var contentString = '<b>'+label+'</b><br>'+html;
    var marker = new google.maps.Marker({
        position: latlng,
        map: cache.map,
        title: label,
        zIndex: Math.round(latlng.lat()*-100000)<<5
        });
        marker.myname = label;

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.setContent(contentString); 
        infowindow.open(map,marker);
        });
    return marker;
}

googleMapService.calcRoute = function(){

if (timerHandle) { clearTimeout(timerHandle); }
if (marker) { marker.setMap(null);}

polyline.setMap(null);
poly2.setMap(null);
directionsDisplay.setMap(null);
    polyline = new google.maps.Polyline({
	path: [],
	strokeColor: '#FF0000',
	strokeWeight: 3
    });
    poly2 = new google.maps.Polyline({
	path: [],
	strokeColor: '#FF0000',
	strokeWeight: 3
    });
    // Create a renderer for directions and bind it to the map.
    var rendererOptions = {
      map: cache.map
    }
directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);

		var travelMode = google.maps.DirectionsTravelMode.DRIVING

		//loop through each marker
		for(var index=0; index<latLngs.length-1;index++){
		console.log("latlng"+latLngs[index]);
	    var request = {
	        origin: latLngs[index],
	        destination: latLngs[index+1],
	        travelMode: travelMode
	    };

		// Route the directions and pass the response to a
		// function to create markers for each step.
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK){
	directionsDisplay.setDirections(response);

        var bounds = new google.maps.LatLngBounds();
        var route = response.routes[0];
        startLocation = new Object();
        endLocation = new Object();

        // For each route, display summary information.
	var path = response.routes[0].overview_path;
	var legs = response.routes[0].legs;
        for (i=0;i<legs.length;i++) {
          if (i == 0) { 
            startLocation.latlng = legs[i].start_location;
            startLocation.address = legs[i].start_address;
            marker = createMarker(legs[i].start_location,"start",legs[i].start_address,"green");
          }
          endLocation.latlng = legs[i].end_location;
          endLocation.address = legs[i].end_address;
          var steps = legs[i].steps;
          for (j=0;j<steps.length;j++) {
            var nextSegment = steps[j].path;
            for (k=0;k<nextSegment.length;k++) {
              polyline.getPath().push(nextSegment[k]);
              bounds.extend(nextSegment[k]);
            }
          }
        }
		polyline.setMap(cache.map);
        cache.map.fitBounds(bounds);
		cache.map.setZoom(18);
			startAnimation();
    }  

 });
 
 }
}

var steps = []
      var step = 50; // 5; // metres
      var tick = 100; // milliseconds
      var eol;
      var k=0;
      var stepnum=0;
      var speed = "";
      var lastVertex = 1;

//=============== animation functions ======================
      function updatePoly(d) {
        // Spawn a new polyline every 20 vertices, because updating a 100-vertex poly is too slow
        if (poly2.getPath().getLength() > 20) {
          poly2=new google.maps.Polyline([polyline.getPath().getAt(lastVertex-1)]);
        }

        if (polyline.GetIndexAtDistance(d) < lastVertex+2) {
           if (poly2.getPath().getLength()>1) {
             poly2.getPath().removeAt(poly2.getPath().getLength()-1)
           }
           poly2.getPath().insertAt(poly2.getPath().getLength(),polyline.GetPointAtDistance(d));
        } else {
          poly2.getPath().insertAt(poly2.getPath().getLength(),endLocation.latlng);
        }
      }

function animate(d) {
// alert("animate("+d+")");
        if (d>eol) {
          cache.map.panTo(endLocation.latlng);
          marker.setPosition(endLocation.latlng);
          return;
        }
        var p = polyline.GetPointAtDistance(d);
        cache.map.panTo(p);
        marker.setPosition(p);
        updatePoly(d);
		//console.log("#####"+d);
        timerHandle = setTimeout(function(){animate(step+d);}, tick);
      }


function startAnimation() {
        eol=polyline.Distance();
		//console.log("#####eol"+eol);
        cache.map.setCenter(polyline.getPath().getAt(0));
         marker = new google.maps.Marker(
		 {
			location:polyline.getPath().getAt(0),
			icon: car
		 });
         marker.setMap(cache.map);
        poly2 = new google.maps.Polyline({path: [polyline.getPath().getAt(0)], strokeColor:"#FF0000", strokeWeight:10});
        setTimeout(function(){animate(50);},200);  // Allow time for the initial map display
}


//=============== ~animation funcitons =====================


//*********************

        /***************************
         * Initialize the Google Map
         **************************/
        function initialize() {
		directionsService = new google.maps.DirectionsService();
		routes = []; 
		

            //We create a cache
            if (!arguments.callee.cache) arguments.callee.cache = {};
            cache = arguments.callee.cache;

            //If there are markers in the cache we clear them.
            if (cache.markers) cache.markers = clearMarkers(cache.markers);

            //else we cache an empty array
            else cache.markers = [];
			
			var sf = new google.maps.LatLng(37.7699298, -122.4469157);
            //If it's the first time we run the function
            if (cache.firstInit === undefined) {
				//console.log("cache:"+cache.firstInit);
                //We now have ran it 
                cache.firstInit = true;

                var mapOptions = {
						zoom: 12,
						center: sf,
						mapTypeId: google.maps.MapTypeId.TERRAIN},
                    bounds,
                    fitBounds = false;

                //the new map
                cache.map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);

            }

			
			//*********************
			
	
	 // Create a renderer for directions and bind it to the map.
    var rendererOptions = {
      map: cache.map
    }
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    
    // Instantiate an info window to hold step text.
    stepDisplay = new google.maps.InfoWindow();

    polyline = new google.maps.Polyline({
	path: [],
	strokeColor: '#FF0000',
	strokeWeight: 3
    });
    poly2 = new google.maps.Polyline({
	path: [],
	strokeColor: '#FF0000',
	strokeWeight: 3
    });
			
			
			//*********************
			
			
			
            //we add the markers to the map and set the listeners
            locations.forEach(function(n, i) {

                //Inner function to check if the user is the same as the user
                //that added the current marker
                function sameUser() {
                    var username = $rootScope.getUsername();
                    return username && n.username === username;
                }

                //We set the color to blue is same user, else don't load
                var icon =
                    sameUser() ?
                    'http://maps.google.com/mapfiles/ms/icons/blue-dot.png' :
                    '.png';
				if(sameUser())
					latLngs.push(n.latlon);
					
                //We create a marker
                var marker = new google.maps.Marker({
                    position: n.latlon,
                    map: cache.map,
                    title: "none",
                    icon: icon
                });
				

                //We add it to the cache
                cache.markers.push(marker);
				
				if(sameUser()){
					routes.push(marker);
				}

                //When we click on a marker
                google.maps.event.addListener(marker, 'click', function(e) {

                    //If owned by the user, allow deletion
                    if (sameUser()) $rootScope.$broadcast("allow");

                    //Else disallow
                    else $rootScope.$broadcast("disallow");

                    //We clear the marker set on the map
                    googleMapService.clearMarker();

                    //the current selected marker
                    currentSelectedMarker = n;

                    //we open the message
                    n.message.open(cache.map, marker);

                    //we hide all alert messages
                    $rootScope.$broadcast("hideAllMessages");
                });
            });
			
            //when we click on the map
            google.maps.event.addListener(cache.map, 'click', function(e) {

                //we disallow deleting
                $rootScope.$broadcast("disallow");

                //we hide all alert messages
                $rootScope.$broadcast("hideAllMessages");

                //If we are logged in, we can set markers on the map
                if ($rootScope.loggedIn()) setMarker(e.latLng, cache.map);
            });
			
        }

        //we show the map for the first time on page load
        google.maps.event.addDomListener(window, 'load',
            googleMapService.refreshLocations);

			
			
			
        return googleMapService;
    });
