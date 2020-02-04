var app = angular.module('catsvsdogs', []);
var socket = io.connect({transports:['polling']});

var bg1 = document.getElementById('background-stats-1');
var bg2 = document.getElementById('background-stats-2');
var products=[];
var productLike=[];


//Get the context of the Chart canvas element we want to select
var ctx = $("#column-chart");

// Chart Options
var chartOptions = {
    // Elements options apply to all of the options unless overridden in a dataset
    // In this case, we are setting the border of each bar to be 2px wide and green
    elements: {
        rectangle: {
            borderWidth: 2,
            borderColor: 'rgb(0, 255, 0)',
            borderSkipped: 'bottom'
        }
    },
    responsive: true,
    animation: false,
    maintainAspectRatio: false,
    responsiveAnimationDuration:500,
    legend: {
        display: false,
        position: 'top',
    },
    scales: {
        xAxes: [{
            display: true,
            gridLines: {
                color: "#f3f3f3",
                drawTicks: false,
            },
            scaleLabel: {
                display: true,
            },
            ticks: {
                fontSize: 14,
                padding: 8
            }
        }],
        yAxes: [{
            display: true,
            gridLines: {
                color: "#f3f3f3",
                drawTicks: false,
            },
            scaleLabel: {
                display: true,
            },
            ticks: {
                beginAtZero: true,
                stepSize: 1
            }
        }]
    },
    title: {
        display: false,
        text: 'Chart.js Bar Chart'
    }
};

// Chart Data
var chartData = {
    labels: products,
    datasets: [{
        label: "Like",
        data: productLike,
        backgroundColor: "#28D094",
        hoverBackgroundColor: "rgba(22,211,154,.9)",
        borderColor: "transparent"
    }]
};

var config = {
    type: 'bar',

    // Chart Options
    options : chartOptions,

    data : chartData
};

// Create the chart
var lineChart = new Chart(ctx, config);

app.controller('statsCtrl', function($scope){

    var updateScores = function(){
    socket.on('scores', function (json) {
       data = JSON.parse(json);
       var a = parseInt(data.a || 0);
       var b = parseInt(data.b || 0);
       var c = parseInt(data.c || 0);
       var d = parseInt(data.d || 0);
       var e = parseInt(data.e || 0);
       var f = parseInt(data.f || 0);

        $scope.a=a;
        $scope.b=b;
        $scope.c=c;
        $scope.d=d;
        $scope.e=e;
        $scope.f=f;

        var products=["蜂蜜烤猪肉","牛肉haggis","红酒炖羊腿","烟熏肠","蜂蜜猪肘","牛肉土豆泥"];
        var productLike=[a,b,c,d,e,f];
        products=products;
        productLike=productLike;

        lineChart.data={
            labels: products,
            datasets: [{
                label: "Like",
                data: productLike,
                backgroundColor:[
                    "rgba(255, 99, 132, 0.7)",
                    "rgba(255, 159, 64, 0.7)",
                    "rgba(255, 205, 86, 0.7)",
                    "rgba(75, 192, 192, 0.7)",
                    "rgba(54, 162, 235, 0.7)",
                    "rgba(153, 102, 255, 0.7)"
                ],
                borderColor:[
                    "rgb(255, 99, 132)",
                    "rgb(255, 159, 64)",
                    "rgb(255, 205, 86)",
                    "rgb(75, 192, 192)",
                    "rgb(54, 162, 235)",
                    "rgb(153, 102, 255)"
                ],
                hoverBackgroundColor: "rgba(22,211,154,.9)",
                borderColor: "transparent"
            }]
        };
        lineChart.update();

        $scope.$apply(function () {
         $scope.total = a + b + c + d +e + f;
       });


    });
  };

  var init = function(){
    document.body.style.opacity=1;
    updateScores();
  };
  socket.on('message',function(data){
    init();
  });
});

function getPercentages(a, b) {
  var result = {};

  if (a + b > 0) {
    result.a = Math.round(a / (a + b) * 100);
    result.b = 100 - result.a;
  } else {
    result.a = result.b = 50;
  }

  return result;
}