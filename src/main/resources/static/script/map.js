class Map {

    constructor(id) {
        this.id = id;
        this.data = [
            ['Lat', 'Long', 'Label']
        ];
        //data
        this.data.push([37.4232, -122.0853, '']);
        this.data.push([37.4289, -122.1697, '']);
        this.data.push([37.6153, -122.3900, '']);
        this.data.push([37.4422, -122.1731, '']);
       
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
        var dataTable = google.visualization.arrayToDataTable(this.data);
              
        this.map = new google.visualization.Map(document.getElementById(this.id));
        this.map.draw(dataTable, {
            showTooltip: true,
            showInfoWindow: true
        });
    }

    onNewData(newData) {
        this.data.push([newData.lat,newData.long,newData.label]);
    }

}
