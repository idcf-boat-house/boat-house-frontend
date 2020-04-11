// server.js
var express = require('express')
var path = require('path')
var serveStatic = require('serve-static')
app = express()
app.use(serveStatic(__dirname + '/dist'))
var port = process.env.PORT || 8080
var hostname = 'localhost'
var http = require('http')
var requestify = require('requestify')
var bodyParser = require('body-parser')

// parse application/json
app.use(bodyParser.json())

app.listen(port, () => {
  console.log(`Server running at http://${hostname}:${port}/`)
})

app.post('/api/product/vote', function (req, res) {
  requestify
    .post('http://statistics-service-api/product/vote', req.body)
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})

app.post('/api/product/join', (req, res) => {
  requestify
    .post('http://product-service-api:8080/api/v1.0/join/save', req.body)
    .then(response => {
      console.log(res.body)
      return res.send(response.body)
    })
})

app.get('/api/foodcategories', function (req, res) {
  requestify
    .get('http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategories')
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})

app.get('/api/foods', function (req, res) {
  requestify
    .get('http://product-service-api:8080/api/v1.0/BoatHouse/Foods')
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})

app.get("/api/searchfood", function(req, res) {
  requestify
    .get("http://product-service-api:8080/api/v1.0/BoatHouse/GetFoodLike?name="+req.query.name)
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
})
//获取购物车
app.get('/api/shopcart', function (req, res) {
  requestify
    .get('http://product-service-api:8080/api/v1.0/BoatHouse/ShopCart?userId='+req.query.userId)
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})

//添加购物车
app.post('/api/food/shopcart', function (req, res) {
  requestify
    .post('http://product-service-api:8080/api/v1.0/BoatHouse/ShopCart', req.body)
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})

//删除购物车某个菜品
app.put('/api/shopcart', function (req, res) {
  const userId=req.query.userId;
  const foodID=req.query.foodID;
  const delete_put = 'http://product-service-api:8080/api/v1.0/BoatHouse/ShopCart?userId='+userId+'&foodID='+foodID;
  requestify
    .put(delete_put)
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})

 //清空购物车
app.delete('/api/shopcart', function (req, res) {
  requestify
    .delete(
      'http://product-service-api:8080/api/v1.0/BoatHouse/ShopCart?userId=' +
        req.query.userId
    )
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})

app.post('/api/login', function (req, res) {
  // console.log(req);
  const { username, password} = req.body;
  const login_post = `http://account-service-api:8080/api/v1.0/login?username=${username}&password=${password}`;
  requestify
  .post(login_post, {})
  .then(response => {
    console.log(response.body)
    return res.send(response.body)
  })
  .fail(function(response) {
		console.log(response.body)
	});
})

app.post('/api/signup',function (req, res) {
  // console.log(req);
  const { username, password} = req.body;
  const signup_post = `http://account-service-api:8080/api/v1.0/signUp?username=${username}&password=${password}`;
  requestify
  .post(signup_post, {})
  .then(response => {
    console.log(response.body)
    return res.send(response.body)
  })
})

app.post("/api/orders/create", function(req, res) {
  requestify
    .post("http://product-service-api:8080/api/v1.0/orders/create", req.body)
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});

//购物车某个菜品数量减一
app.put('/api/ShopCartReduceFoodNum', function (req, res) {
  const reduce_put = 'http://product-service-api:8080/api/v1.0/BoatHouse/ShopCartReduceFoodNum';  
  requestify
    .put(reduce_put,req.body)
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})  

//购物车某个菜品数量加1
app.put('/api/ShopCartAddFoodNum', function (req, res) {
  const add_put = 'http://product-service-api:8080/api/v1.0/BoatHouse/ShopCartAddFoodNum';   
  requestify
    .put(add_put,req.body)
    .then(function (response) {
      console.log(response.body)
      return res.send(response.body)
    })
})  
app.get("/api/intro/intro_page", function(req, res) {
  requestify
    .get("http://product-service-api:8080/api/v1.0/intro/intro_page/" + req.query.page_id)
    .then(function(response) {
      console.log(response.body);
      return res.send(response.body);
    });
});