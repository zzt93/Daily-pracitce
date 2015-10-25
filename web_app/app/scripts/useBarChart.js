/**
 * Created by zzt on 10/25/15.
 */

var randomScalingFactor = function() {
    return (Math.random() > 0.5 ? 2.0 : 1.0) * Math.round(Math.random() * 100);
};

var produceBarChart = function () {
    var ctx = document.getElementById("myChart");
    var barChartData = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [{
            label: 'Dataset 1',
            backgroundColor: "rgba(220,220,220,0.5)",
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }, {
            hidden: true,
            label: 'Dataset 2',
            backgroundColor: "rgba(151,187,205,0.5)",
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }, {
            label: 'Dataset 3',
            backgroundColor: "rgba(151,187,205,0.5)",
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
        }]

    };
    new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
            responsive: true
        }
    });
};

produceBarChart();
