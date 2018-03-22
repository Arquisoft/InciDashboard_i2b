class Map {
    constructor(id) {
    		this.id = id;
        google.charts.load("current", {
            "packages": ["map"],
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
            ['Lat', 'Long', 'Labels'],
            [37.4232, -122.0853, ''],
            [37.4289, -122.1697, ''],
            [37.6153, -122.3900, ''],
            [37.4422, -122.1731, '']
        ]);
        
        this.map = new google.visualization.Map(document.getElementById(this.id));
        this.map.draw(data, {
            showTooltip: true,
            showInfoWindow: true
        });
    }

    onNewData(data) {
        // code to handle new data from kafka
    }

}
