
var images = [];

collection.forEach(element => {
	console.log(element);
	images.push({
		"zoomLevel": 5,
		"scale": 0.5,
		"title": element.inciName,
		"latitude": parseFloat(element.location.lat),
		"longitude": parseFloat(element.location.lon),
		"url": "/incident/" + element.inciName + "/details"

	});
});

function onNewIncident(incident) {
	images.push({
		"zoomLevel": 5,
		"scale": 0.5,
		"title": incident.inciName,
		"latitude": parseFloat(incident.location.lat),
		"longitude": parseFloat(incident.location.lon)
	});

	var previousHeight = $("#chartdiv").height();
	$("#chartdiv").height(previousHeight + 1);
}

var map = AmCharts.makeChart("chartdiv", {
	"type": "map",
	"theme": "dark",
	"projection": "miller",
	"imagesSettings": {
		"rollOverColor": "#089282",
		"rollOverScale": 3,
		"selectedScale": 3,
		"selectedColor": "#089282",
		"color": "#13564e"
	},
	"areasSettings": {
		"unlistedAreasColor": "#15A892",
		"autoZoom": true,
		"selectedColor": "#d0e0de",
		"rollOverColor": "#15A892",
		"color": "#15A892"
	},
	"dataProvider": {
		"map": "worldLow",
		"images": images,
		"getAreasFromMap": true
	}
});

// add events to recalculate map position when the map is moved or
// zoomed
map.addListener("positionChanged", updateCustomMarkers);
// this function will take current images on the map and create HTML
// elements
// for them
function updateCustomMarkers(event) {
	// get map object
	var map = event.chart;

	// go through all of the images
	for (var x in map.dataProvider.images) {
		// get MapImage object
		var image = map.dataProvider.images[x];

		// check if it has corresponding HTML element
		if ('undefined' == typeof image.externalElement)
			image.externalElement = createCustomMarker(image);

		// reposition the element accoridng to coordinates
		var xy = map.coordinatesToStageXY(image.longitude, image.latitude);
		image.externalElement.style.top = xy.y + 'px';
		image.externalElement.style.left = xy.x + 'px';
	}
}

// this function creates and returns a new marker element
function createCustomMarker(image) {
	// create holder
	var holder = document.createElement('div');
	holder.className = 'map-marker';
	holder.title = image.title;
	holder.style.position = 'absolute';

	// maybe add a link to it?
	if (undefined != image.url) {
		holder.onclick = function () {
			window.location.href = image.url;
		};
		holder.className += ' map-clickable';
	}

	// create dot
	var dot = document.createElement('div');
	dot.className = 'dot';
	holder.appendChild(dot);

	// create pulse
	var pulse = document.createElement('div');
	pulse.className = 'pulse';
	holder.appendChild(pulse);

	// append the marker to the map container
	image.chart.chartDiv.appendChild(holder);

	return holder;
}
