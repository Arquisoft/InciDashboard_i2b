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
        });

        stompClient.subscribe('/incident/standard', function (data) {
            console.log(data);
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
