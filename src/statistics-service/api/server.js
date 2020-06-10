var express = require('express'),
    async = require('async'),
    pg = require("pg"),
    cookieParser = require('cookie-parser'),
    bodyParser = require('body-parser'),
    methodOverride = require('method-override'),
    app = express(),
    server = require('http').Server(app),
    io = require('socket.io')(server),
    redis   = require('redis'),
    guid=require('guid');

var port = process.env.PORT || 6000;

app.use(cookieParser());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(methodOverride('X-HTTP-Method-Override'));
app.use(function(req, res, next) {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader("Access-Control-Allow-Credentials", "true");
  res.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
  res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
  next();
});

app.get('/', function (req, res) {
    res.send('Welcome BoatHouse Statistics Service!');
});

app.post("/product/vote",function(req,res){
  if(req.body.data)
  {
    var product=req.body.data.product;
    var g=guid.create().value;
    var client  = redis.createClient('6379', 'statistics-service-redis');
    var data={"voter_id": g, "vote": product};
    client.rpush('votes',JSON.stringify(data),function(){
    });
 
  }

  res.statusCode=200;
  res.send();
  

})

server.listen(port, function () {
  var port = server.address().port;
  console.log('App running on port ' + port);
});






