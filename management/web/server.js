var express = require("express"),
  async = require("async"),
  pg = require("pg"),
  cookieParser = require("cookie-parser"),
  requestify = require("requestify");
(bodyParser = require("body-parser")),
  (methodOverride = require("method-override")),
  (app = express()),
  (server = require("http").Server(app)),
  (io = require("socket.io")(server)),
  (serveStatic = require("serve-static")),
  (path = require("path"));
var multiparty = require("multiparty");
const axios = require("axios");
var fs = require("fs");

io.set("transports", ["polling"]);

var port = process.env.PORT || 4000;

io.sockets.on("connection", function(socket) {
  socket.emit("message", { text: "Welcome!" });

  socket.on("subscribe", function(data) {
    socket.join(data.channel);
  });
});

async.retry(
  { times: 1000, interval: 10000 },
  function(callback) {
    pg.connect("postgres://postgres@statistics-service-db/postgres", function(
      err,
      client,
      done
    ) {
      if (err) {
        console.error("Waiting for db");
      }
      callback(err, client);
    });
  },
  function(err, client) {
    if (err) {
      return console.err("Giving up");
    }
    console.log("Connected to db");
    getVotes(client);
  }
);

function getVotes(client) {
  client.query(
    "SELECT vote, COUNT(id) AS count FROM votes GROUP BY vote",
    [],
    function(err, result) {
      if (err) {
        console.error("Error performing query: " + err);
      } else {
        var votes = collectVotesFromResult(result);
        io.sockets.emit("scores", JSON.stringify(votes));
      }

      setTimeout(function() {
        getVotes(client);
      }, 10000);
    }
  );
}

function collectVotesFromResult(result) {
  var votes = { a: 0, b: 0 };

  result.rows.forEach(function(row) {
    votes[row.vote] = parseInt(row.count);
  });

  return votes;
}
app.use(serveStatic(__dirname + "/dist"));
app.use(cookieParser());
app.use(bodyParser());
app.use(methodOverride("X-HTTP-Method-Override"));
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content-Type, Accept"
  );
  res.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
  next();
});

server.listen(port, function() {
  var port = server.address().port;
  console.log("App running on port " + port);
});

app.get("/api/foodcategories", function(req, res) {
  requestify
    .get("http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategories")
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

app.post("/api/foodcategory", function(req, res) {
  requestify
    .post(
      "http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategory",
      req.body
    )
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

app.put("/api/foodcategory", function(req, res) {
  requestify
    .put(
      "http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategory",
      req.body
    )
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

app.delete("/api/foodcategory", function(req, res) {
  requestify
    .delete(
      "http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategory?id=" +
        req.query.id
    )
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

app.get("/api/food", function(req, res) {
  requestify
    .get(
      "http://product-service-api:8080/api/v1.0/BoatHouse/Food?id=" +
        req.query.id
    )
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

app.post("/api/food", function(req, res) {
  var form = new multiparty.Form();
  form.parse(req, function(err, fields, files) {
    // var postForm = new FormData();
    let url = `http://product-service-api:8080/api/v1.0/BoatHouse/Food?菜品分类ID=${fields["菜品分类ID"][0]}&菜品名称=${fields["菜品名称"][0]}&菜品价格=${fields["菜品价格"][0]}&菜品描述=${fields["菜品描述"][0]}`;
    if (files["菜品图片"] !== undefined) {
      var tempPath = files["菜品图片"][0].path;
      var tempFileNameList = tempPath.split("/");
      var tempFileName = tempFileNameList[tempFileNameList.length - 1];
      let nodeFile = fs.createReadStream(tempPath);
      let outFilePath = "./dist/foods/" + tempFileName;
      let dbPath = "./foods/" + tempFileName;
      let outFile = fs.createWriteStream(outFilePath);
      nodeFile.pipe(outFile);
      url = `http://product-service-api:8080/api/v1.0/BoatHouse/Food?菜品分类ID=${fields["菜品分类ID"][0]}&菜品名称=${fields["菜品名称"][0]}&菜品价格=${fields["菜品价格"][0]}&菜品描述=${fields["菜品描述"][0]}&菜品图片=${dbPath}`;
    }
    axios
      .post(encodeURI(url))
      .then(function(response) {
        console.log(response.body);
        return res.send(response.body);
      })
      .catch(function(res) {
        console.log(res);
      });
  });
});

app.put("/api/food", function(req, res) {
  var form = new multiparty.Form();
  form.parse(req, function(err, fields, files) {
    let url = `http://product-service-api:8080/api/v1.0/BoatHouse/Food?菜品ID=${fields["菜品ID"][0]}&菜品分类ID=${fields["菜品分类ID"][0]}&菜品名称=${fields["菜品名称"][0]}&菜品价格=${fields["菜品价格"][0]}&菜品描述=${fields["菜品描述"][0]}`;
    if (files["菜品图片"] !== undefined) {
      var tempPath = files["菜品图片"][0].path;
      var tempFileNameList = tempPath.split("/");
      var tempFileName = tempFileNameList[tempFileNameList.length - 1];
      let nodeFile = fs.createReadStream(tempPath);
      let outFilePath = "./dist/foods/" + tempFileName;
      let dbPath = "./foods/" + tempFileName;
      let outFile = fs.createWriteStream(outFilePath);
      nodeFile.pipe(outFile);
      url = `http://product-service-api:8080/api/v1.0/BoatHouse/Food?菜品ID=${fields["菜品ID"][0]}&菜品分类ID=${fields["菜品分类ID"][0]}&菜品名称=${fields["菜品名称"][0]}&菜品价格=${fields["菜品价格"][0]}&菜品描述=${fields["菜品描述"][0]}&&菜品图片=${dbPath}`;
    }

    axios
      .put(encodeURI(url))
      .then(function(response) {
        console.log(response.body);
        return res.send(response.body);
      })
      .catch(function(res) {
        console.log(res);
      });
  });
});

app.delete("/api/food", function(req, res) {
  requestify
    .delete(
      "http://product-service-api:8080/api/v1.0/BoatHouse/Food?id=" +
        req.query.id
    )
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

app.get("/join/list", function(req, res) {
  requestify
    .get("http://product-service-api:8080/api/v1.0/join/list")
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});
