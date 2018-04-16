function connect() {
    var socket = new SockJS('/dashboard');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/incident/standard', function (data) {
            var incident = JSON.parse(data.body);

            var operator = incident.properties.operator;
            if (operator !== null && operator === currentOperator) {
                increaseNavCount(incident);
                increaseIncidents();
            }

            updatePieChart(incident);
            updateBarChart(incident);
        });

    });
}

$(document).ready(function () {
    connect();
});
