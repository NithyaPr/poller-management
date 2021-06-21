var express = require('express')
var app = express()

app.use('/', express.static('.'))
app.use('/poller/manageservices', express.static('./manageServices.html'))

app.listen(8080)

console.log("Server running on localhost:8080")