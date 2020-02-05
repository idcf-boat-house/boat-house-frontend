var express = require('express'),
    async = require('async'),
    pg = require("pg"),
    cookieParser = require('cookie-parser'),
    bodyParser = require('body-parser'),
    methodOverride = require('method-override'),
    app = express(),
    server = require('http').Server(app),
    io = require('socket.io')(server),
    redis   = require('redis');


var port =  4000;

app.use(cookieParser());
app.use(methodOverride('X-HTTP-Method-Override'));
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  res.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
  next();
});

app.get('/', function (req, res) {
    res.send('Welcome BoatHouse Statistics Service!');
});

app.get("/product/vote",function(req,res){
  
  var client  = redis.createClient('6379', 'localhost');
  var data={'voter_id': "001", 'vote': "data01"};
  client.rpush('test2',JSON.stringify(data),function(){
    
  });
  res.statusCode=200;
  res.send();
  

})

server.listen(port, function () {
  var port = server.address().port;
  console.log('App running on port ' + port);
});






