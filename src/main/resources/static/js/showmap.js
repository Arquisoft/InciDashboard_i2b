
window.onload = function () {

//more param

    google.charts.load("current", {
        "packages": ["map"],
        // Note: you will need to get a mapsApiKey for your project.
        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
        "mapsApiKey": "AIzaSyAeN23rfAUlKlCk4xjiFV7waULxBJ1j370"
    });
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Lat', 'Long', 'Name'],
            [37.4232, -122.0853, 'Work'],
            [37.4289, -122.1697, 'University'],
            [37.6153, -122.3900, 'Airport'],
            [37.4422, -122.1731, 'Shopping']
        ]);

        var map = new google.visualization.Map(document.getElementById('map_div'));
        map.draw(data, {
            showTooltip: true,
            showInfoWindow: true
        });
    }
}

