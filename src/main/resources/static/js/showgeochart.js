window.onload = function () {

    //gets the params of the class file(intend to use is receiving arrays of locations)
    var params = document.body.getElementsByTagName('script');
    query = params[0].classList;
    var param_a = query[0];
    var param_b = query[1];
    var param_c = query[2];

    google.charts.load('current', {
        'packages': ['geochart'],
        // Note: you will need to get a mapsApiKey for your project.
        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
        'mapsApiKey': 'AIzaSyAeN23rfAUlKlCk4xjiFV7waULxBJ1j370'
    });
    google.charts.setOnLoadCallback(drawRegionsMap);

    function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable([
            ['Country', 'Popularity'],
            ['Germany', 200],
            ['United States', 300],
            ['Brazil', 400],
            ['Canada', 500],
            ['France', 600],
            ['RU', 700]
        ]);

        var options = {};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);
    }
}