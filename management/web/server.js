var express = require('express'),
  async = require('async'),
  pg = require('pg'),
  cookieParser = require('cookie-parser'),
  requestify = require('requestify')
bodyParser = require('body-parser'),
methodOverride = require('method-override'),
app = express(),
server = require('http').Server(app),
io = require('socket.io')(server),
serveStatic = require('serve-static'),
path = require('path')

io.set('transports', ['polling'])

var port = process.env.PORT || 4000

io.sockets.on('connection', function (socket) {
  socket.emit('message', { text: 'Welcome!' })

  socket.on('subscribe', function (data) {
    socket.join(data.channel)
  })
})

async.retry(
  {times: 1000, interval: 10000},
  function (callback) {
    pg.connect('postgres://postgres@statistics-service-db/postgres', function (err, client, done) {
      if (err) {
        console.error('Waiting for db')
      }
      callback(err, client)
    })
  },
  function (err, client) {
    if (err) {
      return console.err('Giving up')
    }
    console.log('Connected to db')
    getVotes(client)
  }
)

function getVotes (client) {
  client.query('SELECT vote, COUNT(id) AS count FROM votes GROUP BY vote', [], function (err, result) {
    if (err) {
      console.error('Error performing query: ' + err)
    } else {
      var votes = collectVotesFromResult(result)
      io.sockets.emit('scores', JSON.stringify(votes))
    }

    setTimeout(function () { getVotes(client) }, 10000)
  })
}

function collectVotesFromResult (result) {
  var votes = {a: 0, b: 0}

  result.rows.forEach(function (row) {
    votes[row.vote] = parseInt(row.count)
  })

  return votes
}
app.use(serveStatic(__dirname + '/dist'))
app.use(cookieParser())
app.use(bodyParser())
app.use(methodOverride('X-HTTP-Method-Override'))
app.use(function (req, res, next) {
  res.header('Access-Control-Allow-Origin', '*')
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept')
  res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS')
  next()
})

server.listen(port, function () {
  var port = server.address().port
  console.log('App running on port ' + port)
})

app.get('/api/foodcategories', function (req, res) {
  requestify.get('http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategories').then(function (response) {
    console.log(response.body)
    return res.send(response.body)
  }
  )
})

app.post('/api/foodcategory', function (req, res) {
  requestify.post('http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategory', req.body).then(function (response) {
    console.log(response.body)
    return res.send(response.body)
  }
  )
})

app.put('/api/foodcategory', function (req, res) {
  requestify.put('http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategory', req.body).then(function (response) {
    console.log(response.body)
    return res.send(response.body)
  }
  )
})

app.delete('/api/foodcategory', function (req, res) {
  requestify.delete('http://product-service-api:8080/api/v1.0/BoatHouse/FoodCategory?id=' + req.query.id).then(function (response) {
    console.log(response.body)
    return res.send(response.body)
  }
  )
})

app.get('/join/list', function (req, res) {
  requestify.get('http://product-service-api:8080/api/v1.0/join/list').then(function (response) {
    console.log(response.body)
    return res.send(response.body)
  }
  )
})
