var stompClient = null;

var graphics;

function connect() {
    var socket = new SockJS('/dashboard');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/incident/test-data', function (str) {
            var data = JSON.parse(str['body']);
            console.log(data);
            // TODO: introduce test data in the graphics
        });

        stompClient.subscribe('/incident/standard', function (data) {
        		// here goes the code when we receive a standard incident
        });

        stompClient.subscribe('/incident/geolocated', function (data) {
        		// here goes the code when we receive a geolocated incident
        });

        stompClient.subscribe('/incident/withOperator', function (data) {
        		// here goes the code when we receive an incident with operator
        });

        stompClient.subscribe('/incident/sensor', function (data) {
        		// here goes the code when we receive an incident from a sensor
        });
        stompClient.subscribe('/incidents', function (data) {
        		// here goes the code when we receive an incident from a sensor
        });
    });
}

$(document).ready(function() {
	graphics = {
	    "map": new Map("map_div"),
	    "geochart": new GeoChart("regions_div"),
	}

    connect();
});
