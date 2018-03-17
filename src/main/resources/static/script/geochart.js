class GeoChart {

    constructor(id) {
    		this.id = id;
        google.charts.load('current', {
            'packages': ['geochart'],
            // Note: you will need to get a mapsApiKey for your project.
            // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
            'mapsApiKey': 'AIzaSyAeN23rfAUlKlCk4xjiFV7waULxBJ1j370'
        });
        google.charts.setOnLoadCallback(function() {
        		this.display();
        		}.bind(this)
        );
    }

    display() {
        var data = google.visualization.arrayToDataTable([
            ['Country', 'Popularity'],
            ['Germany', 200],
            ['Spain', 5],
            ['United States', 300],
            ['Brazil', 400],
            ['Canada', 500],
            ['France', 600],
            ['RU', 700]
        ]);
        var options = {};
        
        this.chart = new google.visualization.GeoChart(document.getElementById(this.id));
        this.chart.draw(data, options);
    }

    onNewData(data) {
        // code to handle new data from kafka
    }

}
