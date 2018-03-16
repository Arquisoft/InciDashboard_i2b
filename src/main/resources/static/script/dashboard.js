var stompClient = null;

function connect() {
    var socket = new SockJS('/dashboard');
    stompClient = Stomp.over(socket);
    console.log("Calling connect.");
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/incident/standard', function (data) {
            $("#chart").append("<p>Hi!</p>");
            console.log(data);
        });
    });
}

$(document).ready(function() {
    connect();
});
