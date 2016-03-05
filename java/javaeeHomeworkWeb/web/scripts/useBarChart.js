/**
 * Created by zzt on 10/25/15.
 */

var randomScalingFactor = function() {
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

