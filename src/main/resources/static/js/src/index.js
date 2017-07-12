/**
 * Created by chrysaora on 6/17/17.
 */
console.log("hello");

// bind stockButton to request data
document.getElementById("stockButton").addEventListener("click", function() {
    var xhr = new XMLHttpRequest();
    // xhr.onreadystatechange = function() {
    //     if (this.readyState == 4 && this.status == 200) {
    //
    //     }
    // }
    xhr.open("GET", "/data?n=20", true);

    //equivalent to onreadystatechange, this.readyState == 4 && this.status == 200
    xhr.onload = function() {
        var data = JSON.parse(xhr.responseText);
        render(data);
    };
    xhr.send();
});

// bind chartButton to request data for graph
document.getElementById("chartButton").addEventListener("click", function() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/data?n=20", true);

    xhr.onload = function() {
        var data = JSON.parse(xhr.responseText);
        console.log(data);
        renderChart(data);
    };
    xhr.send();
});

var render = function(data) {
    var htmlString = "";
    data.forEach(function(e) {
        htmlString += "<p>[" + e.ticker + "] " + e.name + " is trading at " + e.price + ".</p>";
    });
    document.getElementById("renderTarget").insertAdjacentHTML("beforeend", htmlString);
};

var renderChart = function(data) {
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
};

