var stompClient = null;
var graphics;
var sensorIncidentsCount = 0;
var personIncidentsCount = 0;
var EntityIncidentsCount = 0;

function connect(graphics) {
    var socket = new SockJS('/dashboard');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/incident/test-data', function (str) {
            var data = JSON.parse(str['body']);
            console.log(data);
        });

        stompClient.subscribe('/incident/standard', function (data) {
            var incident = JSON.parse(data.body);

            var operator = incident.properties.operator;
            if (operator !== null && operator === currentOperator) {
                increaseNavCount(incident);
                increaseIncidents();
            }
        });

    });
}
