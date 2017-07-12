/**
 * Created by chrysaora on 6/26/17.
 */
var renderChart = function(form) {
    console.log("hello");
    console.log(form.elements["ticker"].value);
    var ticker = form.elements["ticker"].value;
    var dataUrl = "/data?ticker=" + ticker;
    console.log(dataUrl);

    var xhr = new XMLHttpRequest();
    xhr.open("GET", dataUrl, true);

    xhr.onload = function() {
        var data = JSON.parse(xhr.responseText);
        renderChartHelper(data);
    };
    xhr.send();
}

var renderChartHelper = function(data) {
    console.log(data);
    var dataList = [data[0].ticker];
    data.forEach(function(e) {
        dataList.push(e.price);
    });
    console.log(dataList);

    var chart = c3.generate({
        bindto: '#chart',
        data: {
            columns: [
                dataList
            ],
            axes: {
                data2: 'y2' // ADD
            }
        },
        axis: {
            y2: {
                show: true // ADD
            }
        }
    });
}