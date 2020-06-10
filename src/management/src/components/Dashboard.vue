<template>
  <div class="app-content content">
    <div class="content-wrapper">
      <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
          <h3 class="content-header-title mb-0 d-inline-block">仪表盘</h3>
          <div class="row breadcrumbs-top d-inline-block">
            <div class="breadcrumb-wrapper col-12">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.html">仪表盘</a>
                </li>
                <li class="breadcrumb-item"><a href="#">数据统计分析</a>
                </li>

              </ol>
            </div>
          </div>
        </div>
      </div>
      <div class="content-body">
        <!-- Column Chart -->
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">最受欢迎菜品 - 数据统计</h4>
                <a class="heading-elements-toggle"><i class="la la-ellipsis-v font-medium-3"></i></a>
                <div class="heading-elements">
                  <ul class="list-inline mb-0">
                    <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                    <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                    <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                    <li><a data-action="close"><i class="ft-x"></i></a></li>
                  </ul>
                </div>
              </div>
              <div class="card-content collapse show">
                <div class="card-body">
                  <canvas id="column-chart" height="400"></canvas>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>


<script>

  var socket = io.connect({transports:['polling']});
  var lineChart;

  export default {
    name: 'Dashboard',
    data () {
      return {
        products: [],
        productLike:[]

      }
    },
    components: {
    },
    mounted(){

      let _this=this;
      var bg1 = document.getElementById('background-stats-1');
      var bg2 = document.getElementById('background-stats-2');
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
        labels: _this.products,
        datasets: [{
          label: "Like",
          data: _this.productLike,
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
      lineChart = new Chart(ctx, config);


      socket.on('message',function(data){
        _this.init();
      });

    },
    methods:{
      init:function(){
        document.body.style.opacity=1;
        this.updateScores();
      },
      updateScores:function(){
        let _this=this;
        socket.on('scores', function (json) {
          let data = JSON.parse(json);
          var a = parseInt(data.a || 0);
          var b = parseInt(data.b || 0);
          var c = parseInt(data.c || 0);
          var d = parseInt(data.d || 0);
          var e = parseInt(data.e || 0);
          var f = parseInt(data.f || 0);


          var products=["蜂蜜烤猪肉","牛肉haggis","红酒炖羊腿","烟熏肠","蜂蜜猪肘","牛肉土豆泥"];
          var productLike=[a,b,c,d,e,f];
          _this.products=products;
          _this.productLike=productLike;
          
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



        });
      }

  }

  }

</script>