const http = require('http')
const serve = http.createServer((req, resp) => {
    if(req.url=='/student'){
        resp.end("Hello student!")
    }
}).listen('5000',()=>{
    console.log("hello axios")
})