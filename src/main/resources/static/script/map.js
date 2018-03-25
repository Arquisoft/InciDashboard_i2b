class Map {

    constructor(id) {
        this.id = id;
    }

    initMap() {
        this.map = new google.maps.Map(this.id, {
            center: { lat: 40.4167, lng: -3.70325 },
            zoom: 4,
            mapTypeId: 'hybrid'
        });
    }

    createMarker(array) {
        var marker = new google.maps.Marker({
            position:new google.maps.LatLng(array.lat, array.lon),
            map: this.map,
            title: array.label
        });
    }
    
}
    /*
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

        ada
    onNewData(array)
        */

