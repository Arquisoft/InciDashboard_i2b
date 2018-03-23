$(document).ready(function () {

    var chart = c3.generate({
        bindto: '#stacked-bar-chart',
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250]
            ],
            type: 'bar'
        },
        bar: {
            width: {
                ratio: 0.7 // this makes bar width 50% of length between ticks
            }
        }
    });

    var chart = c3.generate({
        bindto: '#donut-chart',
        data: {
            columns: [
                ['data1', 30],
                ['data2', 120],
            ],
            type: 'donut',
            onclick: function (d, i) { console.log("onclick", d, i); },
            onmouseover: function (d, i) { console.log("onmouseover", d, i); },
            onmouseout: function (d, i) { console.log("onmouseout", d, i); }
        },
        donut: {
            title: 'incidence ratio'
        }
    });

})