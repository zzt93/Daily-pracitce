/**
 * Created by zzt on 10/25/15.
 */

var randomScalingFactor = function() {
    return (Math.random() > 0.5 ? 2.0 : 1.0) * Math.round(Math.random() * 100);
};

var produceLineChart = function (eleId) {
    var ctx = document.getElementById(eleId);
    var lineChartData = {
        labels: ['50kg', '51kg', '52kg', '53kg', '54kg', '55kg', '56kg'],
        datasets: [{
            label: 'My First dataset',
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()],
            yAxisID: 'y-axis-1'
        }, {
            label: 'My Second dataset',
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()],
            yAxisID: 'y-axis-2'
        }]
    };
    Chart.Line(ctx, {
        data: lineChartData,
        options: {
            responsive: true,
            hoverMode: 'label',
            stacked: false,
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        offsetGridLines: false
                    }
                }],
                yAxes: [{
                    type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                    display: true,
                    position: 'left',
                    id: 'y-axis-1'
                }, {
                    type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                    display: true,
                    position: 'right',
                    id: 'y-axis-2',

                    // grid line settings
                    gridLines: {
                        drawOnChartArea: false // only want the grid lines for one axis to show up
                    }
                }]
            }
        }
    });

};

produceLineChart('relation-line-chart');
produceLineChart('cmp-line-chart');
