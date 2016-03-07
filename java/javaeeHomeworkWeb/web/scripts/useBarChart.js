/**
 * Created by zzt on 10/25/15.
 */

var randomScalingFactor = function () {
    return (Math.random() > 0.5 ? 2.0 : 1.0) * Math.round(Math.random() * 100);
};

var produceBarChart = function (id, barChartData) {
    var ctx = document.getElementById(id);

    new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
            responsive: true
        }
    });
};

var produceTwoAxisChart = function (id, barChartData) {
    var ctx = document.getElementById(id);

    new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
            responsive: true,
            tooltips: {
                mode: 'label'
            },
            elements: {
                line: {
                    fill: false
                }
            },
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    },
                    labels: {
                        show: true
                    }
                }],
                yAxes: [{
                    type: "linear",
                    display: true,
                    position: "left",
                    id: "y-left",
                    gridLines: {
                        display: false
                    },
                    labels: {
                        show: true
                    }
                }, {
                    type: "linear",
                    display: true,
                    position: "right",
                    id: "y-right",
                    gridLines: {
                        display: false
                    },
                    labels: {
                        show: true
                    }
                }]
            }
        }
    });

};

