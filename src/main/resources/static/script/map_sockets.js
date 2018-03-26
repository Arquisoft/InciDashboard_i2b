function connect() {
    var socket = new SockJS('/dashboard');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/incident/standard', function (data) {
            var incident = JSON.parse(data.body);

            // update map
            onNewIncident(incident);

            var operator = incident.properties.operator;
            if (operator !== null && operator === currentOperator) {
                increaseNavCount();
            }
        });

    });
}

$(document).ready(function () {
    connect();
});
