// server.js
var express = require("express");
var path = require("path");
var serveStatic = require("serve-static");
app = express();
app.use(serveStatic(__dirname + "/dist"));
var port = process.env.PORT || 8080;
var hostname = "localhost";
var http = require("http");
var requestify = require("requestify");
var bodyParser = require("body-parser");

// parse application/json
app.use(bodyParser.json());

app.listen(port, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

app.post("/api/product/vote", function(req, res) {
  requestify
    .post("http://statistics-service-api/product/vote", req.body)
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

app.post("/api/product/join", (req, res) => {
  requestify
    .post("http://product-service-api:8080/api/v1.0/join/save", req.body)
    .then(response => {
      console.log(res.body);
      return res.send(response.body);
    });
});

app.get("/api/foodcategories", function(req, res) {
  requestify
    .get("http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategories")
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

app.get("/api/foods", function(req, res) {
  requestify
    .get("http://product-service-api:8080/api/v1.0/BoatHouse/Foods")
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});
