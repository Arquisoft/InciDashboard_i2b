
/*
 * Adds a new incident to the current view.
 */
function addIncident(incident) {
    console.log("Adding incident!");
    var payload = "<div class='uk-card uk-card-default uk-width-1-2@m uk-align-center uk-margin-bottom'>";
    payload += "<div class='uk-card-header'><h3 class='uk-card-title uk-text-center'>";
    payload += incident.inciName + "</h3></div><div class='uk-card-body'>";
    payload += "<p class='uk-text-center'>Incident sent from agent named ";
    payload += incident.agent.username + " of kind " + incident.agent.kind + ".</p>";

    if (incident.location.lat && incident.location.lon) {
        payload += "<div class='location uk-margin-small-bottom'>";
        payload += "<h4 class='uk-heading-bullet'>Location</h4>";
        payload += "<ul class='uk-list uk-list-bullet'><li>";
        payload += "Latitude: " + incident.location.lat + "</li>";
        payload += "<li>Longitude" + incident.location.lon + "</li>";
        payload += "</ul></div>";
    }

    var tags = incident.tags;
    if (tags.length != 0) {
        payload += "<div class='tags uk-margin-small-bottom'>";
        payload += "<h4 class='uk-heading-bullet'>Tags</h4>";
        payload += "<ul class='uk-list uk-list-striped'>";
        for (var tag in tags) {
            payload += "<li>" + tags[tag] + "</li>";
        }
        payload += "</ul></div>";
    }

    var properties = incident.properties;
    var keys = Object.keys(properties);
    if (keys.length !== 0) {
        payload += "<div class='properties'><h4 class='uk-heading-bullet'>";
        payload += "Properties:</h4><ul class='uk-list uk-list-divider'>";
        for (var i = 0; i < keys.length; i++) {
            var key = keys[i];
            var value = incident.properties[key];
            payload += "<li>" + key + ": " + value + "</li>";
        }
        payload += "</ul></div>";
    }

    payload += "</div><div class='uk-card-footer'><a href='";
    payload += "/incident/" + incident.inciName + "/details' ";
    payload += "class='uk-button uk-button-text uk-align-center'>";
    payload += "Modify</a></div></div>";

    $("#inciList").prepend(payload);
}

function connect() {
    var socket = new SockJS('/dashboard');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/incident/standard', function (data) {
            console.log(data.body);
            var incident = JSON.parse(data.body);

            var operator = incident.properties.operator;
            if (operator !== null && operator === currentOperator) {
                addIncident(incident);
            }
        });

    });
}

$(document).ready(function () {
    resetNavCount(); // reset notification count
    connect();
});
