$(document).ready(function () {
    /*<![CDATA[*/

    // 5 or 6 most used tags
    // dictionary of tagname, repetitions
    var tags = /*[[mostUsedTags]]*/;

    var barChart = c3.generate({
        bindto: '#stacked-bar-chart',
        data: {
            x: 'x',
            columns: [
                ['x', 'Fire', 'Pollution', 'Tag3', 'Tag4', 'Tag5'],
                ['Most Used Tags', 50, 150, 140, 200, 10],
            ],
            type: 'bar'
        },
        axis: {
            x: {
                type: 'category',
                tick: {
                    multiline: true
                },
            }
        },
        bar: {
            width: {
                ratio: 0.5
            }
        },
    });
    // dictionary containing the number of incidences of each type
    var typeofIncidenceCount = /*[[mostUsedTags]]*/;
    var donutChart = c3.generate({
        bindto: '#donut-chart',
        data: {
            columns: [
                ['Geolocated', 30],
                ['Sensor', 120],
                ['Entity', 30],
                ['Person', 120]
            ],
            type: 'donut',
        },
        donut: {
            title: 'Incidencen ratio'
        }
    });

    var tempChart = c3.generate({
        bindto: '#line-chart',
        data: {
            x: 'x',
            columns: [
                ['x', '00:00', '04:00', '08:00', '12:00', '16:00:00', '20:00'],
                ['Temperature/Hour', 25, 20, 17, 25, 25, 20, 23, 20, 19, 14, 15, 10]
            ]
        },
        axis: {
            x: {
                type: 'category',
                tick: {
                    count: 6
                }
            }
        }
    });
    /*]]>*/
})